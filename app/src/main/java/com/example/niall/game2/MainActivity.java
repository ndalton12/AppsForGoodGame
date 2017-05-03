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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);

        // Set background to green
        getWindow().getDecorView().setBackgroundColor(Color.rgb(0, 153, 51));

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
