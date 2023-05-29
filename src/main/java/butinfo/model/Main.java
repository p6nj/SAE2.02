package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws NoSuchElementException, FileNotFoundException, IOException {
        // TODO: debug with scenario 1 && hand verify
        Scenario s = new Scenario(1);
        int total = 0;
        for (Quest q : s.efficace1()) {
            if (q.getId() == 0)
                total += q.getXp();
        }
        System.out.println(total);
    }
}
