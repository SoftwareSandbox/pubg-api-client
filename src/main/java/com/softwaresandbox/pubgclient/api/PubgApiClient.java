package com.softwaresandbox.pubgclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.softwaresandbox.pubgclient.PubgApiClientException;
import com.softwaresandbox.pubgclient.model.player.PlayersResponse;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.softwaresandbox.pubgclient.PropertyFileReader.readPubgApiKey;
import static java.time.ZonedDateTime.parse;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public class PubgApiClient {

    private static final String BASE_URL = "https://api.playbattlegrounds.com";

    private String region = "pc-eu";

    public String getPlayers(Set<String> players) throws PubgApiClientException {
        try {
            return Unirest.get(BASE_URL + "/shards/" + region + "/players?filter[playerNames]=" + players.stream().collect(joining(",")))
                    .header("Authorization", "Bearer " + readPubgApiKey())
                    .header("Accept", "application/vnd.api+json")
                    .asString().getBody();
        } catch (UnirestException e) {
            // TODO improve error message depending on the actual call
            throw new PubgApiClientException("Error while calling Pubg API", e);
        }
    }

    public String getStatus() throws PubgApiClientException {
        try {
            return Unirest.get(BASE_URL + "/status")
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new PubgApiClientException("Error retrieving Pubg API status", e);
        }
    }

    // TODO remove
    public static void main(String[] args) {
        try {
            String players = new PubgApiClient().getPlayers(new HashSet<>(asList("Jooones", "zwiep")));
            System.out.println(players);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(ZonedDateTime.class, (JsonDeserializer<ZonedDateTime>) (json, typeOfT, context) -> parse(json.getAsString()));
            Gson gson = gsonBuilder.create();
            PlayersResponse playerResponse = gson.fromJson(players, PlayersResponse.class);
            System.out.println(playerResponse);
        } catch (PubgApiClientException e) {
            e.printStackTrace();
        }
    }

}
