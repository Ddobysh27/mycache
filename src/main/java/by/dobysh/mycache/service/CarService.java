package by.dobysh.mycache.service;

import by.dobysh.mycache.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> allCars();
    void add(Car car);
    void delete(Car car);
    void edit(Car car);
    Car getById(int id);
    List<Car> getByYear(int year);
}
