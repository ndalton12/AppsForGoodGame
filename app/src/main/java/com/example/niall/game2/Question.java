package com.example.niall.game2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {

    private String que;
    private String ans1;
    private String ans2;
    private String con1;
    private String con2;
    public ArrayList<String[]> returner;

    //"/AppsForGoodGame/app/src/main/assets/txtQuestionSet.txt"

    public Question(String filePath){
        File inputFile = new File(filePath);
        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int i=0;


        int counter=0;
        while(in.hasNextLine())  {
            String line = in.nextLine();

            i=line.indexOf(';');
            que=line.substring(0, i);
            line=line.substring(0,i)+"-"+line.substring(i+1);
            ans1=line.substring(i+1, line.indexOf(';'));

            i=line.indexOf(';');
            line=line.substring(0,i)+"-"+line.substring(i+1);
            ans2=line.substring(i+1, line.indexOf(';'));

            i=line.indexOf(';');
            line=line.substring(0,i)+"-"+line.substring(i+1);
            con1=line.substring(i+1, line.indexOf(';'));

            i=line.indexOf(';');
            line=line.substring(0,i)+"-"+line.substring(i+1);
            con2=line.substring(i+1);

            String[] setThing={que, ans1, ans2, con1, con2};
            returner.add(setThing);
            counter++;
        }

    }

    public String[] getQuestionInfo(int qNum){
        if(qNum>=returner.size())
            return null;
        return returner.get(qNum);
    }
    public String getQuestion(int qNum){
        if(qNum>=returner.size())
            return null;
        return (returner.get(qNum))[0];
    }
    public String getAns1(int qNum){
        if(qNum>=returner.size())
            return null;
        return returner.get(qNum)[1];
    }
    public String getAns2(int qNum){
        if(qNum>=returner.size())
            return null;
        return returner.get(qNum)[2];
    }
    public String getCons1(int qNum){
        if(qNum>=returner.size())
            return null;
        return returner.get(qNum)[3];
    }
    public String getCons2(int qNum){
        if(qNum>=returner.size())
            return null;
        return returner.get(qNum)[4];
    }



}
