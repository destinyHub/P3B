package com.example.i16092.tugasuasp3b;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGamePage extends Fragment implements View.OnClickListener,View.OnTouchListener, TimerListener{
    ListenerGamePage listener;
    Activity act;
    LinearLayout layout;

    ImageView ivCanvas;
    Bitmap mBitmap;
    Canvas mCanvas;
    Engine e;
    CountDownTimer cdt;
    TextView tvScore,tvTime;
    int mColorBackground,player, width, height;
    Paint paint;

    public FragmentGamePage() {
        // Required empty public constructor
    }

    public static FragmentGamePage newInstance(ListenerGamePage listener, int mColorBackground, Engine e, int width, int height){//activity yg implements listener, seolah olah jd 1 kelas dgn listener
        FragmentGamePage result = new FragmentGamePage();
        result.listener= listener; //pake kelas listener utk sambungin fragment ke activity yg implement listener ini
        result.act = listener.getActivity(); //dptin data dr activity ngandelin metod yg ada di listener
        result.paint = listener.paint();

        result.mColorBackground = mColorBackground;

        result.player = 0;
        result.layout = result.listener.getLinearLayout();
        result.e = e;
        result.width = width; result.height = height;

        //CUT PASTE
        result.mBitmap = Bitmap.createBitmap(result.width, result.height, Bitmap.Config.ARGB_4444);
        //System.out.println("BITMAP SIZE: Lebar:"+result.width+" Tinggi:"+result.height);

        return result;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_fragment_game_page,null,false); //inflate fragment xml ke activity
        this.tvScore = result.findViewById(R.id.tvScore);
        this.tvTime = result.findViewById(R.id.tvTime);
        this.ivCanvas = result.findViewById(R.id.ivCanvas);
        this.cdt = new Timer(this);

        this.ivCanvas.setScaleType(ImageView.ScaleType.CENTER);
        this.ivCanvas.setImageBitmap(this.mBitmap);
        this.mCanvas = new Canvas(this.mBitmap);
        this.clearCanvas();

        //Engine Part Initial+Timer (Engine perantara Main Activity dan Pencatat Lokasi Images)
        //this.e = new Engine(this.act, paint, this.layout.getWidth(), this.layout.getHeight(),this);
        this.e.randomCreateObject();
        this.cdt.start();

        //Listener
        this.ivCanvas.setOnTouchListener(this);

        return result;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int corX = (int) event.getX();
        int corY = (int) event.getY();

        if(v == this.ivCanvas){  //int idxItem = pli.getIdx(corX,corY);//this.pli.printAllXY();

        }
        return false;
    }

    public void clearCanvas(){
        this.mCanvas.drawColor(this.mColorBackground);
        this.ivCanvas.invalidate();
    }

    @Override
    public void setTime(String input) {
        this.tvTime.setText(input);
    }

    @Override
    public void isFinished(){
        //this.player++; //this.listener.setListView("  Player "+this.player+" : "+this.e.getScore()+"");
        //this.listener.addScore(this.e.getScore(),"  Player "+this.player+" : ");
        this.tvScore.setText("Score: "+100);
        this.listener.stopSensor();
        this.cdt.cancel();
        this.e.resetAll();
        this.clearCanvas();

        this.listener.changeToStartPage();
    }

    public void drawCircle(float x, float y, int radius, Paint paint, int idxDigambar){
        this.e.cekGoal(x, y, idxDigambar);
        this.mCanvas.drawCircle(x, y, radius, paint);
    }

    public void invalidateImageView(){
        this.ivCanvas.invalidate();
    }
}
