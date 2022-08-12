package ch.supsi.localchat.frontend.model;

import ch.supsi.localchat.frontend.ChatController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Contact extends HBox {

    private Label label = new Label();
    private HBox labelBox = new HBox();
    private ImageView userImage;
    private final String username;

    public Contact(String username) {
        this.username=username;
        label.setText(username);
        HBox.setHgrow(label, Priority.ALWAYS);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.getChildren().add(label);
        labelBox.paddingProperty().setValue(new Insets(10));
        var profileIcon = ChatController.class.getResourceAsStream("profileIcon.png");
        var image = new Image(profileIcon);

        userImage = new ImageView(image);
        userImage.setFitWidth(50);
        userImage.setFitHeight(50);

        this.getChildren().add(userImage);

        this.getChildren().add(labelBox);

    }

    public String getUsername() {
        return username;
    }
}
