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
        if(choice==1){
            ans=q.getAns1();
            //Elena needs to finish this
        }
    }
}
