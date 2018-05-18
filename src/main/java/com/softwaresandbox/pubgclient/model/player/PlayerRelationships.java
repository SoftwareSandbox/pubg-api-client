package com.softwaresandbox.pubgclient.model.player;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.DataList;

public class PlayerRelationships {

    // TODO find a way to retrieve matchIds as a String here without the intermediate object
    @SerializedName("matches")
    private DataList<MatchId> matchIds;

    public DataList<MatchId> getMatchIds() {
        return matchIds;
    }

    public void setMatchIds(DataList<MatchId> matchIds) {
        this.matchIds = matchIds;
    }

    @Override
    public String toString() {
        return "PlayerRelationships{" +
                "matchIds=" + matchIds +
                '}';
    }
}
