package butinfo.view;

import java.util.TreeSet;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class Scene1 extends Scene {
    private static VBox root = new VBox();
    private static TilePane buttons = new TilePane();
    private static Label solutionNumber = new Label("Nombre de solutions calculées : 1");
    private static TreeSet<ScenarioButton> buttonCollection = new TreeSet<>();

    public Scene1() {
        super(root);
        root.getChildren().addAll(buttons, solutionNumber);
        buttonCollection.add(new ScenarioButton(0));
        for (ScenarioButton b : buttonCollection)
            buttons.getChildren().add(b);
    }

}
