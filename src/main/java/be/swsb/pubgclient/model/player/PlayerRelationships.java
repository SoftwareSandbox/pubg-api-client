package be.swsb.pubgclient.model.player;

import be.swsb.pubgclient.model.DataList;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PlayerRelationships {

    public PlayerRelationships() {
        this(new DataList<>());
    }

    public PlayerRelationships(DataList<MatchId> matchIds) {
        this.matchIds = matchIds;
    }

    public DataList<MatchId> getMatchIds() {
        return matchIds;
    }
    // TODO find a way to retrieve matchIds as a String here without the intermediate object

    @SerializedName("matches")
    private DataList<MatchId> matchIds;

    public void setMatchIds(DataList<MatchId> matchIds) {
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
