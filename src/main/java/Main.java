import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<JsonObject> objects = JsonHelper.parseJsonFile("dblp_papers_v11_first_100_lines.txt");
        System.out.println(objects.size() + " objects parsed from file.");

        for (JsonObject object : objects) {
            System.out.println(object.get("title").getAsString());
        }
    }
}
