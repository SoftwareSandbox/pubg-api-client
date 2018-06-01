package be.swsb.pubgclient.model.player;

import java.time.ZonedDateTime;

public class PlayerAttributes {

    private String name;
    private String shardId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String titleId;
    private String patchVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    @Override
    public String toString() {
        return "PlayerAttributes{" +
                "name='" + name + '\'' +
                ", shardId='" + shardId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", titleId='" + titleId + '\'' +
                ", patchVersion='" + patchVersion + '\'' +
                '}';
    }
}
