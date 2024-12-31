package ua.edu.ucu.apps.task3;

import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PDLReader implements InfoExtractor {

    private static final Dotenv DOT_ENV = Dotenv.load();
    private static final String API_KEY = DOT_ENV.get("PDL_API_KEY");

    private static final String API_URL 
    = "https://api.peopledatalabs.com/v5/company/enrich?website=";

    @Override
    public Company extractInfo(String website) {

        String text = null;

        try {

            URL inputUrl = new URL(website);
            String domain = inputUrl.getHost();

            URL url = new URL(API_URL + domain);
            
            HttpURLConnection connection = (HttpURLConnection) url
            .openConnection();
            
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);

            text = new Scanner(connection.getInputStream())
            .useDelimiter("\\Z").next();

        }
        
        catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            return new Company();
    
        }
        
        catch (IOException e) {
            System.err.println("Error during HTTP request: " + e.getMessage());
            return new Company();

        }

        if (text != null) {
            JSONObject jsonObject = new JSONObject(text);
            return createCompanyFromJson(jsonObject);
        }

        return new Company();
    }

    private Company createCompanyFromJson(JSONObject jsonObject) {

        Company company = new Company();
        company.setName(jsonObject.optString("name", "Nan"));
        company.setDescription(jsonObject.optString("summary", "Nan"));
        
        return company;
    }

    @Override
    public String getExtractionMethodName() {
        return "PDL Reader";
    }
}
