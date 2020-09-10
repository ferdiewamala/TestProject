package com.example.testproject;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiUtility {
    private static final String QUERY_PARAMETER_KEY = "api";

    private ApiUtility () {}

    public static final String BASE_API_URL = "https://gadsapi.herokuapp.com";

    public static URL buildUrl (String title) {
        URL url = null;
        Uri uri= Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .build();
        try {
            url = new URL(url.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return url;
        }

    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner= new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if(hasData) {
                return scanner.next();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            return null;
        } finally {
            connection.disconnect();
        }
    }
}
