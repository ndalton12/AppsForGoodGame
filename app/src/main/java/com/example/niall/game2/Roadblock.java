package com.example.niall.game2;

/**
 * Created by ElenaCappy on 5/9/17.
 */

public class Roadblock {

    private String prompt;
    private int effect;

    //This method is the constructor for the class. It takes in one string and
    // one int and sets them to variables
    public Roadblock(String p, int e){
        this.prompt=p;
        this.effect=e;
    }

    //The method below is mostly from checking to make sure that the code parces
    //correctly in the Game onCreate method. It returns a string qith all the
    // info from the Question class for one Question object
    public String toString(){
        return "Prompt: "+prompt+" Effect: "+ effect;
    }

    //The following methods are getters and setters for all of the variables of this class
    public String getPrompt(){
        return prompt;
    }
    public int getEffect(){
        return effect;
    }


}
