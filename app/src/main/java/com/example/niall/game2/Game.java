package com.example.niall.game2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Game extends Activity {

    public Intent music;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Controller aController = (Controller) getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        // Sets the background for the left/right menus to be green
        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

        music = new Intent();
        music.setClass(this,MusicService.class);

        // Sliding screens
        SlidingMenu menuStats = new SlidingMenu(this);
        menuStats.setMode(SlidingMenu.LEFT_RIGHT);
        menuStats.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menuStats.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menuStats.setShadowWidthRes(R.dimen.shadow_width);
        menuStats.setShadowDrawable(R.drawable.shadow);
        menuStats.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menuStats.setFadeDegree(0.35f);
        menuStats.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        // Set first menu
        menuStats.setMenu(R.layout.stats);

        // Set second Menu
        menuStats.setSecondaryMenu(R.layout.decision_history);
        menuStats.setSecondaryShadowDrawable(R.drawable.shadowright);

        /**** Test case for adding text views to decision history -- will be implemented with the game
        TODO? Make decision class to handle this? or just create private method instead < - probably easier
        TextView test;
        LinearLayout llay = (LinearLayout) findViewById(R.id.scrollLinear);

        test = new TextView(this);
        test.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        test.setText("Hello there");
        llay.addView(test);

        ScrollView sv = (ScrollView) findViewById(R.id.decisionScrollView);
        sv.invalidate();
        sv.requestLayout();*/

        AssetManager assetManager=getAssets(); //This line allows us to access the assets folder

        //The code enclosed in the try/catch creates an input stream and scanner. Then it parces
        // the input it gets from its file and puts it into a Question class. This goes on in a while loop
        try {
            InputStream stream = assetManager.open("txtQuestionSet.txt");
            Scanner in = new Scanner(stream);
            int i;
            Question newQ;

            int counter=0;
            while(in.hasNextLine())  {
                String line = in.nextLine();

                i=line.indexOf(';');
                String que=line.substring(0, i);
                line=line.substring(0,i)+"-"+line.substring(i+1);
                String ans1=line.substring(i+1, line.indexOf(';'));

                i=line.indexOf(';');
                line=line.substring(0,i)+"-"+line.substring(i+1);
                String ans2=line.substring(i+1, line.indexOf(';'));

                i=line.indexOf(';');
                line=line.substring(0,i)+"-"+line.substring(i+1);
                String con1=line.substring(i+1, line.indexOf(';'));

                i=line.indexOf(';');
                line=line.substring(0,i)+"-"+line.substring(i+1);
                String con2=line.substring(i+1);


                int c1 = Integer.parseInt(con1);
                int c2 = Integer.parseInt(con2);

                newQ=new Question(que, ans1, ans2, c1, c2);
                Log.i("Elena",newQ.toString());
                aController.addQuestion(newQ);

                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    Launches pop up menu on screen
     */
    public void popMenu(View v) {
        DialogFragment newFragment = new Menu();
        newFragment.show(getFragmentManager(), "menu");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SettingMenu.getMusicState())
            startService(music);
        setFullscreen();
    }

    /*
    Helper method to set fullscreen
     */
    private void setFullscreen() {
        int uiOptions = this.getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        this.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            stopService(music);
        }
    }

//    public void onClickButton(Question q, int c, ){
//        Decision d=new Decision(q, c);
//        aController.addDecision(d);
//        aController.changeStats(q, c);
//    }

}