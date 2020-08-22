
package CLIENT;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Solvequiz extends Thread {
    JFrame frame;
    static int questionno=0;
    JTextArea textarea;
    JPanel panel;
    JRadioButton b1,b2,b3,b4;
   JButton saveresponse,endthequiz;
    ButtonGroup g;
    int noofques;
    int marks=0,correctresponse=0;
    String s[][];
    JButton bt[];
    String correctanswer[];
    int response[];
    JLabel l;
    public String username,quizid;
    public Solvequiz(String username,String quizid,int noofques,String s[][])
    {
        this.username=username;
        this.quizid=quizid;
        this.noofques=noofques;
        this.s=s;
        correctanswer=new String[noofques];
        for(int i=0;i<noofques;i++)
            correctanswer[i]=s[i][6];

        response=new int[noofques];
        for(int i=0;i<noofques;i++)
            response[i]=-1;
        bt=new JButton[noofques];
        frame=new JFrame("QUIZ");
                initialise();
        panel=new JPanel();
        textarea=new JTextArea("Questions will be displayed in this area.");
        b1=new JRadioButton("Option 1.");
        b2=new JRadioButton("Option 2.");
        b3=new JRadioButton("Option 3.");
        b4=new JRadioButton("Option 4.");
        endthequiz=new JButton("END QUIZ");
        saveresponse=new JButton("SAVE RESPONSE");
        g=new ButtonGroup();
        g.add(b1);
        g.add(b2);
        g.add(b3);
        g.add(b4);
        frame.add(panel);
        frame.add(textarea);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(saveresponse);
        frame.add(endthequiz);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.setVisible(true);
         panel.setBounds(1040,80,200,400);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        textarea.setBounds(20, 80, 1000, 150);
        b1.setBounds(20, 310, 50, 50);
        b2.setBounds(20, 370, 50, 50);
        b3.setBounds(20, 430, 50, 50);
        b4.setBounds(20, 490, 50, 50);
        saveresponse.setBounds(1000,600,150,30);
        saveresponse.setBackground(Color.RED);
        endthequiz.setBounds(1000,530,150,30);
        endthequiz.setBackground(Color.YELLOW);
        for(int i=0;i<noofques;i++)
        {
            bt[i]=new JButton(String.valueOf(i+1));
            bt[i].setPreferredSize(new Dimension(50,50));
              panel.add(bt[i]);
            bt[i].addActionListener(new ActionListener(){
        

                @Override
                public void actionPerformed(ActionEvent e) {
                   String text=((JButton)e.getSource()).getText();
                   questionno=Integer.parseInt(text);
                   questionno--;
                   textarea.setText(s[questionno][1]);
                   b1.setText(s[questionno][2]);
                   b2.setText(s[questionno][3]);
                   b3.setText(s[questionno][4]);
                   b4.setText(s[questionno][5]);
                   bt[questionno].setBackground(Color.ORANGE);
                   if(response[questionno]!=-1)
                   {
                       if(response[questionno]==1)
                           b1.setSelected(true);
                       
                       else if(response[questionno]==2)
                           b2.setSelected(true);
                       
                       else if(response[questionno]==3)
                           b3.setSelected(true);
                       
                       else if(response[questionno]==4)
                           b4.setSelected(true);
                   }
                   
                   else
                   {
                       b1.setSelected(false);
                       b2.setSelected(false);
                       b3.setSelected(false);
                       b4.setSelected(false);
                   }
                
                }
            
      
                
        
            });
         }
        
            saveresponse.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(b1.isSelected())
                {
                    response[questionno]=1;
                    bt[questionno].setBackground(Color.GREEN);
                }
                else if(b2.isSelected())
                {
                     response[questionno]=2;
                     bt[questionno].setBackground(Color.GREEN);
                }
                else if(b3.isSelected())
                {
                     response[questionno]=3;
                     bt[questionno].setBackground(Color.GREEN);
                }
                else if(b4.isSelected())
                {
                     response[questionno]=4;
                     bt[questionno].setBackground(Color.GREEN);
                }
                else
                {
                    response[questionno]=-1;
                    bt[questionno].setBackground(Color.ORANGE);
                }
            }
        });
        
        endthequiz.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    endthequiz.setBackground(Color.GREEN);
                    for(int i=0;i<noofques;i++)
                    {
                        if(response[i]!=-1)
                        {
                            if(response[i]==1 && correctanswer[i].equals("a"))
                                marks++;
                            else if(response[i]==2 && correctanswer[i].equals("b"))
                                marks++;
                            else if(response[i]==3 && correctanswer[i].equals("c"))
                                marks++;
                            else if(response[i]==4 && correctanswer[i].equals("d"))
                                marks++;
                                
                        }
                    }
                    JOptionPane.showMessageDialog(null,"YOU HAVE SCORED "+marks+" out of "+noofques);
                    correctresponse=marks;
                    Submitresult sr=new Submitresult(quizid,username,marks, noofques, correctresponse);
                    Client c=new Client();
                    c.send(sr);
                    studentsignedin s=new studentsignedin(username);
                    s.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
        
                
 }
    void initialise()
    {
         l=new JLabel("TIMER");
         l.setFont(new Font("Tahoma",Font.PLAIN,25));
         l.setBounds(100,20,1000,80);
         frame.add(l);
     
         super.start();
         
    }
                                                                              
             public void run()
             {
                 int totalsecond=noofques*60,minute,second,hour;
                 int key=0;
                 String s="",m="",h="";
                 while(totalsecond>=0)
                 {
                     minute=totalsecond/60;
                     second=totalsecond%60;
                     hour=minute/60;
                     minute=minute%60;
                     totalsecond--;
                     if(totalsecond<=60&&key==0)
                     {
                         l.setBackground(Color.RED);
                         key=1;
                         JOptionPane.showMessageDialog(null,"Hurry Up!");
                     }
                     h=(hour<10)?"0":"";
                     m=(minute<10)?"0":"";
                     s=(second<10)?"0":"";
                     
                     l.setText("Hour : "+h+hour+" Minutes : "+m+minute+" Seconds : "+s+second+"");
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException ex) {
                     ex.printStackTrace();
                     }
                 }
                 
                
                 
                 
                 try {
                    endthequiz.setBackground(Color.GREEN);
                    for(int i=0;i<noofques;i++)
                    {
                        if(response[i]!=-1)
                        {
                            if(response[i]==1 && correctanswer[i].equals("a"))
                                marks++;
                            else if(response[i]==2 && correctanswer[i].equals("b"))
                                marks++;
                            else if(response[i]==3 && correctanswer[i].equals("c"))
                                marks++;
                            else if(response[i]==4 && correctanswer[i].equals("d"))
                                marks++;
                                
                        }
                    }
                    JOptionPane.showMessageDialog(null,"YOU HAVE SCORED "+marks+" out of "+noofques);
                    correctresponse=marks;
                    Submitresult sr=new Submitresult(quizid,username,marks, noofques, correctresponse);
                    Client c=new Client();
                    c.send(sr);
                    studentsignedin st=new studentsignedin(username);
                    st.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                 
                 
                 
                 
                 
                 
                 
                 
                 
             }

         
    
}