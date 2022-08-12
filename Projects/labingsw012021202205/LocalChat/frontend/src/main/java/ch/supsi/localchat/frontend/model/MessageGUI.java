package ch.supsi.localchat.frontend.model;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MessageGUI extends HBox {
    private Label text, time;
    private Rectangle rectangle;
    private StackPane composedBox;
    private AnchorPane box;

    public MessageGUI(String text, String time, boolean isYou) {
        final int maxL = 40;
        final int basicWidth = 350;
        double height = 50, width = basicWidth;
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            if (i != 0 && i % maxL == 0) {
                message.append("\n");
                height += 20;
            }

            message.append(text.charAt(i));
        }
        if(text.length()<maxL/2)
            width/=2;
        this.text = new Label(message.toString());

        this.text.setPadding(new Insets(8));
        this.time = new Label("\n" + time);
        this.time.setPadding(new Insets(5));

        this.rectangle = new Rectangle(width, height);
        this.rectangle.setArcWidth(10);
        this.rectangle.setArcHeight(10);
        this.rectangle.strokeProperty().setValue(Color.BLACK);
        this.composedBox = new StackPane(rectangle, this.text, this.time);

        this.paddingProperty().setValue(new Insets(3));
        StackPane.setAlignment(this.text, Pos.CENTER_LEFT);
        StackPane.setAlignment(this.time, Pos.BOTTOM_RIGHT);

        this.box = new AnchorPane();
        this.box.getChildren().add(composedBox);
        this.box.maxWidth(this.getWidth() / 2);
        AnchorPane.setBottomAnchor(composedBox, 0.);
        HBox.setHgrow(box, Priority.ALWAYS);
        if (isYou) {
            AnchorPane.setRightAnchor(composedBox, 0.);
            rectangle.setFill(Color.rgb(65,105,225));
        } else {
            AnchorPane.setLeftAnchor(composedBox, 0.);
            rectangle.setFill(Color.rgb(30,144,255));

        }
        AnchorPane.setTopAnchor(composedBox, 0.);

        this.getChildren().add(box);
    }
}
