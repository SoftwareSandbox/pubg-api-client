package com.softwaresandbox.pubgclient.model.player;

public class MatchId {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MatchId{" +
                "id='" + id + '\'' +
                '}';
    }
}
