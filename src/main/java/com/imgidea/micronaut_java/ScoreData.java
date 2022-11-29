package com.imgidea.micronaut_java;

public class ScoreData {

    public String topscore_id;
    public  String name;
    public  String score;
    public  String datetime;

    public ScoreData(){
    }

    public ScoreData(String topscore_id, String name, String score, String datetime) {
        this.topscore_id = topscore_id;
        this.name = name;
        this.score = score;
        this.datetime = datetime;
    }
}
