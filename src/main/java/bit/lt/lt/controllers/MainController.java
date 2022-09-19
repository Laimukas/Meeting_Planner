package bit.lt.lt.controllers;

import bit.lt.lt.data.Meeting;
import bit.lt.lt.db.Db;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView list() throws IOException {
        Db db = new Db();
        List<Meeting> list = db.readJsonFile();
        ModelAndView mav = new ModelAndView("meetings");
        mav.addObject("meetings", list);
        return mav;
    }
}
