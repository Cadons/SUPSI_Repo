package ch.supsi.localchat.frontend;

import ch.supsi.localchat.frontend.model.ContactGUI;
import ch.supsi.localchat.frontend.model.Language;
import ch.supsi.localchat.frontend.model.MessageGUI;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import static ch.supsi.localchat.frontend.LocalChat.*;
import static ch.supsi.localchat.frontend.model.Language.EN;
import static ch.supsi.localchat.frontend.model.Language.IT;

public class ChatController implements Initializable {

    /**
     * Init all elements of the GUI
     */
    private ObservableList<ContactGUI> contactsView = FXCollections.observableArrayList(new ArrayList<ContactGUI>());
    @FXML
    private Label messageReceiverLabel;//
    @FXML
    private Label contactsMenuLabel;
    @FXML
    private MenuItem logoutBtn;//
    @FXML
    private Menu prefMenu;
    @FXML
    private Menu languageMenu;
    @FXML
    private RadioMenuItem italianMenu;
    @FXML
    private RadioMenuItem englishMenu;
    @FXML
    private MenuItem teamMembersMenu;
    @FXML
    private MenuItem prodNameMenu;
    @FXML
    private MenuItem prodVersionMenu;
    @FXML
    private TextField searchContactTextField;
    @FXML
    private TextField messageTextBox;
    @FXML
    private Button messageSendButton;

    @FXML
    private ListView<ContactGUI> contactsListView;
    @FXML
    private VBox messageList;
    @FXML
    private ScrollPane chatBoxView;

    public static boolean isFirstOpening = true;

