package bit.lt.lt.data;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    public static int nextId = 1;

    private Integer id;
    private String prname;
    private String status;
    private Integer [] meetings;

    public Person(){this.id = nextId++;}

    public Person(String prname, String status, Integer[] meetings) {
        this.id = nextId++;
        this.prname = prname;
        this.status = status;
        this.meetings = meetings;
    }

    public Person(Integer id, String prname, String status, Integer[] meetings) {
        this.id = id;
        this.prname = prname;
        this.status = status;
        this.meetings = meetings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrname() {
        return prname;
    }

    public void setPrname(String prname) {
        this.prname = prname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer[] getMeetings() {
        return meetings;
    }

    public void setMeetings(Integer[] meetings) {
        this.meetings = meetings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getPrname(), person.getPrname()) && Objects.equals(getStatus(), person.getStatus()) && Arrays.equals(getMeetings(), person.getMeetings());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getPrname(), getStatus());
        result = 31 * result + Arrays.hashCode(getMeetings());
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + prname + '\'' +
                ", status='" + status + '\'' +
                ", meetings=" + Arrays.toString(meetings) +
                '}';
    }
}
