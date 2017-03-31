package com.example.niall.game2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);
    }

    public void startGame(View v) {
        Intent intent = new Intent(this, Game.class);

        startActivity(intent);
    }
}
