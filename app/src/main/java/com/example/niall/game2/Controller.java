package com.example.niall.game2;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by ElenaCappy on 4/26/17.
 */

public class Controller extends Application {
    private ArrayList<Question> questionSet=new ArrayList<Question>();


    //adds a question to the arraylist
    public void addQuestion(Question addition){

        questionSet.add(addition);
    }
    //returns first
    public Question getQuestionStart(){
        return questionSet.get(0);
        //questionSet.remove(0);
    }
    public Question getQuestionRand(){

        return questionSet.get(0);
       // questionSet.remove(0);
    }
}
