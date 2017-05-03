package com.example.niall.game2;

/**
 * Created by ElenaCappy on 5/2/17.
 */

public class Decision {

    private String que;
    private String ans;
    private int eff;

    //"/AppsForGoodGame/app/src/main/assets/txtQuestionSet.txt"

    public Decision(Question q, int choice){
        que=q.getQue();
        ans=q.getAns(choice);
        eff=q.getEff(choice);

    }
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
