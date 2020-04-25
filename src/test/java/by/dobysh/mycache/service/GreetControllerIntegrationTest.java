package by.dobysh.mycache.service;

import by.dobysh.mycache.config.HibernateConfig;
import by.dobysh.mycache.config.WebConfig;
import by.dobysh.mycache.model.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
@WebAppConfiguration
public class GreetControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    @Qualifier("carServiceImpl")
    private CarService carService;

    @Autowired
    private InMemoryCacheWithDelayQueue inMemoryCache;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("carController"));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print())

                .andExpect(view().name("cars"));
    }


    @Test
    public void addToCache(){

        Car car;
        car = new Car();
        car.setId(5);
        car.setTitle("Audi");
        car.setYear(1988);

        List<Car> carList = inMemoryCache.getCache();
        Assert.assertFalse(carList.contains(car));

        carService.getById(car.getId());

        carList = inMemoryCache.getCache();
        Assert.assertTrue(carList.contains(car));

    }

    @Test
    public void deleteFromCache(){

        Car car;
        car = new Car();
        car.setId(5);
        car.setTitle("Audi");
        car.setYear(1988);

//        Assert.assertFalse("car в кэшэ отсутствует",inMemoryCache.getCache().contains(car));

        carService.getById(car.getId());

        Assert.assertTrue("car в кэшэ присутствует",inMemoryCache.getCache().contains(car));


        assertTimeout(ofMillis(inMemoryCache.getLifeTimeCache()), () -> {
            int i = 0;
            while (inMemoryCache.getCache().contains(car)){
                i++;
            }
        });

        Assert.assertFalse("car из кэша должен быть удалён", inMemoryCache.getCache().contains(car));

    }
    @Test
    public void updateTimeLifeCache(){
        Car car;
         car = new Car();
        car.setId(5);
        car.setTitle("Audi");
        car.setYear(1988);

//        Assert.assertFalse("car в кэшэ отсутствует",inMemoryCache.getCache().contains(car));


        carService.getById(car.getId());

        Assert.assertTrue("1", inMemoryCache.getCache().contains(car));


        assertTimeout(ofMillis(inMemoryCache.getLifeTimeCache()), () -> {
            int i = 0;
            while (inMemoryCache.getCache().contains(car)){
                i++;
            }
        });
        carService.getById(car.getId());

        Assert.assertTrue("3",inMemoryCache.getCache().contains(car));

    }




}
//1) добавить в кэш
//2) удаление из кэша по истечению счётчика (времени)
//3) при повторном добавлении в кэш обновление счётчика (времени)



//100 ПОЛЬЗОВАТЕЛЕЙ ПОТОКАМИ ОБНОВЛЯЮТ КЭШ
//НАПИСАТЬ ТЕСТЫ
//В КАКОМ СЛУЧАЕ
//ПРИДУМАТЬ СЦЕНАРИЙ ТЕСТА
//
//1) добавить в кэш - ищем запись в бд, потом проверяем добавилась ли она в кэш
//2) удаление из кэща - проверяем удаление из кэша чеере промежуток времени
//3) обновление счётчика - при повторном добавлении в кэш обновляется время жизни
//4)


























//    @Test
//    public void givenGreetURI_whenMockMVC_thenVerifyResponse() throws Exception {
//        MvcResult mvcResult = this.mockMvc.perform(post("/search"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("Hello World!!!"))
//                .andReturn();
//
//        assertEquals("application/json;charset=UTF-8",
//                mvcResult.getResponse().getContentType());
//    }

//    @Test
//    public void testSearchById() {
//        System.out.println("Run");
//        Car car = new Car();
//        car.setId(5);
//        car.setTitle("Audi");
//        car.setYear(1988);
//        System.out.println("obj car created");
//
//
//         wac.getBean("carController");
//        CarController carController = new CarController();
//        ModelAndView modelAndView = carController.searchById(car);
//        System.out.println(modelAndView);
//        assertEquals(5, 5);
//    }