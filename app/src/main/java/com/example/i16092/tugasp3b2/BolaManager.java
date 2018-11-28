package com.example.i16092.tugasp3b2;

import java.util.LinkedList;

public class BolaManager {
    LinkedList<Bola> kumpulanBola;
    int counter;

    public BolaManager(){
        this.kumpulanBola = new LinkedList<Bola>(); //bola yg paling pertama itu teh goalnya
        this.counter = -1;
    }

    public void addObject(float x, float y, int radius,int warna){
        this.counter++; //ngitung jumlah bola
        Bola b = new Bola(x,y,radius,warna);
        this.kumpulanBola.add(b);
    }


    /**
     * Method ini digunakan untuk merandom objek agar tidak bertabrakan dan menimpa
     * @param x
     * @param y
     * @return
     */
    public int getIdxCollision(int x, int y){//klo bkn -1 ada nilainya
        int res = -1;//-1 klo x,y ga ada
        for (int i=0; i<this.kumpulanBola.size();i++){
            if(Math.abs(this.kumpulanBola.get(i).getX()-x) <= this.kumpulanBola.get(i).getRadiusKlik()*2){
                if(Math.abs(this.kumpulanBola.get(i).getY()-y) <= this.kumpulanBola.get(i).getRadiusKlik()*2){
                    res = i; //Log.d("TESTING collision 1", "idx: "+i+" Collision: "+this.kumpulanBola.get(i).getX()+" "+this.kumpulanBola.get(i).getY()+" Param: "+x+" "+y);
                }
            }
            if(Math.abs(this.kumpulanBola.get(i).getY()-y) <= this.kumpulanBola.get(i).getRadiusKlik()*2){
                if(Math.abs(this.kumpulanBola.get(i).getX()-x) <= this.kumpulanBola.get(i).getRadiusKlik()*2){
                    res = i; //Log.d("TESTING collision 2", "idx: "+i+" Collision: "+this.kumpulanBola.get(i).getX()+" "+this.kumpulanBola.get(i).getY()+" Param: "+x+" "+y);
                }
            }
        }

        return res;
    }

    /**
     * Method ini digunakan untuk mengeset toleransi batas klik objek
     * jika klik diluar objek yang benar maka tidak akan dideteksi.
     * @param x
     * @param y
     * @return
     */
    public int getIdxKlik(float x, float y){
        int res = -1;//-1 klo x,y ga ada
        for (int i=0; i<this.kumpulanBola.size();i++){
            if(Math.abs(this.kumpulanBola.get(i).getX()-x) <= this.kumpulanBola.get(i).getRadiusKlik()){
                if(Math.abs(this.kumpulanBola.get(i).getY()-y) <= this.kumpulanBola.get(i).getRadiusKlik()){
                    res = i; //Log.d("TESTING klik 1", "idx: "+i+" Collision: "+this.kumpulanBola.get(i).getX()+" "+this.kumpulanBola.get(i).getY()+" Param: "+x+" "+y);
                }
            }
            if(Math.abs(this.kumpulanBola.get(i).getY()-y) <= this.kumpulanBola.get(i).getRadiusKlik()){
                if(Math.abs(this.kumpulanBola.get(i).getX()-x) <= this.kumpulanBola.get(i).getRadiusKlik()){
                    res = i; //Log.d("TESTING klik 2", "idx: "+i+" Collision: "+this.kumpulanBola.get(i).getX()+" "+this.kumpulanBola.get(i).getY()+" Param: "+x+" "+y);
                }
            }
        }


        return res;
    }


    public void clear(){
        this.kumpulanBola.clear();
    }

    public LinkedList<Bola> getKumpulanBola(){
        return this.kumpulanBola;
    }
}
