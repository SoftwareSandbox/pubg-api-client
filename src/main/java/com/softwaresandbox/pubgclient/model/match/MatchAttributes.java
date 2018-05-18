package com.softwaresandbox.pubgclient.model.match;

import java.time.ZonedDateTime;

public class MatchAttributes {

    private String shardId;
    private String gameMode;
    private String mapName;
    private ZonedDateTime createdAt;
    private int duration;
    private String titleId;

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Override
    public String toString() {
        return "MatchAttributes{" +
                "shardId='" + shardId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", mapName='" + mapName + '\'' +
                ", createdAt=" + createdAt +
                ", duration=" + duration +
                ", titleId='" + titleId + '\'' +
                '}';
    }
}