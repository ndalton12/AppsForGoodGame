package com.example.niall.game2;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingMenu extends Activity {

    public Intent music;
    private Switch musicSwitch;
    public static boolean musicOffOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_menu);

        // Set fullscreen
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        getWindow().getDecorView().setBackgroundColor(Color.rgb(215, 217, 221));

        music = new Intent();
        music.setClass(this,MusicService.class);

        musicSwitch = (Switch) findViewById(R.id.musicswitch);

        // Set switch off or on depending if music is on or off
        if (musicOffOn)
            musicSwitch.setChecked(true);
        else
            musicSwitch.setChecked(false);

        // Switch listener to put music on or off
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    startService(music);
                    musicOffOn = true;
                }else{
                    stopService(music);
                    musicOffOn = false;
                }

            }
        });
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
    Go back to Game activity
     */
    public void goBack(View v) {
        NavUtils.navigateUpFromSameTask(this);
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

    /*
    Returns the state of the music - off or on
     */
    public static boolean getMusicState() {
        return musicOffOn;
    }
}
