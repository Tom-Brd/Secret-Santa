package com.tom.secretSanta;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class JsonReaderTest {
    @Test
    void testAddParticipantsFromJson_validFile() throws IllegalAccessException {
        String jsonFile = "participants.json";
        JsonReader jsonReader = new JsonReader();
        List<Participant> participants = jsonReader.addParticipantsFromJson(jsonFile);

        assertNotNull(participants);
        assertFalse(participants.isEmpty());
    }

    @Test
    void testAddParticipantsFromJson_invalidFile() {
        String jsonFile = "invalid.json";
        JsonReader jsonReader = new JsonReader();

        assertThrows(IllegalArgumentException.class, () -> jsonReader.addParticipantsFromJson(jsonFile));
    }

    @Test
    void testAddParticipantsFromJson_tooFewParticipants() {
        String jsonFile = "participantsTooFew.json";
        JsonReader jsonReader = new JsonReader();

        assertThrows(IllegalAccessException.class, () -> jsonReader.addParticipantsFromJson(jsonFile));
    }

    @Test
    void testAddParticipantsFromJson_nullFile() {
        String jsonFile = null;
        JsonReader jsonReader = new JsonReader();

        assertThrows(IllegalArgumentException.class, () -> jsonReader.addParticipantsFromJson(jsonFile));
    }

    @Test
    void testAddParticipantsFromJson_FileNotFound() {
        String jsonFile = "nonExistent.json";
        JsonReader jsonReader = new JsonReader();

        assertThrows(IllegalArgumentException.class, () -> jsonReader.addParticipantsFromJson(jsonFile));
    }

    @Test
    void testAddParticipantsFromJson_invalidEmail() {
        String jsonFile = "invalidEmail.json";
        JsonReader jsonReader = new JsonReader();

        assertThrows(IllegalArgumentException.class, () -> jsonReader.addParticipantsFromJson(jsonFile));
    }
}
