package bit.lt.lt.service;

import bit.lt.lt.data.Person;
import bit.lt.lt.db.Db;
import bit.lt.lt.data.Meeting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MeetingService {

    private final Db db;

    public ArrayList<Meeting> getAllMeetings() {
        return db.readJsonFile();
    }

    public Meeting addNewMeeting(Meeting meeting) {
        ArrayList<Meeting> meetings = getAllMeetings();
        meetings.add(meeting);
        db.writeToJsonFile(meetings);
        return meeting;
    }

    public ArrayList<Meeting> getMeetingsByDescription(ArrayList<Meeting> list, String description) {
        ArrayList<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getDescription().toLowerCase().contains(description.toLowerCase())) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public ArrayList<Meeting> getMeetingsByResponsiblePerson(ArrayList<Meeting> list, Integer id) {
        ArrayList<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getResponsiblePerson().getId().equals(id)) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public ArrayList<Meeting> getMeetingsByCategory(ArrayList<Meeting> list, String category) {
        ArrayList<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getCategory().toLowerCase().contains(category.toLowerCase())) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public ArrayList<Meeting> getMeetingsByType(ArrayList<Meeting> list, String type) {
        ArrayList<Meeting> sortas = new ArrayList<>();
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

    public ArrayList<Meeting> getMeetingsByNumberOfAttendeesFrom(ArrayList<Meeting> list, Integer nr) {
        ArrayList<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getAtendees().size() >= nr) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public ArrayList<Meeting> getMeetingsByNumberOfAttendeesTo(ArrayList<Meeting> list, Integer nr) {
        ArrayList<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getAtendees().size() <= nr) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public ArrayList<Meeting> getMeetingsById(ArrayList<Meeting> list, Integer id) {
        ArrayList<Meeting> sortas = new ArrayList<>();
        for (Meeting meetas : list) {
            if (meetas.getId() == id) {
                sortas.add(meetas);
            }
        }
        return sortas;
    }

    public Meeting getOneMeeting(ArrayList<Meeting> list, Integer id) {
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

    public Meeting addAttendee(Person attendee,
                               Integer meetingId) {
        ArrayList<Meeting> meetings = getAllMeetings();
        if (meetingId == null) {
            throw new NullPointerException("You have to get id for meeting");
        }
        for (Meeting meeting : meetings) {
            if (meetingId.equals(meeting.getId())) {
                List<Integer> attendees = meeting.getAtendees();
                attendees.add(attendee.getId());
                meeting.setAtendees(attendees);
                db.writeToJsonFile(meetings);
                return meeting;
            }
        }
        throw new IllegalArgumentException("Meeting not found");
    }

    public Meeting deleteMeeting(Integer id) throws IOException {
        ArrayList<Meeting> meetings = getAllMeetings();
        if (id == null) {
            throw new NullPointerException("You have to get id for meeting");
        }
        for (Meeting meeting : meetings) {
            if (id.equals(meeting.getId())) {
                meetings.remove(meeting);
                db.writeToJsonFile(meetings);
                return meeting;
            }
        }
        throw new IllegalArgumentException("Meeting not found");
    }

    public Meeting removeAttendeeFromMeeting(Integer meetingId, Integer id) {
        ArrayList<Meeting> meetings = getAllMeetings();
        Meeting meeting = getOneMeeting(meetings, meetingId);
        for (Meeting m : meetings) {
            if (m.equals(meeting)) {
                List<Integer> attendees = m.getAtendees();
                try {
                    for (int i = 0; i <= attendees.size(); i++) {
                        if (attendees.get(i).equals(id)) {
                            attendees.remove(attendees.get(i));
                            m.setAtendees(attendees);
                            meetings.set(m.getId(), m);
                            db.writeToJsonFile(meetings);
                            return m;
                        }
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    // ignore
                }
            }
        }
        throw new IllegalArgumentException("Meeting not found");
    }

    public ArrayList<Meeting> removeAttendeeFromAllMeetings(Integer id) {
        ArrayList<Meeting> meetings = getAllMeetings();
        if (id == null) {
            throw new NullPointerException("You have to get id for attendant");
        }
        for (Meeting m : meetings) {
            List<Integer> attendees = m.getAtendees();
            for (int i = 0; i <= attendees.size(); i++) {
                if (attendees.get(i).equals(id)) {
                    attendees.remove(attendees.get(i));
                    m.setAtendees(attendees);
                    meetings.set(m.getId(), m);
                    attendees.clear();
                }
            }
        }
        db.writeToJsonFile(meetings);
        return meetings;
    }
}
