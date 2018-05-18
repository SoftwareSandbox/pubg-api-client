package com.softwaresandbox.pubgclient.model.match.roster;

public class ParticipantId {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ParticipantId{" +
                "id='" + id + '\'' +
                '}';
    }
}
