
package CLIENT;

import COMMON.DatabaseOperation;
import java.io.IOException;
import javafx.util.Pair;
import javax.swing.JButton;

public class Quiztime {
    public String username,quizid;
    public Quiztime(String username,String quizid) {
        this.username=username;
        this.quizid=quizid;
    }
    
    
    public void startquiz() throws IOException, ClassNotFoundException
    {
        Client c=new Client();
        Pair p=new Pair(quizid,"RECIEVEQUIZ");
        c.send(p);
        String[][] s=(String[][])c.recieve();
        int noofques=s.length;
        
        Solvequiz sq=new Solvequiz(username,quizid,noofques,s);
         
        
    }
}
