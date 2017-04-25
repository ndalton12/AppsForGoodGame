package com.example.niall.game2;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

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
        setFullscreen();
    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            stopService(music);
        }
    }

    public static boolean getMusicState() {
        return musicOffOn;
    }
}
