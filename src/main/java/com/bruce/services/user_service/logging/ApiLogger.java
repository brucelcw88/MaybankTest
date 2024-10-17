package com.bruce.services.user_service.logging;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.springframework.stereotype.Component;

@Component
public class ApiLogger {
    private static final Logger logger = Logger.getLogger(ApiLogger.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler(
                    "api_calls_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object logApiCall(String urlString, String method, String requestBody) {
        try {
            // Log the request
            logger.info("Request URL: " + urlString);
            logger.info("Request Method: " + method);
            if (requestBody != null) {
                logger.info("Request Body: " + requestBody);
            }

            // Create the URL and connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);

            System.out.println("connection = " + connection);

            // Send request body if it's a POST request
            if ((Arrays.asList("PUT", "POST").contains(method)) && requestBody != null) {
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(requestBody.getBytes());
                    os.flush();
                }
            }

            // Get the response
            int responseCode = connection.getResponseCode();
            logger.info("Response Status Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Log the response
            logger.info("Response Body: " + response);
        } catch (Exception e) {
            logger.severe("Error occurred: " + e.getMessage());
        }
        return null;
    }

}
