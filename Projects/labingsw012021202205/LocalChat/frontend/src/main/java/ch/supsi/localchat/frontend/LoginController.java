package ch.supsi.localchat.frontend;


import ch.supsi.localchat.frontend.model.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ch.supsi.localchat.frontend.LocalChat.pref;

public class LoginController implements Initializable {

    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTxt;

    /**
     * @apiNote try to login user if it exist.
     *
     * @throws IOException
     */
    @FXML
    private void loginButton() throws IOException {
        if(!checkInput())
            return;

        System.out.println("Login");

        if (LocalChat.userController.login(usernameTxt.getText())) {
            usernameTxt.clear();

            var uri = LocalChat.class.getResource("chat.fxml");

            FXMLLoader fxmlLoader = new FXMLLoader(uri);
            var scene = new Scene(fxmlLoader.load(), 800, 600);
            LocalChat.stage.setScene(scene);
            LocalChat.stage.setTitle("Local Chat");
            LocalChat.stage.show();
            ChatController.isFirstOpening = true;
        } else {
            Alert ok = new Alert(Alert.AlertType.ERROR);
            ok.setTitle(pref.translate("LoginFailedTitle"));
            ok.setContentText(pref.translate("LoginFailedContent"));
            ok.show();
        }

    }

    /**
     * @apiNote try to register user if it still doesn't exist.
     */
    @FXML
    private void registerButton() {
        if(!checkInput())
            return;

        System.out.println("register");

        if (LocalChat.userController.registerUser(usernameTxt.getText())) {
            Alert ok = new Alert(Alert.AlertType.INFORMATION);
            ok.setTitle(pref.translate("RegistrationDoneTitle"));
            ok.setContentText(pref.translate("RegistrationDoneContent"));
            ok.show();
        } else {
            Alert ok = new Alert(Alert.AlertType.ERROR);
            ok.setTitle(pref.translate("RegistrationFailedTitle"));
            ok.setContentText(pref.translate("RegistrationFailedContent"));
            ok.show();
        }
        usernameTxt.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pref.setDefault();
        registerBtn.setText(pref.translate(registerBtn.getId()));
        loginBtn.setText(pref.translate(loginBtn.getId()));
    }

    /**
     *
     * @apiNote listen for enter button pressed on UsernameTextBox
     *
     */
    public void keyPressed(KeyEvent e) throws IOException {
        KeyCode key = e.getCode();
        if(key == KeyCode.ENTER)
            loginButton();
    }

    /**
     * @apiNote check if username has been typed
     *
     * @return false if usernameTxt TextBox is empty
     */
    private boolean checkInput(){
        if(usernameTxt.getText().equals("")) {
            Alert noInputAlert = new Alert(Alert.AlertType.ERROR);
            noInputAlert.setTitle(pref.translate("LoginEmptyUsernameTitle"));
            noInputAlert.setContentText(pref.translate("LoginEmptyUsernameText"));
            noInputAlert.show();
            return false;
        }
        return true;
    }
}
