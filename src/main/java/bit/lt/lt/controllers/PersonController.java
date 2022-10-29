package bit.lt.lt.controllers;


import bit.lt.lt.data.Meeting;
import bit.lt.lt.data.Person;
import bit.lt.lt.db.MeetingDb;
import bit.lt.lt.db.PersonDb;
import bit.lt.lt.service.MeetingService;
import bit.lt.lt.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    private final MeetingDb meetingDb = new MeetingDb();
    private final PersonDb personDb = new PersonDb();

    private final MeetingService meetingService=new MeetingService(meetingDb);
    private final PersonService personService=new PersonService(personDb);

    @GetMapping("persons")
    public ModelAndView list() throws IOException {
        List<Person> list = personService.getAllPeople();
        ModelAndView mav = new ModelAndView("persons");
        mav.addObject("persons", list);
        return mav;
    }
    @GetMapping("searchPerson")
    public ModelAndView searching(
            @RequestParam("status") String status
                ) throws IOException {
        List<Person> list = personService.getAllPeople();
        List<Person> sortas = personService.getPersonByStatus(list,status);
        ModelAndView mav = new ModelAndView("persons");
        mav.addObject("persons", sortas);
        return mav;
    }
    @GetMapping("person/new")
    public ModelAndView newPerson() {
        ModelAndView mav = new ModelAndView("newPerson");
        return mav;
    }
    @PostMapping("saveNewPerson")
    public ModelAndView saveNewPerson(
            @PathVariable(value = "id", required = false) Integer id,
            @RequestParam("prname") String name,
            @RequestParam("status") String status,
            @RequestParam("meetings") Integer[] meetings
    ) throws IOException{
        ModelAndView mav;
        List<Person> list = new ArrayList<>();
        if (id == null) {
            Person newPerson = new Person(name,status,meetings);
            personService.addNewPerson(newPerson);
            list = personService.getAllPeople();
            mav = new ModelAndView("persons");
            mav.addObject("persons", list);
        } else {
            Person newPerson = new Person(id,name,status,meetings);
            personService.updatePerson(newPerson);
            list = personService.getAllPeople();
            mav = new ModelAndView("persons");
            mav.addObject("persons", list);
        }
        return mav;
    }
//    @PostMapping("editPerson")
//    public ModelAndView editPerson(
//            @PathVariable(value = "id", required = false) Integer id,
//            @RequestParam("prname") String name,
//            @RequestParam("status") String status,
//            @RequestParam("meetings") Integer[] meetings
//    ) throws IOException{
//        ModelAndView mav;
//        List<Person> list = new ArrayList<>();
//        if (id == null) {
//            Person newPerson = new Person(name,status,meetings);
//            personService.addNewPerson(newPerson);
//            list = personService.getAllPeople();
//            mav = new ModelAndView("persons");
//            mav.addObject("persons", list);
//        } else {
//            Person newPerson = new Person(id,name,status,meetings);
//            personService.updatePerson(newPerson);
//            list = personService.getAllPeople();
//            mav = new ModelAndView("persons");
//            mav.addObject("persons", list);
//        }
//        return mav;
//    }

    @GetMapping("person/{id}")
    public ModelAndView showPerson(@PathVariable("id") Integer id) throws IOException {
        Person person = personService.getOnePerson (personService.getAllPeople(), id);
        ModelAndView mav = new ModelAndView("person");
        mav.addObject("person", person);
        return mav;
    }

    @GetMapping("person/{id}/delete")
    public String deletePerson(@PathVariable("id") Integer id) throws IOException {
        meetingService.removeAttendeeFromAllMeetings(id);
        personService.deletePerson(id);
//        List<Person> list = personService.getAllPeople();
//        ModelAndView mav = new ModelAndView("persons");
//        mav.addObject("persons", list);
//        return mav;
        return "redirect:/persons";
    }
}
