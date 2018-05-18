package com.softwaresandbox.pubgclient.model;

public class Link {

    private String self;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @Override
    public String toString() {
        return "Link{" +
                "self='" + self + '\'' +
                '}';
    }
}
