package by.dobysh.mycache.controller;

import by.dobysh.mycache.service.InMemoryCacheWithDelayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {

    private InMemoryCacheWithDelayQueue inMemoryCacheWithDelayQueue;

    @Autowired
    public void setInMemoryCacheWithDelayQueue(InMemoryCacheWithDelayQueue inMemoryCacheWithDelayQueue) {
        this.inMemoryCacheWithDelayQueue = inMemoryCacheWithDelayQueue;
    }


    /* this is the conroller's part of the magic; I'm just using a simple GET but you
     * could just as easily do a POST here, obviously
     */
    @RequestMapping(value="/subView", method= RequestMethod.GET)
    public ModelAndView getSubView( Model model ) {

        model.addAttribute("cacheCarsList", inMemoryCacheWithDelayQueue.getCache());
        return new ModelAndView( "subView" );




    }

}
