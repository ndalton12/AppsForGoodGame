package com.example.niall.game2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

    //public MusicHandler musicHandler;
    public Intent music;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        //musicHandler = new MusicHandler();
        /*** WIP FIX WITH MUSIC doBindService();

        music = new Intent();
        music.setClass(this,MusicService.class);
        startService(music);*/

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

    /*
    Music player
     */
    /**************** WORK IN PROGRESS: TODO FIX MUSICHANDLER CLASS AND STOP MUSIC WHEN NOT IN FOCUS
    private boolean mIsBound = false;
    private MusicService mServ;

    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }*/

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

        //*** FIX WITH MUSIC stopService(music);
    }

}
