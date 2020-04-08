package by.dobysh.mycache.service;

import by.dobysh.mycache.dao.CarDAO;
import by.dobysh.mycache.dao.CarDAOImpl;
import by.dobysh.mycache.model.Car;

import java.util.List;

public class CarServiceImpl implements CarService {

    private CarDAO carDAO = new CarDAOImpl();

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
