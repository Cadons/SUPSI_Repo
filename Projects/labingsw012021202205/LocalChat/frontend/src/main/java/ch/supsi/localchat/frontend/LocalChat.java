package ch.supsi.localchat.frontend;


import ch.supsi.localchat.backend.controls.UserController;
import ch.supsi.localchat.backend.controls.UserPreferencesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LocalChat extends Application {
    static Stage stage;//
    static UserController userController = new UserController();
    static ch.supsi.localchat.backend.controls.ChatController chatController = new ch.supsi.localchat.backend.controls.ChatController();
    static UserPreferencesController pref = new UserPreferencesController();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Starting chat");
        //start with login
        LocalChat.stage=stage;
        var uri= LocalChat.class.getResource("login.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(uri);
        var scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        stage.show();
    }

    private void closeWindowEvent(WindowEvent event) {
        System.out.println("Window close request : saving");
    }
}
