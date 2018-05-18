package com.softwaresandbox.pubgclient.model.match;

import com.google.gson.annotations.SerializedName;
import com.softwaresandbox.pubgclient.model.Links;

public class Match {

    private String id;
    @SerializedName("attributes")
    private MatchAttributes matchAttributes;
    @SerializedName("relationships")
    private MatchRelationships matchRelationships;
    private Links links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MatchAttributes getMatchAttributes() {
        return matchAttributes;
    }

    public void setMatchAttributes(MatchAttributes matchAttributes) {
        this.matchAttributes = matchAttributes;
    }

    public MatchRelationships getMatchRelationships() {
        return matchRelationships;
    }

    public void setMatchRelationships(MatchRelationships matchRelationships) {
        this.matchRelationships = matchRelationships;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", matchAttributes=" + matchAttributes +
                ", matchRelationships=" + matchRelationships +
                ", links=" + links +
                '}';
    }
}
