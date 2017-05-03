package com.example.niall.game2;

import android.app.Activity;
import android.widget.TextView;

public class StatsValues extends Activity {
    private int moneySpent;
    private int totalMoney;
    private int numChoices;
    TextView numMoney;

    public StatsValues() {
        moneySpent = 0;
        totalMoney = 100;
        numChoices = 0;
        numMoney = (TextView) findViewById(R.id.NumMoney);
    }

    public void updateNumChoices() {
        numChoices = numChoices +1;
    }

    public void updateMoney(int x) {
        totalMoney = totalMoney + x;
        if(x<0) {
            moneySpent = moneySpent+x;
        }
        numMoney.setText(Integer.toString(totalMoney));
    }
    public int getTotalMoney() {
        return totalMoney;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public int getNumChoices() {
        return numChoices;
    }
        }