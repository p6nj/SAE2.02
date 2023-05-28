package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws NoSuchElementException, FileNotFoundException, IOException {
        Scenario s=new Scenario(1);
        System.out.println(s);
    }
}
