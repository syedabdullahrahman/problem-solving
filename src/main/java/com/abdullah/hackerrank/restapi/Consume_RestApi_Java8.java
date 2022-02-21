package com.abdullah.hackerrank.restapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

enum CONSTANTS {

    CURLY_OPEN_BRACKETS('{'),
    CURLY_CLOSE_BRACKETS('}'),
    SQUARE_OPEN_BRACKETS('['),
    SQUARE_CLOSE_BRACKETS(']'),
    COLON(':'),
    COMMA(','),
    SPECIAL('|');

    private final char constant;

    // Constructor
    CONSTANTS(char constant) { this.constant = constant; }

    // Method
    // Overriding exiting toString() method
    @Override public String toString()
    {
        return String.valueOf(constant);
    }
}

// Class 1
// To parse json object
class JSONObject {

    private final static char specialChar;
    private final static char commaChar;
    private HashMap<String, String> objects;

    static
    {
        specialChar = String.valueOf(CONSTANTS.SPECIAL)
                .toCharArray()[0];
        commaChar = String.valueOf(CONSTANTS.COMMA)
                .toCharArray()[0];
    }

    // Constructor if this class
    public JSONObject(String arg) { getJSONObjects(arg); }

    // Method 1
    // Storing json objects as key value pair in hash map
    public void getJSONObjects(String arg)
    {

        objects = new HashMap<String, String>();

        if (arg.startsWith(String.valueOf(
                CONSTANTS.CURLY_OPEN_BRACKETS))
                && arg.endsWith(String.valueOf(
                CONSTANTS.CURLY_CLOSE_BRACKETS))) {

            StringBuilder builder = new StringBuilder(arg);
            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);
            builder = replaceCOMMA(builder);

            for (String objects : builder.toString().split(
                    String.valueOf(CONSTANTS.COMMA))) {

                String[] objectValue = objects.split(
                        String.valueOf(CONSTANTS.COLON), 2);

                if (objectValue.length == 2)
                    this.objects.put(
                            objectValue[0]
                                    .replace("'", "")
                                    .replace("\"", ""),
                            objectValue[1]
                                    .replace("'", "")
                                    .replace("\"", ""));
            }
        }
    }

    // Method 2
    public StringBuilder replaceCOMMA(StringBuilder arg)
    {

        boolean isJsonArray = false;

        for (int i = 0; i < arg.length(); i++) {
            char a = arg.charAt(i);

            if (isJsonArray) {

                if (String.valueOf(a).compareTo(
                        String.valueOf(CONSTANTS.COMMA))
                        == 0) {
                    arg.setCharAt(i, specialChar);
                }
            }

            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.SQUARE_OPEN_BRACKETS))
                    == 0)
                isJsonArray = true;
            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.SQUARE_CLOSE_BRACKETS))
                    == 0)
                isJsonArray = false;
        }

        return arg;
    }

    // Method 3
    // Getting json object value by key from hash map
    public String getValue(String key)
    {
        if (objects != null) {
            return objects.get(key).replace(specialChar,
                    commaChar);
        }
        return null;
    }

    // Method 4
    // Getting json array by key from hash map
    public JSONArray getJSONArray(String key)
    {
        if (objects != null)
            return new JSONArray(
                    objects.get(key).replace('|', ','));
        return null;
    }
}

// Class 2
// To parse json array
class JSONArray {

    private final static char specialChar;
    private final static char commaChar;

    private ArrayList<String> objects;

    static
    {
        specialChar = String.valueOf(CONSTANTS.SPECIAL)
                .toCharArray()[0];
        commaChar = String.valueOf(CONSTANTS.COMMA)
                .toCharArray()[0];
    }

    // Constructor of this class
    public JSONArray(String arg) { getJSONObjects(arg); }

    // Method 1
    // Storing json objects in array list
    public void getJSONObjects(String arg)
    {

        objects = new ArrayList<String>();

        if (arg.startsWith(String.valueOf(
                CONSTANTS.SQUARE_OPEN_BRACKETS))
                && arg.endsWith(String.valueOf(
                CONSTANTS.SQUARE_CLOSE_BRACKETS))) {

            StringBuilder builder = new StringBuilder(arg);

            builder.deleteCharAt(0);
            builder.deleteCharAt(builder.length() - 1);

            builder = replaceCOMMA(builder);

            // Adding all elements
            // using addAll() method of Collections class
            Collections.addAll(
                    objects,
                    builder.toString().split(
                            String.valueOf(CONSTANTS.COMMA)));
        }
    }

    // Method 2
    public StringBuilder replaceCOMMA(StringBuilder arg)
    {
        boolean isArray = false;

        for (int i = 0; i < arg.length(); i++) {
            char a = arg.charAt(i);
            if (isArray) {

                if (String.valueOf(a).compareTo(
                        String.valueOf(CONSTANTS.COMMA))
                        == 0) {
                    arg.setCharAt(i, specialChar);
                }
            }

            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.CURLY_OPEN_BRACKETS))
                    == 0)
                isArray = true;

            if (String.valueOf(a).compareTo(String.valueOf(
                    CONSTANTS.CURLY_CLOSE_BRACKETS))
                    == 0)
                isArray = false;
        }

        return arg;
    }

    // Method  3
    // Getting json object by index from array list
    public String getObject(int index)
    {
        if (objects != null) {
            return objects.get(index).replace(specialChar,
                    commaChar);
        }

        return null;
    }

    // Method 4
    // Getting json object from array list
    public JSONObject getJSONObject(int index)
    {

        if (objects != null) {
            return new JSONObject(
                    objects.get(index).replace('|', ','));
        }

        return null;
    }
}

class Result {

    /*
     * Complete the 'getUsernames' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts INTEGER threshold as parameter.
     *
     * URL for cut and paste
     * https://jsonmock.hackerrank.com/api/article_users?page=<pageNumber>
     */

    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/article_users?";
    private static final Logger logger = Logger.getLogger(Result.class.getName());

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

    private static JSONObject getJSONObject(final HttpURLConnection connection) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            content.append(inputLine);
        }
        return new JSONObject(content.toString());
    }

    public static List<String> getUsernames(int threshold) {
        List<String> userNameList = new ArrayList<>();
        Integer pageNumber = 1;
        HttpURLConnection connection = null;

        final Map<String, String> parametersReq1 = new HashMap<>();
        parametersReq1.put("page", pageNumber.toString());
        try{
            final String GET_URL = BASE_URL + getParamsString(parametersReq1);

            URL url = new URL(GET_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");


            if (connection.getResponseCode() != 200) {
                logger.log(Level.SEVERE,"Failed to GET User List : HTTP error code : " + connection.getResponseCode());
                throw new RuntimeException("Failed to GET User List : HTTP error code :" + connection.getResponseCode());
            }

            JSONObject jsonObject = getJSONObject(connection);
            String dataArrayJSON = jsonObject.getValue("data");
            JSONObject dataArray = new JSONObject(dataArrayJSON);



        } catch (UnsupportedEncodingException unsupportedEncodingException){
            logger.log(Level.SEVERE,unsupportedEncodingException.getMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userNameList;
    }
}

class Solution2 {
    public static void main(String[] args) throws IOException {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int threshold = Integer.parseInt(bufferedReader.readLine().trim());*/

        List<String> result = Result.getUsernames(10);

        /*bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();*/
    }
}
