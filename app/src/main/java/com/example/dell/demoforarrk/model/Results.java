package com.example.dell.demoforarrk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Results implements Serializable {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("height")
    private String height;

    @Expose
    @SerializedName("mass")
    private String mass;

    @Expose
    @SerializedName("hair_color")
    private String hairColor;

    @Expose
    @SerializedName("skin_color")
    private String skinColor;

    @Expose
    @SerializedName("eye_color")
    private String eyeColor;


    @Expose
    @SerializedName("birth_year")
    private String birthYear;

    @Expose
    @SerializedName("gender")
    private String gender;

    @Expose
    @SerializedName("homeworld")
    private String homeWorld;

    @Expose
    @SerializedName("films")
    private ArrayList<String> films;

    @Expose
    @SerializedName("species")
    private ArrayList<String> species;

    @Expose
    @SerializedName("vehicles")
    private ArrayList<String> vehicles;

    @Expose
    @SerializedName("starships")
    private ArrayList<String> starShips;

    @Expose
    @SerializedName("created")
    private String created;

    @Expose
    @SerializedName("edited")
    private String edited;

    @Expose
    @SerializedName("url")
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<String> films) {
        this.films = films;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList<String> species) {
        this.species = species;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<String> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<String> getStarShips() {
        return starShips;
    }

    public void setStarShips(ArrayList<String> starShips) {
        this.starShips = starShips;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
