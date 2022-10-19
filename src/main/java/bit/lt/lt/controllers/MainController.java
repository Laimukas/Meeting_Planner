package bit.lt.lt.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MainController {
    @GetMapping("/")
    public ModelAndView list() throws IOException {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}