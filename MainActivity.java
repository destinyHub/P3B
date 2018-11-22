package com.example.stevin.tugasbesaruas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ListenerGamePage{
    LinearLayout layout,fragContainer;
    Paint paint, paintText;
    int mColorBackground, mColorBackground2,white;
    int highScore=0;
    Engine e;
    Button btnPlay,btnExit;

    FragmentManager fm;
    FragmentTransaction ft;
    FragmentGamePage fgp;
    ScoreManager sm;

    ImageView ivCanvas, ivTarget;
    Bitmap mBitmap, targetBitmap;
    Canvas mCanvas, targetCanvas;

    BolaManager bm;
    SensorReader sr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.layout = findViewById(R.id.layout);
        this.fragContainer = findViewById(R.id.fragContainer);
        this.btnPlay = findViewById(R.id.btnPlay);
        this.btnExit = findViewById(R.id.btnExit);

        //Paint Initial
        int mColorTest = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        int mColorTest2 = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        this.white = ResourcesCompat.getColor(getResources(), R.color.white, null);
        this.paint = new Paint();
        paint.setColor(mColorTest);
        this.paintText = new Paint();
        paintText.setColor(mColorTest2);

        this.mColorBackground = ResourcesCompat.getColor(getResources(), R.color.white, null);
        this.mColorBackground2 = ResourcesCompat.getColor(getResources(), R.color.moccasin, null);

        //Atributes
        this.fm = this.getSupportFragmentManager();
        //this.psl = new PenyimpanScoreList(this);
        this.bm = new BolaManager();
        //this.psl.clearAll();
        this.sm = new ScoreManager();
        this.sr = new SensorReader(this);
        //load();
        //firstInitiate();

        this.btnPlay.setOnClickListener(this);
        this.btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == this.btnPlay){

            this.e = new Engine(this, this.paint, this.layout.getWidth(), this.layout.getHeight());
            this.fgp = FragmentGamePage.newInstance(this,this.mColorBackground, this.e, this.layout.getWidth(), this.layout.getHeight());
            this.ft = this.fm.beginTransaction();
            this.ft.replace(R.id.fragContainer,this.fgp);
            this.ft.commit();

            this.sr.start();
        }
        else if(v == this.btnExit){
            System.exit(0);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Paint paint() {
        return this.paint;
    }

    @Override
    public void changeToStartPage() {

    }

    @Override
    public void addScore(int score, String nama) {
        //ini masukin ke score manager
        this.sm.addPlayersScore(score, nama);
    }

    @Override
    public void save() {

    }

    @Override
    public LinearLayout getLinearLayout() {
        return this.layout;
    }

    public void updatePhoneStatus(float x, float y){
        this.e.movePlayerCircle(x,y);
    }

    @Override
    public void stopSensor(){
        this.sr.stop();
    }

    @Override
    public void drawCircle(float x, float y, int radius, Paint p, int idxDigambar) {
        this.fgp.drawCircle(x,y,radius,p,idxDigambar);
    }

    @Override
    public void invalidateImageView() {
        this.fgp.invalidateImageView();
    }

    @Override
    public void clearCanvas() {
        this.fgp.clearCanvas();
    }

    public int getScoreListSize(){
        return this.sm.getScoreListSize();
    }

    public void stopGame(){
        this.fgp.isFinished();
    }
}
