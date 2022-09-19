package bit.lt.lt.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Meeting {

    public static int nextId = 1;

    private Integer id;
    private String name;
    private Person responsiblePerson;
    private String description;
    private String category;
    private String type;
    private String startDate;
    private String endDate;
    private List<Integer> atendees;

    public Meeting(){this.id = nextId++;}

    public Meeting( String name, Person responsiblePerson, String description, String category, String type, String startDate, String endDate, List<Integer> atendees) {
        this.id = nextId++;
        this.name = name;
        this.responsiblePerson = responsiblePerson;
        this.description = description;
        this.category = category;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.atendees = atendees;
    }

    public Meeting(Integer id, String name, Person responsiblePerson, String description, String category, String type, String startDate, String endDate, List<Integer> atendees) {
        this.id = id;
        this.name = name;
        this.responsiblePerson = responsiblePerson;
        this.description = description;
        this.category = category;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.atendees = atendees;
    }
}
