package be.swsb.pubgclient.gson;

import be.swsb.pubgclient.AbstractUnitTest;
import be.swsb.pubgclient.model.DataList;
import be.swsb.pubgclient.model.player.MatchId;
import be.swsb.pubgclient.model.player.PlayerRelationships;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerRelationshipsAdapterTest extends AbstractUnitTest {


    private PlayerRelationshipsAdapter playerRelationshipsAdapter;
    private JsonTreeWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonParser jsonParser;
    private FileReader fileReader;
    private PlayerRelationships playerRelationships;
    private Gson gson;
    private MatchId matchId1;
    private MatchId matchId2;

    @Before
    public void setUp() {
        jsonWriter = new JsonTreeWriter();
        jsonParser = new JsonParser();
        gson = new Gson();
        playerRelationshipsAdapter = new PlayerRelationshipsAdapter(gson);
        playerRelationships = new PlayerRelationships();
        matchId1 = new MatchId("id1");
        matchId2 = new MatchId("id2");
    }


    @Test
    public void write_whenEmptyMatches_thenCreateJsonWithEmptyMatchesData() {
        String file = readFile("relationships_samples/emptyMatches.json");
        JsonElement expectedJsonElement = jsonParser.parse(file);
        JsonElement actualJsonElement = playerRelationshipsAdapter.toJsonTree(playerRelationships);

        assertThat(actualJsonElement).isEqualTo(expectedJsonElement);
    }

    @Test
    public void write_whenTwoMatchesPresent_thenCreateJsonWithTwoMatches() {
        matchId1.setId("439b4e90-f9ea-49ef-bf62-00241492dffe");
        matchId2.setId("9a6459d0-5b58-476f-9111-29893a794d67");
        ArrayList<MatchId> matchIds = Lists.newArrayList(matchId1, matchId2);
        playerRelationships.setMatchIds(new DataList<>(matchIds));

        String file = readFile("relationships_samples/twoMatches.json");
        JsonElement expectedJsonElement = jsonParser.parse(file);
        JsonElement actualJsonElement = playerRelationshipsAdapter.toJsonTree(playerRelationships);

        assertThat(actualJsonElement).isEqualTo(expectedJsonElement);
    }

    @Test
    public void read_whenEmptyMatches_thenCreatePlayerRelationshipsWithNoMatchIds() throws IOException {
        String file = readFile("relationships_samples/emptyMatches.json");
        jsonReader = new JsonTreeReader(jsonParser.parse(file));
        PlayerRelationships expected = new PlayerRelationships();

        PlayerRelationships actual = playerRelationshipsAdapter.read(jsonReader);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void read_whenTwoMatchesPresent_thenCreatePlayerRelationShipsWithTwoMatches() throws IOException {
        matchId1.setId("439b4e90-f9ea-49ef-bf62-00241492dffe");
        matchId2.setId("9a6459d0-5b58-476f-9111-29893a794d67");
        ArrayList<MatchId> matchIds = Lists.newArrayList(matchId1, matchId2);
        playerRelationships.setMatchIds(new DataList<>(matchIds));
        String file = readFile("relationships_samples/twoMatches.json");
        jsonReader = new JsonTreeReader(jsonParser.parse(file));

        PlayerRelationships actual = playerRelationshipsAdapter.read(jsonReader);

        assertThat(actual).isEqualTo(playerRelationships);
    }

    @Test
    public void readWrite_shouldReturnSameJson() throws IOException {

        String file = readFile("relationships_samples/fiveMatches.json");
        JsonElement expectedJsonElement = jsonParser.parse(file);

        jsonReader = new JsonTreeReader(expectedJsonElement);
        PlayerRelationships read = playerRelationshipsAdapter.read(jsonReader);
        JsonElement actualJsonElement = playerRelationshipsAdapter.toJsonTree(read);

        assertThat(actualJsonElement).isEqualTo(expectedJsonElement);
    }
}