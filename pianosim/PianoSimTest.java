package pianosim;

import java.awt.Dimension;
import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PianoSimTest {
    //piano constants
    final static int NUM_OCTAVES = 2;
    final static int WHITE_KEYS_IN_OCTAVE = 7;
    final static int KEYS_IN_OCTAVE = 12;
    final static int TOTAL_WHITE_KEYS = NUM_OCTAVES * WHITE_KEYS_IN_OCTAVE;
    final static int TOTAL_KEYS = NUM_OCTAVES * KEYS_IN_OCTAVE;
    
    //sizing constants
    final static int SCALE = 135;
    
    final static int KEY_WIDTH = SCALE;
    final static int BOTTOM_KEY_HEIGHT = SCALE;
    final static int TOP_KEY_HEIGHT = SCALE * 2;
    final static int TOP_BLACK_KEY_WIDTH = (SCALE * 2) / 3;
    final static int TOP_WHITE_KEY_WIDE_WIDTH = (SCALE * 2) / 3;
    final static int TOP_WHITE_KEY_SKINNY_WIDTH = SCALE / 3;
    final static int KEY_HEIGHT = SCALE * 3;
    final static int PIANO_WIDTH = KEY_WIDTH * TOTAL_WHITE_KEYS;
    
    final static Dimension FRAME_DIMENSION = new Dimension(KEY_WIDTH * TOTAL_WHITE_KEYS, KEY_HEIGHT);
    
    //final static Dimension BOTTOM_SCREEN_DIMENSION = new Dimension(KEY_WIDTH * TOTAL_WHITE_KEYS, BOTTOM_KEY_HEIGHT);
    //final static Dimension TOP_SCREEN_DIMENSION = new Dimenstion(KEY_WIDTH * TOTAL_WHITE_KEYS, TOP_KEY_HEIGHT)
    
    
    public static void main(String[] args) {
        assert SCALE % 3 == 0;
        // Creating both frame and screen, with screen being in the frame
        BasicFrame frame = new BasicFrame("Piano Simulator");
        final SpriteComponent screen = new SpriteComponent();
        
        /*
        final SpriteComponent bottomScreen = new SpriteComponent();
        final SpriteComponent topScreen = new SpriteComponent();
        bottomScreen.setPreferredSize(BOTTOM_SCREEN_DIMENSION);
        topScreen.setPreferredSize(TOP_SCREEN_DIMENSION);
        */
        
        screen.setPreferredSize(FRAME_DIMENSION);
        final String[][] LAYOUT = {{"help"},{"piano"}};
        //final String[][] LAYOUT = {{"help"},{"pianoTop"},{"pianoBottom"};
        final String helpDialog = "The computer keys on the left correspond to the piano key on the right: \n z : C1";
        
        frame.addKeyListener(new KeyAdapter() {   
            @Override
            public void keyPressed(KeyEvent ke) {
                char key = ke.getKeyChar();
                switch(key){
                case 'z': /*play c1*/;
                        break;
                case 's': /*play c#1*/;
                        break;
                case 'x': /*play d1*/;
                        break;
                case 'd': /*play d#1*/;
                        break;
                case 'c': /*play e1*/;
                        break;
                case 'v': /*play f1*/;
                        break;
                case 'g': /*play f#1*/;
                        break;
                case 'b': /*play g1*/;
                        break;
                case 'h': /*play g#1*/;
                        break; 
                case 'n': /*play a1*/;
                        break; 
                case 'j': /*play a#1*/;
                        break;  
                case 'm': /*play b1*/;
                        break;
                case 'q': /*play c2*/;
                        break;
                case '2': /*play c#2*/;
                        break;
                case 'w': /*play d2*/;
                        break;
                case '3': /*play d#2*/;
                        break;
                case 'e': /*play e2*/;
                        break;
                case 'r': /*play f2*/;
                        break;
                case '5': /*play f#2*/;
                        break;
                case 't': /*play g2*/;
                        break;
                case '6': /*play g#2*/;
                        break; 
                case 'y': /*play a2*/;
                        break; 
                case '7': /*play a#2*/;
                        break;  
                case 'u': /*play b2*/;
                        break;
                default: ;
                        break;
                } 
            }
        });
        
        final JButton helpButton = new JButton("Help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(helpButton, helpDialog);
            }
        });
        frame.add(LAYOUT,"help", helpButton);
        
        /*
        Color ivory = new Color(255,255,240);
        Image bottomWhiteKeyImg = BasicFrame.createImage(KEY_WIDTH, KEY_HEIGHT);
        Graphics bottomWhiteKeyGraphic = bottomWhiteKeyImg.getGraphics();
        bottomWhiteKeyGraphic.setColor(ivory);
        bottomWhiteKeyGraphic.fillRect(0, 0, KEY_WIDTH, KEY_HEIGHT);
        bottomWhiteKeyGraphic.drawRect(0, 0, KEY_WIDTH, KEY_HEIGHT);
        Picture bottomWhiteKeyPicture = new Picture(bottomWhiteKeyImg);
        
        Image keyOutlineImg= BasicFrame.createImage(KEY_WIDTH, KEY_HEIGHT);
        Graphics keyOutlineGraphic = keyOutlineImg.getGraphics();
        keyOutlineGraphic.setColor(Color.BLACK);
        keyOutlineGraphic.drawRect(0, 0, KEY_WIDTH-1, KEY_HEIGHT-1);
        Picture keyOutlinePicture = new Picture(keyOutlineImg);
        
        Sprite key1 = new Sprite();
        key1.setPicture(bottomWhiteKeyPicture);
        
        Sprite key1Outline = new Sprite();
        key1Outline.setPicture(keyOutlinePicture);
        
        screen.addSprite(key1);
        screen.addSprite(key1Outline);
        */
        for(int i = 0; i < TOTAL_WHITE_KEYS; i++){
            Color keyColor = new Color(255, 255, 255-i);
            Picture keyPicture = createBottomKeyPicture(keyColor);
            
            Sprite key = new Sprite();
            
            key.setPicture(keyPicture);
            key.setX(KEY_WIDTH * i);
            key.setY(KEY_WIDTH * 2); //delete this later
            
            screen.addSprite(key);
            
            
        }
        screen.start(0,10);
        frame.add(LAYOUT, "piano", screen);
        frame.show();
    }
    
    public static Picture createBottomKeyPicture(Color c){
        Image bottomWhiteKeyImg = BasicFrame.createImage(KEY_WIDTH, BOTTOM_KEY_HEIGHT);
        Graphics bottomWhiteKeyGraphic = bottomWhiteKeyImg.getGraphics();
        bottomWhiteKeyGraphic.setColor(c);
        bottomWhiteKeyGraphic.fillRect(0, 0, KEY_WIDTH, KEY_HEIGHT);
        bottomWhiteKeyGraphic.setColor(Color.BLACK);
        bottomWhiteKeyGraphic.drawRect(-1, -1, KEY_WIDTH, KEY_HEIGHT);
        Picture bottomWhiteKeyPicture = new Picture(bottomWhiteKeyImg);
        
        return bottomWhiteKeyPicture;
    }
    
}


//Array of key sprites? 
//method to create pictures