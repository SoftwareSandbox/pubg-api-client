package be.swsb.pubgclient.model.match;

import be.swsb.pubgclient.model.DataList;
import com.google.gson.annotations.SerializedName;

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
