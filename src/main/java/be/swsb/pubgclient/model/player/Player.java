package be.swsb.pubgclient.model.player;

import be.swsb.pubgclient.model.Links;
import com.google.gson.annotations.SerializedName;

public class Player {

    private String id;
    @SerializedName("attributes")
    private PlayerAttributes playerAttributes;
    @SerializedName("relationships")
    private PlayerRelationships playerRelationships;
    private Links links;

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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", playerAttributes=" + playerAttributes +
                ", playerRelationships=" + playerRelationships +
                ", links=" + links +
                '}';
    }
}
