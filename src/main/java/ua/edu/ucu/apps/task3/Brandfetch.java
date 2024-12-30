package ua.edu.ucu.apps.task3;

import java.net.MalformedURLException;
import java.net.URL;

import io.github.cdimascio.dotenv.Dotenv;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Brandfetch implements InfoExtractor {

    private static final Dotenv DOT_ENV = Dotenv.load();
    private static final String API_KEY = DOT_ENV.get("BRANDFETCH_API_KEY");
    private static final String API_URL = "https://api.brandfetch.io/v2/brands/";

    @Override
    public Company extractInfo(String website) {

        URL inputUrl;

        try {
            inputUrl = new URL(website);
        } catch (MalformedURLException e) {
            e.getMessage();
            return new Company();
        }

        String domain = inputUrl.getHost();

        String urlWithDomain = API_URL + domain;
        HttpResponse<JsonNode> response = Unirest.get(urlWithDomain)
                .header("Authorization", "Bearer " + API_KEY)
                .asJson();

        if (response.isSuccess()) {
            return parseResponse(response.getBody());
        }
        else {
            System.out.println("Request failed: " + response.getStatus());
            System.out.println(response.getBody());
        }

        return new Company();
    }

    private Company parseResponse(JsonNode jsonNode) {
        Company company = new Company();

        try {
            JSONObject jsonResponse = jsonNode.getObject();

            if (!jsonResponse.optString("name").equals("")) {
                company.setName(jsonResponse.optString("name"));
            }            
            company.setDescription(jsonResponse.optString("description"));

            if (jsonResponse.has("logos")) {
                String logoUrl = jsonResponse.getJSONArray("logos")
                    .getJSONObject(0)
                    .getJSONArray("formats")
                    .getJSONObject(0)
                    .getString("src");
                company.setLogo(logoUrl);
            }

        } catch (Exception e) {
            System.out.println("Error parsing Brandfetch response: " + e.getMessage());
        }

        return company;
    }


    @Override
    public String getExtractionMethodName() {
        return "Brandfetch";
    }
}
