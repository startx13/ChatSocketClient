package chatsocketclient;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    private FinestraChat f;
    private Socket socket = null;
    private DataOutputStream out = null;
    private BufferedReader in = null;
    
    public Client(String indirizzo,int porta)
    {
        try
        {
            f = new FinestraChat(this);
            socket = new Socket(indirizzo, porta);
            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Reader r = new Reader(in);
            /*while(socket.isConnected())
            {
                if(takeText())
                    out.writeBytes(testo + "\n");
            }*/
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    public void stampaServer(String s)
    {
        try
        {
            out.writeBytes(s + "\n");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public class Reader implements Runnable
    {
        private BufferedReader in = null;
        Thread t = null;
        public Reader(BufferedReader in)
        {
            this.in = in;
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() 
        {
            while(true)
            {
                try
                {
                    f.setArea(in.readLine());
                    //System.out.println(in.readLine().getBytes());
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
    
