package com.tom.secretSanta;

public class Main {
    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();
        try {
            SecretSanta secretSanta = new SecretSanta(jsonReader.addParticipantsFromJson("participants.json"));

            secretSanta.assignParticipants();
            secretSanta.sendEmails();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
