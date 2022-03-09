package org.motorin.serverSocket.lesson.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {
    public static void main(String[] args) throws IOException {

        final var url = new URL("file:E:\\Projects\\server-socket-lesson\\src\\org\\motorin\\serverSocket\\lesson\\MyServer.java");
        var urlConnection = url.openConnection();

        byte[] bytes = urlConnection.getInputStream().readAllBytes();
        System.out.println(new String(bytes));
    }

    private static void getGoogle() throws IOException {
        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();   // по умолчанию он создаёт get запросы

        //для создания пост запроса
        urlConnection.setDoOutput(true);

        try(var outputStream = urlConnection.getOutputStream()){
            //поток для записи в тело запроса
        }
    }
}
