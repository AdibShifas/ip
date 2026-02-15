package flores;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox with the given text and image.
     *
     * @param text The text to display.
     * @param img  The image to display.
     */
    public DialogBox(String msg, Image img) {
        text = new Label(msg);
        displayPicture = new ImageView(img);

        // Styling the text and image
        text.setWrapText(true);
        displayPicture.setFitWidth(50.0); // Smaller, more reasonable size
        displayPicture.setFitHeight(50.0);

        // Circle clip for image
        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("dialog-box-user");
        return db;
    }

    public static DialogBox getFloresDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("dialog-box-flores");
        return db;
    }
}
