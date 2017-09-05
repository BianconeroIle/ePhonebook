package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class User implements Serializable {

    private String gender;
    private String email;
    private String dob;
    private String registered;
    private String phone;
    private String cell;
    private String nat;

    private Name name;
    private Location location;
    private Picture picture;
    private Login login;
    private ID id;


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    @Override
    public String toString() {
        return "User{" +
                "gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", registered='" + registered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", nat='" + nat + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", picture=" + picture +
                ", login=" + login +
                ", id=" + id +
                '}';
    }
}
