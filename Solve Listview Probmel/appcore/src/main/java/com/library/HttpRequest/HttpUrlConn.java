package com.library.HttpRequest;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Rz Rasel on 01-Aug-16.
 */
public class HttpUrlConn {
    // |------------------------------------|
    private Context context;
    // |------------------------------------|
    private String requestUrlStr = null;
    private HttpURLConnection httpURLConnection = null;
    private ListenerHandler listenerHandler;
    private HashMap<String, String> urlHeaderList = new HashMap<String, String>();

    // |------------------------------------|
    private static HttpUrlConn instance = null;

    public static HttpUrlConn getInstance() {
        if (instance == null) {
            instance = new HttpUrlConn();
        }
        return instance;
    }

    // |------------------------------------|
    public interface ListenerHandler {
        public void HTTPDataListener(int argResponseCode, String argHTTPData);
    }

    public void setUrlHeader(HashMap<String, String> argUrlHeaderList) {
        this.urlHeaderList = argUrlHeaderList;
    }

    // |------------------------------------|
    public void postInit(String argRequestUrl, HashMap<String, String> argRequestParams, ListenerHandler argListenerHandler) {
        this.requestUrlStr = argRequestUrl;
        this.listenerHandler = argListenerHandler;
        /*byte[] bytesUrlParams;
        bytesUrlParams = funGetPostParams(argRequestParams);*/
        String urlParams = funGetPostParams(argRequestParams);
        try {
            URL url = new URL(requestUrlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setFixedLengthStreamingMode(urlParams.getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //String userCredentials = "username:password";
            //String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
            //httpURLConnection.setRequestProperty ("Authorization", basicAuth);
            //httpURLConnection.setRequestProperty("jbd-token", "b50e3f767ec8d6e033958fd199fb1a8e");
            if (urlHeaderList.size() > 0) {
                JSONObject jsonObject = new JSONObject(urlHeaderList);
                //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
                for (Map.Entry<String, String> entry : urlHeaderList.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    httpURLConnection.setRequestProperty(key, value);
                }
            }
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            /*OutputStream outputStream = null;
            outputStream = this.httpURLConnection.getOutputStream();
            outputStream.write(urlParams);
            outputStream.close();*/
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(urlParams);
            writer.flush();
            writer.close();
            outputStream.close();
            //httpURLConnection.connect();
            // handle the response
            int httpResponseCode = this.httpURLConnection.getResponseCode();
            String httpData = readHttpUrlData(httpURLConnection);
            if (httpData == null || httpData.isEmpty())
                httpData = "";
            listenerHandler.HTTPDataListener(httpResponseCode, httpData.toString());
            //System.out.println("PRINT_DATA data:- -----------------*******************************" + httpData);
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            System.out.println("PRINT_ERROR Malformed:- " + e.getMessage());
        } catch (ProtocolException e) {
            //e.printStackTrace();
            System.out.println("PRINT_ERROR Protocol:- " + e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("PRINT_ERROR IO:- " + e.getMessage());
        } finally {
            if (httpURLConnection != null) // Make sure the connection is not null.
                httpURLConnection.disconnect();
        }
    }

    // |------------------------------------|
    // |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private String readHttpUrlData(HttpURLConnection argHttpURLConnection) {
        String retVal = "";
        InputStream inputStream = null;
        try {
            if (argHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = argHttpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //sb.append(line + "\n");
                    stringBuilder.append(line);
                }
                inputStream.close();
                argHttpURLConnection.disconnect();
                retVal = stringBuilder.toString();
            } else {
                System.out.println("PRINT_ERROR:- " + "Failed with error code " + argHttpURLConnection.getResponseCode());
                inputStream = argHttpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //sb.append(line + "\n");
                    stringBuilder.append(line);
                }
                inputStream.close();
                argHttpURLConnection.disconnect();
                System.out.println("PRINT_ERROR_STRING: " + stringBuilder.toString());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("PRINT_ERROR:- " + e.getMessage().toString());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("PRINT_ERROR:- " + e.getMessage().toString());
        }
        return retVal;
    }

    // |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
    private String funGetPostParams(Map<String, String> argUrlParameters) {
        //byte[] retVal;
        String retVal = null;
        StringBuilder stringBuilderUrlParams = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = argUrlParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> urlParameters = iterator.next();
            stringBuilderUrlParams.append(urlParameters.getKey()).append('=').append(urlParameters.getValue());
            if (iterator.hasNext()) {
                stringBuilderUrlParams.append('&');
            }
        }
        //String strUrlParams = stringBuilderUrlParams.toString();
        //retVal = strUrlParams.getBytes();
        retVal = stringBuilderUrlParams.toString();
        return retVal;
    }
}