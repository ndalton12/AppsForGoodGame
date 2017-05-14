package com.example.niall.game2;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends Activity {

    public Intent music;
    Controller aController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        // Set fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        music = new Intent();
        music.setClass(this, MusicService.class);
        if (SettingMenu.getMusicState())
            startService(music);

        aController = (Controller) getApplicationContext();
        aController.reset();

        // Set background to green
        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

        AssetManager assetManager = getAssets(); //This line allows us to access the assets folder

        // The code enclosed in the try/catch creates an input stream and scanner. Then it parses
        // the input it gets from its file and puts it into a Question class. This goes on in a while loop
        try {
            InputStream stream = assetManager.open("txtQuestionSet.txt");
            Scanner in = new Scanner(stream);
            int i;
            Question newQ;

            int counter = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();

                i = line.indexOf(';');
                String que = line.substring(0, i);
                line = line.substring(0, i) + "-" + line.substring(i + 1);
                String ans1 = line.substring(i + 1, line.indexOf(';'));

                i = line.indexOf(';');
                line = line.substring(0, i) + "-" + line.substring(i + 1);
                String ans2 = line.substring(i + 1, line.indexOf(';'));

                i = line.indexOf(';');
                line = line.substring(0, i) + "-" + line.substring(i + 1);
                String con1 = line.substring(i + 1, line.indexOf(';'));

                i = line.indexOf(';');
                line = line.substring(0, i) + "-" + line.substring(i + 1);
                String con2 = line.substring(i + 1);


                int c1 = Integer.parseInt(con1);
                int c2 = Integer.parseInt(con2);

                newQ = new Question(que, ans1, ans2, c1, c2);
                Log.i("Elena", newQ.toString());
                aController.addQuestion(newQ);

                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream stream2 = assetManager.open("roadblockText");
            Scanner in2 = new Scanner(stream2);
            int j;
            Roadblock newR;

            int counter = 0;
            while (in2.hasNextLine()) {
                String line = in2.nextLine();

                j = line.indexOf(';');
                String prompter = line.substring(0, j);
                line = line.substring(0, j) + "-" + line.substring(j + 1);
                String effString = line.substring(j + 1);

                int effInt = Integer.parseInt(effString);

                newR = new Roadblock(prompter, effInt);
                Log.i("Roadblock added", newR.toString());
                aController.addRoadblock(newR);
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    /*
    Initialize intent to start Game activity
     */
    public void startGame(View v) {
        Intent intent = new Intent(this, Game.class);

        startActivity(intent);

        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SettingMenu.getMusicState())
            startService(music);
    }

    @Override
    public void onPause() {
        super.onPause();

        PowerManager powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenAwake = (Build.VERSION.SDK_INT < 20? powerManager.isScreenOn():powerManager.isInteractive());

        if (!isScreenAwake)
            stopService(music);
    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            stopService(music);
        }
    }

}
