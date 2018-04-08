package pianosim;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MyClip {
    File soundFile;
    AudioInputStream audioIn;
    Clip clip;
    
    public MyClip() {       
    }
    
    public MyClip(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        soundFile = new File(fileName);
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        clip = AudioSystem.getClip();      
    }   
    
    public void load(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        soundFile = new File(fileName);
        audioIn = AudioSystem.getAudioInputStream(soundFile);
        clip = AudioSystem.getClip();
    }
    
    public void play() throws LineUnavailableException, IOException {
        clip.open(audioIn);
        clip.start();
    }
    
    public void loop() throws LineUnavailableException, IOException {     
        clip.open(audioIn);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop() {
        clip.stop();
        clip.close();
    }
    
    public boolean isPlaying() {
        return clip.isActive();
    }
    
}
