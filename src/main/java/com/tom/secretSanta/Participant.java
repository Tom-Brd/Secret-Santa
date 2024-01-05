package com.tom.secretSanta;

import org.apache.commons.validator.routines.EmailValidator;

public class Participant {
    private final String name;
    private final String email;
    private Participant assignedParticipant;

    public Participant(String name, String email) {
        if (!isEmailValid(email)) {
            throw new IllegalArgumentException("Email is not valid");
        }
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    private boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public void setAssignedParticipant(Participant participant) {
        this.assignedParticipant = participant;
    }

    public Participant getAssignedParticipant() {
        return this.assignedParticipant;
    }

    public String messageBody() {
        return "Heyy " + this.name + ",\n\nLa personne qui t'a été assignée pour le Secret Santa est " + this.assignedParticipant.getName() + " !";
    }
}
