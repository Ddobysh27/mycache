package by.dobysh.mycache.dao;

import by.dobysh.mycache.model.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CarDAOImpl implements CarDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Car> cars = new HashMap<>();

    static {
        Car car1 = new Car();
        car1.setId(AUTO_ID.getAndIncrement());
        car1.setTitle("BMW");
        car1.setYear(2010);
        cars.put(car1.getId(), car1);

        Car car2 = new Car();
        car2.setId(AUTO_ID.getAndIncrement());
        car2.setTitle("Mers");
        car2.setYear(2015);
        cars.put(car2.getId(), car2);

        Car car3 = new Car();
        car3.setId(AUTO_ID.getAndIncrement());
        car3.setTitle("Porsche");
        car3.setYear(2020);
        cars.put(car3.getId(), car3);
        // + film2, film3, film4, ...
    }

    @Override
    public List<Car> allCars() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public void add(Car car) {
        car.setId(AUTO_ID.getAndIncrement());
        cars.put(car.getId(), car);
    }

    @Override
    public void delete(Car car) {
        cars.remove(car.getId());
    }

    @Override
    public void edit(Car car) {
        cars.put(car.getId(), car);
    }

    @Override
    public Car getById(int id) {
        return cars.get(id);
    }
}