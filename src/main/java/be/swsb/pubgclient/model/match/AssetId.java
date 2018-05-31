package be.swsb.pubgclient.model.match;

public class AssetId {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AssetId{" +
                "id='" + id + '\'' +
                '}';
    }
}
