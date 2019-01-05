package org.ljelic.instafram.util.api;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    private final String url;
    private final Method method;
    private final Map<String, String> headers;
    private final Map<String, String> parameters;
    private ResponseListener listener;

    public RequestBuilder(String url) {
        this(url, Method.POST);
    }

    private RequestBuilder(String url, Method method) {
        this.url = url;
        this.method = method;

        headers = new HashMap<>();
        parameters = new HashMap<>();
    }

    public RequestBuilder addHeader(String key, String value) {
        headers.put(key, value);

        return this;
    }

    public RequestBuilder addParameter(String key, String value) {
        parameters.put(key, value);

        return this;
    }

    public RequestBuilder setListener(ResponseListener listener) {
        this.listener = listener;

        return this;
    }

    public void build() throws IOException {
        HttpsURLConnection con = getConnection();

        makeRequest(con);

        Response response = getResponse(con);

        notifyListener(response);
    }

    private HttpsURLConnection getConnection() throws IOException {
        HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
        con.setRequestMethod(method.name());
        con.setDoOutput(true);
        setHeaders(con);

        return con;
    }

    private void makeRequest(HttpsURLConnection con) throws IOException {
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(con.getOutputStream());
            setParameters(out);
            out.flush();
        }finally {
            if(out != null) {
                out.close();
            }
        }
    }

    private void setHeaders(HttpsURLConnection con) {
        for(String key : headers.keySet()) {
            con.setRequestProperty(key, headers.get(key));
        }
    }

    private void setParameters(DataOutputStream out) throws IOException {
        int i = 0;

        for(String key : parameters.keySet()) {
            if(i++ > 0) {
                out.writeBytes("&");
            }

            out.writeBytes(key + "=" + URLEncoder.encode(parameters.get(key), "UTF-8"));
        }
    }

    private Response getResponse(HttpsURLConnection con) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStream in = null;

        try {
            in = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;

            while((line = br.readLine()) != null) {
                builder.append(line);
            }
        }finally {
            if(in != null) {
                in.close();
            }
        }

        String line = builder.toString();
        String code = line.substring(0, line.indexOf(' '));
        String message = line.substring(line.indexOf(' ') + 1);

        return new Response(Integer.parseInt(code), message);
    }

    private void notifyListener(Response response) {
        if(listener != null) {
            listener.onResponse(response.getCode(), response.getMessage());
        }
    }

    private static class Response {

        private final int code;
        private final String message;

        Response(int code, String message) {
            this.code = code;
            this.message = message;
        }

        int getCode() {
            return code;
        }

        String getMessage() {
            return message;
        }
    }
}