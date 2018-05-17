package com.softwaresandbox.pubgclient.model.player;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.Link;

public class Player {

    private String id;
    @SerializedName("attributes")
    private PlayerAttributes playerAttributes;
    @SerializedName("relationships")
    private PlayerRelationships playerRelationships;
    @SerializedName("links")
    private Link link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerAttributes getPlayerAttributes() {
        return playerAttributes;
    }

    public void setPlayerAttributes(PlayerAttributes playerAttributes) {
        this.playerAttributes = playerAttributes;
    }

    public PlayerRelationships getPlayerRelationships() {
        return playerRelationships;
    }

    public void setPlayerRelationships(PlayerRelationships playerRelationships) {
        this.playerRelationships = playerRelationships;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
