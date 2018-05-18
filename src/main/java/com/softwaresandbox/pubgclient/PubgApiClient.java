package com.softwaresandbox.pubgclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.softwaresandbox.pubgclient.api.PubgApiCaller;
import com.softwaresandbox.pubgclient.model.match.MatchResponse;
import com.softwaresandbox.pubgclient.model.match.ParticipantRosterAsset;
import com.softwaresandbox.pubgclient.model.match.asset.Asset;
import com.softwaresandbox.pubgclient.model.match.participant.Participant;
import com.softwaresandbox.pubgclient.model.match.roster.Roster;
import com.softwaresandbox.pubgclient.model.player.Player;
import com.softwaresandbox.pubgclient.model.player.PlayerResponse;
import com.softwaresandbox.pubgclient.model.player.PlayersResponse;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.ZonedDateTime.parse;
import static java.util.Collections.singleton;

public class PubgApiClient {

    private PubgApiCaller pubgApiCaller;

    public PubgApiClient() {
        this.pubgApiCaller = new PubgApiCaller();
    }

    public Optional<PlayerResponse> getPlayerByName(String playerName, String region) throws PubgApiClientException {
        PlayersResponse playersResponse = getPlayersByName(singleton(playerName), region);
        List<Player> players = playersResponse.getPlayers();
        if (players.size() > 0) {
            return Optional.of(new PlayerResponse(players.get(0), playersResponse.getLinks()));
        }
        return Optional.empty();
    }

    public Optional<PlayerResponse> getPlayerById(String playerId, String region) throws PubgApiClientException {
        PlayersResponse playersResponse = getPlayersById(singleton(playerId), region);
        List<Player> players = playersResponse.getPlayers();
        if (players.size() > 0) {
            return Optional.of(new PlayerResponse(players.get(0), playersResponse.getLinks()));
        }
        return Optional.empty();
    }

    public PlayersResponse getPlayersByName(Set<String> playerNames, String region) throws PubgApiClientException {
        String playersJson = pubgApiCaller.getPlayersByName(playerNames, region);
        return getPlayerResponse(playersJson);
    }

    public PlayersResponse getPlayersById(Set<String> playerIds, String region) throws PubgApiClientException {
        String playersJson = pubgApiCaller.getPlayersById(playerIds, region);
        return getPlayerResponse(playersJson);
    }

    private PlayersResponse getPlayerResponse(String playersJson) {
        return getGson().fromJson(playersJson, PlayersResponse.class);
    }

    // TODO getPlayerSeasonResponse(String playerId, String seasonId)

    // TODO getSeasons()

    public Optional<MatchResponse> getMatch(String id, String region) throws PubgApiClientException {
        String matchJson = pubgApiCaller.getMatch(id, region);
        MatchResponse matchResponse = getGson().fromJson(matchJson, MatchResponse.class);
        return matchResponse.getMatch() != null ? Optional.of(matchResponse) : Optional.empty();
    }

    // TODO getSamples() {

    // TODO telemetry related stuff

    // TODO getStatus()

    // TODO test separately
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, getZonedDateTimeJsonDeserializer());
        gsonBuilder.registerTypeAdapter(ParticipantRosterAsset.class, (JsonDeserializer<ParticipantRosterAsset>) (json, typeOfT, context) -> {
            String type = json.getAsJsonObject().get("type").getAsString();
            if (type.equals("participant")) {
                return context.deserialize(json, Participant.class);
            }
            if (type.equals("roster")) {
                return context.deserialize(json, Roster.class);
            }
            if (type.equals("asset")) {
                return context.deserialize(json, Asset.class);
            }
            throw new IllegalArgumentException("Unknown type for " + ParticipantRosterAsset.class.getSimpleName() + " deserialization: " + type);
        });
        return gsonBuilder.create();
    }

    private JsonDeserializer<ZonedDateTime> getZonedDateTimeJsonDeserializer() {
        return (json, typeOfT, context) -> parse(json.getAsString());
    }

}
