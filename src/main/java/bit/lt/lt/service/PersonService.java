package bit.lt.lt.service;

import bit.lt.lt.data.Meeting;
import bit.lt.lt.data.Person;
import bit.lt.lt.db.Db;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class PersonService {

    private final Db db;

    public ArrayList<Person> getAllPeople() {
        return db.readJsonFilePeople();
    }

    public Person addNewPerson(Person person) {
        ArrayList<Person> list = getAllPeople();
        list.add(person);
        db.writeToJsonFilePeople(list);
        return person;
    }

    public Person getOnePerson(ArrayList<Person> list, Integer id) {
        if (id == null) {
            throw new NullPointerException("You have to get id for person");
        }
        for (Person person : list) {
            if (id.equals(person.getId())) {
                System.out.println("Person we are looking for: " + person);
                return person;
            }
        }
        throw new IllegalArgumentException("Person not found");
    }

    public ArrayList<Person> getPersonByName(ArrayList<Person> list, String name) {
        ArrayList<Person> sortas = new ArrayList<>();
        for (Person person : list) {
            if (person.getPrname().toLowerCase().contains(name.toLowerCase())) {
                sortas.add(person);
            }
        }
        return sortas;
    }

    public ArrayList<Person> getPersonById(ArrayList<Person> list, Integer id) {
        ArrayList<Person> sortas = new ArrayList<>();
        for (Person person : list) {
            if (person.getId() == (id)) {
                sortas.add(person);
            }
        }
        return sortas;
    }

    public ArrayList<Person> getPersonByStatus(ArrayList<Person> list, String status) {
        ArrayList<Person> sortas = new ArrayList<>();
        for (Person person : list) {
            if (person.getStatus().toLowerCase().contains(status.toLowerCase())) {
                sortas.add(person);
            }
        }
        return sortas;
    }

    public Person deletePerson(Integer id) throws IOException {
        ArrayList<Person> list = getAllPeople();
        if (id == null) {
            throw new NullPointerException("You have to get id for meeting");
        }
        for (Person person : list) {
            if (id.equals(person.getId())) {
                list.remove(person);
                db.writeToJsonFilePeople(list);
                return person;
            }
        }
        throw new IllegalArgumentException("Meeting not found");
    }

}
