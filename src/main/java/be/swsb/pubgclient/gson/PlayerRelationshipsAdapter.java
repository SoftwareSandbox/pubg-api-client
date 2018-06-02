package be.swsb.pubgclient.gson;

import be.swsb.pubgclient.model.DataList;
import be.swsb.pubgclient.model.player.MatchId;
import be.swsb.pubgclient.model.player.PlayerRelationships;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;

public class PlayerRelationshipsAdapter extends TypeAdapter<PlayerRelationships> {

    private final Gson gson;

    public PlayerRelationshipsAdapter(Gson gson) {
        this.gson = gson;
    }


    @Override
    public void write(JsonWriter writer, PlayerRelationships playerRelationships) throws IOException {
        if (playerRelationships == null) {
            writer.nullValue();
        } else {
            writer.setIndent("  ");
            writer.beginObject();
            writeAssets(writer);
            writeMatchIds(writer, playerRelationships);
            writer.endObject();
        }

    }

    private void writeAssets(JsonWriter writer) throws IOException {
        writer
            .name("assets")
            .beginObject()
                .name("data")
                .beginArray()
                .endArray()
            .endObject();
}

    private void writeMatchIds(JsonWriter writer, PlayerRelationships playerRelationships) throws IOException {
        writer.name("matches");
        writer.beginObject();
        writer.name("data");
        writer.beginArray();

        DataList<MatchId> matchIds = playerRelationships.getMatchIds();
        List<MatchId> matchIdsData = matchIds.getData();
        if(matchIdsData != null) {
            matchIdsData.forEach((matchId) -> writeMatchId(writer, matchId));
        }

        writer.endArray();
        writer.endObject();
    }

    private void writeMatchId(JsonWriter writer, MatchId matchId) {
        try {
            JsonElement jsonElement = gson.toJsonTree(matchId);
            jsonElement.getAsJsonObject().addProperty("type", "match");
            Streams.write(jsonElement, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerRelationships read(JsonReader reader) throws IOException {
        if(reader.peek() == null) {
            reader.nextNull();
            return null;
        }
        PlayerRelationships playerRelationships = new PlayerRelationships();
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.nextName()) {
                case "assets": readAssets(reader, playerRelationships); break;
                case "matches": readMaches(reader, playerRelationships); break;
            }
        }
        reader.endObject();
        return playerRelationships;
    }

    private void readMaches(JsonReader reader, PlayerRelationships playerRelationships) throws IOException {
        reader.beginObject();
        reader.nextName();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.skipValue();
        }
        reader.endArray();
        reader.endObject();
    }


    private void readAssets(JsonReader reader, PlayerRelationships playerRelationships) throws IOException {
        reader.beginObject();
        reader.nextName();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.skipValue();
        }
        reader.endArray();
        reader.endObject();
    }
}
