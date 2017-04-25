package com.example.niall.game2;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Game extends Activity {

    public Intent music;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        music = new Intent();
        music.setClass(this,MusicService.class);

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

}