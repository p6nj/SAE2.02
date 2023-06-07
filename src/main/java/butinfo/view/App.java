package butinfo.view;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(400);
        stage.setHeight(300);
        stage.centerOnScreen();
        Scene scene = new Scene1();
        stage.setScene(scene);
        stage.setTitle("coucou");

        URI uri = ClassLoader.getSystemResource("CSS").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath, "style.css");
        scene.getStylesheets().add(path.toFile().toURI().toString());

        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
