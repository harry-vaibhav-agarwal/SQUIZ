/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIENT;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    
    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    public Client() throws IOException
    {
            socket=new Socket("127.0.0.1",5436);
            out=new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());
    }
    public void send(Object ob) throws IOException
    {
        out.writeObject(ob);
    }
    public Object recieve() throws IOException, ClassNotFoundException
    {
        return in.readObject();
    }
}
