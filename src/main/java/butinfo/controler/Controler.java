package butinfo.controler;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import butinfo.view.App;
import butinfo.view.ScenarioButton;
import butinfo.view.Scene2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class Controler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent a) {
        Object source = a.getSource();
        if (source instanceof ScenarioButton) {
            // scene1 called scene2 with the scenario choice
            Scene2.setScenario(((ScenarioButton) source).getN());
            App.setScene(2);
        } else if (((Node) source).getId() == "back")
            App.setScene((int) ((Node) source).getUserData());
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
