package be.swsb.pubgclient;

import be.swsb.pubgclient.api.PubgApiCaller;
import be.swsb.pubgclient.gson.PlayerRelationShipsAdapterFactory;
import be.swsb.pubgclient.gson.PlayerRelationshipsAdapter;
import be.swsb.pubgclient.model.match.MatchResponse;
import be.swsb.pubgclient.model.match.ParticipantRosterAsset;
import be.swsb.pubgclient.model.match.asset.Asset;
import be.swsb.pubgclient.model.match.participant.Participant;
import be.swsb.pubgclient.model.match.roster.Roster;
import be.swsb.pubgclient.model.player.Player;
import be.swsb.pubgclient.model.player.PlayerRelationships;
import be.swsb.pubgclient.model.player.PlayerResponse;
import be.swsb.pubgclient.model.player.PlayersResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.ZonedDateTime.parse;
import static java.util.Collections.singleton;


/**
 * <a href="https://github.com/SoftwareSandbox/pubg-api-client">https://github.com/SoftwareSandbox/pubg-api-client</a><br><br>
 *
 * Example usage:
 * <pre>
 * new PubgApiClient().getPlayerByName("shroud", "pc-na");
 * </pre>
 *
 */
public class PubgApiClient {

    public static final String DEFAULT_API_BASE_URL = "https://api.playbattlegrounds.com";

    private PubgApiCaller pubgApiCaller;

    PubgApiClient(String apiBaseUrl) {
        this.pubgApiCaller = new PubgApiCaller(apiBaseUrl);
    }

    PubgApiClient(String apiBaseUrl, String apiKey) {
        PubgApiKey.setPubgApiKey(apiKey);
        this.pubgApiCaller = new PubgApiCaller(apiBaseUrl);
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

    public Optional<MatchResponse> getMatch(String id, String region) throws PubgApiClientException {
        String matchJson = pubgApiCaller.getMatch(id, region);
        MatchResponse matchResponse = getGson().fromJson(matchJson, MatchResponse.class);
        return matchResponse.getMatch() != null ? Optional.of(matchResponse) : Optional.empty();
    }

    // TODO test separately
    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, getZonedDateTimeJsonDeserializer());
        gsonBuilder.registerTypeAdapterFactory(new PlayerRelationShipsAdapterFactory());
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
        //TODO lose dependency on gson by using JsonSerialize/JsonDeserializer
        return gsonBuilder.create();
    }

    private JsonDeserializer<ZonedDateTime> getZonedDateTimeJsonDeserializer() {
        return (json, typeOfT, context) -> parse(json.getAsString());
    }

    void setPubgApiCaller(PubgApiCaller pubgApiCaller) {
        this.pubgApiCaller = pubgApiCaller;
    }
}
