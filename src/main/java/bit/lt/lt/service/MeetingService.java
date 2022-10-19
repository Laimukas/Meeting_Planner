package bit.lt.lt.service;

import bit.lt.lt.data.Meeting;
import bit.lt.lt.data.Person;
import bit.lt.lt.db.MeetingDb;
import bit.lt.lt.db.PersonDb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Service
//@AllArgsConstructor

public class MeetingService {

    private final PersonDb personDb = new PersonDb();

    private final MeetingDb meetingDb;

    public MeetingService(MeetingDb meetingDb) {
        this.meetingDb = meetingDb;
    }

    public List<Meeting> getAllMeetings() {
        return meetingDb.readJsonFile();
    }

    public Meeting addNewMeeting(Meeting meeting) {
        List<Meeting> meetings = getAllMeetings();
        meetings.add(meeting);
        meetingDb.writeToJsonFile(meetings);
        return meeting;
    }

    public List<Meeting> getMeetingByDescriptionOrName(List<Meeting> list, String description, String name) {
        if (name != null && name.equals("")) {
            System.out.println("Deja, nurodytas pavadinimas neegzistuoja!");
        } else if (description != null && description.equals("")) {
            System.out.println("Deja, nurodytas paaiskinimas neegzistuoja!");
        }
        List<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getDescription().toLowerCase().contains(description.toLowerCase())
                    && meetas.getName().toLowerCase().contains(name.toLowerCase())) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public List<Meeting> getMeetingsByResponsiblePerson(List<Meeting> list, Integer id) {
        List<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getResponsiblePerson().getId().equals(id)) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public Person getResponsiblePersonByMeeting(List<Meeting> list, Integer meetingId) {
        Person person = new Person();
        for (Meeting meetas : list) {
            if (meetas.getId().equals(meetingId)) {
                person = meetas.getResponsiblePerson();
            }
        }
        return person;
    }

    public List<Meeting> getMeetingsByCategory(List<Meeting> list, String category) {
        List<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getCategory().toLowerCase().contains(category.toLowerCase())) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public List<Meeting> getMeetingsByType(List<Meeting> list, String type) {
        List<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getType().toLowerCase().contains(type.toLowerCase())) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

//    public List<Meeting> getMeetingsByDateFrom(List<Meeting> list, String dateFrom) {
//        List<Meeting> sortas = new ArrayList<>();
//        LocalDateTime dateFromDt = LocalDateTime.parse(dateFrom);
//        for (Meeting meetas : list) {
//            if (meetas.getEndDate().compareTo(dateFromDt) >= 0) {
//                sortas.add(meetas);
//            }
//        }
//        return sortas;
//    }
//
//    public List<Meeting> getMeetingsByDateTo(List<Meeting> list, String dateTo) {
//        List<Meeting> sortas = new ArrayList<>();
//        LocalDateTime dateToDt = LocalDateTime.parse(dateTo);
//        for (Meeting meetas : list) {
//            if (meetas.getEndDate().compareTo(dateToDt) >= 0) {
//                sortas.add(meetas);
//            }
//        }
//        return sortas;
//    }

    public List<Meeting> getMeetingsByNumberOfAttendeesFrom(List<Meeting> list, Integer nr) {
        List<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getAtendees().size() >= nr) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public List<Meeting> getMeetingsByNumberOfAttendeesTo(List<Meeting> list, Integer nr) {
        List<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getAtendees().size() <= nr) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public Meeting getOneMeeting(List<Meeting> list, Integer id) throws IOException {
        if (id == null) {
            throw new NullPointerException("You have to get id for meeting");
        }
        for (Meeting meeting : list) {
            if (id.equals(meeting.getId())) {
                System.out.println("Meeting we are looking for: " + meeting);
                return meeting;
            }
        }
        throw new IllegalArgumentException("Meeting not found");
    }

    public List<Meeting> addAttendee(Person attendee,
                                     Integer meetingId) {
        List<Meeting> meetings = getAllMeetings();
        if (meetingId == null) {
            throw new NullPointerException("You have to get id for meeting");
        }
        for (Meeting meeting : meetings) {
            if (meetingId.equals(meeting.getId())) {
                List<Integer> attendees = meeting.getAtendees();
                attendees.add(attendee.getId());
                meeting.setAtendees(attendees);
                meetingDb.writeToJsonFile(meetings);
                break;
            }
        }
        return meetings;
    }

    public List<Meeting> removeAttendee(Integer meetingId,Integer atendeeId) {
        List<Meeting> meetings = getAllMeetings();
        List<Integer> attendees = new ArrayList<>();
        if (meetingId == null && atendeeId == null) {
            throw new NullPointerException("You have to get ids for meeting or atendee");
        }
        for (Meeting meeting : meetings) {
            if (meetingId.equals(meeting.getId())) {
                attendees = meeting.getAtendees();
                for (int i = 0;i<attendees.size();i++){
                    if (attendees.get(i).equals(atendeeId)){
                        attendees.remove(i);
                    }
                }
                meeting.setAtendees(attendees);
                meetingDb.writeToJsonFile(meetings);
                break;
            }
        }
        return meetings;
    }

    public List<Integer> getAttendeesIdFromMeeting(Integer meetingId) {
        List<Meeting> meetings = getAllMeetings();
        List<Integer> attendeesId = new ArrayList<>();
        if (meetingId == null) {
            throw new NullPointerException("You have to get id for meeting");
        }
        for (Meeting meeting : meetings) {
            if (meetingId.equals(meeting.getId())) {
                attendeesId = meeting.getAtendees();
                break;
            }
        }return attendeesId;
    }

    public List<Meeting> deleteMeeting(Integer id) throws IOException {
        List<Meeting> meetings = getAllMeetings();
        if (id == null) {
            throw new NullPointerException("You must to have id");
        }
        for (Meeting meeting : meetings) {
            if (id.equals(meeting.getId())) {
                meetings.remove(meeting);
                meetingDb.writeToJsonFile(meetings);
                break;
            } else {
                throw new IOException(
                        "Cannot delete meeting because you are not");
            }
        }
        return meetings;
    }

    public List<Meeting> updateMeeting(Meeting setMeeting) {
        List<Meeting> meetings = getAllMeetings();
        for (Meeting meeting : meetings) {
            if (setMeeting.getId().equals(meeting.getId())) {
                meeting.setName(setMeeting.getName());
                meeting.setResponsiblePerson(setMeeting.getResponsiblePerson());
                meeting.setCategory(setMeeting.getCategory());
                meeting.setDescription(setMeeting.getDescription());
                meeting.setType(setMeeting.getType());
                meeting.setStartDate(setMeeting.getStartDate());
                meeting.setEndDate(setMeeting.getEndDate());
                meeting.setAtendees(setMeeting.getAtendees());
                meetings.set(meeting.getId(), meeting);
                meetingDb.writeToJsonFile(meetings);
            }
        }
        return meetings;
    }
}
