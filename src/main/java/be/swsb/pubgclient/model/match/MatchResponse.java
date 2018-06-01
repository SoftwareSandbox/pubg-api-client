package be.swsb.pubgclient.model.match;

import be.swsb.pubgclient.model.Links;
import be.swsb.pubgclient.model.match.asset.Asset;
import be.swsb.pubgclient.model.match.participant.Participant;
import be.swsb.pubgclient.model.match.roster.Roster;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class MatchResponse {

    @SerializedName("data")
    private Match match;
    @SerializedName("included")
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<ParticipantRosterAsset> participantRosterAssetList = new ArrayList<>();
    @SerializedName("links")
    private Links links;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<Participant> getParticipants() {
        return this.participantRosterAssetList.stream()
                .filter(participantRosterAsset -> participantRosterAsset instanceof Participant)
                .map(participantRosterAsset -> (Participant) participantRosterAsset)
                .collect(toList());
    }

    public List<Roster> getRosters() {
        return this.participantRosterAssetList.stream()
                .filter(participantRosterAsset -> participantRosterAsset instanceof Roster)
                .map(participantRosterAsset -> (Roster) participantRosterAsset)
                .collect(toList());
    }

    public List<Asset> getAssets() {
        return this.participantRosterAssetList.stream()
                .filter(participantRosterAsset -> participantRosterAsset instanceof Asset)
                .map(participantRosterAsset -> (Asset) participantRosterAsset)
                .collect(toList());
    }

    @Override
    public String toString() {
        return "MatchResponse{" +
                "match=" + match +
                ", participants=" + getParticipants() +
                ", rosters=" + getRosters() +
                ", assets=" + getAssets() +
                ", links=" + links +
                '}';
    }
}
