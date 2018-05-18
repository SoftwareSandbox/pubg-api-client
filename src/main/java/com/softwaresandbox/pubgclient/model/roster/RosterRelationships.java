package com.softwaresandbox.pubgclient.model.roster;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.DataList;

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
