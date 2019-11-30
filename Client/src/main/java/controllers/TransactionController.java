package controllers;


import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
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
            } else if (method.equals("PUT")) {
                return put(mainUrl, jPayLoad);
            } else if (method.equals("POST")) {
                return post(mainUrl, jPayLoad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "nadada";


    }

    public String get(String mainUrl) throws IOException {      //these methods are copied from apache httpc.client

        CloseableHttpResponse response1 = null;
// The underlying HTTP connection is still held by the response object
// to allow the response content to be streamed directly from the network socket.
// In order to ensure correct deallocation of system resources
// the user MUST call CloseableHttpResponse#close() from a finally clause.
// Please note that if response content is not fully consumed the underlying
// connection cannot be safely re-used and will be shut down and discarded
// by the connection manager.
        try {
            HttpGet httpGet = new HttpGet(rootURL + mainUrl);
            response1 = httpClient.execute(httpGet);
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
        }
        return null;
    }


    public String put(String mainUrl, String jPayLoad) throws IOException {     //this code is identical to post except that we changed all "post" to "put"
        CloseableHttpResponse response1 = null;
        HttpPut httpPut = new HttpPut(rootURL + mainUrl);
        httpPut.setEntity(new StringEntity(jPayLoad));
        CloseableHttpResponse response2 = httpClient.execute(httpPut);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response2.close();
        }
        return null;
    }

    public String post(String mainUrl, String jPayLoad) throws IOException {    //these methods are copied from apache httpc.client
        CloseableHttpResponse response1 = null;
        HttpPost httpPost = new HttpPost(rootURL + mainUrl);
        httpPost.setEntity(new StringEntity(jPayLoad));
        CloseableHttpResponse response2 = httpClient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response2.close();
        }
        return null;

    }
}
