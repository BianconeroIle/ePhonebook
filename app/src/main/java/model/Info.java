package model;

import java.io.Serializable;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class Info implements Serializable{

    private String seed;
    private int results;
    private int page;
    private String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Info{" +
                "seed='" + seed + '\'' +
                ", results=" + results +
                ", page=" + page +
                ", version='" + version + '\'' +
                '}';
    }
}
