import java.awt.*;
import java.util.Random;

/**
 * The Ball class represents the ball object in a Pong game.
 * It extends the Rectangle class and includes methods for moving and drawing the ball.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class Ball extends Rectangle {

    /** The Random object for generating random values. */
    Random random;

    /** The x-axis velocity of the ball. */
    int xVelocity;

    /** The y-axis velocity of the ball. */
    int yVelocity;

    /** The initial speed of the ball. */
    int  initialSpeed = 2;

    /**
     * Constructs a Ball object with the specified position, width, and height.
     *
     * @param x      The x-coordinate of the ball's top-left corner.
     * @param y      The y-coordinate of the ball's top-left corner.
     * @param width  The width of the ball.
     * @param height The height of the ball.
     */
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }

    /**
     * Sets the x-axis direction (velocity) of the ball.
     *
     * @param randomXDirection The x-axis direction (1 for right, -1 for left).
     */
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    /**
     * Sets the y-axis direction (velocity) of the ball.
     *
     * @param randomYDirection The y-axis direction (1 for down, -1 for up).
     */
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    /**
     * Moves the ball based on its current velocity.
     */
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    /**
     * Draws the ball on the specified graphics context.
     *
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }
}
