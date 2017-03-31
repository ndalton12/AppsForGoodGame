package com.example.niall.game2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class Game extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        //final View decorView = getActivity().getWindow().getDecorView();
        int uiOptions = this.getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;

        newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        this.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);

        // stats history
        SlidingMenu menuStats = new SlidingMenu(this);
        menuStats.setMode(SlidingMenu.LEFT_RIGHT);
        menuStats.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menuStats.setShadowWidthRes(R.dimen.shadow_width);
        menuStats.setShadowDrawable(R.drawable.shadow);
        menuStats.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menuStats.setFadeDegree(0.35f);
        menuStats.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menuStats.setMenu(R.layout.stats);
        menuStats.setSecondaryMenu(R.layout.stats);
        menuStats.setSecondaryShadowDrawable(R.drawable.shadowright);

    }
}