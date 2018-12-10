package com.kt.gigaiot_sdk.network;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.kt.gigaiot_sdk.network.data.SvrResponse;
import com.kt.gigaiot_sdk.util.Utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NP1014425901 on 2015-03-12.
 */
public class HttpTransport {

    private static String TAG = "IOT.HttpTransport";

    public static final int METHOD_GET  = 2001;
    public static final int METHOD_POST = 2002;

    private static final String CONTENT_TYPE    = "Content-Type";   // 헤더에 들어갈 content type key name
    private static final String MIME_PREFIX     = "application/";   // 헤더에 들어갈 mime prefix
    private static final String CHARSET_UTF8    = ";charset=utf-8";
    private static final String MIME_JSON	    = "json" + CHARSET_UTF8;
    private static final String MIME_FORMDATA   = "x-www-form-urlencoded";
    private static final String CONTENT_LENGTH  = "Content-Length";

    private static final String OAUTH_AUTHORIZATION         = "Authorization";
    private static final String OAUTH_GRANT_TYPE_KEY        = "grant_type";
    private static final String OAUTH_GRANT_TYPE_PW         = "password";
    private static final String OAUTH_GRANT_TYPE_CREDENTIAL = "client_credentials";

    private final int CONNECTION_TIMEOUT    = 15000;
    private final int SOCKET_TIMEOUT        = 10000;

    /**
     * GET 방식
     *
     * @param url   request URL
     * @return      서버 응답 String
     */
    public String getJSONfromURL(String accessToken, String url) {

        Gson gson = new Gson();
        InputStream is = null;
        String result = "";

        HttpClient client = null;
        HttpParams params = null;
        HttpEntity entity = null;
        HttpGet httpGet = null;
        HttpResponse response = null;

        try {

            //
            SchemeRegistry registry = new SchemeRegistry();
            params = new BasicHttpParams();
            if(url != null && url.contains("https")){

                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sslSocketFactory = new IotSSLSocketFactory(trustStore);
                sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                registry.register(new Scheme("https", sslSocketFactory, Integer.parseInt("443")));
            }
            else{
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Integer.parseInt("80")));
            }

            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
            client = new DefaultHttpClient(cm, params);
            //

            //client = new DefaultHttpClient();
            httpGet = new HttpGet(url);

            /*params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);*/

            httpGet.addHeader("Authorization", "Bearer " + accessToken);

            response = client.execute(httpGet);

            if(response==null) {
                Log.d(TAG, "HttpTransport.getJSONfromURL() ###HTTP Error### response null");

                return makeUnknownErrorJson();

            }

