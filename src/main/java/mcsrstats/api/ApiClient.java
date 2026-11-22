package mcsrstats.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mcsrstats.model.MatchInfo;
import mcsrstats.model.UserProfile;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiClient {

    private static final String BASE_URL = "https://api.mcsrranked.com";

    private final HttpClient http = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public UserProfile getUser(String name) throws IOException, InterruptedException {
        JsonNode data = get("/users/" + encode(name));
        return mapper.treeToValue(data, UserProfile.class);
    }

    public List<MatchInfo> getMatches(String name, int count) throws IOException, InterruptedException {
        JsonNode data = get("/users/" + encode(name) + "/matches?count=" + count + "&type=2");
        return mapper.readerForListOf(MatchInfo.class).readValue(data);
    }

    // Requests the data
    private JsonNode get(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 429) {
            throw new IOException("Rate limited, try again in a few minutes");
        }

        JsonNode root = mapper.readTree(response.body());
        if (!"success".equals(root.path("status").asText())) {
            throw new IOException(root.path("data").asText("Request failed"));
        }
        return root.path("data");
    }

    private String encode(String text) {
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    // Test
    public static void main(String[] args) throws Exception {
        ApiClient client = new ApiClient();
        UserProfile user = client.getUser("RED_LIME");
        System.out.println(user.nickname() + " - Elo " + user.eloRate() + ", rank " + user.eloRank());
        System.out.println("Matches fetched: " + client.getMatches("RED_LIME", 10).size());
    }
}