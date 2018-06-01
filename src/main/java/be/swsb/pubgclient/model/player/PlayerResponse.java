package be.swsb.pubgclient.model.player;

import be.swsb.pubgclient.model.Links;

public class PlayerResponse {

    private Player player;
    private Links links;

    public PlayerResponse(Player player, Links links) {
        this.player = player;
        this.links = links;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "PlayerResponse{" +
                "player=" + player +
                ", links=" + links +
                '}';
    }
}
