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
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT); // Default alignment for user (Right side)
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
        return new DialogBox(text, img);
    }

    public static DialogBox getFloresDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); // Flip it so the bot's image is on the left
        return db;
    }
}
