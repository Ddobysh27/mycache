package by.dobysh.mycache.dao;

import by.dobysh.mycache.model.Car;

import java.util.List;

public interface CarDAO {
    List<Car> allCars();
    void add(Car car);
    void delete(Car car);
    void edit(Car car);
    Car getById(int id);
}
