package ua.edu.ucu.apps.task3;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class ScraperAndAi implements InfoExtractor {
    private static final Dotenv DOT_ENV = Dotenv.load();

    private static final String API_URL 
    = "https://api.openai.com/v1/chat/completions";

    private static final String API_KEY = DOT_ENV.get("AI_API_KEY");
    private static final int MAX_TOKENS = 100;

    @Override
    public Company extractInfo(String website) {

        String scrapedContent = scrapeWebsite(website);

        if (scrapedContent.isEmpty()) {
            System.out.println("Failed to scrape content from website.");
            return new Company();
        }

        String prompt = String.format(
                "Extract the following information from the website " 
                + "content '%s': name, description, and logo URL. Return" 
                + " the result in JSON format with keys 'name', " 
                + "'description', and 'logo'.",
                scrapedContent
        );

        System.out.println(prompt);

        JSONArray messages = new JSONArray();
        messages.put(new JSONObject()
                .put("role", "system")
                .put("content", "You are a helpful assistant " 
                + "specialized in extracting company information."));
        messages.put(new JSONObject()
                .put("role", "user")
                .put("content", prompt));

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "gpt-3.5-turbo");
        jsonBody.put("messages", messages);
        jsonBody.put("max_tokens", MAX_TOKENS);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {

                String responseBody = response.body().string();
                JSONObject responseJson = new JSONObject(responseBody);
                JSONArray choices = responseJson.getJSONArray("choices");

                if (choices.length() > 0) {
                    String content = choices.getJSONObject(0)
                    .getJSONObject("message").getString("content");

                    JSONObject companyInfo = new JSONObject(content);

                    String name = companyInfo.optString("name", "NaN");

                    String description = companyInfo
                    .optString("description", "NaN");

                    String logo = companyInfo.optString("logo", "NaN");

                    return new Company(name, description, logo);
                }
            }

            else {
                System.out.println("Request failed: " + response.code());
                if (response.body() != null) {
                    System.out.println(response.body().string());
                }
            }
        }

        catch (IOException e) {
            e.getMessage();
        }

        return new Company();
    }

    private String scrapeWebsite(String website) {
    
        try {

            Document doc = Jsoup.connect(website).get();
            String title = doc.title();

            String description = doc.select("meta[name=description]")
            .attr("content");

            String logoImage = "NaN";
            
            Element logoElement = doc
            .selectFirst("img[class*=logo], img[id*=logo]");

            if (logoElement != null) {
                logoImage = logoElement.attr("src");

                if (!logoImage.startsWith("http")) {
                    String baseUrl = extractBaseUrl(website);
                    logoImage = baseUrl + logoImage;
                }
            }

            String content = doc.body().text();
            return "Title: " + title + "\nDescription: " + description 
            + "\nLogo URL: " + logoImage + "\nContent: " + content;
        } 
        
        catch (IOException e) {
            System.out.println("Error scraping website: " + e.getMessage());
            return "";
        }
    }

    private String extractBaseUrl(String website) {
        try {
            java.net.URL url = new java.net.URL(website);
            return url.getProtocol() + "://" + url.getHost();
        }
        catch (Exception e) {
            System.out.println("Error extracting base URL: " + e.getMessage());
            return website;
        }
    }

    @Override
    public String getExtractionMethodName() {
        return "WebScraper + OpenAI";
    }
}
