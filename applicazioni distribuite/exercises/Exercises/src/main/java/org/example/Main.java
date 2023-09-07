package org.example;

import java.io.*;

import java.net.Socket;


class ThreadReader extends Thread{
    Socket client;
    BufferedReader in;
    public ThreadReader(Socket client) throws IOException {
        this.client=client;
        this.in=new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        super.run();
        try {
            while (client.isConnected())
            {
                System.out.println(in.readLine());
            }
            in.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
class ThreadWriter extends Thread{
    Socket client;
    PrintWriter out;
    public ThreadWriter(Socket client) throws IOException {
        this.client=client;
        this.out=new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
    }

    @Override
    public void run() {
        while (client.isConnected())
        {
            out.println((""+client.getLocalPort()+" "+client.getPort()));
            out.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        out.close();
    }
}
class  ClientInstance extends Thread
{
    @Override
    public void run() {
        try {
            Socket client = new Socket("localhost", 5000); //Il programma client si connette al

//input da console
            while (!client.isConnected())
            {}
            ThreadReader reader = new ThreadReader(client);
            reader.start();
            ThreadWriter writer = new ThreadWriter(client);
            writer.start();

        }
        catch (IOException ioe) {
            System.out.println("Errore di connessione: "+ioe.getMessage());
        }
    }
}
public class Main {
    public static void main(String[] args) {
        ClientInstance clientInstance = new ClientInstance();
        clientInstance.start();
        ClientInstance clientInstance1 = new ClientInstance();
        clientInstance1.start();

    }
    }