package be.swsb.pubgclient;

import be.swsb.pubgclient.api.PubgApiCaller;
import be.swsb.pubgclient.model.match.Match;
import be.swsb.pubgclient.model.match.MatchResponse;
import be.swsb.pubgclient.model.match.asset.Asset;
import be.swsb.pubgclient.model.match.participant.Participant;
import be.swsb.pubgclient.model.match.participant.ParticipantStats;
import be.swsb.pubgclient.model.match.roster.Roster;
import be.swsb.pubgclient.model.player.Player;
import be.swsb.pubgclient.model.player.PlayersResponse;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.junit.MockitoJUnit.rule;

public class PubgApiClientTest {

    private static final String NON_EXISTING_PLAYER = "NonExistingPlayer";
    private static final String NO_PLAYERS_FOUND_RESPONSE = "{\"errors\":[{\"title\":\"Not Found\",\"detail\":\"No players found matching criteria\"}]}";
    private static final String NO_MATCH_FOUND_RESPONSE = "{\"errors\":[{\"title\":\"Not Found\",\"detail\":\"No match found with ID\"}]}";
    private static final String MATCH_ID = "439b4e90-f9ea-49ef-bf62-00241492dffe";
    private static final String REGION = "pc-eu";

    private static String twoPlayersSuccessResponse;
    private static String matchSuccessResponse;
    private static String matchSuccessResponseAdjustedStats;

    @Rule
    public MockitoRule rule = rule();

    @Mock
    private PubgApiCaller pubgApiCaller;

    private PubgApiClient pubgApiClient = new PubgApiClient();

    @BeforeClass
    public static void beforeClass() throws Exception {
        twoPlayersSuccessResponse = new String(readAllBytes(get(requireNonNull(PubgApiClientTest.class.getClassLoader().getResource("getPlayersResponse.json")).toURI())));
        matchSuccessResponse = new String(readAllBytes(get(requireNonNull(PubgApiClientTest.class.getClassLoader().getResource("getMatchResponse.json")).toURI())));
        matchSuccessResponseAdjustedStats = new String(readAllBytes(get(requireNonNull(PubgApiClientTest.class.getClassLoader().getResource("getMatchResponseAdjustedStats.json")).toURI())));
    }

    @Before
    public void setUp() {
        pubgApiClient.setPubgApiCaller(pubgApiCaller);
    }

    @Test
    public void getPlayersByNameWhenPlayersFoundThenReturnFilledPlayersResponse() throws PubgApiClientException {
        Set<String> playerNames = new HashSet<>(asList("Jooones", "zwiep"));
        when(pubgApiCaller.getPlayersByName(playerNames, REGION)).thenReturn(twoPlayersSuccessResponse);

        PlayersResponse actual = pubgApiClient.getPlayersByName(playerNames, REGION);

        assertThat(actual.getPlayers()).hasSize(2);
        assertThat(actual.getPlayers()).extracting(player -> player.getPlayerAttributes().getName()).containsOnly("Jooones", "zwiep");
    }

    @Test
    public void getPlayersByNameWhenNoPlayersFoundThenReturnPlayersResponseWithEmptyPlayerList() throws PubgApiClientException {
        Set<String> playerNames = singleton(NON_EXISTING_PLAYER);
        when(pubgApiCaller.getPlayersByName(playerNames, REGION)).thenReturn(NO_PLAYERS_FOUND_RESPONSE);

        PlayersResponse actual = pubgApiClient.getPlayersByName(playerNames, REGION);

        assertThat(actual.getPlayers()).isEmpty();
    }

    @Test
    public void getPlayersByIdWhenPlayersFoundThenReturnFilledPlayersResponse() throws PubgApiClientException {
        Set<String> playerIds = new HashSet<>(asList("account.2b95c68272fd467db565f5134277993b", "account.b020ad1e124140c49f9ca3c5d47f99bb"));
        when(pubgApiCaller.getPlayersById(playerIds, REGION)).thenReturn(twoPlayersSuccessResponse);

        PlayersResponse actual = pubgApiClient.getPlayersById(playerIds, REGION);

        assertThat(actual.getPlayers()).hasSize(2);
        assertThat(actual.getPlayers()).extracting(Player::getId).containsOnly("account.2b95c68272fd467db565f5134277993b", "account.b020ad1e124140c49f9ca3c5d47f99bb");
    }

