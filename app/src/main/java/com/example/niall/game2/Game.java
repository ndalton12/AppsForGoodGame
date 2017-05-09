package com.example.niall.game2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Game extends Activity {
    public Intent music;
    private Controller aController;
    private TextView questionText;
    private Button ansButton1;
    private Button ansButton2;
    private TextView totalMoney;
    private TextView spentMoney;
    private TextView numChoices;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        // Sets the background for the left/right menus to be green
        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

        // Initialize music
        music = new Intent();
        music.setClass(this,MusicService.class);

        // Initialize controller
        aController = (Controller) getApplicationContext();

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

        questionText = (TextView) findViewById(R.id.question_text);
        totalMoney = (TextView) findViewById(R.id.tot_money);
        spentMoney = (TextView) findViewById(R.id.spent_money);
        numChoices = (TextView) findViewById(R.id.num_choices);

        ansButton1 = (Button) findViewById(R.id.answer_button1);
        ansButton2 = (Button) findViewById(R.id.answer_button2);

        //if(aController.getRemainingQuestions())

        Question quest = aController.getQuestionRand();

        questionText.setText(quest.getQue());
        ansButton1.setText(quest.getAns1());
        ansButton2.setText(quest.getAns2());

        /**** Test case for adding text views to decision history -- will be implemented with the game
        TextView test;
        LinearLayout llay = (LinearLayout) findViewById(R.id.scrollLinear);

        test = new TextView(this);
        test.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        test.setText("Hello there");
        llay.addView(test);

        ScrollView sv = (ScrollView) findViewById(R.id.decisionScrollView);
        sv.invalidate();
        sv.requestLayout();*/

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

    public void answerButton1(View v){
        Decision d = new Decision(aController.getCurrentQuestion(), 1);
        aController.addDecision(d);
        aController.changeStats(aController.getCurrentQuestion(), 1);

        StatsValues stats = aController.getStatsValues();

        totalMoney.setText(String.valueOf(stats.getTotalMoney()));
        spentMoney.setText(String.valueOf(stats.getMoneySpent()));
        numChoices.setText(String.valueOf(stats.getNumChoices()));

        if(aController.getRemainingQuestions().size() == 0)
            finishGame();
        else {

            Question quest = aController.getQuestionRand();

            questionText.setText(quest.getQue());
            ansButton1.setText(quest.getAns1());
            ansButton2.setText(quest.getAns2());
        }
    }

    public void answerButton2(View v){
        Decision d = new Decision(aController.getCurrentQuestion(), 2);
        aController.addDecision(d);
        aController.changeStats(aController.getCurrentQuestion(), 2);
        StatsValues stats = aController.getStatsValues();

        totalMoney.setText(String.valueOf(stats.getTotalMoney()));
        spentMoney.setText(String.valueOf(stats.getMoneySpent()));
        numChoices.setText(String.valueOf(stats.getNumChoices()));

        if(aController.getRemainingQuestions().size() == 0)
            finishGame();
        else {

            Question quest = aController.getQuestionRand();

            questionText.setText(quest.getQue());
            ansButton1.setText(quest.getAns1());
            ansButton2.setText(quest.getAns2());
        }
    }

    public void finishGame(){

    }



}