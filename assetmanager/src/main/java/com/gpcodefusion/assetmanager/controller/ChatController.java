package com.gpcodefusion.assetmanager.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private static final String OPENAI_API_KEY = "xxxx";
    private static final String OPENAI_URL = "xxxx";

    @PostMapping("/ask")
    public ResponseEntity<String> getAIResponse(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        String response = getStaticResponse(message);

        if (response != null) {
            return ResponseEntity.ok(response);
        }

        try {
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(OPENAI_API_KEY);

            // Build body
            Map<String, Object> body = new HashMap<>();
            body.put("model", "gpt-3.5-turbo");
            body.put("temperature", 0.7);
            body.put("max_tokens", 150);

            List<Map<String, String>> messages = List.of(
                    Map.of("role", "system", "content", "You are an AI assistant for asset management."),
                    Map.of("role", "user", "content", message)
            );
            body.put("messages", messages);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> aiResponse = restTemplate.postForEntity(OPENAI_URL, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(aiResponse.getBody());
            String aiText = root.path("choices").get(0).path("message").path("content").asText();

            return ResponseEntity.ok(aiText);

        } catch (HttpClientErrorException ex) {
            // Return full error from OpenAI (e.g., invalid_api_key)
            return ResponseEntity.status(ex.getStatusCode()).body("OpenAI API error: " + ex.getResponseBodyAsString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error: " + e.getMessage());
        }
    }

    // Static keyword matching
    private String getStaticResponse(String message) {
        if (message == null) return null;

        message = message.toLowerCase();

        Map<String, String> staticMap = Map.ofEntries(
                entry("add asset", "To add an asset, go to the 'Add Asset' page, enter the asset details..."),
                entry("search asset", "To search for an asset, scan the barcode/QR code, type the name..."),
                entry("update asset", "To update an asset, find it in the list and click 'Edit'..."),
                entry("delete asset", "To delete an asset, go to 'Asset Management', select the asset, and click 'Delete'..."),
                entry("generate barcode", "Use the 'Add Asset' page and click 'Generate Barcode'..."),
                entry("track asset", "Scan the asset's QR/barcode to track it..."),
                entry("hi", "How can I assist you with your asset management needs today?"),
                entry("hello", "How can I assist you with your asset management needs today?"),
                entry("good morning", "Good morning! How can I help you with your assets today?"),
                entry("good afternoon", "Good afternoon! What would you like to do with your assets today?"),
                entry("good evening", "Good evening! Need help with anything?")

        );

        for (String key : staticMap.keySet()) {
            if (message.contains(key)) {
                return staticMap.get(key);
            }
        }

        return null;
    }

    private static Map.Entry<String, String> entry(String key, String value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }
}
