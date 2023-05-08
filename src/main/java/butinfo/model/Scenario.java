package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Scenario {
    public int number;
    private ArrayList<Quest> quests = new ArrayList<Quest>();

    public static String readScenarioToString(int id) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(
                Paths.get(Scenario.class.getResource(String.format("/CSV/scenario_%d.csv", id)).getPath()));
        return new String(encodedBytes, StandardCharsets.UTF_8);
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

    private Quest parse(String rawQuest) throws NoSuchElementException {
        return new Parser(rawQuest).parse();
    }

    private Quest getQuest(int id) throws NoSuchElementException {
        for (Quest quest : quests) {
            if (quest.id == id)
                return quest;
        }
        throw (new NoSuchElementException());
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < quests.size() - 1; i++) {
            result = result + quests.get(i).toString() + "\n";
        }
        result = result + quests.get(quests.size() - 1);
        return result;
    }

    private void debug(String s) {
        System.out.println("DEBUG: " + s);
    }

    private void debug(String[] s) {
        System.out.print("DEBUG: [");
        for (String s2 : s) {
            System.out.print("\n\t" + s2 + ',');
        }
        System.out.print("\n]\n");
    }

    private void debug(int i) {
        debug(String.format("%d", i));
    }

}
