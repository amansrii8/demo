package com.example.dell.demoforarrk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StarWarDetailResponseModel {

    @Expose
    @SerializedName("count")
    private int count;

    @Expose
    @SerializedName("next")
    private String next;

    @Expose
    @SerializedName("previous")
    private String previous;

    @Expose
    @SerializedName("results")
    private ArrayList<Results> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
