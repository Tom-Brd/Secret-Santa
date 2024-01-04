package com.tom.secretSanta;

import java.util.ArrayList;
import java.util.List;

public class SecretSanta {

    private final List<Participant> participants;

    public SecretSanta(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void assignParticipants() {
        List<Participant> participantsCopy = new ArrayList<>(this.participants);
        for (Participant participant : this.participants) {
            Participant assignedParticipant = participantsCopy.get((int) (Math.random() * participantsCopy.size()));
            while (assignedParticipant == participant) {
                assignedParticipant = participantsCopy.get((int) (Math.random() * participantsCopy.size()));
            }
            participantsCopy.remove(assignedParticipant);
            participant.setAssignedParticipant(assignedParticipant);
        }
    }

}