    @FXML
    private void logoutButton(ActionEvent event) throws IOException {
        System.out.println("Logout");
        /**
         *
         * DO LOGOUT CHECKS
         *
         * **/
        userController.logout();
        var uri = LocalChat.class.getResource("login.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(uri);
        var scene = new Scene(fxmlLoader.load(), 800, 600);
        LocalChat.stage.setScene(scene);
        stage.setTitle("Login");
        LocalChat.stage.show();
    }

    @FXML
    private void changeLanguage(ActionEvent event) {
        /**
         *
         * Change Language
         *
         * **/

        Language selectedLanguage = EN;
        switch (((MenuItem) event.getSource()).getId()) {
            case "italianMenu":
                selectedLanguage = IT;
                break;
            case "englishMenu":
            default:
                selectedLanguage = EN;
                break;
        }
        pref.changeLanguage(selectedLanguage.toString());

        translateGUI();
    }


    @FXML
    private void sendMessage() {
        if(!messageTextBox.getText().equals("")) {
            chatBoxView.setVvalue(Double.MAX_VALUE);
            chatController.sendMessage(messageTextBox.getText());
            messageList.getChildren().add(new MessageGUI(messageTextBox.getText(), LocalDateTime.now().format(DateTimeFormatter.ISO_TIME).substring(0, 5), true));
            messageTextBox.clear();
        }

    }


    /**
     * @apiNote Retrieves user contacts from services and displays themon the ListBiew
     * @param value
     */
    private void searchContact(String value) {
        contactsView.clear();
        if (value.equals("")) {
            var contacts = ReadJSON.parseArray(userController.getContacts());
            contacts.forEach(e -> {
                var contact = (JSONObject) e;

                contactsView.add(new ContactGUI(contact.getString("username")));

            });
        } else {
            var contacts = ReadJSON.parseArray(userController.searchContact(value));
            contacts.forEach(e -> {
                var contact = (JSONObject) e;
                if (!contact.getString("username").equals(userController.getActiveUser()))
                    contactsView.add(new ContactGUI(contact.getString("username")));
            });
        }
    }

    /**
     * @apiNote handle enter button pressed on Message TextBox
     */
    public void keyPressed(KeyEvent e) throws IOException {
        KeyCode key = e.getCode();
        if(key == KeyCode.ENTER)
            sendMessage();
    }

    private void openChatWithContact(String username) {

        if(isFirstOpening){
            messageTextBox.setVisible(true);
            messageSendButton.setVisible(true);
            isFirstOpening = false;
        }

        messageReceiverLabel.setText(username);
        var contacts = ReadJSON.parseArray(userController.getContacts());
        AtomicBoolean isPresent = new AtomicBoolean(false);
        contacts.forEach(e -> {
            var contact = (JSONObject) e;
            if (contact.getString("username").equals(username)) {
                isPresent.set(true);
            }
        });
        if (isPresent.get() == false) {
            userController.addContact(username);
        }
        var result = ReadJSON.parseArray(chatController.getChat(username));
        System.out.println(result.toString());
        System.out.println(result.length());
        for (int i = 0; i < result.length(); i++) {
            var message = (JSONObject) result.get(i);
            boolean isYou = true;
            if (!message.getString("from").equals(userController.getActiveUser())) {
                isYou = false;
            }

            messageList.getChildren().add(new MessageGUI(message.getString("text"), message.getString("time"), isYou));
            chatBoxView.setVvalue(Double.MAX_VALUE);
        }


    }

    @FXML
    private void groupMembers() {
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle(pref.translate("teamMembersMenu"));
        message.setContentText("Matteo Cadoni, Simone Finiletti, Daniel Ibrahim");
        message.show();
    }

    @FXML
    private void productName() {
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle(pref.translate("prodNameMenu"));
        //Get jar name
        message.setContentText(getClass().getPackage().getImplementationTitle());
        message.show();
    }

    @FXML
    private void productVersion() throws IOException {
        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setTitle(pref.translate("prodVersionMenu"));
        message.setContentText(getVersion());
        message.show();
    }

    private String getVersion() throws IOException {

        return getClass().getPackage().getImplementationVersion();

    }


    private void translateGUI() {
        /**
         * Call translation service
         * */
        var x = pref.getLanguage();
        contactsMenuLabel.setText(pref.translate(contactsMenuLabel.getId()));
        logoutBtn.setText(pref.translate(logoutBtn.getId()));
        prefMenu.setText(pref.translate(prefMenu.getId()));
        languageMenu.setText(pref.translate(languageMenu.getId()));
        italianMenu.setText(pref.translate(italianMenu.getId()));
        englishMenu.setText(pref.translate(englishMenu.getId()));
        switch (pref.getLanguage()) {
            case "IT" -> italianMenu.setSelected(true);
            case "EN" -> englishMenu.setSelected(true);
        }
        teamMembersMenu.setText(pref.translate(teamMembersMenu.getId()));
        prodNameMenu.setText(pref.translate(prodNameMenu.getId()));
        prodVersionMenu.setText(pref.translate(prodVersionMenu.getId()));
        searchContactTextField.setPromptText(pref.translate(searchContactTextField.getId()));
        messageTextBox.setPromptText(pref.translate(messageTextBox.getId()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pref.changeLanguage(pref.getLanguage());
        translateGUI();

        contactsListView.setEditable(true);
        var contacts = ReadJSON.parseArray(userController.getContacts());
        var unknownSenders = ReadJSON.parseArray(userController.getAnonymous());

        contacts.forEach(e -> {
            var contact = (JSONObject) e;
            contactsView.add(new ContactGUI(contact.getString("username")));
        });
        unknownSenders.forEach(e -> {
            var contact = (JSONObject) e;
            contactsView.add(new ContactGUI(contact.getString("username"),1));
        });
        contactsListView.setItems(contactsView);
        contactsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ContactGUI>() {
            @Override
            public void changed(ObservableValue<? extends ContactGUI> observableValue, ContactGUI contact, ContactGUI t1) {
                if(t1!=null)
                    System.out.println("Selected " + t1.getUsername());
                messageList.getChildren().clear();
                if(t1!=null) {
                    openChatWithContact(t1.getUsername());
                    t1.updateStatus();//Update opacity
                }
                messageTextBox.clear();

            }
        });

        searchContactTextField.textProperty().addListener((Observable, oldValue, newValue) -> {
            contactsView.clear();
            searchContact(newValue);
        });

        messageSendButton.setVisible(false);
        messageTextBox.setVisible(false);
    }



}
