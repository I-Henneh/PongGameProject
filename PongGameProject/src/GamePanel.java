import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * The GamePanel class represents the main panel for the Pong game.
 * It handles the game loop, user input, collisions, scoring, and winner declaration.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class GamePanel extends JPanel implements Runnable {

    private static Image CONSTANT_IMAGE = new ImageIcon("C:\\Users\\nanay\\Downloads\\soccer-field-1.jpg").getImage();
    private final Background background;
    private final Sprite centerSprite;

    private Sound paddleHitSound = new Sound("C:\\Users\\nanay\\OneDrive\\Desktop\\gamehit.wav");
    private Sound scoreSound = new Sound("C:\\Users\\nanay\\OneDrive\\Desktop\\crowd.wav");
    private Sound winnerSound = new Sound("C:\\Users\\nanay\\OneDrive\\Desktop\\fanfare.wav");

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    private volatile boolean gameActive;  // Flag indicating whether the game is active
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    /**
     * Constructs a GamePanel object, initializing the game components.
     */
    GamePanel() {
        CONSTANT_IMAGE = new ImageIcon("C:\\Users\\nanay\\Downloads\\RUlogo.png").getImage();
        int centerX = GAME_WIDTH / 2 - CONSTANT_IMAGE.getWidth(null) / 2;
        int centerY = GAME_HEIGHT / 2 - CONSTANT_IMAGE.getHeight(null) / 2;
        background = new Background(new ImageIcon("C:\\Users\\nanay\\Downloads\\soccer-field-1.jpg").getImage());
        centerSprite = new Sprite(CONSTANT_IMAGE, centerX, centerY);
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameActive = true;  // Initialize gameActive to true
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Creates a new ball at the center of the game window.
     */
    public void newBall() {
        random = new Random();
        int initialXVelocity = 2;  // Set an appropriate initial value for X velocity
        int initialYVelocity = 2;  // Set an appropriate initial value for Y velocity
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    /**
     * Creates new paddles for player 1 and player 2.
     */
    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    /**
     * Paints the game components on the panel.
     *
     * @param g The Graphics object used for rendering.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call super.paint() to clear and prepare the panel for painting

        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        background.draw(graphics, getWidth(), getHeight());
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }


    /**
     * Draws the game components on the specified Graphics object.
     *
     * @param g The Graphics object used for rendering.
     */
    public void draw(Graphics g) {
        centerSprite.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Moves the paddles and the ball.
     */
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    /**
     * Checks for collisions between the ball and paddles, updates scores, and handles game events.
     *
     * @throws InterruptedException If interrupted while sleeping.
     */
    public void checkCollision() throws InterruptedException {
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            paddleHitSound.play();
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            paddleHitSound.play();
            if (ball.yVelocity > 0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if (paddle1.y <= 0)
            paddle1.y = 0;
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        if (ball.x <= 0) {
            score.player2++;
            scoreSound.play();
            System.out.println("Player 2: " + score.player2);
            newPaddles();
            Thread.sleep(500);
            newBall();
            handleScoreUpdate(2);

        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            scoreSound.play();
            System.out.println("Player 1: " + score.player1);
            newPaddles();
            newBall();
            Thread.sleep(500);
            handleScoreUpdate(1);
        }
    }

    /**
     * Runs the game loop, handling updates and rendering.
     */
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (gameActive) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                move();
                try {
                    checkCollision();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Declares the winner, plays the winner sound, and prompts for restart or exit.
     *
     * @param player The player who won.
     */
    private void declareWinner(int player) {

        gameActive = false;
        winnerSound.play();
        winnerSound.play();
        String winnerMessage = "Player " + player + " wins!\n" +
                "Scores: Player1 " + score.getPlayer1Score() + " - " + score.getPlayer2Score() + " Player2";
        JOptionPane.showMessageDialog(this, winnerMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        int choice = JOptionPane.showOptionDialog(
                this,
                "Do you want to restart the game?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );

        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * Restarts the game by resetting scores, paddles, and the ball.
     */
    private void restartGame() {
        // Create a new game thread and start the game again
        newPaddles();
        gameThread = new Thread(() -> {
            try {
                // Sleep for 3 seconds (adjust the time as needed)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newBall();

            // Reset game components
            score.player1 = 0;
            score.player2 = 0;

            // Set game as active and repaint the panel
            gameActive = true;
            repaint();
            run(); // Start the game loop again
        });
        gameThread.start();
    }

    /**
     * Handles score updates and declares a winner if the score reaches the winning threshold.
     *
     * @param player The player who scored.
     */
    private void handleScoreUpdate(int player) {
        newPaddles();
        newBall();
        if (player == 1 && score.player1 == 3) {
            declareWinner(1);
        } else if (player == 2 && score.player2 == 3) {
            declareWinner(2);
        }
    }

    /**
     * Handles keyboard input for player paddles.
     */
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
