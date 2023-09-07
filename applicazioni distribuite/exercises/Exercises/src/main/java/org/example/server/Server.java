package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Connection extends Thread{

    private  final Socket client;


    private  int id;
    class Write extends Thread{
        private  PrintWriter out;

        private final Socket client;

        Write(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                this.out=new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (client.isConnected())
            {
                out.println((""+id+" "+Server.queue));
                out.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
         this.out.close();
        }
    }
    class Read extends Thread{
        private  BufferedReader in;
        private final Socket client;

        Read(Socket client) {
            this.client = client;
        }


        @Override
        public void run() {
            try {
                this.in=new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (client.isConnected())
            {
                try {
                    Server.queue=in.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                this.in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public Connection(Socket client, int id) throws IOException {
        this.client=client;


        this.id=id;
        Server.ids++;
    }

    @Override
    public void run() {
        System.out.println("Connessione accettata da: " + client);
        Write writer=new Write(client);
        Read reader=new Read(client);
        writer.start();
        reader.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
public class Server {
    public volatile static String queue="";
    public static int ids=0;

    public static void main(String[] args) {
        ServerSocket server = null; // Il server si mette in ascolto
        try {
            server = new ServerSocket(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while ( true ) //sulla porta di servizio
            try {
                System.out.println("In attesa di connessione...");
                Socket client = server.accept();
                Thread thread=new Thread(new Connection(client,ids));
                thread.start();



            }
            catch (IOException ioe) {

            }
    }
}
