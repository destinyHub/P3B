package com.example.i16092.tugasp3b2;

import android.os.CountDownTimer;

public class Timer extends CountDownTimer{
    TimerListener tl;
    //MainActivity act;
    CountDownTimer cdt;

    public Timer(TimerListener tl){
        super(100000,1000);
        //this.act = act;
        this.tl = tl;
    }

    @Override
    public void onTick(long millisUntilFinished) { //ini auto update ketika timer berjalan
        this.tl.setTime( millisUntilFinished/1000 + " Seconds remaining " );
    }

    @Override
    public void onFinish() {
        //this.tl.setTime("Done!");
        this.tl.isFinished();
    }
}
