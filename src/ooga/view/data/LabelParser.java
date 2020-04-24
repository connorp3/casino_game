package ooga.view.data;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;

/***
 * This class is used to distinguish text from images in the frontend
 * @author Connor Penny
 */

public class LabelParser {
    private final String IMAGEFILE_SUFFIXES =
            String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
    private final String DEFAULT_RESOURCE_FOLDER = "/resources/Images/";
    private final double DEFAULT_IMAGE_SIZE = 50.0;

    public LabelParser() {
    }

    /***
     * Determines if the string passed to it is an image and returns an image node if so
     * @param label the string that may be an image
     * @return a node that has been properly parsed
     */
    public Node parseLabel(String label) {
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            ImageView image = new ImageView(new Image(getClass().getResourceAsStream(DEFAULT_RESOURCE_FOLDER + label)));
            image.setFitHeight(DEFAULT_IMAGE_SIZE);
            image.setFitWidth(DEFAULT_IMAGE_SIZE);
            return image;
        }
        else {
            return new Text(label);
        }
    }
}
