package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    int varRandMole;
    TextView mTimeView, mScoreView;

    private int varScore = 0;
    final Handler handler = new Handler();
    private int maxTime = 60*1000;
    private long stepTime = 1*1000;
    public int timeInterval = 1000; //delay
    public int moleUpTime = 350;
    public CountDownTimer myTimer = new myTimer(maxTime, stepTime);
    public MediaPlayer mPlayerWhack;
    public MediaPlayer mPlayerMiss;
    public String currentDiff = "Easy";
    public ImageView moleClick[] = new ImageView[9];
    public int yValue;
    ImageView imageMole1, imageMole2, imageMole3, imageMole4, imageMole5, imageMole6, imageMole7, imageMole8, imageMole9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        mTimeView = findViewById(R.id.txtTimeVal);
        mScoreView = findViewById(R.id.txtScoreVal);

        imageMole1 = findViewById(R.id.imageMole1);
        imageMole2 = findViewById(R.id.imageMole2);
        imageMole3 = findViewById(R.id.imageMole3);
        imageMole4 = findViewById(R.id.imageMole4);
        imageMole5 = findViewById(R.id.imageMole5);
        imageMole6 = findViewById(R.id.imageMole6);
        imageMole7 = findViewById(R.id.imageMole7);
        imageMole8 = findViewById(R.id.imageMole8);
        imageMole9 = findViewById(R.id.imageMole9);

        myTimer.start();
        handler.post(moleLoop);

        mPlayerWhack = MediaPlayer.create(GameActivity.this, R.raw.whack);
        mPlayerMiss = MediaPlayer.create(GameActivity.this, R.raw.miss);

        moleClick[0] = findViewById(R.id.imageMole1);
        moleClick[1] = findViewById(R.id.imageMole2);
        moleClick[2] = findViewById(R.id.imageMole3);
        moleClick[3] = findViewById(R.id.imageMole4);
        moleClick[4] = findViewById(R.id.imageMole5);
        moleClick[5] = findViewById(R.id.imageMole6);
        moleClick[6] = findViewById(R.id.imageMole7);
        moleClick[7] = findViewById(R.id.imageMole8);
        moleClick[8] = findViewById(R.id.imageMole9);

        //fit animation based on device metrics
        DisplayMetrics metrics = new DisplayMetrics(); //info about display to fit
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int sheight = metrics.heightPixels;
        yValue = (sheight/12) * -1; // how much mole will move up/down during animation

        moleClick[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[0].getTranslationY()<0){
                    moleClick[0].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[1].getTranslationY()<0){
                    moleClick[1].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[2].getTranslationY()<0){
                    moleClick[2].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[3].getTranslationY()<0){
                    moleClick[3].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[4].getTranslationY()<0){
                    moleClick[4].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[5].getTranslationY()<0){
                    moleClick[5].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[6].getTranslationY()<0){
                    moleClick[6].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[7].getTranslationY()<0){
                    moleClick[7].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });
        moleClick[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moleClick[8].getTranslationY()<0){
                    moleClick[8].animate().translationY(0).setDuration(20);
                    directHit();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myTimer.cancel();
        mPlayerWhack.stop();
        mPlayerMiss.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTimer.cancel();
        mPlayerWhack.stop();
        mPlayerMiss.stop();
    }

    public class myTimer extends CountDownTimer{
        public myTimer(int maxTime, long stepTime){
            super(maxTime,stepTime);
        }

        @Override
        public void onFinish() {
            this.cancel();
            String messageTime = "You ran out of time";
            EndGame(varScore, messageTime);
            timeInterval = 1000;
            moleUpTime = 350; //time taken for mole
        }


//increase difficulty every 5 sec
        public void onTick(long millisUntilFinish) {
            mTimeView.setText(String.valueOf(millisUntilFinish / 1000));
            if (((millisUntilFinish / 1000) % 5 == 0) && (millisUntilFinish / 1000) != 60) {
                increaseDifficulty(); //

            }
        }
    }


    public void increaseDifficulty() { // could potentially have easy, medium, hard
        String diff1 = "Easy"; //makes faster when increasing difficulty
//        if(currentDiff.equals(diff1)){
            timeInterval *= 0.99;
            moleUpTime *= 0.99;
//        }
    }

    //pass final score + reason
    public void EndGame(int endScore, String reason) {
        Intent intent = new Intent(GameActivity.this, EndActivity.class);
        intent.putExtra("Score", endScore);
        intent.putExtra("Reason", reason);
        myTimer.cancel();
        startActivity(intent);
        finish();
    }
// runnable = instances intended to be executed by a thread
    public Runnable moleLoop = new Runnable() { //putting mole randomly in the holes
        int varPrevRandMole = 10;
        @Override
        public void run() {

            // Pick a mole at random, if you get the same twice, re-roll until it's different
            varRandMole = new Random().nextInt(8);

            if (varRandMole == varPrevRandMole){
                do
                    varRandMole = new Random().nextInt(8);
                while (varRandMole == varPrevRandMole);

            }
            varPrevRandMole = varRandMole;
        // animating w info of distance + time for mole to animate
            moleClick[varRandMole].animate().translationY(yValue).setDuration(moleUpTime); // animating w info of distance + time for mole to animate
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    for(int i = 0; i<9; i++){
                        if(moleClick[i].getTranslationY() == yValue){
                            final int j = i;
                            runOnUiThread(new Runnable() { //runs specific action on UI thread (looper)
                                @Override
                                public void run() {
                                    moleClick[j].animate().translationY(0).setDuration(5);
                                }
                            });

                            if(mPlayerMiss.isPlaying() && mPlayerMiss != null){ //audio when miss
                                mPlayerMiss.stop();
                                mPlayerMiss.reset();
                                mPlayerMiss.release();
                            }
                            mPlayerMiss.start();

                        }
                    }
                }
            },timeInterval);
            handler.postDelayed(moleLoop, timeInterval);
        }
    };


    private void updateScore(int varScore) {
        mScoreView.setText(String.valueOf(varScore));
    }

    public void directHit(){
        if(mPlayerWhack != null && mPlayerWhack.isPlaying()){
            mPlayerWhack.stop();
            mPlayerWhack.reset();
            mPlayerWhack.release();
        }
        mPlayerWhack = MediaPlayer.create(GameActivity.this, R.raw.whack);
        mPlayerWhack.start();
        varScore += 250;
        updateScore(varScore);
    }

}