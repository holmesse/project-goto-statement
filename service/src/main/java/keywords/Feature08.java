package keywords;


import java.util.*;
import java.io.*;
import java.net.*;


public class Feature08 {
    public class Response {
        public int status;
        public String json;
        public Response() { status = -1; json = null; }
    }
    
    private Response response;
    
    public void getRequest(String request) {
        response = new Response();
        try {
            URL url = new URL(request); 
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            response.status = conn.getResponseCode();
            if(response.status != 200) {
                response.json = null;
            }
            else {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    output.append(line);
                }
                conn.disconnect();
                response.json = output.toString();
            }
        } 
        catch (IOException e) {}
    }
    
    public int getResponseStatus() {
        return response.status;
    }
    
    public String getResponseString() {
        return response.json;
    }
}