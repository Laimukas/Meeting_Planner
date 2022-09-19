package bit.lt.lt.controllers;

import bit.lt.lt.data.Meeting;
import bit.lt.lt.data.Person;
import bit.lt.lt.exception.ApiException;
import bit.lt.lt.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingControler {

    private final MeetingService planer;

    @Autowired
    public MeetingControler(MeetingService planer) {
        this.planer = planer;
    }

    //    @GetMapping("/get")
    @GetMapping
    public ResponseEntity<ArrayList<Meeting>> getMeetings(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "description", required = false) String desc,
            @RequestParam(name = "resId", required = false) Integer resId,
            @RequestParam(name = "category", required = false) String cat,
            @RequestParam(name = "type", required = false) String type,
//            @RequestParam(name = "dateFrom", required = false) String dateFrom,
//            @RequestParam(name = "dateTo", required = false) String dateTo,
            @RequestParam(name = "countTo", required = false) Integer countTo,
            @RequestParam(name = "countFrom", required = false) Integer countFrom) {
        ArrayList<Meeting> meets = planer.getAllMeetings();
        if (id != null) {
            meets = planer.getMeetingsById(meets, id);
        }
        if (desc != null) {
            meets = planer.getMeetingsByDescription(meets, desc);
        }
        if (resId != null) {
            meets = planer.getMeetingsByResponsiblePerson(meets, resId);
        }
        if (cat != null) {
            meets = planer.getMeetingsByCategory(meets, cat);
        }
        if (type != null) {
            meets = planer.getMeetingsByType(meets, type);
        }
//        if(dateFrom != null){
//            meets = planer.getMeetingsByDateFrom(meets, dateFrom);
//        }
//        if(dateTo != null){
//            meets = planer.getMeetingsByDateTo(meets, dateTo);
//        }
        if (countFrom != null) {
            meets = planer.getMeetingsByNumberOfAttendeesFrom(meets, countFrom);
        }
        if (countTo != null) {
            meets = planer.getMeetingsByNumberOfAttendeesTo(meets, countTo);
        }

        if (meets.size() == 0) {
            throw new ApiException("No meetings found by these criteria!", 5000);
        }
        return new ResponseEntity<>(meets, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Meeting> addMeeting(@RequestBody Meeting meeting) {
        if (meeting == null) {
            throw new RuntimeException("Meeting is null");
        }
        return new ResponseEntity<>(planer.addNewMeeting(meeting), HttpStatus.OK);
    }

    @PutMapping("/addAttendee/{meetingId}")
    public ResponseEntity<Meeting> addNewAttendeeToMeeting(
            @RequestBody Person person,
            @PathVariable("meetingId") Integer meetingId) {
        if (person == null && meetingId == null) {
            throw new RuntimeException("Cannot add this person because of data lose.");
        }
        return new ResponseEntity<>(planer.addAttendee(person, meetingId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{meetingId}")
    public ResponseEntity<Meeting> deleteMeeting(
            @PathVariable("meetingId") Integer meetingId) throws IOException {
        if (meetingId == null) {
            throw new RuntimeException("MeetingId is missing");
        }
        return new ResponseEntity<>(planer.deleteMeeting(meetingId), HttpStatus.OK);
    }

    @PutMapping("/removeAttendee/{meetingId}/{attendeeId}")
    public ResponseEntity<Meeting> removePersonFromMeeting(
            @PathVariable("meetingId") Integer meetingId,
            @PathVariable("attendeeId") Integer attendeeId) {
        if (meetingId == null && attendeeId == null) {
            throw new RuntimeException("MeetingId or attendeeId is missing");
        }
        planer.removeAttendeeFromMeeting(meetingId, attendeeId);
        return new ResponseEntity<>(planer.getOneMeeting(planer.getAllMeetings(), meetingId),
                HttpStatus.OK);
    }
    //reik tikrint sita metoda,kazkas neveikia
    @PutMapping("/bannAttendee/{attendeeId}")
    public ResponseEntity<ArrayList<Meeting>> removePersonFromAllMeetings(
            @PathVariable("attendeeId") Integer attendeeId) {
        if (attendeeId == null) {
            throw new RuntimeException("AttendeeId is missing");
        }
        planer.removeAttendeeFromAllMeetings(attendeeId);
        return new ResponseEntity<>(planer.getAllMeetings(),
                HttpStatus.OK);
    }
}
