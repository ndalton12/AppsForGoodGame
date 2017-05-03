package com.example.niall.game2;

public class StatsValues {
            private int moneySpent;
            private int totalMoney;
            private int numChoices;
    public StatsValues() {
        moneySpent = 0;
        totalMoney = 100;
        numChoices = 0;
            }

    public int getMoneySpent() {
        return moneySpent;
    }
    public void updatenumChoices() {
        numChoices = numChoices +1;
    }
    public void updatetotalMoney() {
        //totalMoney = totalMoney + Question.eff1();
    }
        }