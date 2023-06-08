package butinfo.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Scene3 extends Scene {
    private static GridPane root = new GridPane();
    private static Label solutionLabel, timeLabel, xpLabel, distanceLabel, nbLabel,
            solutionLabelTitle = new Label("Solution :"), timeLabelTitle = new Label("Temps :"),
            xpLabelTitle = new Label("XP :"), distanceLabelTitle = new Label("Distance :"),
            nbLabelTitle = new Label("Nombre de quêtes :");
    private static Button back = new Button("Recommencer");

    public Scene3() {
        super(root);
    }

}
