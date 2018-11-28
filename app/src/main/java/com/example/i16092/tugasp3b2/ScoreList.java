package com.example.i16092.tugasp3b2;

public class ScoreList implements Comparable<ScoreList>{
    int score;
    String nama;

    public ScoreList(int score, String nama){
        this.score = score;
        this.nama = nama;
    }

    @Override
    public int compareTo(ScoreList b) {
        if(this.score<b.getScore()){
            return 1;
        }
        else if(this.score>b.getScore()){
            return -1;
        }
        else{
            return 0;
        }
    }

    public int getScore(){
        return this.score;
    }
    public String getNama(){
        return this.nama;
    }
}
