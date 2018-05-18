package com.softwaresandbox.pubgclient;

import com.softwaresandbox.pubgclient.api.PubgApiCaller;
import com.softwaresandbox.pubgclient.model.player.Player;
import com.softwaresandbox.pubgclient.model.player.PlayersResponse;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.junit.MockitoJUnit.rule;

public class PubgApiClientTest {

    private static final String NON_EXISTING_PLAYER = "NonExistingPlayer";
    private static final String NO_PLAYERS_FOUND_RESPONSE = "{\"errors\":[{\"title\":\"Not Found\",\"detail\":\"No players found matching criteria\"}]}";

    private static String twoPlayersSuccessResponse;

    @Rule
    public MockitoRule rule = rule();

    @Mock
    private PubgApiCaller pubgApiCaller;

    @InjectMocks
    private PubgApiClient pubgApiClient;

    @BeforeClass
    public static void setUp() throws Exception {
        twoPlayersSuccessResponse = new String(Files.readAllBytes(Paths.get(PubgApiClientTest.class.getClassLoader().getResource("getPlayersResponse.json").toURI())));
    }

    @Test
    public void getPlayersByNameWhenPlayersFoundThenReturnFilledPlayersResponse() throws PubgApiClientException {
        Set<String> playerNames = new HashSet<>(asList("Jooones", "zwiep"));
        when(pubgApiCaller.getPlayersByName(playerNames)).thenReturn(twoPlayersSuccessResponse);

        PlayersResponse actual = pubgApiClient.getPlayersByName(playerNames);

        assertThat(actual.getPlayers()).hasSize(2);
        assertThat(actual.getPlayers()).extracting(player -> player.getPlayerAttributes().getName()).containsOnly("Jooones", "zwiep");
    }

    @Test
    public void getPlayersByNameWhenNoPlayersFoundThenReturnPlayersResponseWithEmptyPlayerList() throws PubgApiClientException {
        Set<String> playerNames = singleton(NON_EXISTING_PLAYER);
        when(pubgApiCaller.getPlayersByName(playerNames)).thenReturn(NO_PLAYERS_FOUND_RESPONSE);

        PlayersResponse actual = pubgApiClient.getPlayersByName(playerNames);

        assertThat(actual.getPlayers()).isEmpty();
    }

    @Test
    public void getPlayersByIdWhenPlayersFoundThenReturnFilledPlayersResponse() throws PubgApiClientException {
        Set<String> playerIds = new HashSet<>(asList("account.2b95c68272fd467db565f5134277993b", "account.b020ad1e124140c49f9ca3c5d47f99bb"));
        when(pubgApiCaller.getPlayersById(playerIds)).thenReturn(twoPlayersSuccessResponse);

        PlayersResponse actual = pubgApiClient.getPlayersById(playerIds);

        assertThat(actual.getPlayers()).hasSize(2);
        assertThat(actual.getPlayers()).extracting(Player::getId).containsOnly("account.2b95c68272fd467db565f5134277993b", "account.b020ad1e124140c49f9ca3c5d47f99bb");
    }

    @Test
    public void getPlayersByIdsWhenNoPlayersFoundThenReturnPlayersResponseWithEmptyPlayerList() throws PubgApiClientException {
        Set<String> playerIds = singleton(NON_EXISTING_PLAYER);
        when(pubgApiCaller.getPlayersById(playerIds)).thenReturn(NO_PLAYERS_FOUND_RESPONSE);

        PlayersResponse actual = pubgApiClient.getPlayersById(playerIds);

        assertThat(actual.getPlayers()).isEmpty();
    }
}