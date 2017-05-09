package com.example.niall.game2;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
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

        music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        aController = (Controller) getApplicationContext();

        // Set background to green
        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

        AssetManager assetManager = getAssets(); //This line allows us to access the assets folder

        //The code enclosed in the try/catch creates an input stream and scanner. Then it parces
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
    }

    /*
    Initialize intent to start Game activity
     */
    public void startGame(View v) {
        Intent intent = new Intent(this, Game.class);

        startActivity(intent);

        finish();
    }


    private void setFullscreen() {
        int uiOptions = this.getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        this.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    @Override
    public void onResume() {
        super.onResume();
        startService(music);
        setFullscreen();
    }

    @Override
    public void onDestroy() {
        super.onResume();

        setFullscreen();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            stopService(music);
        }
    }

}
