package ua.edu.ucu.apps.task3;

import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;

public class OpenAIExample {
    private static final Dotenv DOT_ENV = Dotenv.load();

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = DOT_ENV.get("API_KEY");

    public static void main(String[] args) {
        JSONArray messages = new JSONArray();

        messages.put(new JSONObject()
                .put("role", "system")
                .put("content", "You are a helpful assistant."));

        messages.put(new JSONObject()
                .put("role", "user")
                .put("content", "Explain how Java memory management works."));

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "gpt-3.5-turbo");
        jsonBody.put("messages", messages);
        jsonBody.put("max_tokens", 100);

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
                System.out.println("Response: " + response.body().string());
            } else {
                System.out.println("Request failed: " + response.code());
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
