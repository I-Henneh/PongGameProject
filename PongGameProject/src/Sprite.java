import java.awt.*;

/**
 * The Sprite class represents a game object with an image.
 * It encapsulates the properties and behavior of a simple sprite.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class Sprite {
    private Image image;
    private int x;
    private int y;

    /**
     * Constructs a Sprite with the specified image, x-coordinate, and y-coordinate.
     *
     * @param image The image representing the sprite.
     * @param x The initial x-coordinate of the sprite.
     * @param y The initial y-coordinate of the sprite.
     */
    public Sprite(Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the sprite on the specified Graphics object.
     *
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    /**
     * Gets the x-coordinate of the sprite.
     *
     * @return The x-coordinate of the sprite.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the sprite.
     *
     * @return The y-coordinate of the sprite.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the width of the sprite.
     *
     * @return The width of the sprite.
     */
    public int getWidth() {
        return image.getWidth(null);
    }

    /**
     * Gets the height of the sprite.
     *
     * @return The height of the sprite.
     */
    public int getHeight() {
        return image.getHeight(null);
    }
}
