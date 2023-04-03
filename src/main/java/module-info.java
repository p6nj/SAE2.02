module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;

    opens main to javafx.fxml;

    exports main;
    exports tests;
}