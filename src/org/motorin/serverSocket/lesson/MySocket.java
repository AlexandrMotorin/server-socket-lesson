package org.motorin.serverSocket.lesson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MySocket {
    public static void main(String[] args) throws IOException {

        try(var socket = new Socket("localhost", 7777);
            var outputStream = new DataOutputStream(socket.getOutputStream());
            var inputStream = new DataInputStream(socket.getInputStream());
            var scanner = new Scanner(System.in)) {

                while (scanner.hasNextLine()){
                    var request = scanner.nextLine();
                    outputStream.writeUTF(request);
                    var response = inputStream.readUTF();
                    System.out.println("Response from server: " + response);
                }

        }

    }
}
