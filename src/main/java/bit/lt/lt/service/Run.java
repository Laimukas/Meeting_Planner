package bit.lt.lt.service;

import bit.lt.lt.db.PersonDb;

import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {

        //tiesion susikuriau klase kad patikrint laiks nuo laiko susikurtus metodu funkcionalumus
//        Db db = new Db();
//        MeetingService ms = new MeetingService(db);
//        PersonService ps = new PersonService(db);
//        ms.getAllMeetings();
        //tikrinu ar prideda naujoka i meeta - veikia
//        Person atendee = ps.getOnePerson(db.readJsonFilePeople(),15);
//        ms.addAttendee(atendee,5);
        //tikrinu ar istrina meeta - veikia
//        ms.deleteMeeting(6);

        //--------- Bandysim pridet/atimt Meeta Asmeniui -----------
        PersonDb personDb = new PersonDb();
        PersonService ps = new PersonService(personDb);
//        int meetas = 5;
//        int asmuo = 8;
//        ps.addMeetingToAtendee(asmuo,meetas); //pataisius viena eilute - veikia :)
//        ps.getPersonById(ps.getAllPeople(),asmuo);
//        ps.removeMeetingFromAtendee(asmuo, meetas); // trina meeta is dalyvio saraso - veikia :)
    }
}
