package com.example.i16092.tugasuasp3b;

import java.util.PriorityQueue;

public class ScoreManager {
    private int highScore;
    private PriorityQueue<ScoreList> scoreList;
    ScoreList[] res;

    public ScoreManager(){
        this.highScore = 0;
        this.scoreList = new PriorityQueue<ScoreList>();
        this.res = new ScoreList[0];
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore() {
        int tempHigh = this.scoreList.peek().getScore();
        if(this.highScore < tempHigh) this.highScore = tempHigh;
    }

    public void setHighScore(int score) {
        this.highScore = score;
    }

    public PriorityQueue<ScoreList> getPlayerScore(){ //Log.d("PQ ",this.scoreList.size()+"");
        return this.scoreList;

    }
    public void addPlayersScore(int score,String name){
        ScoreList sl = new ScoreList(score,name);
        this.scoreList.add(sl);
        this.setHighScore();
    }

    public int getScoreListSize(){
        return this.scoreList.size();
    }

    public void clearAll(){
        this.scoreList = new PriorityQueue<ScoreList>();
    }
}
