package butinfo.view;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(10);
        stage.setHeight(10);
        stage.centerOnScreen();
        Scene scene = new Scene(new HBox());
        stage.setScene(scene);
        stage.setTitle("coucou");

        URI uri = ClassLoader.getSystemResource("CSS").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath, "style.css");
        scene.getStylesheets().add(path.toFile().toURI().toString());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
