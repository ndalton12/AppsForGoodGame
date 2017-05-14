package com.example.niall.game2;

public class Question {

    private String que;
    private String ans1;
    private String ans2;
    private int eff1; //This is the monetary value of ans1
    private int eff2; //This is the monetary value of ans2

    //This method is the constructor for the class. It takes in three strings and
    // two ints and sets them to variables
    public Question(String question, String ans1, String ans2, int eff1, int eff2){
        this.que=question;
        this.ans1=ans1;
        this.ans2=ans2;
        this.eff1=eff1;
        this.eff2=eff2;
    }

    //The method below is mostly from checking to make sure that the code parces
    //correctly in the Game onCreate method. It returns a string qith all the
    // info from the Question class for one Question object
    public String toString(){
         return "question: "+que+" answer1: "+ ans1+" answer2: "+ ans2+" money val1: "+eff1+" money val 2: "+eff2;
    }

    //The following methods are getters and setters for all of the variables of this class
    public String getQue(){
       return que;
   }
    public String getAns1(){
        return ans1;
    }
    public String getAns2(){
        return ans2;
    }
    public int getEff1(){
        return eff1;
    }
    public int getEff2(){
        return eff2;
    }
    public void setQue(String q){
        que=q;
    }
    public void setAns1(String a){
        ans1=a;
    }
    public void setAns2(String a){
        ans2=a;
    }
    public void setEff1(int ef){
        eff1=ef;
    }
    public void setEff2(int ef){
        eff2=ef;
    }

    //This method gets the effect of the specific answer chosen.
    public int getEff(int val){
        if(val==1)
            return eff1;
        else
            return eff2;
    }

    //This method returns the answer of the specific answer chosen.
    //Note: might delete this later if it is not useful for stats
    //or something. I think that the Decision class makes it irrelevant.
    public String getAns(int val){
        if(val==1)
            return ans1;
        else
            return ans2;
    }

}
