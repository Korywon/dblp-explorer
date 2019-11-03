import com.google.gson.*;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static ArrayList<JsonObject> tier_1_papers = new ArrayList<JsonObject>();
    private static ArrayList<JsonObject> tier_2_papers = new ArrayList<JsonObject>();
    private static ArrayList<JsonObject> tier_3_papers = new ArrayList<JsonObject>();
    private static ArrayList<String> tier_1_references = new ArrayList<String>();
    private static ArrayList<String> tier_2_references = new ArrayList<String>();
    private static int lineCount = 1;
    private static long startTime;
    private static long timeElapsed;
    private static String keyword;

    public static void main(String[] args) {
        keyword = CommandListener.promptForInput("Enter keyword to search for: ");

        Path path = Paths.get("dblp_papers_v11.txt");

        System.out.println("Beginning tier 1 search.");

        resetStatistics();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(Main::parseLineTier1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tier_1_references = getReferences(tier_1_papers);
        System.out.println(tier_1_references.size() + " tier 3 papers found.");

        System.out.println("Beginning tier 2 search.");

        resetStatistics();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(Main::parseLineTier2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tier_2_references = getReferences(tier_2_papers);
        System.out.println(tier_2_references.size() + " tier 3 papers found.");

        System.out.println("Beginning tier 3 search.");

        resetStatistics();
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(Main::parseLineTier3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Main.updateStatistics();
        System.out.println(lineCount + " papers searched. Time elapsed: " + timeElapsed + " ms");
    }

    public static void parseLineTier1(String line) {
        JsonObject object;
        try {
            object = JsonParser.parseString(line).getAsJsonObject();
        } catch (JsonParseException e) {
            System.out.println("Warning: Line is not a valid JSON. Skipping line " + lineCount + ".");
            return;
        } catch (IllegalStateException e) {
            System.out.print("Warning: Line is not a JSON object. Skipping line " + lineCount + ".");
            return;
        }

        if (Main.matchLike(object.get("title").getAsString(), Main.keyword)) {
            tier_1_papers.add(object);
        }

        Main.updateStatistics();
        if (lineCount % 100000 == 0) {
            Main.reportStatistics();
        }
    }

    public static void parseLineTier2(String line) {
        // attempts to parse the line into a JsonObject
        JsonObject object;
        try {
            object = JsonParser.parseString(line).getAsJsonObject();
        } catch (JsonParseException e) {
            System.out.println("Warning: Line is not a valid JSON. Skipping line " + lineCount + ".");
            return;
        } catch (IllegalStateException e) {
            System.out.print("Warning: Line is not a JSON object. Skipping line " + lineCount + ".");
            return;
        }

        // if references contains the object's id, add the object to the tier 2 papers
        if (tier_1_references.contains(object.get("id").getAsString())) {
            tier_2_papers.add(object);
        }

        Main.updateStatistics();
        if (lineCount % 100000 == 0) {
            Main.reportStatistics();
        }
    }

    public static void parseLineTier3(String line) {
        // attempts to parse the line into a JsonObject
        JsonObject object;
        try {
            object = JsonParser.parseString(line).getAsJsonObject();
        } catch (JsonParseException e) {
            System.out.println("Warning: Line is not a valid JSON. Skipping line " + lineCount + ".");
            return;
        } catch (IllegalStateException e) {
            System.out.print("Warning: Line is not a JSON object. Skipping line " + lineCount + ".");
            return;
        }

        // if references contains the object's id, add the object to the tier 2 papers
        if (tier_2_references.contains(object.get("id").getAsString())) {
            tier_3_papers.add(object);
        }

        Main.updateStatistics();
        if (lineCount % 100000 == 0) {
            Main.reportStatistics();
        }
    }

    public static ArrayList<String> getReferences(ArrayList<JsonObject> papers) {
        ArrayList<String> references = new ArrayList<String>();
        for (JsonObject object : papers) {
            JsonArray array = object.get("references").getAsJsonArray();
            for (JsonElement element : array) {
                String referenceId = element.getAsString();
                references.add(referenceId);
            }
        }

        return references;
    }

    private static boolean matchLike(String string, String keyword) {
        String regex = ".*" + keyword + ".*";
        return string.toLowerCase().matches(regex);
    }

    private static void resetStatistics() {
        lineCount = 1;
        startTime = System.currentTimeMillis();
        timeElapsed = (new Date()).getTime() - startTime;
    }

    private static void updateStatistics() {
        lineCount++;
        timeElapsed = (new Date()).getTime() - startTime;
    }

    private static void reportStatistics() {
        System.out.println(lineCount + " papers searched.");
        System.out.println(tier_1_papers.size() + " tier 1 papers indexed.");
        System.out.println(tier_2_papers.size() + " tier 2 papers indexed.");
        System.out.println(tier_3_papers.size() + " tier 3 papers indexed.");
        System.out.println("Time elapsed since start: " + timeElapsed + " ms\n");
    }
}
