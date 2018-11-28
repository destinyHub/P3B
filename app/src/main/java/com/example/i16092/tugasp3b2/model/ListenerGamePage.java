package com.example.i16092.tugasp3b2.model;

import android.app.Activity;
import android.graphics.Paint;
import android.widget.LinearLayout;

public interface ListenerGamePage {
    public Activity getActivity();
    public Paint paint();
    public void changeToStartPage();
    public void addScore(int Score, String nama);
    public void save();
    public LinearLayout getLinearLayout();
    public void stopSensor();
    public void drawCircle(float x, float y, int radius, Paint p, int idxDigambar);
    public void invalidateImageView();
    public void clearCanvas();
}
