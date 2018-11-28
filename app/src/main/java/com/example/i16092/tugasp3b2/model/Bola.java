package com.example.i16092.tugasp3b2.model;

public class Bola {
    protected  float x,y;
    protected int radiusKlik,warna;

    public Bola(float x, float y, int radius,int warna){
        this.x = x;
        this.y = y;
        this.radiusKlik = radius;
        this.warna = warna;
    }

    /**
     *
     * @param
     * @return
     */
    public int getWarna(){
        return this.warna;
    }
    public int getRadiusKlik() { return this.radiusKlik; }
    public float getX() { return this.x; }
    public float getY() { return this.y; }
}
