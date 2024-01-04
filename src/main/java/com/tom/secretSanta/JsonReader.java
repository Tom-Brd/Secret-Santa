package com.tom.secretSanta;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public List<Participant> addParticipantsFromJson(String jsonFile) throws IllegalAccessException {
        JSONArray jsonArray = openJsonFile(jsonFile);
        validateJsonArray(jsonArray);
        return parseParticipants(jsonArray);
    }

    private JSONArray openJsonFile(String filename) throws IllegalAccessException {
        try (InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new IllegalAccessException("Could not find file " + filename);
            }
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(new InputStreamReader(inputStream));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading file " + filename + ": " + e.getMessage());
        }
    }

    private void validateJsonArray(JSONArray jsonArray) throws IllegalAccessException {
        if (jsonArray.size() < 2) {
            throw new IllegalAccessException("There must be at least 2 participants");
        }
    }

    private List<Participant> parseParticipants(JSONArray jsonArray) {
        List<Participant> participants = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject participantObject = (JSONObject) object;
            participants.add(new Participant((String) participantObject.get("name"), (String) participantObject.get("email")));
        }
        return participants;
    }
}
