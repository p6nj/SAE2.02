import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Scenario implements Fields {
    public int number;
    private ArrayList<Quest> quests;

    private static ArrayList<String> readFileLines(String filename) throws IOException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        ArrayList<String> everything = new ArrayList<String>();
        try {
            String line = br.readLine();

            while (line != null) {
                everything.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return everything;
    }

    public Scenario(int id) throws NoSuchElementException, FileNotFoundException, IOException {
        number = id;
        for (String line : readFileLines(String.format("dat/scenario_%d.csv", id))) {
            System.out.println(line);
            quests.add(parse(line.split("|")));
        }
    }

    private Quest parse(String[] rawQuest) throws NoSuchElementException {
        int id, t, xp;
        Coordinates coords;
        Quest[][] conds = new Quest[2][2];
        String name, s, temp;

        id = Integer.parseInt(rawQuest[Fields.ID]);
        t = Integer.parseInt(rawQuest[Fields.TIME]);
        xp = Integer.parseInt(rawQuest[Fields.EXP]);
        String[] rawCoords = rawQuest[Fields.POSITION].split("\\(")[1].split("\\)")[0].split(",");
        coords = new Coordinates(Integer.parseInt(rawCoords[0]), Integer.parseInt(rawCoords[1]));
        if ((s = rawQuest[Fields.ANTECEDENTS]) != "()") {
            s = s.substring(1, s.length() - 1);// remove surrounding parenthesis
            String first = "", second = "";
            int chooser = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    chooser = first.isEmpty() ? 1 : 2;
                } else if (c == ')')
                    chooser = 0;
                else {
                    if (chooser == 1)
                        first = first + c;
                    else
                        second = second + c;
                }
            }
            if (!first.isEmpty()) {
                String[] firstSplit = first.split(",");
                if (!(temp = firstSplit[0]).isEmpty())
                    conds[0][0] = getQuest(Integer.parseInt(temp));
                if (!(temp = firstSplit[1]).isEmpty())
                    conds[0][1] = getQuest(Integer.parseInt(temp));
            }
            if (!second.isEmpty()) {
                String[] secondSplit = second.split(",");
                if (!(temp = secondSplit[0]).isEmpty())
                    conds[1][0] = getQuest(Integer.parseInt(temp));
                if (!(temp = secondSplit[1]).isEmpty())
                    conds[1][1] = getQuest(Integer.parseInt(temp));
            }
        }
        name = rawQuest[Fields.NAME];
        return new Quest(id, coords, conds, t, xp, name);
    }

    private Quest getQuest(int id) throws NoSuchElementException {
        for (Quest quest : quests) {
            if (quest.id == id)
                return quest;
        }
        throw (new NoSuchElementException());
    }

    public String toString() {
        return quests.toString();
    }

}
