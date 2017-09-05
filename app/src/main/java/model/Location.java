package model;

import java.io.Serializable;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class Location implements Serializable {

    private String street;
    private String ciy;
    private String state;
    private String postcode;
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCiy() {
        return ciy;
    }

    public void setCiy(String ciy) {
        this.ciy = ciy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", ciy='" + ciy + '\'' +
                ", state='" + state + '\'' +
                ", postcode=" + postcode +
                '}';
    }

    public String getCity() {
        return city;
    }
}
