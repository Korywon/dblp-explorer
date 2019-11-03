import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {
    /**
     * Loads a file in via a filepath and then returns the file content as an array list of strings.
     * @param filepath String of the filepath.
     * @return Returns an array list of strings.
     */
    public static ArrayList<String> loadFile(String filepath) {
        ArrayList<String> contentLines = new ArrayList<String>();

        try {
            FileInputStream inputStream = new FileInputStream(filepath);
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                contentLines.add(nextLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentLines;
    }
}
