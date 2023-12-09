import java.awt.*;

/**
 * The Score class represents the scoring system in a Pong game.
 * It extends the Rectangle class and includes methods for drawing and getting player scores.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class Score extends Rectangle {

    /** The width of the game screen. */
    private static int GAME_WIDTH;

    /** The height of the game screen. */
    private static int GAME_HEIGHT;


    /** The score of player 1. */
    public int player1;

    /** The score of player 2. */
    public int player2;

    /**
     * Constructs a Score object with the specified game width and height.
     *
     * @param gameWidth  The width of the game screen.
     * @param gameHeight The height of the game screen.
     */
    public Score(int gameWidth, int gameHeight) {
        GAME_WIDTH = gameWidth;
        GAME_HEIGHT = gameHeight;
    }

    /**
     * Draws the scores on the specified graphics context.
     *
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 80));
        // Draw player scores
        g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), (GAME_WIDTH / 2) - 150, 70);
        g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), (GAME_WIDTH / 2) + 70, 70);
    }

    /**
     * Gets the score of player 1.
     *
     * @return The score of player 1.
     */
    public int getPlayer1Score() {
        return player1;
    }

    /**
     * Gets the score of player 2.
     *
     * @return The score of player 2.
     */
    public int getPlayer2Score() {
        return player2;
    }
}
