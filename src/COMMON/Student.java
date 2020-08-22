/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMMON;

import CLIENT.Client;
import java.io.IOException;
import java.io.Serializable;

public class Student implements Serializable{
    public String username,name;
   public String password;
    public String key;
    public int mobilenumber;
    public Student(String username, String name, String password, int mobilenumber,String key) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobilenumber = mobilenumber;
        this.key=key;
    }

    public Student(String username, String password, String key) {
        this.username = username;
        this.password = password;
        this.key = key;
    }
   
    
    
}
