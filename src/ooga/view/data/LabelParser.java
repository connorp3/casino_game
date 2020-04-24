package ooga.view.data;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;

public class LabelParser {
    private final String IMAGEFILE_SUFFIXES =
            String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));
    private final String DEFAULT_RESOURCE_FOLDER = "/resources/Images/";
    private final double DEFAULT_IMAGE_SIZE = 50.0;

    public LabelParser() {
    }

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
