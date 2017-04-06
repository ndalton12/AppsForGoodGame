package com.example.niall.game2;

import java.util.ArrayList;
import java.util.Random;

public class Question {

    int moneyValue;
    String question;
    String opt1;
    String opt2;
    ArrayList<String> questions;
    ArrayList<String> opt1s;
    ArrayList<String> opt2s;
    String[] returned = new String[3];
    public String[] Question(){
        Random randy=new Random();
        int rand= randy.nextInt(questions.size());
        this.question=questions.get(rand);
        this.opt1=opt1s.get(rand);
        this.opt2=opt2s.get(rand);
        this.returned[0]=question;
        this.returned[1]=opt1;
        this.returned[2]=opt2;
        return returned;
    }

}
