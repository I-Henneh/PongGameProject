import java.awt.*;
import javax.swing.*;

/**
 * The GameFrame class represents the main frame for the Pong game.
 * It contains a GamePanel as the main content.
 * The frame is set up with a title, resizable property, background color, default close operation,
 * and is positioned at the center of the screen.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class GameFrame extends JFrame {

    /** The GamePanel instance representing the main content of the frame. */
    private GamePanel panel;

    /**
     * Constructs a new GameFrame, initializing the GamePanel and setting up the frame properties.
     */
    GameFrame() {
        panel = new GamePanel();

        // Add the GamePanel to the content pane
        this.add(panel);

        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * The main method to create and run the GameFrame.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create and run the game frame
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}
