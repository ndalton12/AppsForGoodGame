package com.example.niall.game2;

import android.app.Application;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ElenaCappy on 4/26/17.
 */

public class Controller extends Application {

    //All of the variables:
    private ArrayList<Question> questionSet=new ArrayList<Question>();
    private ArrayList<Question> questionSetMaster=new ArrayList<Question>();
    private ArrayList<Question> questionSetOrdered=new ArrayList<Question>();



    //adds a question to the arraylist
    public void addQuestion(Question addition){
        questionSet.add(addition);
        questionSetMaster.add(addition);
    }

    //returns first Question object in the list
    public Question getQuestionStart(){
        Question q = questionSet.get(0);
        questionSet.remove(0);
        questionSetOrdered.add(q);
        return q;

    }

    //returns a random question object
    public Question getQuestionRand(){
        Random rand = new Random();
        int num=rand.nextInt(questionSet.size());
        Question q = questionSet.get(num);
        questionSet.remove(num);
        questionSetOrdered.add(q);
        return q;
    }

    //returns array list of all questions
    public ArrayList<Question> getOriginalQuestions(){
        return questionSetMaster;
    }

    //returns array list of all questions left
    public ArrayList<Question> getRemainingQuestions(){
        return questionSet;
    }

    //returns array list of all questions in order
    public ArrayList<Question> getOrderedQuestions(){
        return questionSetOrdered;
    }


}
