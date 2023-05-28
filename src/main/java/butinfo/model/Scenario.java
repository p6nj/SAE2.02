package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Scenario {
    public int number;
    protected Vector<Quest> quests = new Vector<>();

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
        for (Quest quest : quests)
            quest.antecedentsFromInt(this);
    }

    public Scenario(String fileContents) throws NoSuchElementException, FileNotFoundException, IOException {
        for (String line : fileContents.split("\\n")) {
            if (!line.isBlank())
                quests.add(parse(line));
        }
        for (Quest quest : quests)
            quest.antecedentsFromInt(this);
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

    public Vector<Quest> efficace1() {
        // prepare table
        Vector<Quest> result = new Vector<>();
        Quest current = getQuest(0);
        result.add(current);
        Vector<Quest> left = quests;
        left.remove(current);
        ArrayDeque<Vector<Quest>> pending = new ArrayDeque<>();
        for (Vector<Quest> antecedents : current.getAntecedents()) {
            pending.add(antecedents);
            for (Quest q : antecedents)
                left.remove(q);
        }
        // structure like (1,2),4: when a vector has a length > 1, the choice will be
        // resolved just when the current quest will be chosen

        Vector<Quest> temp; // where the antecedent group is put before resolution
        Quest best; // the best quest to choose next for resolution
        while (!pending.isEmpty()) {
            // new current from the deque
            /*
             * TODO: locally evaluate & resolve each candidate of the queue before polling
             * to ensure always picking the closest one
             */
            /*
             * TODO: remove possible duplicates in the antecedents queue
             */
            temp = pending.poll();
            if (temp.size() == 1)
                current = temp.firstElement();
            else {
                best = temp.firstElement();
                for (Quest q : temp)
                    if (current.compareTo(best) > current.compareTo(q))
                        best = q;
                current = best;
            }

            // add new antecedents to the deque
            for (Vector<Quest> antecedents : current.getAntecedents()) {
                pending.add(antecedents);
                for (Quest q : antecedents)
                    left.remove(q);
            }

            // update the results
            result.add(0, current);
        }

        return result;
    }

}
