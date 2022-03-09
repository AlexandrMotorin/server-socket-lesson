package org.motorin.serverSocket.lesson.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

public class HttpClientRunner {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000"))
                .header("content-type","application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("resources","first.json")))
                .build();

        var response1 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        var response2 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        var response3 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        var body = response3.get().body();
        System.out.println(body);

    }

}
