package butinfo.controler;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

    /**
     * Lists the scenario CSV files to display correct buttons in the GUI. Source:
     * https://www.baeldung.com/java-list-directory-files
     * 
     * @return Set<String>: the set of all file names in the CSV folder
     */
    public static Set<String> scenarioFilesList() {
        URI uri;
        try {
            uri = ClassLoader.getSystemResource("CSV").toURI();
            String dir = Paths.get(uri).toString();
            return Stream.of(new File(dir).listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new TreeSet<>();
    }

}
