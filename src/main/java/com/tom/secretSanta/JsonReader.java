package com.tom.secretSanta;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonReader {
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
}
