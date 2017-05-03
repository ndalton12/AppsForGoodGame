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

    public void updateNumChoices() {
        numChoices = numChoices +1;
    }

    public void updateMoney(int x) {
        totalMoney = totalMoney + x;
        if(x<0) {
            moneySpent = moneySpent+x;
        }
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