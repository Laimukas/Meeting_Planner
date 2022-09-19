package bit.lt.lt.db;

import bit.lt.lt.data.Meeting;
import bit.lt.lt.data.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Repository
public class Db {
    private static final String MEETING_FILE_PATH =
            "src/main/resources/data/meeting.json";
    private static final String PERSON_FILE_PATH =
            "src/main/resources/data/people.json";

    private final ObjectMapper mapper = createObjectMapper();

    public void writeToJsonFile(ArrayList<Meeting> meetings) {
        try {
            mapper.writeValue(new File(MEETING_FILE_PATH), meetings);
        } catch (IOException e) {
            System.out.println("Failed to write JSON file");
        }
    }

    public ArrayList<Meeting> readJsonFile() {
        ArrayList<Meeting> meetings = null;
        try {
            meetings = mapper.readValue(new File(MEETING_FILE_PATH), new TypeReference<>() {
            });
        } catch (IOException exception) {
            System.out.println("failed to read file");
        }
        return meetings;
    }

    public void writeToJsonFilePeople(ArrayList<Person> list) {
        try {
            mapper.writeValue(new File(PERSON_FILE_PATH), list);
        } catch (IOException e) {
            System.out.println("Failed to write JSON file");
        }
    }

    public ArrayList<Person> readJsonFilePeople() {
        ArrayList<Person> list = null;
        try {
            list = mapper.readValue(new File(PERSON_FILE_PATH), new TypeReference<>() {
            });
        } catch (IOException exception) {
            System.out.println("failed to read file");
        }
        return list;
    }

    private ObjectMapper createObjectMapper() {
        final ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        return om;
    }
}