            Log.i(TAG, "HttpTransport.getJSONfromURL() #### response  status : " + response.getStatusLine());

            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){

                return makeHttpErrorJson(response.getStatusLine());
            }


            entity = response.getEntity();
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch(ConnectTimeoutException e){

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_CONNECT_TIMEOUT, ApiConstants.MSG_CONNECT_TIMEOUT);
            result = JsonHandler.toJson(svrResponse);

        } catch (Exception e) {
            e.printStackTrace();

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_UNKNOWN, ApiConstants.MSG_UNKNOWN + Utils.getStackTrace(e));
            result = JsonHandler.toJson(svrResponse);
        }

        Log.i(TAG, "getJSONfromURL request url = " + url + "\n result = " + result);

        return result;
    }


    /**
     * POST 방식
     *
     * @param url 	    request URL
     * @param jsonData	request Body
     * @return	        서버응답 String
     */
    public String postJsonToURL(String accessToken, String url, String jsonData)
    {
        Gson gson = new Gson();
        InputStream is = null;
        String result = "";

        HttpClient client = null;
        HttpParams params = null;
        HttpEntity httpBody = null;
        HttpPost httpPost = null;
        HttpResponse response = null;

        try
        {
            //
            SchemeRegistry registry = new SchemeRegistry();
            params = new BasicHttpParams();
            if(url != null && url.contains("https")){

                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sslSocketFactory = new IotSSLSocketFactory(trustStore);
                sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                registry.register(new Scheme("https", sslSocketFactory, Integer.parseInt("443")));
            }
            else{
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Integer.parseInt("80")));
            }

            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
            client = new DefaultHttpClient(cm, params);
            //

            //client = new DefaultHttpClient();
            httpBody = new StringEntity(jsonData);
            httpPost = new HttpPost(url);

            /*params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);*/

            httpPost.setEntity(httpBody);


            httpPost.addHeader(CONTENT_TYPE, MIME_PREFIX + MIME_JSON);
            httpPost.addHeader("Authorization", "Bearer " + accessToken);

            Log.d(TAG, CONTENT_LENGTH + " : " + String.valueOf(jsonData.length()));

            response = client.execute(httpPost);

            if(response==null) {
                Log.d(TAG, "HttpTransport.postJsonToURL() ###HTTP Error### response null");

                return makeUnknownErrorJson();

            }

            Log.i(TAG, "HttpTransport.postJsonToURL() #### response  status : " + response.getStatusLine());

            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){

                return makeHttpErrorJson(response.getStatusLine());
            }

            HttpEntity retHe = response.getEntity();

            is = retHe.getContent();


            Header[] headers = response.getHeaders(CONTENT_TYPE);
            if(headers != null)
            {
                for(int i=0;i<headers.length;i++)
                {


                    if(headers[i].getValue().equals(MIME_PREFIX + MIME_JSON))
                    {
                        Log.w(TAG, "HttpTransport.postJsonToURL() ###HTTP Error### ");
                        break;
                    }

                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        }catch(ConnectTimeoutException e){

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_CONNECT_TIMEOUT, ApiConstants.MSG_CONNECT_TIMEOUT);
            result = JsonHandler.toJson(svrResponse);

        } catch (Exception e) {
            e.printStackTrace();

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_UNKNOWN, ApiConstants.MSG_UNKNOWN + Utils.getStackTrace(e));
            result = JsonHandler.toJson(svrResponse);
        }

        Log.i(TAG, "postJsonToURL request url = " + url + "\n result = " + result);

        if (TextUtils.isEmpty(result)) {               //Http Status 200 OK 에도 불구하고 Content가 없는 경우
            return makeUnknownErrorJson();
        }

        return result;
    }

    /**
     * PUT 방식
     *
     * @param url 	    request URL
     * @param jsonData	request Body
     * @return	        서버응답 String
     */
    public String putJsonToURL(String accessToken, String url, String jsonData)
    {
        InputStream is = null;
        String result = "";

        HttpClient client = null;
        HttpParams params = null;
        HttpEntity httpBody = null;
        HttpPut httpPut = null;
        HttpResponse response = null;

        try
        {

            //
            SchemeRegistry registry = new SchemeRegistry();
            params = new BasicHttpParams();
            if(url != null && url.contains("https")){

                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sslSocketFactory = new IotSSLSocketFactory(trustStore);
                sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                registry.register(new Scheme("https", sslSocketFactory, Integer.parseInt("443")));
            }
            else{
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Integer.parseInt("80")));
            }

            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
            client = new DefaultHttpClient(cm, params);
            //

            //client = new DefaultHttpClient();
            httpBody = new StringEntity(jsonData);
            httpPut = new HttpPut(url);

            /*params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);*/

            httpPut.setEntity(httpBody);


            httpPut.addHeader(CONTENT_TYPE, MIME_PREFIX + MIME_JSON);
            httpPut.addHeader("Authorization", "Bearer " + accessToken);

            Log.d(TAG, CONTENT_LENGTH + " : " + String.valueOf(jsonData.length()));

            response = client.execute(httpPut);

            if(response==null) {
                Log.d(TAG, "HttpTransport.putJsonToURL() ###HTTP Error### response null");

                return makeUnknownErrorJson();

            }

            Log.i(TAG, "HttpTransport.putJsonToURL() #### response  status : " + response.getStatusLine());

            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){

                return makeHttpErrorJson(response.getStatusLine());
            }

            HttpEntity retHe = response.getEntity();

            is = retHe.getContent();


            Header[] headers = response.getHeaders(CONTENT_TYPE);
            if(headers != null)
            {
                for(int i=0;i<headers.length;i++)
                {


                    if(headers[i].getValue().equals(MIME_PREFIX + MIME_JSON))
                    {
                        Log.w(TAG, "HttpTransport.putJsonToURL() ###HTTP Error### ");
                        break;
                    }

                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        }catch(ConnectTimeoutException e){

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_CONNECT_TIMEOUT, ApiConstants.MSG_CONNECT_TIMEOUT);
            result = JsonHandler.toJson(svrResponse);

        } catch (Exception e) {
            e.printStackTrace();

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_UNKNOWN, ApiConstants.MSG_UNKNOWN + Utils.getStackTrace(e));
            result = JsonHandler.toJson(svrResponse);
        }

        Log.i(TAG, "putJsonToURL request url = " + url + "\n result = " + result);

        return result;
    }


    /**
     * DELETE 방식
     *
     * @param url 	    request URL
     * @return	        서버응답 String
     */
    public String deleteFromURL(String accessToken, String url)
    {
        InputStream is = null;
        String result = "";

        HttpClient client = null;
        HttpParams params = null;
        HttpDelete httpDelete = null;
        HttpResponse response = null;

        try
        {

            SchemeRegistry registry = new SchemeRegistry();
            params = new BasicHttpParams();
            if(url != null && url.contains("https")){

                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sslSocketFactory = new IotSSLSocketFactory(trustStore);
                sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                registry.register(new Scheme("https", sslSocketFactory, Integer.parseInt("443")));
            }
            else{
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Integer.parseInt("80")));
            }

            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
            client = new DefaultHttpClient(cm, params);

            httpDelete = new HttpDelete(url);


            httpDelete.addHeader(CONTENT_TYPE, MIME_PREFIX + MIME_JSON);
            httpDelete.addHeader("Authorization", "Bearer " + accessToken);

            response = client.execute(httpDelete);

            if(response==null) {
                Log.d(TAG, "HttpTransport.deleteFromURL() ###HTTP Error### response null");

                return makeUnknownErrorJson();

            }

            Log.i(TAG, "HttpTransport.deleteFromURL() #### response  status : " + response.getStatusLine());

            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){

                return makeHttpErrorJson(response.getStatusLine());
            }

            HttpEntity retHe = response.getEntity();

            is = retHe.getContent();


            Header[] headers = response.getHeaders(CONTENT_TYPE);
            if(headers != null)
            {
                for(int i=0;i<headers.length;i++)
                {


                    if(headers[i].getValue().equals(MIME_PREFIX + MIME_JSON))
                    {
                        Log.w(TAG, "HttpTransport.deleteFromURL() ###HTTP Error### ");
                        break;
                    }

                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        }catch(ConnectTimeoutException e){

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_CONNECT_TIMEOUT, ApiConstants.MSG_CONNECT_TIMEOUT);
            result = JsonHandler.toJson(svrResponse);

        } catch (Exception e) {
            e.printStackTrace();

            SvrResponse<Void> svrResponse = new SvrResponse(ApiConstants.CODE_UNKNOWN, ApiConstants.MSG_UNKNOWN + Utils.getStackTrace(e));
            result = JsonHandler.toJson(svrResponse);
        }

        Log.i(TAG, "deleteFromURL request url = " + url + "\n result = " + result);

        return result;
    }

    public String uploadFile(String accessToken, String url, String filePath){

        Log.d(TAG, "uploadFile fileName = " + filePath.substring(filePath.lastIndexOf("/") + 1) );

        String result = "";
        InputStream is = null;

        String boundary = "^******^";       //데이터 구분문자
        String delimiter = "\r\n--" + boundary + "\r\n"; // 데이터 경계선


        try {

            URL connectURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection();

            //읽기와 쓰기 모두 가능하게 설정
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //캐시를 사용하지 않게 설정
            conn.setUseCaches(false);

            //POST타입으로 설정
            conn.setRequestMethod("POST");

            //헤더 설정
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            FileInputStream fileInputStream = new FileInputStream(new File(filePath));

            //Output스트림을 열어
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes("--" + boundary + "\r\n");
            dos.writeBytes("Content-Disposition: form-data; name=\"atcFile\";filename=\""+ filePath.substring(filePath.lastIndexOf("/") + 1) +"\"" + "\r\n");
            dos.writeBytes("\r\n");

            //버퍼사이즈를 설정하여 buffer할당
            int bytesAvailable = fileInputStream.available();
            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[] buffer = new byte[bufferSize];

            //스트림에 작성
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0)
            {
                // Upload file part(s)
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            dos.writeBytes("\r\n");
            dos.writeBytes("--" + boundary + "--" + "\r\n");
            fileInputStream.close();

            //써진 버퍼를 stream에 출력.
            dos.flush();

            // 결과 반환 (HTTP RES CODE)
            is = conn.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

            conn.disconnect();

        }catch(Exception e){

            e.printStackTrace();

        }

        Log.d(TAG, "uploadFile url = " + url + " | filePath = " + filePath + "\n result = " + result);

        return result;

    }



    public String getAccessToken(String url, String clientId, String clientSecret, String id, String password)
    {
        InputStream is = null;
        String result = "";

        HttpClient client = null;
        HttpParams params = null;
        HttpPost httpPost = null;
        HttpResponse response = null;

        try
        {

            //
            SchemeRegistry registry = new SchemeRegistry();
            params = new BasicHttpParams();
            if(url != null && url.contains("https")){

                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sslSocketFactory = new IotSSLSocketFactory(trustStore);
                sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                registry.register(new Scheme("https", sslSocketFactory, Integer.parseInt("443")));
            }
            else{
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), Integer.parseInt("80")));
            }

            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

            ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
            client = new DefaultHttpClient(cm, params);
            //

            //client = new DefaultHttpClient();
            httpPost = new HttpPost(url);

            /*params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);*/


            String authorizationString = "Basic " + Base64.encodeToString((clientId + ":" + clientSecret).getBytes(),Base64.NO_WRAP); //Base64.NO_WRAP flag

            httpPost.addHeader(OAUTH_AUTHORIZATION, authorizationString);

            List<NameValuePair> nameValuePairs = new ArrayList<>();

            if(TextUtils.isEmpty(id)){
                nameValuePairs.add(new BasicNameValuePair(OAUTH_GRANT_TYPE_KEY, OAUTH_GRANT_TYPE_CREDENTIAL));
            }else {

                nameValuePairs.add(new BasicNameValuePair(OAUTH_GRANT_TYPE_KEY, OAUTH_GRANT_TYPE_PW));
                nameValuePairs.add(new BasicNameValuePair("username", id));
                nameValuePairs.add(new BasicNameValuePair("password", password));
            }


            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            Log.i(TAG, "HttpTransport.getAccessToken() #### request url : " + url);
            Log.i(TAG, "HttpTransport.getAccessToken() #### authorization : " + authorizationString);

            response = client.execute(httpPost);

            if(response==null) {
                Log.d(TAG, "HttpTransport.getAccessToken() ###HTTP Error### response null");

                return makeOAuthUnknownErrorJson();

            }

            Log.i(TAG, "HttpTransport.getAccessToken() #### response  status : " + response.getStatusLine());

            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){

                return makeOAuthHttpErrorJson(response.getStatusLine());
            }

            HttpEntity retHe = response.getEntity();

            is = retHe.getContent();


            Header[] headers = response.getHeaders(CONTENT_TYPE);
            if(headers != null)
            {
                for(int i=0;i<headers.length;i++)
                {


                    if(headers[i].getValue().equals(MIME_PREFIX + MIME_JSON))
                    {
                        Log.w(TAG, "HttpTransport.getAccessToken() ###HTTP Error### ");
                        break;
                    }

                }
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        }catch(ConnectTimeoutException e){

            result = makeOAuthTimeoutErrorJson();

        } catch (Exception e) {
            e.printStackTrace();

            result = makeOAuthUnknownErrorJson();
        }

        Log.i(TAG, "getAccessToken request url = " + url + "\n result = " + result);

        return result;
    }


    private String makeOAuthTimeoutErrorJson(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("error", ApiConstants.CODE_CONNECT_TIMEOUT);
            obj.put("error_description", ApiConstants.MSG_CONNECT_TIMEOUT);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return obj.toString();
    }

    private String makeOAuthHttpErrorJson(StatusLine statusLine){

        JSONObject obj = new JSONObject();

        try {
            obj.put("error", ApiConstants.CODE_NG);
            obj.put("error_description", statusLine.getReasonPhrase());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return obj.toString();

    }

    private String makeOAuthUnknownErrorJson(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("error", ApiConstants.CODE_NG);
            obj.put("error_description", "Unknown");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return obj.toString();
    }

    private String makeHttpErrorJson(StatusLine statusLine){

        JSONObject obj = new JSONObject();

        try {
            obj.put("responseCode", ApiConstants.CODE_NG);
            obj.put("message", statusLine.getReasonPhrase());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return obj.toString();

    }

    private String makeUnknownErrorJson(){
        JSONObject obj = new JSONObject();

        try {
            obj.put("responseCode", ApiConstants.CODE_NG);
            obj.put("message", "Unknown");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return obj.toString();
    }

}
