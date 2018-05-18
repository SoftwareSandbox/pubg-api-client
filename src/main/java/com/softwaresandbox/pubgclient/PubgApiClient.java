package com.softwaresandbox.pubgclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.softwaresandbox.pubgclient.api.PubgApiCaller;
import com.softwaresandbox.pubgclient.model.player.PlayersResponse;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.ZonedDateTime.parse;
import static java.util.Arrays.asList;

public class PubgApiClient {

    private PubgApiCaller pubgApiCaller;

    public PubgApiClient() {
        this.pubgApiCaller = new PubgApiCaller();
    }

    public PlayersResponse getPlayersByName(Set<String> playerNames) throws PubgApiClientException {
        String playersJson = pubgApiCaller.getPlayersByName(playerNames);
        return getGson().fromJson(playersJson, PlayersResponse.class);
    }

    public PlayersResponse getPlayersById(Set<String> playerIds) throws PubgApiClientException {
        String playersJson = pubgApiCaller.getPlayersById(playerIds);
        return getGson().fromJson(playersJson, PlayersResponse.class);
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, getZonedDateTimeJsonDeserializer());
        return gsonBuilder.create();
    }

    private JsonDeserializer<ZonedDateTime> getZonedDateTimeJsonDeserializer() {
        return (json, typeOfT, context) -> parse(json.getAsString());
    }

    // TODO remove
    public static void main(String[] args) {
        try {
            PlayersResponse playerResponse = new PubgApiClient().getPlayersByName(new HashSet<>(asList("Jooones", "zwiep")));
//            PlayersResponse playerResponse = new PubgApiClient().getPlayersById(new HashSet<>(asList("account.2b95c68272fd467db565f5134277993b", "account.b020ad1e124140c49f9ca3c5d47f99bb")));
            System.out.println(playerResponse);
        } catch (PubgApiClientException e) {
            e.printStackTrace();
        }
    }
}
