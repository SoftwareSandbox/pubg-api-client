package be.swsb.pubgclient.model.match.asset;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

public class AssetAttributes {

    @SerializedName("URL")
    private String url;
    private String name;
    private String description;
    private ZonedDateTime createdAt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "AssetAttributes{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