    @Test
    public void getPlayersByIdsWhenNoPlayersFoundThenReturnPlayersResponseWithEmptyPlayerList() throws PubgApiClientException {
        Set<String> playerIds = singleton(NON_EXISTING_PLAYER);
        when(pubgApiCaller.getPlayersById(playerIds, REGION)).thenReturn(NO_PLAYERS_FOUND_RESPONSE);

        PlayersResponse actual = pubgApiClient.getPlayersById(playerIds, REGION);

        assertThat(actual.getPlayers()).isEmpty();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void getMatchWhenMatchFoundThenReturnMatchResponse() throws PubgApiClientException {
        when(pubgApiCaller.getMatch(MATCH_ID, REGION)).thenReturn(matchSuccessResponse);

        Optional<MatchResponse> actual = pubgApiClient.getMatch(MATCH_ID, REGION);

        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getMatch().getId()).isEqualTo(MATCH_ID);
        assertThat(actual.get().getMatch().getMatchAttributes().getMapName()).isEqualTo("Erangel_Main");
        assertThat(actual.get().getParticipants()).extracting(participant -> participant.getParticipantAttributes().getParticipantStats().getName()).contains("Jooones");
    }

    @Test
    public void getMatchWhenNoMatchFoundThenReturnEmptyOptional() throws PubgApiClientException {
        when(pubgApiCaller.getMatch(MATCH_ID, REGION)).thenReturn(NO_MATCH_FOUND_RESPONSE);

        Optional<MatchResponse> actual = pubgApiClient.getMatch(MATCH_ID, REGION);

        assertThat(actual).isEmpty();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void getMatch_MatchResponseContainsAllRequiredObjects() throws PubgApiClientException {
        when(pubgApiCaller.getMatch(MATCH_ID, REGION)).thenReturn(matchSuccessResponseAdjustedStats);

        Optional<MatchResponse> actual = pubgApiClient.getMatch(MATCH_ID, REGION);

        assertContainsAllRequiredObjects(actual.get());
    }

    @SuppressWarnings("ConstantConditions")
    private void assertContainsAllRequiredObjects(MatchResponse actual) {
        Match actualMatchObject = actual.getMatch();
        assertThat(actualMatchObject.getMatchAttributes()).isNotNull();
        assertThat(actualMatchObject.getMatchAttributes().getShardId()).isEqualTo("pc-eu");
        assertThat(actualMatchObject.getMatchAttributes().getMapName()).isEqualTo("Erangel_Main");
        assertThat(actualMatchObject.getMatchAttributes().getCreatedAt()).isEqualTo(ZonedDateTime.parse("2018-05-10T11:15:00Z"));
        assertThat(actualMatchObject.getMatchAttributes().getDuration()).isEqualTo(1735);
        assertThat(actualMatchObject.getMatchAttributes().getGameMode()).isEqualTo("squad-fpp");
        assertThat(actualMatchObject.getMatchAttributes().getTitleId()).isEqualTo("bluehole-pubg");

        assertThat(actualMatchObject.getLinks().getSelf()).isEqualTo("https://api.playbattlegrounds.com/shards/pc-eu/matches/439b4e90-f9ea-49ef-bf62-00241492dffe");

        Participant actualParticipant = actual.getParticipants().stream().filter(participant -> participant.getId().equals("093d650a-22ed-4199-b33a-7394ccf4e741")).findAny().get();
        assertThat(actualParticipant.getParticipantAttributes()).isNotNull();
        assertThat(actualParticipant.getParticipantAttributes().getShardId()).isEqualTo("pc-eu");

        ParticipantStats actualParticipantStats = actualParticipant.getParticipantAttributes().getParticipantStats();
        assertThat(actualParticipantStats.getAssists()).isEqualTo(1);
        assertThat(actualParticipantStats.getBoosts()).isEqualTo(4);
        assertThat(actualParticipantStats.getDamageDealt()).isEqualTo(240.09);
        assertThat(actualParticipantStats.getDeathType()).isEqualTo("byplayer");
        assertThat(actualParticipantStats.getHeadShotKills()).isEqualTo(1);
        assertThat(actualParticipantStats.getHeals()).isEqualTo(7);
        assertThat(actualParticipantStats.getKillPlace()).isEqualTo(15);
        assertThat(actualParticipantStats.getKillPoints()).isEqualTo(1095);
        assertThat(actualParticipantStats.getKillPointsDelta()).isEqualTo(23.0170212);
        assertThat(actualParticipantStats.getKillStreaks()).isEqualTo(1);
        assertThat(actualParticipantStats.getKills()).isEqualTo(2);
        assertThat(actualParticipantStats.getLastKillPoints()).isEqualTo(1);
        assertThat(actualParticipantStats.getLastWinPoints()).isEqualTo(1);
        assertThat(actualParticipantStats.getLongestKill()).isEqualTo(32);
        assertThat(actualParticipantStats.getMostDamage()).isEqualTo(1.5);
        assertThat(actualParticipantStats.getName()).isEqualTo("Jooones");
        assertThat(actualParticipantStats.getPlayerId()).isEqualTo("account.2b95c68272fd467db565f5134277993b");
        assertThat(actualParticipantStats.getRevives()).isEqualTo(1);
        assertThat(actualParticipantStats.getRideDistance()).isEqualTo(1208.06);
        assertThat(actualParticipantStats.getRoadKills()).isEqualTo(1);
        assertThat(actualParticipantStats.getTeamKills()).isEqualTo(1);
        assertThat(actualParticipantStats.getTimeSurvived()).isEqualTo(1678);
        assertThat(actualParticipantStats.getVehicleDestroys()).isEqualTo(1);
        assertThat(actualParticipantStats.getWalkDistance()).isEqualTo(3966.58667);
        assertThat(actualParticipantStats.getWeaponsAcquired()).isEqualTo(1);
        assertThat(actualParticipantStats.getWinPlace()).isEqualTo(3);
        assertThat(actualParticipantStats.getWinPoints()).isEqualTo(1538);
        assertThat(actualParticipantStats.getWinPointsDelta()).isEqualTo(3.837533);

        Roster actualMatchRoster = actual.getRosters().stream().filter(roster -> roster.getId().equals("a32208bf-e02f-4e9a-954c-0ab528710d22")).findAny().get();
        assertThat(actualMatchRoster.getRosterAttributes().getShardId()).isEqualTo("pc-eu");
        assertThat(actualMatchRoster.getRosterAttributes().getRosterStats().getRank()).isEqualTo(21);
        assertThat(actualMatchRoster.getRosterAttributes().getRosterStats().getTeamId()).isEqualTo("8");
        assertThat(actualMatchRoster.getRosterAttributes().getWon()).isFalse();
        assertThat(actualMatchRoster.getRosterRelationships().getParticipantIds().getData()).isNotEmpty();

        Asset actualMatchAsset = actual.getAssets().stream().filter(asset -> asset.getId().equals("97f021bc-5447-11e8-b199-0a58647ba40b")).findAny().get();
        assertThat(actualMatchAsset.getAssetAttributes()).isNotNull();
        assertThat(actualMatchAsset.getAssetAttributes().getName()).isEqualTo("telemetry");
        assertThat(actualMatchAsset.getAssetAttributes().getDescription()).isEqualTo("");
        assertThat(actualMatchAsset.getAssetAttributes().getCreatedAt()).isEqualTo(ZonedDateTime.parse("2018-05-10T11:45:10Z"));
        assertThat(actualMatchAsset.getAssetAttributes().getUrl()).isEqualTo("https://telemetry-cdn.playbattlegrounds.com/bluehole-pubg/pc-eu/2018/05/10/11/45/97f021bc-5447-11e8-b199-0a58647ba40b-telemetry.json");

        assertThat(actual.getLinks().getSelf()).isEqualTo("https://api-origin.playbattlegrounds.com/shards/pc-eu/matches/439b4e90-f9ea-49ef-bf62-00241492dffe");
    }
}