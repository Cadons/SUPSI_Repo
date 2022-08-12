package ch.supsi.localchat.frontend.model;

import ch.supsi.localchat.frontend.ChatController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.InputStream;

public class ContactGUI extends HBox {

    private Label label = new Label();
    private HBox labelBox = new HBox();
    private InputStream profileIcon = ChatController.class.getResourceAsStream("profileIcon.png");

    private Image image = new Image(profileIcon);

    private ImageView userImage = new ImageView(image);
    private String username;

    public ContactGUI(String username) {
        this.username=username;
        label.setText(username);
        HBox.setHgrow(label, Priority.ALWAYS);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.getChildren().add(label);
        labelBox.paddingProperty().setValue(new Insets(10));

        userImage.setFitWidth(50);
        userImage.setFitHeight(50);

        this.getChildren().add(userImage);

        this.getChildren().add(labelBox);
    }

    public ContactGUI(String username, int flag){
        this(username);
        //if unknown
        userImage.setOpacity(0.5);
    }
    public void updateStatus(){
        userImage.setOpacity(1);
    }
    public String getUsername() {
        return username;
    }
}
