package by.dobysh.mycache.service;

import by.dobysh.mycache.dao.CarDAO;
import by.dobysh.mycache.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private InMemoryCacheWithDelayQueue inMemoryCacheWithDelayQueue;

    @Autowired
    public void setInMemoryCacheWithDelayQueue(InMemoryCacheWithDelayQueue inMemoryCacheWithDelayQueue){
        this.inMemoryCacheWithDelayQueue = inMemoryCacheWithDelayQueue;
    }

    private CarDAO carDAO;

    @Autowired
    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    @Transactional
    public List<Car> allCars() {
        return carDAO.allCars();
    }

    @Override
    @Transactional
    public void add(Car car) {
        carDAO.add(car);
    }

    @Override
    @Transactional
    public void delete(Car car) {
        carDAO.delete(car);
    }

    @Override
    @Transactional
    public void edit(Car car) {
        carDAO.edit(car);
    }

    @Override
    @Transactional
    public Car getById(int id) {
        Car car = inMemoryCacheWithDelayQueue.get(String.valueOf(id));
        if (car == null){
            return carDAO.getById(id);
        }
        return car;
    }

    @Override
    @Transactional
    public List<Car> getByYear(int year) {
        return carDAO.getByYear(year);
    }
}
