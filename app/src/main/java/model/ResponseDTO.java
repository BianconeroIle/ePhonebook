package model;


import java.util.List;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class ResponseDTO {

    private List<User> results;

    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "ResponseDTO{" +
                "results=" + results +
                ", info=" + info +
                '}';
    }
}
