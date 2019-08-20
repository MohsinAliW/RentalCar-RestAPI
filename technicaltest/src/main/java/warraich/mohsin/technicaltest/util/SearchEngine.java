package warraich.mohsin.technicaltest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import warraich.mohsin.technicaltest.models.*;

public class SearchEngine {
	
	private static final int timeoutMilliseconds = 2000;

    private List<String> supplierEndPoints = new ArrayList<>();


    public SearchEngine() {}

    public SearchResponse newSearch(Location pickUp, Location dropOff, int numberOfPassengers) {

        SearchResponse searchResponse = new SearchResponse();

        for(String supplierEndPoint: supplierEndPoints) {

            URL url = buildUrl(supplierEndPoint, pickUp, dropOff);

            try {
                APIResponse response = makeApiCall(url);

                if (response == null) continue;

                for (Ride options: response.getOptions())  {
                    Ride newRide = new Ride(options.getcar_type(), options.getPrice(), response.getSupplierId());
                    searchResponse.getResultsList().add(newRide);
                }
            }
            catch (java.net.SocketTimeoutException e)
            {
                System.out.println("Connection timeout.");
                continue;
            } catch (IOException e) {
                System.out.println("Could not read the response.");
                continue;
            }

        }

        searchResponse.removeIrrelevantResults(numberOfPassengers);
        searchResponse.sortPriceDescending();

        return searchResponse;
    }

    public SearchResponse newSearch(Location pickUp, Location dropOff) {
        return this.newSearch(pickUp, dropOff, 0);
    }


    public APIResponse makeApiCall(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(timeoutMilliseconds);


        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            System.out.println("Response code was " + responseCode + " for " + url.toString());
            System.out.println("Skipped this supplier");
            return null;
        }

        String responseString = readResponse(connection);

        return deserializeResponse(responseString);
    }

    private APIResponse deserializeResponse(String responseString) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        
        

        return  gson.fromJson(responseString, APIResponse.class);
    }


    private String readResponse(HttpURLConnection connection) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return  response.toString();

    }


    public URL buildUrl(String supplierEndPoint, Location pickUp, Location dropOff)
    {
        StringBuilder stringBuilder = new StringBuilder(supplierEndPoint);

        stringBuilder.append("?pickup=");
        stringBuilder.append(pickUp.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(pickUp.getLongitude());

        stringBuilder.append("&");

        stringBuilder.append("dropoff=");
        stringBuilder.append(dropOff.getLatitude());
        stringBuilder.append(",");
        stringBuilder.append(dropOff.getLongitude());

        try {
            return new URL(stringBuilder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Could not build URL.");
        }

        return null;
    }


    public void addSupplier(String endPoint) {
        supplierEndPoints.add(endPoint);
    }

}
