/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMMON;

import CLIENT.Client;
import java.io.IOException;
import java.io.Serializable;

public class Teacher implements Serializable{
    public String username,name;
   public String password;
    public String key;
    public int mobilenumber,teacherid;
    public Teacher(String username, String name, String password, int mobilenumber,int teacherid,String key) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobilenumber = mobilenumber;
        this.teacherid=teacherid;
        this.key=key;
    }

    public Teacher(String username, String password,int teacherid, String key) {
        this.username = username;
        this.password = password;
        this.teacherid=teacherid;
        this.key = key;
    }
   
    
    
}
