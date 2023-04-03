import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import model.Scenario;

public class Main {
    public static void main(String[] args) throws NoSuchElementException, FileNotFoundException, IOException {
        System.out.println(new Scenario(1));
    }
}
