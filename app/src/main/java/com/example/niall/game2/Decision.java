package com.example.niall.game2;

/**
 * Created by ElenaCappy on 5/2/17.
 */

public class Decision {

    private String que;
    private String ans; //specific answer chosen
    private int eff; //specific monetary value of ans


    // Here is the constructor. It takes the question object and either a 1 or a 2.
    // Then it calls methods in the Question class to set values for the variables
    // listed above.
    public Decision(Question q, int choice){
        que=q.getQue();
        ans=q.getAns(choice);
        eff=q.getEff(choice);
    }

    //This is a method that turns all of the data into a string and returns it. This
    //will probably be used in the decision history, whenever we get to it.
    public String toString(){
        return que + "\nYou chose the answer: "+ans+" with a monetary impact of "+eff;
    }

    //The following three methods are getters for the decision class. Will probably
    // be used in the on click method in Game
    public String getQuestion(){
        return que;
    }
    public String getAnswer(){
        return ans;
    }
    public int getEffect() {
        return eff;
    }
}
