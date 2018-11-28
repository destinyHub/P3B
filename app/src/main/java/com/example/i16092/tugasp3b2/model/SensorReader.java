package com.example.i16092.tugasp3b2.model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.i16092.tugasp3b2.view.MainActivity;

public class SensorReader implements SensorEventListener {
    SensorManager manager;
    Sensor accelerometer,magnetometer;
    MainActivity ma;
    float x,y,z,vector; //accelerometer
    int counter;
    float[] xy;

    public SensorReader(MainActivity ma){
        this.ma = ma;
        this.manager = (SensorManager) this.ma.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = this.manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.counter = 0;
        this.vector = 0;
        this.xy = new float[2];
    }

    public void start(){
        this.manager.registerListener(this,this.accelerometer,SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop(){
        this.manager.unregisterListener(this,this.accelerometer);
    }

    public void perubahanPosisi(float[] input){ //cek diam ato ga
        this.xy = this.prosesToXY(input[0], input[1], input[2]);
        this.ma.updatePhoneStatus(xy[0],xy[1]);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == this.accelerometer){
            this.perubahanPosisi(event.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float[] prosesToXY(float x, float y, float z){ //xyz saat terdeksi gerakan
        float[] res = new float[2];
        res[0] = (float)Math.ceil(x);
        res[1] = (float)Math.ceil(y);

        return res;
    }

}
