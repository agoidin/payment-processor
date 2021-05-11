package com.payments.paymentprocessor.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CountryByIPService {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String clientIp) throws IOException, JSONException {
        try (InputStream is = new URL(
                "http://ip-api.com/json/" + clientIp + "?fields=status,country"
        ).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public static String getRequestStatus(String clientIp) {
        try {
            return readJsonFromUrl(clientIp).get("status").toString();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getRequestCountry(String clientIp) {
        try {
            return readJsonFromUrl(clientIp).get("country").toString();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
