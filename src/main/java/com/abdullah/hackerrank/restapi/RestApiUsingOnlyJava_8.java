package com.abdullah.hackerrank.restapi;

/**
 * Created On:  1:22 AM 18-Feb-22
 *
 * @author Syed Abdullah
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}

class Solution {
    static Logger logger = Logger.getLogger(String.valueOf(Solution.class));
    /*
     * Complete the 'getTotalGoals' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING team
     *  2. INTEGER year
     */

    public static int getTotalGoals(String team, int year) throws IOException {
        HttpURLConnection conn = null;
        BufferedReader br = null;
        try {
            String baseURL = "https://jsonmock.hackerrank.com/api/football_matches?";

            Integer pageNumber = 1;

            Map<String, String> parametersReq1 = new HashMap<>();
            parametersReq1.put("page", pageNumber.toString());
            parametersReq1.put("team1", team);
            parametersReq1.put("year", String.valueOf(year));


            Map<String, String> parametersReq2 = new HashMap<>();
            parametersReq2.put("page", pageNumber.toString());
            parametersReq2.put("team2", team);
            parametersReq2.put("year", String.valueOf(year));


            String team1URL = baseURL + ParameterStringBuilder.getParamsString(parametersReq1);
            String team2URL = baseURL + ParameterStringBuilder.getParamsString(parametersReq2);


            URL url = new URL(team1URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


                /*conn.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(parametersReq1));
                out.flush();
                out.close();*/


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                content.append(inputLine);
            }
            JSONObject jsonObject = new JSONObject(content.toString());

            System.out.println(jsonObject);

            System.out.println(jsonObject.getValue("page"));

            return Integer.parseInt(jsonObject.getValue("page"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            conn.disconnect();
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String team = "Barcelona";//bufferedReader.readLine();

        int year = 2011;//Integer.parseInt(bufferedReader.readLine().trim());

        int result = getTotalGoals(team, year);
        System.out.println(String.valueOf(result));

            /*bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();*/
    }
}

