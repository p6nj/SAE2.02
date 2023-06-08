package butinfo.controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import butinfo.model.Scenario;
import butinfo.model.SolutionStats;
import butinfo.model.UnaccessibleQuestException;
import butinfo.view.App;
import butinfo.view.ScenarioButton;
import butinfo.view.Scene2;
import butinfo.view.Scene3;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Labeled;

public class Controler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent a) {
        Object source = a.getSource();
        if (source instanceof ScenarioButton) {
            // scene1 called scene2 with the scenario choice
            int scenario = ((ScenarioButton) source).getN();
            Scene2.setScenario(scenario);
            Scene3.setScenario(scenario);
            App.setScene(2);
        } else if (((Node) source).getId() == "back")
            App.setScene(1);
        else {
            int scenario = Scene2.getChosenScenario();
            String method = ((Labeled) source).getText();
            Scene3.setMethod(method);
            SolutionStats result;

            try {
                result = method == "Efficace" ? new SolutionStats(new Scenario(scenario).efficace1())
                        : new SolutionStats(new Scenario(scenario).exhaustive1());
                Scene3.setResults(result);
                App.setScene(3);
            } catch (NoSuchElementException e) {
                System.err.println("Aucune solution trouvée.");
            } catch (FileNotFoundException e) {
                System.err.println("Fichier CSV inaccessible : " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Erreur d'I/O : " + e.getMessage());
            } catch (UnaccessibleQuestException e) {
                System.err.println(e.getMessage());
            }

        }
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
