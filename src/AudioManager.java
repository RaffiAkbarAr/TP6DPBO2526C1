
import javax.sound.sampled.*;
import java.io.File;

public class AudioManager {
    private Clip bgm;

    // Memainkan efek suara singkat setiap burung melompat.
    public void playJump() {
        try {
            File file = new File("assets/jump.wav");
            if (!file.exists()) file = new File("FlappyBird/assets/jump.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip jump = AudioSystem.getClip();
            jump.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    jump.close();
                }
            });
            jump.open(stream);
            jump.start();
        } catch (Exception e) {
            System.err.println("Efek suara lompat tidak dapat diputar: " + e.getMessage());
        }
    }
    public void playBgm() {
        if (bgm != null && bgm.isRunning()) return;
        try {
            File file = new File("assets/bgm.wav");
            if (!file.exists()) file = new File("FlappyBird/assets/bgm.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            bgm = AudioSystem.getClip();
            bgm.open(stream);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("BGM tidak dapat diputar: " + e.getMessage());
        }
    }
    public void stopBgm() { if (bgm != null) { bgm.stop(); bgm.close(); bgm = null; } }
}
