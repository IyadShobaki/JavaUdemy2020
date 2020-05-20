package com.shobaki;

import java.io.IOException;
import java.net.ServerSocket;


public class Main {
    //We have to update the code, so the server can handle each client
    //on a separate thread
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {  //the port number can be between 0 - 65,535 inclusive
            while (true) {
                new Echoer(serverSocket.accept()).start();
                //the equivalent code
//                Socket socket = serverSocket.accept();
//                Echoer echoer = new Echoer(socket);
//                echoer.start();

            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}


// 1
//package com.shobaki;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Main {
//        //We have to update the code, so the server can handle each client
//        //on a separate thread
//    public static void main(String[] args) {
//        try (ServerSocket serverSocket = new ServerSocket(5000)) {  //the port number can be between 0 - 65,535 inclusive
//
//            //move the next lines to while loop so the server can accept more client connections
////            Socket socket = serverSocket.accept();
////            System.out.println("Client Connected");
////            BufferedReader input = new BufferedReader(
////                    new InputStreamReader(socket.getInputStream()));
////            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//
//            while (true) {
//we moved all the following code to Echoer class
//--------------------------------------------
//                Socket socket = serverSocket.accept();
//                System.out.println("Client Connected");
//                BufferedReader input = new BufferedReader(
//                        new InputStreamReader(socket.getInputStream()));
//                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//
//                String echoString = input.readLine();
//                try{
//                    Thread.sleep(15000);
//                }catch(InterruptedException e){
//                    System.out.println("Thread interrupted");
//                }
//                if (echoString.equals("exit")) {
//                    break;
//                }
//                output.println("Echo from server: " + echoString);
//--------------------------------------------------
//            }
//        } catch (IOException e) {
//            System.out.println("Server exception " + e.getMessage());
//        }
//    }
//}
