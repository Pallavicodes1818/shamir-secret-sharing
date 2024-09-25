package src;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ShamirSecretSharing {
    // Function to decode the value from its base
    public static long decodeValue(String base, String value) {
        int baseInt = Integer.parseInt(base); // Convert base string to int
        return Long.parseLong(value, baseInt); // Convert value from the given base
    }

    public static void main(String[] args) {
        JSONParser parser = new JSONParser(); // JSON parser to read file

        try {
            // Step 1: Read JSON input from a file
            Object obj = parser.parse(new FileReader("src/input.json")); // Path to the JSON file
            JSONObject jsonObject = (JSONObject) obj;

            // Step 2: Extract 'n' and 'k' from the "keys" object
            JSONObject keys = (JSONObject) jsonObject.get("keys");
            long n = (long) keys.get("n");
            long k = (long) keys.get("k");

            // Step 3: Parse and decode the x and y values
            int[][] points = new int[(int) n][2]; // Create an array to store points

            int index = 0;
            for (int i = 1; i <= n; i++) {
                // Each point is represented as an object with base and value
                JSONObject point = (JSONObject) jsonObject.get(String.valueOf(i));
                String base = (String) point.get("base");
                String value = (String) point.get("value");

                // x value is the index (i), y value is decoded from the base
                points[index][0] = i; // x value
                points[index][1] = (int) decodeValue(base, value); // decoded y value
                index++;
            }

            // Output the points for verification
            System.out.println("Decoded points (x, y):");
            for (int i = 0; i < n; i++) {
                System.out.println("(" + points[i][0] + ", " + points[i][1] + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
