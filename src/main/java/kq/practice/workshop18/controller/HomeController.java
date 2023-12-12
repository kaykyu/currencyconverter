package kq.practice.workshop18.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kq.practice.workshop18.controller.service.HomeService;
import kq.practice.workshop18.model.Request;

@RestController
@RequestMapping(path = { "/", "index.html" })
public class HomeController {

    @Autowired
    HomeService hmSvc;

    @GetMapping
    public ModelAndView getHome() {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("convert", false);
        mav.addObject("currency", hmSvc.getCurr());
        mav.addObject("request", new Request());
        return mav;
    }

    @PostMapping(path = "/convert")
    public ModelAndView postConvert(@ModelAttribute Request request) {

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("convert", true);
        mav.addObject("result", hmSvc.getConvert(request));
        mav.addObject("currTo", hmSvc.findCurr(request.getCurrTo()));
        mav.addObject("currFrom", hmSvc.findCurr(request.getCurrFrom()));
        mav.addObject("currency", hmSvc.getCurr());
        mav.addObject("request", request);

        return mav;
    }
}
