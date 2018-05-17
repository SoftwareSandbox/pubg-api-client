package com.softwaresandbox.pubgclient.api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.softwaresandbox.pubgclient.PubgApiClientException;

import java.util.HashSet;
import java.util.Set;

import static com.softwaresandbox.pubgclient.PropertyFileReader.readPubgApiKey;
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
            System.out.println(new PubgApiClient().getPlayers(new HashSet<>(asList("Jooones", "zwiep"))));
        } catch (PubgApiClientException e) {
            e.printStackTrace();
        }
    }

}
