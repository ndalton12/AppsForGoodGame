package com.example.niall.game2;

import android.app.Application;
import java.util.ArrayList;
import java.util.Random;

public class Controller extends Application {

    //initializing data
    private ArrayList<Question> questionSet=new ArrayList<Question>();
    private ArrayList<Question> questionSetMaster=new ArrayList<Question>();
    private ArrayList<Question> questionSetOrdered=new ArrayList<Question>();
    private ArrayList<Decision> decisionSet=new ArrayList<Decision>();
    StatsValues stats = new StatsValues();

    //not sure how this is useful yet, but it's here - Elena
    public StatsValues getStatsValues(){
        return stats;
    }

    //adds a decision to the decision arraylist
    public void addDecision(Decision d){
        decisionSet.add(d);
    }

    //returns the effect of the last decision added
    public int getLastEffect(){
        Decision d = decisionSet.get(decisionSet.size()-1);
        return d.getEffect();
    }

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
