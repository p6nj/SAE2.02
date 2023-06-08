package butinfo.view;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import butinfo.controler.Controler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Controler controler = new Controler();
    private static Scene scene1 = new Scene1(controler), scene2 = new Scene2(controler), scene3 = new Scene3(controler);
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        App.stage = stage;
        stage.setWidth(400);
        stage.setHeight(300);
        stage.centerOnScreen();
        stage.setScene(scene1);
        stage.setTitle("coucou");

        URI uri = ClassLoader.getSystemResource("CSS").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath, "style.css");
        scene1.getStylesheets().add(path.toFile().toURI().toString());
        scene2.getStylesheets().add(path.toFile().toURI().toString());
        scene3.getStylesheets().add(path.toFile().toURI().toString());

        stage.show();
        // setScene(3);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static void setScene(int scene) {
        stage.setScene(scene == 1 ? scene1 : scene == 2 ? scene2 : scene3);
    }

}
