package CLIENT;

import java.io.Serializable;

public class quiz implements Serializable{
   public String username;
   //key for operation type
    public int numberofquestion;
    public String questiontype,quizid,subject,key;

    public quiz(String username, int numberofquestion, String questiontype, String quizid,String subject, String key) {
        this.username = username;
        this.numberofquestion = numberofquestion;
        this.questiontype = questiontype;
        this.quizid = quizid;
        this.subject=subject;
        this.key = key;
    }



   
}
 