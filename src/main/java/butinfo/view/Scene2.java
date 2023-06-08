package butinfo.view;

import butinfo.controler.Controler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Scene2 extends Scene {
    private static VBox root = new VBox();
    private final static String format = "Scénario %d";
    private static Label title = new Label("Choisissez une méthode :"), scenario = new Label(String.format(format, 0));
    private static HBox methods = new HBox();
    private static Button back = new Button("Menu précédent");
    private static Separator separator = new Separator();
    private static int chosenScenario = 0;

    public Scene2(Controler c) {
        super(root);
        root.getChildren().addAll(scenario, title, methods, separator, back);
        Button efficace = new Button("Efficace");
        Button exhaustive = new Button("Exhaustive");
        efficace.setOnAction(c);
        exhaustive.setOnAction(c);
        back.setOnAction(c);
        methods.getChildren().addAll(efficace, exhaustive);
        separator.setVisible(false);
        separator.setMinHeight(20);
        methods.getStyleClass().add("container");
        back.setId("back");
        back.setUserData(1);
    }

    public static void setScenario(int scenario) {
        Scene2.scenario.setText(String.format(format, scenario));
        chosenScenario = scenario;
    }

    public static int getChosenScenario() {
        return chosenScenario;
    }

}
