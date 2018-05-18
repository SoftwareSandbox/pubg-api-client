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
import com.softwaresandbox.pubgclient.model.player.PlayersResponse;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;

import static java.time.ZonedDateTime.parse;

public class PubgApiClient {

    private PubgApiCaller pubgApiCaller;

    public PubgApiClient() {
        this.pubgApiCaller = new PubgApiCaller();
    }

    // TODO
//    public Optional<PlayerResponse> getPlayerByName(String playerName) throws PubgApiClientException {
//        throw new UnsupportedOperationException("TODO");
//    }

    // TODO
//    public Optional<PlayerResponse> getPlayerById(String playerId) throws PubgApiClientException {
//        throw new UnsupportedOperationException("TODO");
//    }

    public PlayersResponse getPlayersByName(Set<String> playerNames) throws PubgApiClientException {
        String playersJson = pubgApiCaller.getPlayersByName(playerNames);
        return getPlayerResponse(playersJson);
    }

    public PlayersResponse getPlayersById(Set<String> playerIds) throws PubgApiClientException {
        String playersJson = pubgApiCaller.getPlayersById(playerIds);
        return getPlayerResponse(playersJson);
    }

    private PlayersResponse getPlayerResponse(String playersJson) {
        return getGson().fromJson(playersJson, PlayersResponse.class);
    }

    // TODO
//    private Object getPlayerSeasonResponse(String playerId, String seasonId) {
//        throw new UnsupportedOperationException("TODO");
//    }

    // TODO
//    private Object getSeasons() {
//        throw new UnsupportedOperationException("TODO");
//    }

    // TODO
//    private Object getSamples() {
//        throw new UnsupportedOperationException("TODO");
//    }

    // TODO
    // telemetry related stuff

    public Optional<MatchResponse> getMatch(String id) throws PubgApiClientException {
        String matchJson = pubgApiCaller.getMatch(id);
        MatchResponse matchResponse = getGson().fromJson(matchJson, MatchResponse.class);
        return matchResponse.getMatch() != null ? Optional.of(matchResponse) : Optional.empty();
    }

    // TODO
//    public String getStatus() {
//        throw new UnsupportedOperationException("TODO");
//    }

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
