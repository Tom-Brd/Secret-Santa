package com.tom.secretSanta;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SecretSantaTest {

    private List<Participant> createSampleParticipants() {
        return Arrays.asList(
                new Participant("Alice", "alice@example.com"),
                new Participant("Bob", "bob@example.com")
        );
    }

    @Test
    void testAssignParticipants_everyParticipantHasMatch() {
        SecretSanta secretSanta = new SecretSanta(createSampleParticipants());
        secretSanta.assignParticipants();

        for (Participant participant : secretSanta.getParticipants()) {
            assertNotNull(participant.getAssignedParticipant());
        }
    }

    @Test
    void testAssignParticipants_NoSelfAssignment() {
        SecretSanta secretSanta = new SecretSanta(createSampleParticipants());
        secretSanta.assignParticipants();

        for (Participant participant : secretSanta.getParticipants()) {
            assertNotEquals(participant, participant.getAssignedParticipant());
        }
    }

    @Test
    void testAssignParticipants_UniqueAssignments() {
        SecretSanta secretSanta = new SecretSanta(createSampleParticipants());
        secretSanta.assignParticipants();

        Set<Participant> assignedSet = new HashSet<>();
        for (Participant participant : secretSanta.getParticipants()) {
            assertTrue(assignedSet.add(participant.getAssignedParticipant()));
        }
    }

    @Test
    void testAssignParticipants_EmptyList() {
        SecretSanta secretSanta = new SecretSanta(new ArrayList<>());
        secretSanta.assignParticipants();
        assertTrue(secretSanta.getParticipants().isEmpty());
    }

}
