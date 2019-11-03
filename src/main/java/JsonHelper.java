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
     * @param filepath File path of the JSON file.
     * @return Returns an array list of <code>JsonObject</code>s.
     */
    public static ArrayList<JsonObject> parseJsonFile(String filepath) {
        ArrayList<JsonObject> objects = new ArrayList<JsonObject>();
        ArrayList<String> contentLines;
        contentLines = FileHelper.loadFile(filepath);
        JsonElement element;
        for (int i = 0; i < contentLines.size(); i++) {
            element = JsonHelper.parseJsonString(contentLines.get(i));
            assert element != null;
            if (element.isJsonObject()) {
                objects.add(element.getAsJsonObject());
            } else {
                System.out.println("Warning: Skipping line " + i + ". Line is not a JSON object.");
            }
        }

        return objects;
    }

    public static JsonObject elementToObject(JsonElement element) {
        if (element.isJsonObject()) {
            return element.getAsJsonObject();
        }
        else {
            return null;
        }
    }
}
