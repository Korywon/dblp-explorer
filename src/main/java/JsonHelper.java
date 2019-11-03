import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    /**
     * Parses a JSON string into a JsonElement and returns that JsonElement.
     * @param string String of JSON to be parsed.
     * @return Returns a JsonElement that was parsed from the string parameter.
     */
    public static JsonElement parseJsonString(String string) {
        try {
            return JsonParser.parseString(string);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parses an entire text file of JSON objects. This is assuming that each line in the text file is an object.
     * @param filepath
     * @return Returns an array list of <code>JsonObject</code>s.
     */
    public static ArrayList<JsonObject> parseJsonFile(String filepath) {
        ArrayList<JsonObject> objects = new ArrayList<JsonObject>();
        ArrayList<String> contentLines;
        contentLines = FileHelper.loadFile("dblp_papers_v11_first_100_lines.txt");

        for (int i = 0; i < contentLines.size(); i++) {
            JsonElement element = JsonHelper.parseJsonString(contentLines.get(i));
            assert element != null;
            if (element.isJsonObject()) {
                objects.add(element.getAsJsonObject());
            } else {
                System.out.println("Warning: Skipping line " + i + ". Line is not a JSON object.");
            }
        }

        return objects;
    }
}
