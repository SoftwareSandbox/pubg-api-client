package com.softwaresandbox.pubgclient.model.match;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.Links;
import com.softwaresandbox.pubgclient.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class MatchResponse {

    @SerializedName("data")
    private Match match;
    // TODO how to fix this?
//    @SerializedName("included")
//    private List<Roster and Participant> rosterAndParticipant = new ArrayList<>();
    @SerializedName("links")
    private Links links;

}
