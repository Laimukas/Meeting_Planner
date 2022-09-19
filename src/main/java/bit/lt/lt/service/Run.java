package bit.lt.lt.service;

import bit.lt.lt.data.Person;
import bit.lt.lt.db.Db;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {

        //tiesion susikuriau klase kad patikrint laiks nuo laiko susikurtus metodu funkcionalumus
        Db db = new Db();
        MeetingService ms = new MeetingService(db);
        PersonService ps = new PersonService(db);
        ms.getAllMeetings();
        //tikrinu ar prideda naujoka i meeta - veikia
//        Person atendee = ps.getOnePerson(db.readJsonFilePeople(),15);
//        ms.addAttendee(atendee,5);
        //tikrinu ar istrina meeta - veikia
//        ms.deleteMeeting(6);
    }
}
