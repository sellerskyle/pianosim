package pianosim;

import java.awt.Dimension;
import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.sounds.ReusableClip;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/*
TODO:
*Add on click functionality

*Add in sounds

*Add file output that records notes that you have played
-have a reset and save button

*Notes should be played at x BPM for 1 beat

Can use time between key pressed and key released for key length

can alter time between play and stop to mimic note length
*/

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
    
    //From StackOverflow
    static PointerInfo pointer;
    static Point point;
    static Robot robot;
    static Color mouseColor;
    
    public static void main(String[] args) {
        assert SCALE % 3 == 0;
        // Creating both frame and screen, with screen being in the frame
        BasicFrame frame = new BasicFrame("Piano Simulator");
        final SpriteComponent screen = new SpriteComponent();
        
        screen.setPreferredSize(FRAME_DIMENSION);
        final String[][] LAYOUT = {{ "help"},{"piano"}};
        final String helpDialog = "The computer keys on the left correspond to the piano key on the right: \n z : C1";
        final String controlDialog = "The computer keys on the left correspond to the piano key on the right: \n z : C1";
        
        //Importing Audio
        final AudioClip c1Track = new ReusableClip("C1.wav");
        final AudioClip cSharp1Track = new ReusableClip("C#1.wav");
        final AudioClip d1Track = new ReusableClip("D1.wav");
        final AudioClip dSharp1Track = new ReusableClip("D#1.wav");
        final AudioClip e1Track = new ReusableClip("E1.wav");
        final AudioClip f1Track = new ReusableClip("F1.wav");
        final AudioClip fSharp1Track = new ReusableClip("F#1.wav");
        final AudioClip g1Track = new ReusableClip("G1.wav");
        final AudioClip gSharp1Track = new ReusableClip("G#1.wav");
        final AudioClip a1Track = new ReusableClip("A1.wav");
        final AudioClip aSharp1Track = new ReusableClip("A#1.wav");
        final AudioClip b1Track = new ReusableClip("B1.wav");
        final AudioClip c2Track = new ReusableClip("C2.wav");
        final AudioClip cSharp2Track = new ReusableClip("C#2.wav");
        final AudioClip d2Track = new ReusableClip("D2.wav");
        final AudioClip dSharp2Track = new ReusableClip("D#2.wav");
        final AudioClip e2Track = new ReusableClip("E2.wav");
        final AudioClip f2Track = new ReusableClip("F2.wav");
        final AudioClip fSharp2Track = new ReusableClip("F#2.wav");
        final AudioClip g2Track = new ReusableClip("G2.wav");
        final AudioClip gSharp2Track = new ReusableClip("G#2.wav");
        final AudioClip a2Track = new ReusableClip("A2.wav");
        final AudioClip aSharp2Track = new ReusableClip("A#2.wav");
        final AudioClip b2Track = new ReusableClip("B2.wav");
        
        frame.addKeyListener(new KeyAdapter() {   
            @Override
            public void keyPressed(KeyEvent ke) {
                char key = ke.getKeyChar(); 
                switch(key){
                case 'z': c1Track.play(); System.out.println("C1 played");
                        break;
                case 's': cSharp1Track.play();
                        break;
                case 'x': d1Track.play();
                        break;
                case 'd': dSharp1Track.play();
                        break;
                case 'c': e1Track.play();
                        break;
                case 'v': f1Track.play();
                        break;
                case 'g': fSharp1Track.play();
                        break;
                case 'b': g1Track.play();
                        break;
                case 'h': gSharp1Track.play();
                        break; 
                case 'n': a1Track.play();
                        break; 
                case 'j': aSharp1Track.play();
                        break;  
                case 'm': b1Track.play();
                        break;
                case 'q': c2Track.play();
                        break;
                case '2': cSharp2Track.play();
                        break;
                case 'w': d2Track.play();
                        break;
                case '3': dSharp2Track.play();
                        break;
                case 'e': e2Track.play();
                        break;
                case 'r': f2Track.play();
                        break;
                case '5': fSharp2Track.play();
                        break;
                case 't': g2Track.play();
                        break;
                case '6': gSharp2Track.play();
                        break; 
                case 'y': a2Track.play();
                        break; 
                case '7': aSharp2Track.play();
                        break;  
                case 'u': b2Track.play();
                        break;
                default: ;
                        break;
                } 
            }
        });
        
        
        screen.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                //From stack overflow
                try{
                    robot = new Robot();
                    pointer = MouseInfo.getPointerInfo();
                    point = pointer.getLocation();
                    mouseColor = robot.getPixelColor((int)point.getX(),(int)point.getY());
                    System.out.println("Color at: " + point.getX() + "," + point.getY() + " is: " + mouseColor);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                //Resume my code
                int keyBlue = mouseColor.getBlue(); //Value of blue used to identify keys
                switch(keyBlue){
                case 254: c1Track.play(); System.out.println("C1 played");
                        break;
                case 1: cSharp1Track.play();
                        break;
                case 253: d1Track.play();
                        break;
                case 2: dSharp1Track.play();
                        break;
                case 252: e1Track.play();
                        break;
                case 251: f1Track.play();
                        break;
                case 3: fSharp1Track.play();
                        break;
                case 250: g1Track.play();
                        break;
                case 4: gSharp1Track.play();
                        break; 
                case 249: a1Track.play();
                        break; 
                case 5: aSharp1Track.play();
                        break;  
                case 248: b1Track.play();
                        break;
                case 247: c2Track.play();
                        break;
                case 6: cSharp2Track.play();
                        break;
                case 246: d2Track.play();
                        break;
                case 7: dSharp2Track.play();
                        break;
                case 245: e2Track.play();
                        break;
                case 244: f2Track.play();
                        break;
                case 8: fSharp2Track.play();
                        break;
                case 243: g2Track.play();
                        break;
                case 9: gSharp2Track.play();
                        break; 
                case 242: a2Track.play();
                        break; 
                case 10: aSharp2Track.play();
                        break;  
                case 241: b2Track.play();
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
        
        Key[] pianoKeys = new Key[TOTAL_KEYS];
        
        for(int i = 1; i <= TOTAL_KEYS; i++) {
            pianoKeys[i-1] = new Key(i, KEY_WIDTH);
        }
        
        for (int i = 0; i < pianoKeys.length; i++) {
            Sprite key = new Sprite();
            key.setPicture(pianoKeys[i].getPicture());
            key.setX(pianoKeys[i].getXPosition());
            screen.addSprite(key);
        }
        
        screen.start(0,10);
        frame.add(LAYOUT, "piano", screen);
        frame.show();
    }
    
    
}


//Array of key sprites? 
//method to create pictures