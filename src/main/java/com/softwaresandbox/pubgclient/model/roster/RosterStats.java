package com.softwaresandbox.pubgclient.model.roster;

public class RosterStats {

    private int rank;
    private String teamId;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "RosterStats{" +
                "rank=" + rank +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
