package by.dobysh.mycache.service;

import by.dobysh.mycache.model.Car;
import org.springframework.stereotype.Service;

@Service
public interface Cache {

    void add(String key, Car value, long periodInMillis);
    void remove(String key);
    Car get(String key);
    void clear();
    long size();
}
