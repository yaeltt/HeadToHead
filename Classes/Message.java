/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.classes;

import java.io.Serializable;

/**
 *
 * @author Yael_Tubul
 */
public class Message implements Serializable{
    private String ans;
    private int ansCode;
    private boolean isGoodAnswer;
    private int pointsToU1;
    private int pointsToU2;

    public Message(String ans, int ansCode, boolean isGoodAnswer,int pointsToU1,int pointsToU2) {
        this.ans = ans;
        this.ansCode = ansCode;
        this.isGoodAnswer = isGoodAnswer;
        this.pointsToU1=pointsToU1;
        this.pointsToU2=pointsToU2;
    }
    public Message(){}

    public int getPointsToU2() {
        return pointsToU2;
    }

    public int getPointsToU1() {
        return pointsToU1;
    }

    public String getAns() {
        return ans;
    }

    public int getAnsCode() {
        return ansCode;
    }
      public boolean GetIsGoodAnswer() {
        return this.isGoodAnswer;
    }
    public void setAns(String ans) {
        this.ans = ans;
    }

    public void setAnsCode(int ansCode) {
        this.ansCode = ansCode;
    }

    public void setIsGoodAnswer(boolean isGoodAnswer) {
        this.isGoodAnswer = isGoodAnswer;
    }

    public void setPointsToU1(int pointsToU1) {
        this.pointsToU1 = pointsToU1;
    }

    public void setPointsToU2(int pointsToU2) {
        this.pointsToU2 = pointsToU2;
    }
}
