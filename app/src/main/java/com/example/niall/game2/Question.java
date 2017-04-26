package com.example.niall.game2;

public class Question {

    private String que;
    private String ans1;
    private String ans2;
    private int eff1;
    private int eff2;

    //"/AppsForGoodGame/app/src/main/assets/txtQuestionSet.txt"

    public Question(String question, String ans1, String ans2, int eff1, int eff2){
        this.que=question;
        this.ans1=ans1;
        this.ans2=ans2;
        this.eff1=eff1;
        this.eff2=eff2;
    }

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

}
