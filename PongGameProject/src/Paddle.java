import java.awt.*;
import java.awt.event.*;

/**
 * The Paddle class represents a player's paddle in a Pong game.
 * It extends the Rectangle class and includes methods for handling user input, movement, and drawing.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class Paddle extends Rectangle {

    /** The unique identifier for the paddle. */
    private int id;

    /** The vertical velocity of the paddle. */
    private int yVelocity;

    /** The speed at which the paddle moves. */
    private int speed = 10;

    /**
     * Constructs a Paddle object with the specified position, dimensions, and identifier.
     *
     * @param x            The x-coordinate of the top-left corner of the paddle.
     * @param y            The y-coordinate of the top-left corner of the paddle.
     * @param paddleWidth  The width of the paddle.
     * @param paddleHeight The height of the paddle.
     * @param id           The unique identifier for the paddle.
     */
    Paddle(int x, int y, int paddleWidth, int paddleHeight, int id) {
        super(x, y, paddleWidth, paddleHeight);
        this.id = id;
    }

    /**
     * Handles the key pressed event to set the vertical direction of the paddle.
     *
     * @param e The KeyEvent object representing the key pressed event.
     */
    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
        }
    }

    /**
     * Handles the key released event to reset the vertical direction of the paddle.
     *
     * @param e The KeyEvent object representing the key released event.
     */
    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }

    /**
     * Sets the vertical direction of the paddle.
     *
     * @param yDirection The vertical direction (positive or negative speed).
     */
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    /**
     * Moves the paddle based on its vertical velocity.
     */
    public void move() {
        y = y + yVelocity;
    }

    /**
     * Draws the paddle on the specified graphics context with a color based on the paddle's identifier.
     *
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g) {
        if (id == 1)
            g.setColor(Color.blue);
        else
            g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}
