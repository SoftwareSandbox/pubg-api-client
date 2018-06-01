package be.swsb.pubgclient.model.match.asset;

import be.swsb.pubgclient.model.match.ParticipantRosterAsset;
import com.google.gson.annotations.SerializedName;

public class Asset implements ParticipantRosterAsset {

    private String id;
    @SerializedName("attributes")
    private AssetAttributes assetAttributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AssetAttributes getAssetAttributes() {
        return assetAttributes;
    }

    public void setAssetAttributes(AssetAttributes assetAttributes) {
        this.assetAttributes = assetAttributes;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", assetAttributes=" + assetAttributes +
                '}';
    }
}
