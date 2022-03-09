package org.motorin.serverSocket.lesson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) throws IOException {

        try(var server = new ServerSocket(7777);
            var socket = server.accept();
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream());
            var scanner = new Scanner(System.in)){

            var request = inputStream.readUTF();

            while (!"stop".equals(request)){
                System.out.println("Client request: "+request);
                var response = scanner.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }
        }
    }
}
