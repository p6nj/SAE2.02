package butinfo.view;

import butinfo.controler.Controler;
import butinfo.model.SolutionStats;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Scene3 extends Scene {
    private static final String format = "Scénario %d";
    private static GridPane root = new GridPane();
    private static Label solutionLabel = new Label(), timeLabel = new Label(), xpLabel = new Label(),
            distanceLabel = new Label(), nbLabel = new Label(), scenarioLabelTitle = new Label(),
            solutionLabelTitle = new Label("Solution :"), timeLabelTitle = new Label("Temps :"),
            xpLabelTitle = new Label("XP :"), distanceLabelTitle = new Label("Distance :"),
            nbLabelTitle = new Label("Nombre de quêtes :"), methodLabel = new Label(),
            methodLabelTitle = new Label("Méthode :");
    private static Button back = new Button("Recommencer");

    public Scene3(Controler c) {
        super(root);
        int line = 0;
        root.addRow(line++, scenarioLabelTitle);
        root.addRow(line++, methodLabelTitle, methodLabel);
        root.addRow(line++, solutionLabelTitle, solutionLabel);
        root.addRow(line++, timeLabelTitle, timeLabel);
        root.addRow(line++, xpLabelTitle, xpLabel);
        root.addRow(line++, distanceLabelTitle, distanceLabel);
        root.addRow(line++, nbLabelTitle, nbLabel);
        root.addRow(line++, back);
        back.setOnAction(c);
        back.setId("back");
    }

    public static void setResults(SolutionStats results) {
        solutionLabel.setText(results.getSolution().toString());
        timeLabel.setText(Integer.toString(results.getTime()));
        xpLabel.setText(Integer.toString(results.getXp()));
        distanceLabel.setText(Integer.toString(results.getDistance()));
        nbLabel.setText(Integer.toString(results.getNb()));
    }

    public static void setMethod(String method) {
        methodLabel.setText(method);
    }

    public static void setScenario(int scenario) {
        scenarioLabelTitle.setText(String.format(format, scenario));
    }

}
