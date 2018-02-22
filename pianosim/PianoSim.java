package pianosim;

import java.awt.Dimension;
import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;

public class PianoSim {
    
    final static int FRAME_WIDTH = 800;
    final static int FRAME_HEIGHT = 400;
    final static Dimension FRAME_DIMENSION = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    public static void main(String[] args) {
        // Creating both frame and screen, with screen being in the frame
        BasicFrame frame = new BasicFrame("Piano Simulator");
        final SpriteComponent screen = new SpriteComponent();
        screen.setPreferredSize(FRAME_DIMENSION);
        
        screen.start(0,10);
    }
}
