package be.swsb.pubgclient.gson;

import be.swsb.pubgclient.model.player.MatchId;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import be.swsb.pubgclient.model.player.PlayerRelationships;

public class PlayerRelationShipsAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if(type.getRawType() != PlayerRelationships.class) {
            return null;
        } else {
            return (TypeAdapter<T>) new PlayerRelationshipsAdapter(gson.getAdapter(MatchId.class));
        }

    }
}
