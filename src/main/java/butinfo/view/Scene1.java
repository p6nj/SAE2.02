package butinfo.view;

import java.util.TreeSet;

import butinfo.controler.Controler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class Scene1 extends Scene {
    private static VBox root = new VBox();
    private static TilePane buttonsPane = new TilePane();
    private static Label solutionNumber = new Label("Nombre de solutions calculées : 1");
    private static TreeSet<ScenarioButton> buttons = new TreeSet<>();

    public Scene1() {
        super(root);
        root.getChildren().addAll(buttonsPane, solutionNumber);
        for (String fileName : Controler.scenarioFilesList()) {
            int scenarioNumber = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            buttons.add(new ScenarioButton(scenarioNumber));
        }
        for (ScenarioButton b : buttons)
            buttonsPane.getChildren().add(b);
    }

}
