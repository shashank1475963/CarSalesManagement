package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoLocationService {
    private final String IPSTACK_API_URL = "http://api.ipstack.com/";
    private final String IPSTACK_API_KEY = "34b17bfa16b93234f64f1548a765faea"; // Replace with your IPStack API key

    private final String GEOCODE_API_URL = "https://geocode.maps.co/reverse?lat={lat}&lon={lon}&api_key={apiKey}";
    private final String GEOCODE_API_KEY = "67c16f67e3c16867901722ikd0fa3bb"; // Replace with your Geocode Maps API key

    private final RestTemplate restTemplate = new RestTemplate();

    public String getLocation() {
        try {
            // Step 1: Get Public IP
            String publicIp = restTemplate.getForObject("https://api64.ipify.org?format=text", String.class);
            System.out.println("🔹 Detected Public IP: " + publicIp);

            // Step 2: Get latitude and longitude from ipstack
            String ipApiResponse = restTemplate.getForObject(
                    IPSTACK_API_URL + publicIp + "?access_key=" + IPSTACK_API_KEY, String.class);
            JSONObject ipJson = new JSONObject(ipApiResponse);
            System.out.println("🌍 IPStack Response: " + ipApiResponse);

            if (ipJson.has("latitude") && ipJson.has("longitude")) {
                double latitude = ipJson.getDouble("latitude");
                double longitude = ipJson.getDouble("longitude");

                // Step 3: Get Address from Geocode Maps API
                String geoApiResponse = restTemplate.getForObject(
                        GEOCODE_API_URL, String.class, latitude, longitude, GEOCODE_API_KEY);
                JSONObject geoJson = new JSONObject(geoApiResponse);
                System.out.println("📍 Geocode Maps Response: " + geoApiResponse);

                if (geoJson.has("display_name")) {
                    return "📍 Location: " + geoJson.getString("display_name");
                } else {
                    return "⚠️ Location not found for given IP.";
                }
            } else {
                return "⚠️ Could not retrieve latitude and longitude.";
            }
        } catch (Exception e) {
            return "❌ Error fetching location: " + e.getMessage();
        }
    }
}
