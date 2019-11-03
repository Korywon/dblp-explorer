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
    }
}
