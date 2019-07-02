package sef.module12.activity;

import java.io.*;
import java.net.InetAddress;//
import java.net.Socket;//
import java.net.UnknownHostException;//
import java.util.Scanner;

public class ClientChat {

    public static void main(String arg[]){

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner i = new Scanner(System.in);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run(){
                    this.start();
                }

                public void start() {
                    try {
                        while(true)
                            System.out.println(in.readLine());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            String line = "";
            while (true){
                line = i.nextLine();
                out.println(line);
                if(line.equalsIgnoreCase("exit"))
                    break;
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        }
    }
}
