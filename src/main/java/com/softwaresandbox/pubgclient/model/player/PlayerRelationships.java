package com.softwaresandbox.pubgclient.model.player;

import com.softwaresandbox.pubgclient.model.DataList;

public class PlayerRelationships {

    private DataList<MatchId> matchIds;

    public DataList<MatchId> getMatchIds() {
        return matchIds;
    }

    public void setMatchIds(DataList<MatchId> matchIds) {
        this.matchIds = matchIds;
    }
}
