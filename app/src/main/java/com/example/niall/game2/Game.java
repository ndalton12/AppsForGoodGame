package com.example.niall.game2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.Random;

public class Game extends Activity {
    public static final String FINISH_ALERT = "finish_alert";
    public Intent music;
    private Controller aController;
    private TextView questionText;
    private Button ansButton1;
    private Button ansButton2;
    private TextView totalMoney;
    private TextView spentMoney;
    private TextView numChoices;
    private TextView moneyCounter;
    private int roadblockOccurrence;
    private int questionCounter;
    private double chance;
    private boolean roadblockNeeded;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        // Initialize counters
        roadblockOccurrence = 0;
        chance = 0.0;
        questionCounter = 0;
        roadblockNeeded = false;

        // Initialize receiver for closing Game activity
        this.registerReceiver(this.finishAlert, new IntentFilter(FINISH_ALERT));

        // Sets the background for the left/right menus to be green
        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

        // Set fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Initialize music
        music = new Intent();
        music.setClass(this,MusicService.class);

        // Initialize controller
        aController = (Controller) getApplicationContext();

        // Sliding screens
        SlidingMenu menuStats = new SlidingMenu(this);
        menuStats.setMode(SlidingMenu.LEFT_RIGHT);
        menuStats.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
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

        // Initialize the text views
        questionText = (TextView) findViewById(R.id.question_text);
        totalMoney = (TextView) findViewById(R.id.tot_money);
        spentMoney = (TextView) findViewById(R.id.spent_money);
        numChoices = (TextView) findViewById(R.id.num_choices);
        moneyCounter = (TextView) findViewById(R.id.money_counter);

        // Initialize the buttons
        ansButton1 = (Button) findViewById(R.id.answer_button1);
        ansButton2 = (Button) findViewById(R.id.answer_button2);

        // Set first question
        Question quest = aController.getQuestionRand();

        questionText.setText(quest.getQue());
        ansButton1.setText(quest.getAns1());
        ansButton2.setText(quest.getAns2());

        // Reset stats
        StatsValues stats = aController.getStatsValues();

        stats.reset();

        // Set money counter
        moneyCounter.setText(
                String.format(
                        "%s%s",
                        getString(R.string.dollar_sign),
                        String.valueOf(stats.getTotalMoney())));

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    /*
    Receiver for closing the Game activity
     */
    BroadcastReceiver finishAlert = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Game.this.finish();
        }
    };

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
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPause() {
        super.onPause();

        PowerManager powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenAwake = (Build.VERSION.SDK_INT < 20? powerManager.isScreenOn():powerManager.isInteractive());

        if (!isScreenAwake)
            stopService(music);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.unregisterReceiver(finishAlert);
    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            stopService(music);
        }
    }

    /*
    If the user chooses answer button 1
     */
    public void answerButton1(View v){
        // Updates decision history
        Decision d = new Decision(aController.getCurrentQuestion(), 1);
        aController.addDecision(d);
        aController.changeStats(aController.getCurrentQuestion(), 1);
        updateDecisions(d.toString());

        // Gets stats
        StatsValues stats = aController.getStatsValues();

        // Update TextViews
        totalMoney.setText(String.valueOf(stats.getTotalMoney()));
        spentMoney.setText(String.valueOf(stats.getMoneySpent()));
        numChoices.setText(String.valueOf(stats.getNumChoices()));
        moneyCounter.setText(
                String.format(
                        "%s%s",
                        getString(R.string.dollar_sign),
                        String.valueOf(stats.getTotalMoney())));

        // End game if necessary, else get next question and set TextViews
        if(aController.getRemainingQuestions().size() == 0 || stats.getTotalMoney() <= 0)
            finishGame();
        else {

            Question quest = aController.getQuestionRand();

            questionText.setText(quest.getQue());
            ansButton1.setText(quest.getAns1());
            ansButton2.setText(quest.getAns2());

            if (roadblockOccurrence < 7 && (roadblockNeeded  || Math.random() + chance >= 0.9)) {
                DialogFragment newFragment = new RoadblockDialog();
                newFragment.show(getFragmentManager(), "roadblock");
                roadblockOccurrence++;
                roadblockNeeded = false;
                questionCounter = 0;
                chance = 0;
            } else {
                questionCounter++;
                chance += 0.05;

                if (questionCounter >= 7) {
                    roadblockNeeded = true;
                }
            }
        }
    }

    /*
    If the user chooses answer button 2
     */
    public void answerButton2(View v){
        // Updates decision history and stats
        Decision d = new Decision(aController.getCurrentQuestion(), 2);
        aController.addDecision(d);
        aController.changeStats(aController.getCurrentQuestion(), 2);
        updateDecisions(d.toString());

        // Get stats
        StatsValues stats = aController.getStatsValues();

        // Update TextViews
        totalMoney.setText(String.valueOf(stats.getTotalMoney()));
        spentMoney.setText(String.valueOf(stats.getMoneySpent()));
        numChoices.setText(String.valueOf(stats.getNumChoices()));
        moneyCounter.setText(
                String.format(
                        "%s%s",
                        getString(R.string.dollar_sign),
                        String.valueOf(stats.getTotalMoney())));

        // End game if necessary, else get next question and set TextViews
        if(aController.getRemainingQuestions().size() == 0 || stats.getTotalMoney() <= 0)
            finishGame();
        else {

            Question quest = aController.getQuestionRand();

            questionText.setText(quest.getQue());
            ansButton1.setText(quest.getAns1());
            ansButton2.setText(quest.getAns2());

            if (roadblockOccurrence < 7 && (roadblockNeeded  || Math.random() + chance >= 0.9)) {
                DialogFragment newFragment = new RoadblockDialog();
                newFragment.show(getFragmentManager(), "roadblock");
                roadblockOccurrence++;
                roadblockNeeded = false;
                questionCounter = 0;
                chance = 0;
            } else {
                questionCounter++;
                chance += 0.05;

                if (questionCounter >= 7) {
                    roadblockNeeded = true;
                }
            }
        }

    }

    /*
    Finishes the game and displays the end dialog
     */
    public void finishGame(){
        DialogFragment newFragment = new EndGameDialog();
        newFragment.show(getFragmentManager(), "end_game_dialog");
    }

    /*
    Updates the decision ScrollView
     */
    private void updateDecisions(String text) {
        // Declare/ intialize TextView and LinearLayout from decision_history.xml
        TextView test;
        LinearLayout llay = (LinearLayout) findViewById(R.id.scrollLinear);

        // Set conditions for TextView
        test = new TextView(this);
        test.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        test.setText(text + "\n");
        test.setTextColor(Color.WHITE);
        test.setTextSize(22);

        // Add the TextView to the LinearLayout
        llay.addView(test);

        // Reverse the order of the TextViews in the LinearLayout
        for(int k = llay.getChildCount()-1 ; k >= 0 ; k--)
        {
            View item = llay.getChildAt(k);
            llay.removeViewAt(k);
            llay.addView(item);
        }

        // Refresh the view
        ScrollView sv = (ScrollView) findViewById(R.id.decisionScrollView);
        sv.bringToFront();
        sv.invalidate();
        sv.requestLayout();
    }

}