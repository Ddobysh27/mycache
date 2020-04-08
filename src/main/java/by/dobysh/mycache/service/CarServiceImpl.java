package by.dobysh.mycache.service;

import by.dobysh.mycache.dao.CarDAO;
import by.dobysh.mycache.dao.CarDAOImpl;
import by.dobysh.mycache.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    @Autowired
    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public List<Car> allCars() {
        return carDAO.allCars();
    }

    @Override
    public void add(Car car) {
        carDAO.add(car);
    }

    @Override
    public void delete(Car car) {
        carDAO.delete(car);
    }

    @Override
    public void edit(Car car) {
        carDAO.edit(car);
    }

    @Override
    public Car getById(int id) {
        return carDAO.getById(id);
    }
}
