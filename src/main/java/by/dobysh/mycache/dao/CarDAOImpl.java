package by.dobysh.mycache.dao;

import by.dobysh.mycache.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> allCars() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Car").list();
    }

    @Override
    public void add(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(car);
    }

    @Override
    public void delete(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(car);
    }

    @Override
    public void edit(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.update(car);
    }

    @Override
    public Car getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Car.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> getByYear(int year) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Car where year = " + year).list();
    }
}