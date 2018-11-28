package com.example.i16092.tugasuasp3b;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Random;

public class Engine {
    MainActivity act;
    Paint paint;
    Random rand;
    BolaManager bm;
    float x,y,speedX,speedY;
    int score, width, height;
    int black, lime, red, yellow, colorAccent;

    public Engine(MainActivity act, Paint p, int width, int height){
        this.act = act;
        this.rand = new Random();

        this.paint = p;

        this.bm = new BolaManager();
        this.x = 10; this.y = 10; this.score = 0;
        this.width = width; this.height = height;
        this.speedX = 0; this.speedY = 0;

        this.setColor();
    }

    public void randomCreateObject(){ //first create
        int jmlhObj = 2;
        int radius = 100;

        for(int i=0; i<jmlhObj; i++){
            this.randomPosition(radius);
            int randomColor = this.rand.nextInt(4)+1;
            if(i != 0) {
                this.bm.addObject(this.x, this.y, radius, randomColor);
                this.randomColorDrawCircle(radius, randomColor,1); Log.d("GOAL POSITION",x+" "+y);
            }
            else{ //utk lingkaran pertama/goal
                this.bm.addObject(this.x, this.y, radius, this.black);
                this.paint.setColor(this.black);
                this.act.drawCircle(this.x, this.y, radius, this.paint,0); Log.d("Player POSITION",x+" "+y);
            }

        }
        this.act.invalidateImageView();
    }

    public void randomPosition(int radius){
        int ranX = this.rand.nextInt(this.width-radius*2) +radius;
        int ranY = this.rand.nextInt(this.height-radius*2)+radius;

        while(bm.getIdxCollision(ranX,ranY) != -1){ //selama masih ada yang collision, di random sampe -1/ga ada
            ranX = this.rand.nextInt(this.width-radius*2)+radius;
            ranY = this.rand.nextInt(this.height-radius*2)+radius;
        }
        this.x = ranX;
        this.y = ranY;
    }

    public void randomColorDrawCircle(int radius, int randomColor, int idxDigambar){
        float x = this.x;
        float y = this.y;
        this.setPaintColor(randomColor);

        this.act.drawCircle(x,y,radius,paint, idxDigambar);
    }

    public void resetXY(){
        this.x = 10; this.y = 10; this.speedX = 0; this.speedY = 0;
    }
    public void resetScore(){
        this.score = 0;
    }
    public void resetBM(){ //reset ball manager
        this.bm.clear();
    }
    public void resetAll(){
        this.resetXY(); this.resetBM(); this.resetScore();
    }

    public void movePlayerCircle(float x, float y){ // x y teh percepatan dari accelerometer
        this.act.clearCanvas(); //suruh main act clear canvas si fragment
        LinkedList<Bola> kumpulan = this.bm.getKumpulanBola();
        float xGoal = kumpulan.get(0).getX();
        float yGoal = kumpulan.get(0).getY();
        float xPlayer = kumpulan.get(1).getX(); //kordinat x dr bola si player
        float yPlayer = kumpulan.get(1).getY(); //kordinat y dr bola si player

        this.speedX -= x*5; this.speedY += y*5;

        boolean border = isBorder(xPlayer+this.speedX, yPlayer+this.speedY,100);
        if(!border) {
            //System.out.println("NOT BORDER");
        }
        else if(border){
            //System.out.println("ISBORDER");
            this.speedX += x; this.speedY -= y;
        }
        xPlayer += this.speedX;
        yPlayer += this.speedY;

        this.paint.setColor(this.black);
        this.act.drawCircle(xGoal, yGoal, 100, this.paint, 0);

        int color = kumpulan.get(1).getWarna();
        this.setPaintColor(color); //Log.d("movePlayer", "SUKSES "+xPlayer+" "+yPlayer);

        this.act.drawCircle(xPlayer, yPlayer, 100, this.paint, 1);
    }

    public void setColor(){
        this.black = ResourcesCompat.getColor(this.act.getResources(), R.color.black, null);
        this.lime = ResourcesCompat.getColor(this.act.getResources(), R.color.lime, null);
        this.red = ResourcesCompat.getColor(this.act.getResources(), R.color.red, null);
        this.yellow = ResourcesCompat.getColor(this.act.getResources(), R.color.yellow, null);
        this.colorAccent = ResourcesCompat.getColor(this.act.getResources(), R.color.colorAccent, null);
    }

    public void setPaintColor(int input){
        if(input == 1){
            paint.setColor(this.lime);
        }
        else if(input == 2){
            paint.setColor(this.red);
        }
        else if(input == 3){
            paint.setColor(this.yellow);
        }
        else{
            paint.setColor(this.colorAccent);
        }
    }

    public void cekGoal(float x, float y, int idxDigambar){
        if(idxDigambar == 1) { //pas gambar si 0 lewat, periksa pas draw circle player
            int res = this.bm.getIdxKlik(x, y);
            if (res == 0) { //cek player/si 1 nabrak ato ga sama 0
                this.act.addScore(100, "Player : " + (this.act.getScoreListSize() + 1));
                Log.d("GOAL", "REACHED " + res + " X:" + x + " Y:" + y);
                this.act.stopGame();
            }
        }
    }

    public boolean isBorder(float x, float y, int radius){
        boolean res = false;
        int lebar = this.act.layout.getWidth();
        int tinggi = this.act.layout.getHeight();

        if(x<0+radius || x>lebar-radius){
            res = true; //System.out.println("isBorder X:"+x+" Y:"+y+" Lebar:"+lebar+" Tinggi:"+tinggi);
        }
        if(y<0+radius || y>tinggi-radius){
            res = true; //System.out.println("isBorder X:"+x+" Y:"+y+" Lebar:"+lebar+" Tinggi:"+tinggi);
        }

        return res;
    }
}
