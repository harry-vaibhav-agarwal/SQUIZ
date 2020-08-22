/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIENT;

import java.io.Serializable;

public class Submitresult implements Serializable{

    public String quizid,username;
    public int marks,numberofquestion,correctresponse;

    public Submitresult(String quizid,String username, int marks, int numberofquestion, int correctresponse) {
        this.quizid=quizid;
        this.username = username;
        this.marks = marks;
        this.numberofquestion = numberofquestion;
        this.correctresponse = correctresponse;
    }
    
    
}
