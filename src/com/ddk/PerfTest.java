package com.ddk;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.Perf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class PerfTest {

    public PerfTest(int totalRequests, List<String> urls) throws IOException {
        try {
            httpClient = HttpClients.createDefault();
            this.urls = urls;
            ExecutorService es = Executors.newFixedThreadPool(5); // 5 concurrent threads
            List<Future<Counters>> futureList = new ArrayList<>();

            // Compute Iteration
            // Example:
            //      10 requests / 11 URLs: (0 Iteration) + 10 requests
            //      11 requests / 10 URLs: (1 Iteration * 10 URLs) + 10 requests
            //      10 requests / 10 URLs: (1 Iteration * 10 URLs)
            //      10 requests / 3 URLs: (3 Iteration * 3 URLs) + 1 request
            int numIteration = totalRequests / urls.size();
            int reminderRequest = totalRequests % urls.size();
            for (int i = 0; i < numIteration; i++) {
                futureList.add(es.submit(new PerfThread(urls, httpClient)));
            }
            if (reminderRequest > 0)
                futureList.add(es.submit(new PerfThread(urls.subList(0, reminderRequest), httpClient)));

            try {
                for (Future<Counters> future : futureList) {
                    Counters c = future.get();
                    System.out.println("Total Requests Completed: " + c.numRequests + ", NumErrors: " + c.error + ", AvgLatency: " + average(c.elapsedTime));
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            es.shutdown();
        } finally {
            httpClient.close();
        }
    }

    public static void main(String[] args) throws IOException {
        int totalRequests = 100;
        List<String> urls = new ArrayList<String>() {{
            add("http://localhost:2222/karthiservice/v1/addTwoFractions?f1=2/9&f2=7/12");
            add("http://localhost:2222/karthiservice/v1/addTwoFractions?f1=2/4&f2=4/8");
            add("http://localhost:2222/karthiservice/v1/addTwoFractions?f1=3/7&f2=11/13");
            add("http://localhost:2222/karthiservice/v1/addtwolinkedlist?num1=999&num2=999");
            add("http://localhost:2222/karthiservice/v1/addtwolinkedlist?num1=200&num2=022");
            add("http://localhost:2222/karthiservice/v1/addtwolinkedlist?num1=1000&num2=999");
            add("http://localhost:2222/karthiservice/v1/validateCreditCard?creditCard=5196081888500645");
            add("http://localhost:2222/karthiservice/v1/validateCreditCard?creditCard=379354508162306");
            add("http://localhost:2222/karthiservice/v1/validateCreditCard?creditCard=4388576018402626");
        }};

        new PerfTest(100, urls);
    }

    private double average(List<Long> values) {
        if ( values == null || values.isEmpty() )
            return 0.0;
        long sum = 0;
        for ( Long v : values )
            sum += v.intValue();
        return (1.0 * sum) / values.size();
    }

    CloseableHttpClient httpClient;
    List<String> urls;
}

// Counter class to keep track of number of requests, number of errors and latencies per thread
class Counters {
    public Counters() {
        numRequests = 0;
        elapsedTime = new ArrayList<>();
        error = 0;
        response = new Hashtable<String, Object>();
    }

    public int numRequests;
    public List<Long> elapsedTime;
    public int error;
    public Map<String, Object> response;
}

class PerfThread implements Callable<Counters> {

    public PerfThread(List<String> urls, CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
        this.urls = urls;
    }

    // Callable Thread
    @Override
    public Counters call() {
        Counters counters = new Counters();
        for ( String url : urls ) {
            doGet(url, counters);
        }
        return counters;
    }
    // Do Http Get
    private void doGet(String url, Counters counters) {
        HttpGet method = new HttpGet(url);
        long startTime = System.currentTimeMillis();
        try (CloseableHttpResponse httpResponse = httpClient.execute(method)) {
            handleResponse(httpResponse, counters);
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            counters.elapsedTime.add(System.currentTimeMillis() - startTime);
        }
    }

    // Record response in Counters Object
    private static void handleResponse(CloseableHttpResponse httpResponse, Counters counters) throws IOException {
        counters.response.put("responseCode", httpResponse.getStatusLine().getStatusCode());
        counters.response.put("responseMessage", httpResponse.getStatusLine().getReasonPhrase());
        if (httpResponse.getEntity() != null)
            counters.response.put("responseBody", EntityUtils.toString(httpResponse.getEntity()));
        counters.response.put("responseHeaders", httpResponse.getAllHeaders());
        if (httpResponse.getStatusLine().getStatusCode() != 200)
            counters.error++;
        counters.numRequests++;
    }

    List<String> urls;
    CloseableHttpClient httpClient;
}
