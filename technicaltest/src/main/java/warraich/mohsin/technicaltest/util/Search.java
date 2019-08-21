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

public class Search {
	
	private static final int time = 2000;

    private List<String> supplierEndPoints = new ArrayList<>();


    public Search() {}

    public Output newSearch(Location pickUp, Location dropOff, int passengers) {

        Output search = new Output();

        for(String supplierEndPoint: supplierEndPoints) {

            URL url = buildUrl(supplierEndPoint, pickUp, dropOff);

            try {
                Response response = makeApiCall(url);

                if (response != null) {
                	for (Ride options: response.getOptions())  {
                        Ride newRide = new Ride(options.getcar_type(), options.getPrice(), response.getSupplierId());
                        search.getResultsList().add(newRide);
                    }
                }
           
            }
            catch (java.net.SocketTimeoutException e)
            {
                System.out.println("Connection timeout.");
                continue;
            } 
            catch (IOException e) {
                System.out.println("Could not read the response.");
                continue;
            }

        }

        search.removeSmallRides(passengers);
        search.sortRides();

        return search;
    }

    public Output newSearch(Location pickUp, Location dropOff) {
        return this.newSearch(pickUp, dropOff, 0);
    }


    public Response makeApiCall(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(time);


        int responseCode = connection.getResponseCode();

        if (responseCode != 200) {
            System.out.println("Response code was " + responseCode + " for " + url.toString());
            System.out.println("Skipped this supplier");
            return null;
        }

        String responseString = readResponse(connection);

        return deserializeResponse(responseString);
    }

    private Response deserializeResponse(String responseString) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return  gson.fromJson(responseString, Response.class);
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
        StringBuilder url = new StringBuilder(supplierEndPoint);

        url.append("?pickup=");
        url.append(pickUp.getLatitude());
        url.append(",");
        url.append(pickUp.getLongitude());

        url.append("&");

        url.append("dropoff=");
        url.append(dropOff.getLatitude());
        url.append(",");
        url.append(dropOff.getLongitude());

        try {
            return new URL(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Could not build URL.");
            
            return null;
        }    
    }

    public void addSupplier(String endPoint) {
        supplierEndPoints.add(endPoint);
    }

}
