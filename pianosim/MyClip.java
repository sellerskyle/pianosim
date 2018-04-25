package pianosim;

import basicgraphics.FileUtility;
import basicgraphics.images.RuntimeIOException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
Can only play 4 sounds together max ( At least in my experimentation )
*/
public class MyClip {
    URL soundURL;
    AudioInputStream audioIn;
    Clip clip;
    String fileName;

    private static Timer timer = new Timer();
    
    public MyClip() {       
    }
    
    /*
    public MyClip(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        String pathToFile;
        Path currentRelativePath = Paths.get("");
        pathToFile = currentRelativePath.toAbsolutePath().toString();
        pathToFile += fileName;
        soundFile = new File(pathToFile);
        //audioIn = AudioSystem.getAudioInputStream(soundFile);
        clip = AudioSystem.getClip();      
    }   
    */
    public MyClip(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.fileName = fileName;
        URL src = null;
        try {
            src = new URL(fileName);
        } catch(MalformedURLException me) {
            ;
        }
        if(src == null)
            src = getClass().getResource(fileName);
        if(src == null) {
            src = FileUtility.findFile(fileName);
            if(src == null) {
                System.out.println("Could not load file: "+ fileName);
                throw new RuntimeIOException("No such file: "+ fileName);
            }
        }
        soundURL = src;
        clip = AudioSystem.getClip();      
    }
    
    public void load(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.fileName = fileName;
         URL src = null;
        try {
            src = new URL(fileName);
        } catch(MalformedURLException me) {
            ;
        }
        if(src == null)
            src = getClass().getResource(fileName);
        if(src == null) {
            src = FileUtility.findFile(fileName);
            if(src == null) {
                System.out.println("Could not load file: "+ fileName);
                throw new RuntimeIOException("No such file: "+ fileName);
            }
        }
        soundURL = src;
        clip = AudioSystem.getClip();
    }
    
    public void play() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        audioIn = AudioSystem.getAudioInputStream(soundURL);
        clip.open(audioIn);
        clip.start();
    }
    
    public void play(long delay, long duration) throws UnsupportedAudioFileException, IOException {
        //timer = new Timer();
        TimerTask playTT = new TimerTask(){
            @Override
            public void run() {
                try {
                    play();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                    Logger.getLogger(MyClip.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        TimerTask stopTT = new TimerTask(){
            @Override
            public void run() {
                clip.stop();
                clip.close();
            }
        };
        timer.schedule(playTT, delay);
        timer.schedule(stopTT, delay + duration);
    }
        
    
    /*
        public void start(int delay, int period) {
        if (t != null) {
            throw new GuiException("SpriteComponent already started");
        }
        t = new Timer();
        TimerTask tt = new TimerTask() {
            int toc = 0;
            @Override
            public void run() {
                TaskRunner.runLater(SpriteComponent.this, new Runnable() {
                    @Override
                    public void run() {
                        moveSprites();
                    }
                });
            }
        };
        t.schedule(tt, delay, period);
        }
    */
    
    public void loop() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        audioIn = AudioSystem.getAudioInputStream(soundURL);
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
    
    public URL getSoundURL() {
        return this.soundURL;
    }
    
    public void setSoundURL(URL url) {
        this.soundURL = url;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public String toString() {
        return this.fileName;
    }
    
}
