package com.softwaresandbox.pubgclient.model;

import java.util.List;

public class DataList<T> {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
