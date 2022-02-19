package com.abdullah.hackerrank.restapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created On:  11:12 PM 18-Feb-22
 *
 * @author Syed Abdullah
 *
 * @see https://mkyong.com/java/java-11-httpclient-examples/
 */

public class RestApiUsingOnlyJava_9 {
    private static final HttpClient httpClientSynchronous = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final  HttpClient httpClientAsynchronous = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private final static ExecutorService executorService = Executors.newFixedThreadPool(5);

    private final static HttpClient httpClientWithExecutorService = HttpClient.newBuilder()
            .executor(executorService)
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, ExecutionException, URISyntaxException {
        SynchronousExample();
        AsynchronousExample();
        CustomExecutorWithConcurrentRequestsExample();
    }

    private static void CustomExecutorWithConcurrentRequestsExample() throws ExecutionException, InterruptedException, URISyntaxException {
        List<URI> targets = Arrays.asList(
                new URI("https://httpbin.org/get?name=mkyong1"),
                new URI("https://httpbin.org/get?name=mkyong2"),
                new URI("https://httpbin.org/get?name=mkyong3"));

        List<CompletableFuture<String>> result = targets.stream()
                .map(RestApiUsingOnlyJava_9::apply)
                .collect(Collectors.toList());

        for (CompletableFuture<String> future : result) {
            System.out.println(future.get());
        }

    }

    private static void AsynchronousExample() throws InterruptedException, ExecutionException, TimeoutException {
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://httpbin.org/get"))
                .GET()
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        CompletableFuture<HttpResponse<String>> response = httpClientSynchronous.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

        System.out.println(result);
    }

    private static void SynchronousExample() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://httpbin.org/get"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClientSynchronous.send(request, HttpResponse.BodyHandlers.ofString());
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
    }

    private static CompletableFuture<String> apply(URI url) {
        return httpClientSynchronous.sendAsync(
                HttpRequest.newBuilder(url)
                        .GET()
                        .setHeader("User-Agent", "Java 11 HttpClient Bot")
                        .build(),
                HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> response.body());
    }
}
