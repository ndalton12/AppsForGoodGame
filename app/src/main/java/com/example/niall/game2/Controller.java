package com.example.niall.game2;

import android.app.Application;
import java.util.ArrayList;
import java.util.Random;

public class Controller extends Application {

    //initializing data
    private ArrayList<Question> questionSet=new ArrayList<Question>();
    private ArrayList<Question> questionSetMaster=new ArrayList<Question>();
    private ArrayList<Question> questionSetOrdered=new ArrayList<Question>();

    private ArrayList<Roadblock> roadblockSet=new ArrayList<Roadblock>();
    private ArrayList<Roadblock> roadblockSetMaster=new ArrayList<Roadblock>();
    private ArrayList<Roadblock> roadblockSetOrdered=new ArrayList<Roadblock>();

    private ArrayList<Decision> decisionSet=new ArrayList<Decision>();

    StatsValues stats = new StatsValues(); //This creates an instance of the stats function


    /*Below are the methods related to the StatsValues class.
     * Still currently a work in progress. */

    //This returns the instance of the StatsValue class
    public StatsValues getStatsValues(){
        return stats;
    }

    //This method takes in a Question and a choice and then helps
    // to change the stats values
    public void changeStats(Question q, int choice){
        stats.updateMoney(q.getEff(choice));
    }
    /*Below are the methods relating to the Decision class/object.
    * Decision is used mainly for decision history, but we might
    * be able to implement it into Stats as well. TBD.*/

    //adds a decision to the decision arraylist
    public void addDecision(Decision d){
        decisionSet.add(d);
    }

    //returns the effect of the last decision added
    public int getLastEffect(){
        Decision d = decisionSet.get(decisionSet.size()-1);
        return d.getEffect();
    }

    /*Below are all of the methods related to the Question class.
    * There are three different question array lists. One is an
    * unordered list of questions, one is an ordered set, and the
    * other deletes the questions as they are read by the user.
    * We need to consider whether we want to use getQuestionStart
    * or getQuestionRand to take random questions*/


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


    /* The following methods correspond to the Roadblock class */

    //adds a roadblock to the arraylist
    public void addRoadblock(Roadblock addition){
        roadblockSet.add(addition);
        roadblockSetMaster.add(addition);
    }

    //returns first roadblock object in the list
    public Roadblock getRoadblockStart(){
        Roadblock q = roadblockSet.get(0);
        roadblockSet.remove(0);
        roadblockSetOrdered.add(q);
        return q;
    }

    //returns a random roadblock object
    public Roadblock getRoadblockRand(){
        Random rand = new Random();
        int num=rand.nextInt(roadblockSet.size());
        Roadblock q = roadblockSet.get(num);
        roadblockSet.remove(num);
        roadblockSetOrdered.add(q);
        return q;
    }

    //returns array list of all roadblocks
    public ArrayList<Roadblock> getOriginalRoadblocks(){
        return roadblockSetMaster;
    }


    //returns array list of all roadblocks left
    public ArrayList<Roadblock> getRemainingRoadblocks(){
        return roadblockSet;
    }

    //returns array list of all roadblocks in order
    public ArrayList<Roadblock> getOrderedRoadblocks(){
        return roadblockSetOrdered;
    }


}
