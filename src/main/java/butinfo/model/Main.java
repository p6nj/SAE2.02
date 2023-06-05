package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws NoSuchElementException, FileNotFoundException, IOException {
        Scenario s = new Scenario(2);
        // int total = 0;
        // for (Quest q : s.efficace1()) {
        // System.out.print(q.getId());
        // if (q.getId() != 0)
        // total += q.getXp();
        // }
        // System.out.println("\n" + total);
        try {
            for (Quest q : s.exhaustive1())
                System.out.print(q.getId());
        } catch (UnaccessibleQuestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
