package be.swsb.pubgclient.model.player;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerRelationships {

    @SerializedName("matches")
    private List<MatchId> matchIds;

    public PlayerRelationships() {
        this(new ArrayList<>());
    }

    public PlayerRelationships(List<MatchId> matchIds) {
        this.matchIds = matchIds;
    }
    public List<MatchId> getMatchIds() {
        return matchIds;
    }

    public void setMatchIds(List<MatchId> matchIds) {
        this.matchIds = matchIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRelationships that = (PlayerRelationships) o;
        return Objects.equals(matchIds, that.matchIds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(matchIds);
    }

    @Override
    public String toString() {
        return "PlayerRelationships{" +
                "matchIds=" + matchIds +
                '}';
    }
}
