package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Scenario {
    public int number;
    protected ArrayList<Quest> quests = new ArrayList<Quest>();

    /**
     * Reads and returns the contents of a scenario as a string. This is a
     * convenience method for use in scenarios that need to be read from CSV files
     * 
     * @param id - The id of the scenario
     * 
     * @return The scenario
     */
    public static String readScenarioToString(int id) throws IOException {
        URI uri;
        try {
            uri = ClassLoader.getSystemResource("CSV").toURI();
            String mainPath = Paths.get(uri).toString();
            Path path = Paths.get(mainPath, String.format("scenario_%d.csv", id));
            byte[] encodedBytes = Files.readAllBytes(path);
            return new String(encodedBytes, StandardCharsets.UTF_8);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Scenario(int id) throws NoSuchElementException, FileNotFoundException, IOException {
        number = id;
        for (String line : readScenarioToString(id).split(System.lineSeparator())) {
            if (!line.isBlank())
                quests.add(parse(line));
        }
    }

    public Scenario(String fileContents) throws NoSuchElementException, FileNotFoundException, IOException {
        for (String line : fileContents.split("\\n")) {
            if (!line.isBlank())
                quests.add(parse(line));
        }
    }

    /**
     * Parses a quest from the string.
     * 
     * @param rawQuest - The string to parse.
     * 
     * @return The parsed quest or null if there was an error parsing the string or
     *         if the string was not parsable
     * 
     * @see butinfo.model.Parser#parse()
     */
    private Quest parse(String rawQuest) throws NoSuchElementException {
        return new Parser(rawQuest).parse();
    }

    /**
     * Returns the quest with the given id. Throws NoSuchElementException if there
     * is no quest with the given id.
     * 
     * @param id - the id of the quest to return.
     * 
     * @return the quest with the given id or null if there is no quest with the
     *         given id in the list
     */
    protected Quest getQuest(int id) throws NoSuchElementException {
        for (Quest quest : quests) {
            if (quest.id == id)
                return quest;
        }
        throw (new NoSuchElementException());
    }

    /**
     * Returns a string representation of the quests with the same structure as the
     * CSV files.
     * 
     * 
     * @return a string representation of the quests in the order they were added to
     *         the list (including the last)
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < quests.size() - 1; i++) {
            result = result + quests.get(i).toString() + "\n";
        }
        result = result + quests.get(quests.size() - 1);
        return result;
    }

    /**
     * Prints debug message to System. out. For debugging purposes only (to know
     * when and if there is debug content printed out).
     * 
     * @param s - the message to be printed
     */
    private void debug(String s) {
        System.out.println("DEBUG: " + s);
    }

    /**
     * Prints debug information to System. out. This is a convenience method for
     * {@link #debug(String s)}.
     * 
     * @param s - String array to be printed
     */
    private void debug(String[] s) {
        System.out.print("DEBUG: [");
        for (String s2 : s) {
            System.out.print("\n\t" + s2 + ',');
        }
        System.out.print("\n]\n");
    }

    /**
     * Prints a debug integer. This is a convenience method for
     * {@link #debug(String[] s)}.
     * 
     * @param i - the integer to print to the debug output stream
     */
    private void debug(int i) {
        debug(String.format("%d", i));
    }

}
