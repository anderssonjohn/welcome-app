package dat255.chalmers.com.welcome.BackendInterfaces;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class BackendConnection {


    public static JSONObject sendGet(String subPath, String authToken) {
        JSONObject json = null;

        String dataUrl = "http://95.80.8.206:3030/";
        dataUrl += subPath;
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(dataUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("authorization", "Token token=" + authToken);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setRequestProperty("Accept","application/html");
            connection.setRequestProperty("charset", "utf-8");

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String responseStr = response.toString();
            Log.d("Server response", responseStr);
            // use jason object to save the auth_token locally
            json = new JSONObject(responseStr);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return json;
    }



    public static JSONObject sendPost(String subPath, String urlParameters, String authToken) {
        JSONObject json = null;

        String dataUrl = "http://95.80.8.206:3030/";
        dataUrl += subPath;
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection
            url = new URL(dataUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("authorization", "Token token=" + authToken);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept","*/*");
            connection.setRequestProperty("charset", "utf-8");



            // Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8")); // Ensures data is sent as UTF-8
            writer.write(urlParameters);
            writer.close();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String responseStr = response.toString();
            Log.d("Server response", responseStr);
            // use json object to save the auth_token locally
            json = new JSONObject(responseStr);
//            if (parameters[0].equals("user")) {
//                saveAuthToken(json);
//            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        return json;
    }



}
