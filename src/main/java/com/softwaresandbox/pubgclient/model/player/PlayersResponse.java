package com.softwaresandbox.pubgclient.model.player;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.Link;

import java.util.ArrayList;
import java.util.List;

public class PlayersResponse {

    @SerializedName("data")
    private List<Player> players= new ArrayList<>();
    @SerializedName("links")
    private Link link;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "PlayersResponse{" +
                "players=" + players +
                ", link=" + link +
                '}';
    }
}
