package pianosim;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class MyClipTester {
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        MyClip testClip = new MyClip("D:\\Documents\\NetBeansProjects\\BasicGraphics\\src\\basicgraphics\\sounds\\C1.wav");
        MyClip testClip2 = new MyClip("D:\\Documents\\NetBeansProjects\\BasicGraphics\\src\\basicgraphics\\sounds\\E1.wav");
        System.out.println("Before");
        testClip.loop();
        testClip2.loop();
        System.out.println("After");
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // A GUI element to prevent the Clip's daemon Thread
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        });
    }
}
