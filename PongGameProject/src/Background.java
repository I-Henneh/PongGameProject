import java.awt.*;

/**
 * The Background class represents the background image for a graphical component.
 * It allows for drawing the background image on a specified Graphics object with a specified width and height.
 *
 * The background image is set during the initialization of the class and can be drawn using the draw method.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class Background {

    /** The background image to be drawn. */
    private Image backgroundImage;

    /**
     * Constructs a Background object with the specified background image.
     *
     * @param backgroundImage The image to be used as the background.
     */
    public Background(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Draws the background image on the specified Graphics object with the given width and height.
     *
     * @param g      The Graphics object on which to draw the background.
     * @param width  The width of the area to cover with the background image.
     * @param height The height of the area to cover with the background image.
     */
    public void draw(Graphics g, int width, int height) {
        g.drawImage(backgroundImage, 0, 0, width, height, null);
    }
}


