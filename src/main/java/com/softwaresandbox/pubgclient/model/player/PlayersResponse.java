package com.softwaresandbox.pubgclient.model.player;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.Links;

import java.util.ArrayList;
import java.util.List;

public class PlayersResponse {

    @SerializedName("data")
    private List<Player> players= new ArrayList<>();
    @SerializedName("links")
    private Links links;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "PlayersResponse{" +
                "players=" + players +
                ", links=" + links +
                '}';
    }
}
