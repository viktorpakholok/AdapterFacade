package ua.edu.ucu.apps.task3;

import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
// import java.net.URLEncoder;
// import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLReader {
    public static void main(String[] args) throws IOException {

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");

        URL url = new URL(
        "https://api.peopledatalabs.com/v5/company/enrich?website=ucu.edu.ua"
        );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", apiKey);
        connection.connect();

        String text = new Scanner(connection.getInputStream())
        .useDelimiter("\\Z").next();

        JSONObject jsonObject = new JSONObject(text);
        System.out.println(jsonObject);
    }
}
