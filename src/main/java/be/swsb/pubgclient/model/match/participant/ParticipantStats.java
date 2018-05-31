package be.swsb.pubgclient.model.match.participant;

import com.google.gson.annotations.SerializedName;

public class ParticipantStats {

    @SerializedName("DBNOs")
    private int dbnos;
    private int assists;
    private int boosts;
    private double damageDealt;
    private String deathType;
    @SerializedName("headshotKills")
    private int headShotKills;
    private int heals;
    private int killPlace;
    private int killPoints;
    private double killPointsDelta;
    private int killStreaks;
    private int kills;
    private int lastKillPoints;
    private int lastWinPoints;
    private int longestKill;
    private double mostDamage;
    private String name;
    private String playerId;
    private int revives;
    private double rideDistance;
    private int roadKills;
    private int teamKills;
    private int timeSurvived;
    private int vehicleDestroys;
    private double walkDistance;
    private int weaponsAcquired;
    private int winPlace;
    private int winPoints;
    private double winPointsDelta;

    public int getDbnos() {
        return dbnos;
    }

    public void setDbnos(int dbnos) {
        this.dbnos = dbnos;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getBoosts() {
        return boosts;
    }

    public void setBoosts(int boosts) {
        this.boosts = boosts;
    }

    public double getDamageDealt() {
        return damageDealt;
    }

    public void setDamageDealt(double damageDealt) {
        this.damageDealt = damageDealt;
    }

    public String getDeathType() {
        return deathType;
    }

    public void setDeathType(String deathType) {
        this.deathType = deathType;
    }

    public int getHeadShotKills() {
        return headShotKills;
    }

    public void setHeadShotKills(int headShotKills) {
        this.headShotKills = headShotKills;
    }

    public int getHeals() {
        return heals;
    }

    public void setHeals(int heals) {
        this.heals = heals;
    }

    public int getKillPlace() {
        return killPlace;
    }

    public void setKillPlace(int killPlace) {
        this.killPlace = killPlace;
    }

    public int getKillPoints() {
        return killPoints;
    }

    public void setKillPoints(int killPoints) {
        this.killPoints = killPoints;
    }

    public double getKillPointsDelta() {
        return killPointsDelta;
    }

    public void setKillPointsDelta(double killPointsDelta) {
        this.killPointsDelta = killPointsDelta;
    }

    public int getKillStreaks() {
        return killStreaks;
    }

    public void setKillStreaks(int killStreaks) {
        this.killStreaks = killStreaks;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLastKillPoints() {
        return lastKillPoints;
    }

    public void setLastKillPoints(int lastKillPoints) {
        this.lastKillPoints = lastKillPoints;
    }

    public int getLastWinPoints() {
        return lastWinPoints;
    }

    public void setLastWinPoints(int lastWinPoints) {
        this.lastWinPoints = lastWinPoints;
    }

    public int getLongestKill() {
        return longestKill;
    }

    public void setLongestKill(int longestKill) {
        this.longestKill = longestKill;
    }

    public double getMostDamage() {
        return mostDamage;
    }

    public void setMostDamage(double mostDamage) {
        this.mostDamage = mostDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getRevives() {
        return revives;
    }

    public void setRevives(int revives) {
        this.revives = revives;
    }

    public double getRideDistance() {
        return rideDistance;
    }

    public void setRideDistance(double rideDistance) {
        this.rideDistance = rideDistance;
    }

    public int getRoadKills() {
        return roadKills;
    }

    public void setRoadKills(int roadKills) {
        this.roadKills = roadKills;
    }

    public int getTeamKills() {
        return teamKills;
    }

    public void setTeamKills(int teamKills) {
        this.teamKills = teamKills;
    }

    public int getTimeSurvived() {
        return timeSurvived;
    }

    public void setTimeSurvived(int timeSurvived) {
        this.timeSurvived = timeSurvived;
    }

    public int getVehicleDestroys() {
        return vehicleDestroys;
    }

    public void setVehicleDestroys(int vehicleDestroys) {
        this.vehicleDestroys = vehicleDestroys;
    }

    public double getWalkDistance() {
        return walkDistance;
    }

    public void setWalkDistance(double walkDistance) {
        this.walkDistance = walkDistance;
    }

    public int getWeaponsAcquired() {
        return weaponsAcquired;
    }

    public void setWeaponsAcquired(int weaponsAcquired) {
        this.weaponsAcquired = weaponsAcquired;
    }

    public int getWinPlace() {
        return winPlace;
    }

    public void setWinPlace(int winPlace) {
        this.winPlace = winPlace;
    }

    public int getWinPoints() {
        return winPoints;
    }

    public void setWinPoints(int winPoints) {
        this.winPoints = winPoints;
    }

    public double getWinPointsDelta() {
        return winPointsDelta;
    }

    public void setWinPointsDelta(double winPointsDelta) {
        this.winPointsDelta = winPointsDelta;
    }

    @Override
    public String toString() {
        return "ParticipantStats{" +
                "dbnos=" + dbnos +
                ", assists=" + assists +
                ", boosts=" + boosts +
                ", damageDealt=" + damageDealt +
                ", deathType='" + deathType + '\'' +
                ", headShotKills=" + headShotKills +
                ", heals=" + heals +
                ", killPlace=" + killPlace +
                ", killPoints=" + killPoints +
                ", killPointsDelta=" + killPointsDelta +
                ", killStreaks=" + killStreaks +
                ", kills=" + kills +
                ", lastKillPoints=" + lastKillPoints +
                ", lastWinPoints=" + lastWinPoints +
                ", longestKill=" + longestKill +
                ", mostDamage=" + mostDamage +
                ", name='" + name + '\'' +
                ", playerId='" + playerId + '\'' +
                ", revives=" + revives +
                ", rideDistance=" + rideDistance +
                ", roadKills=" + roadKills +
                ", teamKills=" + teamKills +
                ", timeSurvived=" + timeSurvived +
                ", vehicleDestroys=" + vehicleDestroys +
                ", walkDistance=" + walkDistance +
                ", weaponsAcquired=" + weaponsAcquired +
                ", winPlace=" + winPlace +
                ", winPoints=" + winPoints +
                ", winPointsDelta=" + winPointsDelta +
                '}';
    }
}