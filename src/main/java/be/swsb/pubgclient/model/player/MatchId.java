package be.swsb.pubgclient.model.player;

import java.util.Objects;

public class MatchId {

    private MatchId() {
    }

    public MatchId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId matchId = (MatchId) o;
        return Objects.equals(id, matchId.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
