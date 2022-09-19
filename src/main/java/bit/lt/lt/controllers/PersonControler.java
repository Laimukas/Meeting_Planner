package bit.lt.lt.controllers;

import bit.lt.lt.data.Meeting;
import bit.lt.lt.data.Person;
import bit.lt.lt.exception.ApiException;
import bit.lt.lt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonControler {

    private final PersonService planer;

    @Autowired
    public PersonControler(PersonService planer) {
        this.planer = planer;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Person>> getListOfPersons(
            @RequestParam(name = "prname", required = false) String prname,
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "status", required = false) String status
    ) {
        ArrayList<Person> list = planer.getAllPeople();
        if (prname != null) {
            list = planer.getPersonByName(list, prname);
        }
        if (id != null) {
            list = planer.getPersonById(list, id);
        }
        if (status != null) {
            list = planer.getPersonByStatus(list, status);
        }

        if (list.size() == 0) {
            throw new ApiException("No meetings found by these criteria!", 5000);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        if (person == null) {
            throw new RuntimeException("Person is missing");
        }
        return new ResponseEntity<>(planer.addNewPerson(person), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<Person> deletePerson(
            @PathVariable("personId") Integer personId) throws IOException {
        if (personId == null) {
            throw new RuntimeException("PersonId is missing");
        }
        return new ResponseEntity<>(planer.deletePerson(personId), HttpStatus.OK);
    }
}
