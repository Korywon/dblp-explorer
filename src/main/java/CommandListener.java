import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandListener {
    /**
     * Prompts the user for an input. Returns a string of the user's input.
     * @param prompt Prints a prompt message before the input.
     * @return Returns the string that the user entered in.
     */
    public static String promptForInput(String prompt) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        try {
            return bufferedReader.readLine();
        }
        catch (Exception ignored) { return ""; }
    }
}
