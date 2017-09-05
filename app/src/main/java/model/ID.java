package model;

import java.io.Serializable;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class ID implements Serializable {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ID{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
