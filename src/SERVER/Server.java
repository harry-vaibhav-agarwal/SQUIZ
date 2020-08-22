package SERVER;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
    
    public static void main(String args[]) throws IOException, ClassNotFoundException
    {
        ServerSocket serversocket;
        Socket socket=null;
        
        
        serversocket=new ServerSocket(5436);
        while(true)
        {
           socket=serversocket.accept();
           HandleClient handleclient =new HandleClient(socket);
           Thread t=new Thread(handleclient);
           t.start();
        }
    }
}