package be.swsb.pubgclient.model.match.roster;

import be.swsb.pubgclient.model.DataList;
import com.google.gson.annotations.SerializedName;

public class RosterRelationships {

    @SerializedName("participants")
    private DataList<ParticipantId> participantIds;

    public DataList<ParticipantId> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(DataList<ParticipantId> participantIds) {
        this.participantIds = participantIds;
    }

    @Override
    public String toString() {
        return "RosterRelationships{" +
                "participantIds=" + participantIds +
                '}';
    }
}
