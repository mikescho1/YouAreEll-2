package controllers;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private CloseableHttpClient httpClient;


    public TransactionController() {                        //transaction controller talks to the server
        this.httpClient = HttpClients.createDefault();
    }

    public String makeURLCall(String mainUrl, String method, String jPayLoad) {
       try {
           if (method.equals("GET")) {
               return get(mainUrl);
           }
       }    catch (Exception e) {
           e.printStackTrace();
       }    finally {
           return "nada";

       }

    }

    public String get(String mainUrl) throws IOException {
        HttpGet httpGet = new HttpGet(rootURL + mainUrl);
        CloseableHttpResponse response1 = httpClient.execute(httpGet);
// The underlying HTTP connection is still held by the response object
// to allow the response content to be streamed directly from the network socket.
// In order to ensure correct deallocation of system resources
// the user MUST call CloseableHttpResponse#close() from a finally clause.
// Please note that if response content is not fully consumed the underlying
// connection cannot be safely re-used and will be shut down and discarded
// by the connection manager.
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            String result = new BufferedReader((new InputStreamReader(entity1.getContent())))       //json file is now saved as result.
                    .lines().collect(Collectors.joining("\n"));
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response1.close();
            return null;
        }


    }


}
