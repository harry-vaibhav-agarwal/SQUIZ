/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER;

import CLIENT.Question;
import CLIENT.Submitresult;
import CLIENT.quiz;
import COMMON.DatabaseOperation;
import COMMON.Student;
import COMMON.Teacher;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Pair;

/**
 *
 * @author Hp
 */
public class HandleClient implements Runnable{
    ObjectInputStream in;
    Socket socket;
    ObjectOutputStream out;
    public HandleClient(Socket socket) throws IOException, ClassNotFoundException
    {
        this.socket=socket;
        in=new ObjectInputStream(socket.getInputStream());
        out=new ObjectOutputStream(socket.getOutputStream());
        
    }
    @Override
    public void run() 
    {
      
 
        try {
            
           Object ob=in.readObject();
           
           if(ob instanceof Student)
            {
                Student student=(Student)ob;
                
                if(student.key.equals("STUDENTSIGNUP"))
                {
                   // System.out.print(student.key);
                    DatabaseOperation db=new DatabaseOperation();
                    String query="INSERT INTO student VALUES(?,?,?,?);";
                    db.st=db.con.prepareStatement(query);
                        db.st.setString(1,student.username);
                        db.st.setString(2,student.name);
                        db.st.setString(3,student.password);
                        db.st.setInt(4,student.mobilenumber);
                        db.st.executeUpdate();
                        
                        
                        String query1="Create table securequiz."+student.username+" (quizid varchar(30),marks int(10),numberofquestion int(10),correctanswer int(10));";
                     db.st=db.con.prepareStatement(query1);
                     System.out.println("Hello");
                //        db.st.setString(1,q.quizid);
                        db.st.execute();
                        System.out.println("Hello2");
        
                        db.con.close();
                }
                else if(student.key.equals("STUDENTLOGIN"))
                {
                    String query="SELECT * FROM student WHERE username=? AND password=?;";       
                    DatabaseOperation db=new DatabaseOperation();
                    db.st=db.con.prepareStatement(query);
                    db.st.setString(1,student.username);
                    db.st.setString(2,student.password);
                    ResultSet rs=db.st.executeQuery();
                  
                    int flag=0;
                    while(rs.next())
                    {
                        flag=1;  
                    }
                    if(flag==0)
                        out.writeObject("DENIED");
                    else
                        out.writeObject("ACCESS");
                  db.con.close();  
                }
            }
           else if(ob instanceof Teacher)
            {
                Teacher teacher=(Teacher)ob;
                if(teacher.key.equals("TEACHERSIGNUP"))
                {
                    System.out.print(teacher.key);
                    DatabaseOperation db=new DatabaseOperation();
                    String query="INSERT INTO teacher VALUES(?,?,?,?,?);";
                    
                        db.st=db.con.prepareStatement(query);
                        db.st.setString(1,teacher.username);
                        db.st.setString(2,teacher.name);
                        db.st.setString(3,teacher.password);
                        db.st.setInt(4,teacher.mobilenumber);
                        db.st.setInt(5,teacher.teacherid);
                        db.st.executeUpdate();
                        db.con.close();
                }
                else if(teacher.key.equals("TEACHERLOGIN"))
                    {
                    String query="SELECT * FROM teacher WHERE username=? AND password=? AND teacherid=?;";       
                    DatabaseOperation db=new DatabaseOperation();
                    db.st=db.con.prepareStatement(query);
                     db.st.setString(1,teacher.username);
                    db.st.setString(2,teacher.password);
                      db.st.setInt(3,teacher.teacherid);
                    ResultSet rs=db.st.executeQuery();
                    int flag=0;
                    while(rs.next())
                        flag=1;
                    if(flag==0)
                        out.writeObject("DENIED");
                    else
                        out.writeObject("ACCESS");
                }
            }
           else if(ob instanceof quiz)
           {
               quiz q=(quiz)ob;
               if(q.key.equals("GENERATEQUIZ"))
               {
                   System.out.print(q.key);
                    DatabaseOperation db=new DatabaseOperation();
                    
                    String query="INSERT INTO quiz VALUES(?,?,?);";
                    
                        db.st=db.con.prepareStatement(query);
                        db.st.setString(1,q.quizid);
                        db.st.setString(2,q.username);
                        db.st.setString(3,q.subject);
                        db.st.executeUpdate();
                           
                     String query1="Create table securequiz."+q.quizid+" (username varchar(30),marks int(10));";
                     db.st=db.con.prepareStatement(query1);
                     System.out.println("Hello");
                //        db.st.setString(1,q.quizid);
                        db.st.execute();
                        System.out.println("Hello2");
                        db.con.close();
               }
           }
           
           else if(ob instanceof Question)
           {
               Question q=(Question)ob;
               
               if(q.key.equals("UPLOADHINT"))
               {
                   DatabaseOperation db=new DatabaseOperation();
                    String query="INSERT INTO hint VALUES(?,?,?,?);";

                 db.st=db.con.prepareStatement(query);
                 db.st.setString(1,q.quizid);
                 db.st.setString(2,q.username);
                 db.st.setString(3,q.questionhint);
                 db.st.setString(4,q.permission);
                 db.st.executeUpdate();
                 db.con.close();
               }
             else
               {
                   DatabaseOperation db=new DatabaseOperation();
                   String query="INSERT INTO questions VALUES(?,?,?,?,?,?,?);";

                 db.st=db.con.prepareStatement(query);
                 db.st.setString(1,q.quizid);
                 db.st.setString(2,q.question);
                 db.st.setString(3,q.option1);
                 db.st.setString(4,q.option2);
                 db.st.setString(5,q.option3);
                 db.st.setString(6,q.option4);
                 db.st.setString(7,q.correct);
                 db.st.executeUpdate();
                 db.con.close();
              }
           }
           else if(ob instanceof Submitresult)
           {
               Submitresult sr=(Submitresult)ob;
               
             DatabaseOperation db=new DatabaseOperation();
             String query="INSERT INTO securequiz."+sr.username+" VALUES(?,?,?,?);";

                 db.st=db.con.prepareStatement(query);
            //     db.st.setString(1,sr.username);
                 db.st.setString(1,sr.quizid);
                 db.st.setInt(2,sr.marks);
                 db.st.setInt(3,sr.numberofquestion);
                 db.st.setInt(4,sr.correctresponse);
                 db.st.executeUpdate();
                 
             String query1="INSERT INTO securequiz."+sr.quizid+" VALUES(?,?);";

                 db.st=db.con.prepareStatement(query1);
            //     db.st.setString(1,sr.quizid);
                 db.st.setString(1,sr.username);
                 db.st.setInt(2,sr.marks);
                 db.st.executeUpdate();    
                
                 db.con.close();
               
           }
           else if(ob instanceof Pair)
           {
               
               Pair p=(Pair)ob;
               if(p.getValue().equals("FETCHRESULT"))
               {
                   String username=(String)p.getKey();
                   String query="Select * from securequiz."+username+" ;";
                   DatabaseOperation db=new DatabaseOperation();
                   db.st=db.con.prepareStatement(query);
                     
                      ResultSet rs=db.st.executeQuery();
                      int count=0;
                      while(rs.next())
                          count=rs.getRow();
                      String s[][]=new String[count][2];
                      rs=db.st.executeQuery();
                      while(rs.next())
                      {
                          int sno=rs.getRow();
                          s[sno-1][0]=rs.getString("username");
                          s[sno-1][1]=String.valueOf(rs.getInt("marks"));
                         
                      }
                      
                      out.writeObject(s);
                    db.con.close();
               }
               else if(p.getValue().equals("FETCHRESULTSTUDENT"))
               {
                   String username=(String)p.getKey();
                   String query="Select * from securequiz."+username+" ;";
                   DatabaseOperation db=new DatabaseOperation();
                   db.st=db.con.prepareStatement(query);
                      // db.st.setString(1,quizid);
                      ResultSet rs=db.st.executeQuery();
                      int count=0;
                      while(rs.next())
                          count=rs.getRow();
                      String s[][]=new String[count][4];
                      rs=db.st.executeQuery();
                      while(rs.next())
                      {
                          int sno=rs.getRow();
                          s[sno-1][0]=rs.getString("quizid");
                          s[sno-1][1]=String.valueOf(rs.getInt("marks"));
                          s[sno-1][2]=String.valueOf(rs.getString("numberofquestion"));
                          s[sno-1][3]=String.valueOf(rs.getInt("correctanswer"));
                         
                      }
                      
                      out.writeObject(s);
                    db.con.close();
               }
               else if(p.getValue().equals("RECIEVEQUIZ"))
               {
                   String quizid=(String)p.getKey();
                   System.out.print(quizid);
                   String query="SELECT * FROM questions WHERE quizid=?;";
                    DatabaseOperation db=new DatabaseOperation();
                    db.st=db.con.prepareStatement(query);
                     db.st.setString(1,quizid);
                      ResultSet rs=db.st.executeQuery();
                      int count=0;
                      while(rs.next())
                          count=rs.getRow();
                      String s[][]=new String[count][7];
                      rs=db.st.executeQuery();
                      while(rs.next())
                      {
                          int sno=rs.getRow();
                          s[sno-1][0]=String.valueOf(sno);
                          s[sno-1][1]=rs.getString("question");
                          s[sno-1][2]=rs.getString("option1");
                          s[sno-1][3]=rs.getString("option2");
                          s[sno-1][4]=rs.getString("option3");
                          s[sno-1][5]=rs.getString("option4");
                          s[sno-1][6]=rs.getString("correctoption");
                         
                      }
                      
                    System.out.println("writing");
                      out.writeObject(s);
                       db.con.close();
                    System.out.println("written");
               }
           }
          
        } catch (IOException | ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
        } 
      
       
    }
}

