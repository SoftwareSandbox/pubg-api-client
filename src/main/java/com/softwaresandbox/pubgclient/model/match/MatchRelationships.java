package com.softwaresandbox.pubgclient.model.match;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.DataList;

public class MatchRelationships {

    @SerializedName("assets")
    private DataList<AssetId> assetIds;
    @SerializedName("rosters")
    private DataList<RosterId> rosterIds;

    public DataList<AssetId> getAssetIds() {
        return assetIds;
    }

    public void setAssetIds(DataList<AssetId> assetIds) {
        this.assetIds = assetIds;
    }

    public DataList<RosterId> getRosterIds() {
        return rosterIds;
    }

    public void setRosterIds(DataList<RosterId> rosterIds) {
        this.rosterIds = rosterIds;
    }

    @Override
    public String toString() {
        return "MatchRelationships{" +
                "assetIds=" + assetIds +
                ", rosterIds=" + rosterIds +
                '}';
    }
}
