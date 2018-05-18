package com.softwaresandbox.pubgclient.api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.softwaresandbox.pubgclient.PubgApiClientException;

import java.util.Set;

import static com.softwaresandbox.pubgclient.PropertyFileReader.readPubgApiKey;
import static java.util.stream.Collectors.joining;

public class PubgApiCaller {

    private static final String BASE_URL = "https://api.playbattlegrounds.com";

    // TODO provide region as a parameter
    private String region = "pc-eu";

    public String getPlayersByName(Set<String> playerNames) throws PubgApiClientException {
        return getPlayers(playerNames, "playerNames");
    }

    public String getPlayersById(Set<String> playerIds) throws PubgApiClientException {
        return getPlayers(playerIds, "playerIds");
    }

    public String getMatch(String id) throws PubgApiClientException {
        return callApi("matches", "/" + id);
    }

    private String getPlayers(Set<String> players, String criteria) throws PubgApiClientException {
        return callApi("players", "?filter[" + criteria + "]=" + players.stream().collect(joining(",")));
    }

    private String callApi(String apiOperation, String parameters) throws PubgApiClientException {
        try {
            return Unirest.get(BASE_URL + "/shards/" + region + "/" + apiOperation + parameters)
                    .header("Authorization", "Bearer " + readPubgApiKey())
                    .header("Accept", "application/vnd.api+json")
                    .asString().getBody();
        } catch (UnirestException e) {
            throw new PubgApiClientException("Error while calling Pubg API operation: /" + apiOperation, e);
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
}
