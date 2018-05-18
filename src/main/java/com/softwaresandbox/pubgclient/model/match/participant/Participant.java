package com.softwaresandbox.pubgclient.model.match.participant;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.match.ParticipantRosterAsset;

public class Participant implements ParticipantRosterAsset {

    private String id;
    @SerializedName("attributes")
    private ParticipantAttributes participantAttributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParticipantAttributes getParticipantAttributes() {
        return participantAttributes;
    }

    public void setParticipantAttributes(ParticipantAttributes participantAttributes) {
        this.participantAttributes = participantAttributes;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", participantAttributes=" + participantAttributes +
                '}';
    }
}
