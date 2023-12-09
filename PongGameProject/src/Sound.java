import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * The Sound class provides functionality for playing sound effects.
 * It supports loading sound files, playing them, and stopping playback.
 *
 * @author  Henneh Ivan Yaw
 * @version 1.0
 */
public class Sound {

    /** The Clip object for playing the sound. */
    private Clip clip;

    /**
     * Constructs a Sound object with the specified sound file.
     *
     * @param soundFilePath The file path of the sound file.
     */
    public Sound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound effect. If the sound is already playing, it stops and restarts from the beginning.
     */
    public void play() {
        if (clip != null) {
            clip.stop(); // Stop the clip if it's still playing
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        }
    }

    /**
     * Stops the playback of the sound effect.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
