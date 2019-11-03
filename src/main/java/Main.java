import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public ArrayList<JsonObject> tier_1_papers;
    public ArrayList<JsonObject> tier_2_papers;
    public ArrayList<JsonObject> tier_3_papers;

    public static void main(String[] args) {
        Path path = Paths.get("dblp_papers_v11_first_100_lines.txt");
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(
                line -> JsonParser.parseString(line).getAsJsonObject()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        String filepath = CommandListener.promptForInput("Enter filename: ");
        String keyword = CommandListener.promptForInput("Enter keyword to search: ");

        String regex = ".*" + keyword + ".*";

        try {
            FileInputStream inputStream = new FileInputStream(filepath);
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            String nextLine;
            JsonObject object;
            JsonElement element;
            int counter = 1;
            long startTime = System.currentTimeMillis();
            System.out.println("Beginning search...");
            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();
                element = JsonHelper.parseJsonString(nextLine);
                assert element != null;
                object = JsonHelper.elementToObject(element);
                if (counter % 100000 == 0) {
                    System.out.println(counter + " papers scanned. Time elapsed since start: " + ((new Date()).getTime() - startTime) + " ms");
                }
                counter++;
            }

            System.out.println("Finished searching " + counter + " papers. Time elapsed: " + ((new Date()).getTime() - startTime) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        /*
        List<JsonObject> objects = JsonHelper.parseJsonFile("dblp_papers_v11_first_100_lines.txt");
        System.out.println(objects.size() + " objects parsed from file.");

        for (JsonObject object : objects) {
            try {
                if (object.get("title").getAsString().toLowerCase().matches(".*" + keyword + ".*")) {
                    System.out.println(object.get("title").getAsString());
                }
            } catch (Exception e) {
                System.out.println("Error: Skipping line " + object.toString());
            }
        }
        */
    }
}
