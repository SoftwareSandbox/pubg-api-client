package be.swsb.pubgclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO improve this, make this class go away?
public class DataList<T> {

    public DataList(){
        this(new ArrayList<>());
    }

    public DataList(List<T> data){
        this.data = data;
    }

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataList<?> dataList = (DataList<?>) o;
        return Objects.equals(data, dataList.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(data);
    }

    @Override

    public String toString() {
        return "DataList{" +
                "data=" + data +
                '}';
    }
}
