package by.dobysh.mycache.controller;

import by.dobysh.mycache.model.Car;
import by.dobysh.mycache.service.CarService;
import by.dobysh.mycache.service.InMemoryCacheWithDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }


    private InMemoryCacheWithDelayQueue inMemoryCacheWithDelayQueue;

    @Autowired
    public void setInMemoryCacheWithDelayQueue(InMemoryCacheWithDelayQueue inMemoryCacheWithDelayQueue) {
        this.inMemoryCacheWithDelayQueue = inMemoryCacheWithDelayQueue;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allCars() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cars");
        modelAndView.addObject("carsList", carService.allCars());
        modelAndView.addObject("cacheCarsList", inMemoryCacheWithDelayQueue.getCache());
        modelAndView.addObject("car", new Car());
        return modelAndView;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Car car = carService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("car", car);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editCar(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        carService.edit(car);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCar(@ModelAttribute("car") Car car) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        carService.add(car);
        return modelAndView;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCar(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Car car = carService.getById(id);
        carService.delete(car);
        return modelAndView;
    }


    @RequestMapping(value = "/addCache/{id}", method = RequestMethod.GET)
    public ModelAndView addCarInCache(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Car car = carService.getById(id);
        inMemoryCacheWithDelayQueue.add(car);
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView getByYear(@ModelAttribute("car") Car car) {
        //List<Car> cars = carService.getByYear(car.getYear());
        Car cars = carService.getById(car.getId());
        inMemoryCacheWithDelayQueue.add(cars);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");

//        Car car = carService.getByYear(year);

        return modelAndView;
    }


}
