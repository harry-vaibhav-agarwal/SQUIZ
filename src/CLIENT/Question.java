
package CLIENT;
import java.io.Serializable;
public class Question implements Serializable {
public String quizid,question, option1, option2, option3, option4,  correct;
public String username,questionhint,permission,key;

    public Question(String quizid, String question, String option1, String option2, String option3, String option4, String correct) {
        this.quizid = quizid;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct = correct;
    }
    public Question(String quizid,String username,String questionhint,String permission,String key)
    {
        this.quizid = quizid;
        this.username = username;
        this.questionhint = questionhint;
        this.permission = permission;
        this.key=key;
    }
   
    
    
}
