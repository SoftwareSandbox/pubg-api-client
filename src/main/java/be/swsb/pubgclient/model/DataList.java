package be.swsb.pubgclient.model;

import java.util.List;

// TODO improve this, make this class go away?
public class DataList<T> {

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "data=" + data +
                '}';
    }
}
