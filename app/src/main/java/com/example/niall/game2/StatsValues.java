package com.example.niall.game2;

public class StatsValues {
    private int moneySpent;
    private int totalMoney;
    private int numChoices;

    // Constructor sets starter values of stats
    public StatsValues() {
        moneySpent = 0;
        totalMoney = 500;
        numChoices = 0;
    }

    // Increments the number of choices made by one
    public void updateNumChoices() {
        numChoices = numChoices + 1;
    }

    // Changes the totalMoney variable based on the parameter
    public void updateMoney(int x) {
        totalMoney = totalMoney + x;
        if(x<0) {
            moneySpent = moneySpent+x;
        }
    }

    // The following three methods are getters for the variables initialized at the top of this code

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public int getNumChoices() {
        return numChoices;
    }

    /*
    Resets the stats values
     */
    public void reset() {
        moneySpent = 0;
        totalMoney = 500;
        numChoices = 0;
    }

}