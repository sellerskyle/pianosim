package pianosim;

import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import static pianosim.Piano.NUM_OCTAVES;
import static pianosim.Piano.PIANO_HEIGHT;
import java.util.Arrays;
import static pianosim.PianoSimTest.KEY_WIDTH;
import static pianosim.PianoSimTest.SCALE;

public class Key {
    
    final static int NUM_OCTAVES = PianoSimTest.NUM_OCTAVES;
    final static int WHITE_KEYS_IN_OCTAVE = PianoSimTest.WHITE_KEYS_IN_OCTAVE;
    final static int KEYS_IN_OCTAVE = PianoSimTest.KEYS_IN_OCTAVE;
    final static int TOTAL_WHITE_KEYS = NUM_OCTAVES * WHITE_KEYS_IN_OCTAVE;
    final static int TOTAL_KEYS = NUM_OCTAVES * KEYS_IN_OCTAVE;
    final static String[] LAYOUT = generatePianoLayout();
    
    final static int SCALE = PianoSimTest.SCALE;
    final static int KEY_WIDTH = SCALE;
    final static int KEY_HEIGHT = SCALE * 3;
    final static int TOP_KEY_HEIGHT = SCALE * 2;
    final static int TOP_BLACK_KEY_WIDTH = (SCALE * 2) / 3;
    final static int TOP_WHITE_KEY_WIDE_WIDTH = (SCALE * 2) / 3;
    final static int TOP_WHITE_KEY_SKINNY_WIDTH = SCALE / 3;
    
    Color color;
    Picture picture;
    String name;
    int xPosition;
    String form;
    
    public Key(int keyNum, int keyWidth) {

        //assigning name
        this.name = LAYOUT[keyNum - 1];
        
        //assigning form
        if(this.name.charAt(1)== '#') {
            form = "black";
        } else if (((this.name.charAt(0) == 'C')||(this.name.charAt(0) == 'F'))&& (this.name.charAt(1)!= '#')) {
            form = "left";
        } else if (((this.name.charAt(0) == 'E')||(this.name.charAt(0) == 'B'))&& (this.name.charAt(1)!= '#')) {
            form = "right";
        } else {
            form = "middle";
        }
        
        //assigning color
        int count = 0;
        if (this.form.equals("black")) {
            for(int i = 0; i < keyNum - 1; i++) {
                if (LAYOUT[i].charAt(1) == '#') {
                    count++;
                }
            }
            this.color = new Color(0, 0, count + 1); //to prevent unwanted sound when click divider line
        } else {
            for(int i = 0; i < keyNum - 1; i++) {
                if (LAYOUT[i].charAt(1) != '#') {
                    count++;
                }
            }
            this.color = new Color(255, 255, 255-count-1); //So I can use transparent white
        }
        
        //assigning picture
        this.picture = createKeyPicture(this.form, this.color);
        
        //assigning xposition
        if(!this.form.equals("black")) {
            xPosition = count * KEY_WIDTH;
        } else {
            String octaveString = Character.toString(this.name.charAt(2));
            int octave = Integer.parseInt(octaveString);
            char baseNote = this.name.charAt(0);
            int xToAdd;
            
            switch(baseNote){
                case 'C': xToAdd  = (1 * SCALE) - (SCALE / 3);
                        break;
                case 'D': xToAdd  = (2 * SCALE) - (SCALE / 3);
                        break;
                case 'F': xToAdd  = (4 * SCALE) - (SCALE / 3);
                        break;
                case 'G': xToAdd  = (5 * SCALE) - (SCALE / 3);
                        break;
                case 'A': xToAdd  = (6 * SCALE) - (SCALE / 3);
                        break;
                default: xToAdd  = 0;
                        break;
            }
            
            this.xPosition = ((octave -1) * (KEY_WIDTH * WHITE_KEYS_IN_OCTAVE)) + xToAdd;
            System.out.printf("  %s: %d, %d%n", this.name, octave, xToAdd);
        }
        //this.xPosition = (keynum-1)
        
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public Picture getPicture() {
        return this.picture;
    }
    
    public int getXPosition() {
        return this.xPosition;
    }
    
    //work on this
    private static String[] generatePianoLayout(){
        String[] layout = new String[NUM_OCTAVES * KEYS_IN_OCTAVE];
        int index;
        
        assert NUM_OCTAVES >= 1;
        
        for (int i = 1; i <= NUM_OCTAVES; i++){
            for(int j = 0; j < KEYS_IN_OCTAVE; j++){
                index = (KEYS_IN_OCTAVE *(i-1))+j;
                
                switch(j){
                case 0: layout[index] = "C" + i;
                        break;
                case 1: layout[index] = "C#" + i;
                        break;
                case 2: layout[index] = "D" + i;
                        break;
                case 3: layout[index] = "D#" + i;
                        break;
                case 4: layout[index] = "E" + i;
                        break;
                case 5: layout[index] = "F" + i;
                        break;
                case 6: layout[index] = "F#" + i;
                        break;        
                case 7: layout[index] = "G" + i;
                        break;
                case 8: layout[index] = "G#" + i;
                        break;
                case 9: layout[index] = "A" + i;
                        break;
                case 10: layout[index] = "A#" + i;
                        break;
                case 11: layout[index] = "B" + i;
                        break;
                default: layout[index] = "There was an error";
                        break;
                }
            }
        }
        return layout;
    }
    
    private Picture createKeyPicture(String f, Color c) {
        Picture p;
        
        if(f.equals("black")) {
            p = createBlackKeyPicture(c);
        } else if (f.equals("left")) {
            p = createLeftKeyPicture(c);
        } else if (f.equals("right")) {
            p = createRightKeyPicture(c);
        } else {
            p = createMiddleKeyPicture(c);
        }
        
        return p;
    }
    
    private static Picture createBlackKeyPicture(Color c) {
        Image blackKeyImg = BasicFrame.createImage(TOP_BLACK_KEY_WIDTH, TOP_KEY_HEIGHT);
        Graphics blackKeyGraphic = blackKeyImg.getGraphics();
        blackKeyGraphic.setColor(c);
        blackKeyGraphic.fillRect(0, 0, TOP_BLACK_KEY_WIDTH, TOP_KEY_HEIGHT);
        Picture blackKeyPicture = new Picture(blackKeyImg);
        
        return blackKeyPicture;
    }
    
    private static Picture createLeftKeyPicture(Color c) {
        Image leftKeyImg = BasicFrame.createImage(KEY_WIDTH, KEY_HEIGHT);
        Graphics leftKeyGraphic = leftKeyImg.getGraphics();
        leftKeyGraphic.setColor(c);
        leftKeyGraphic.fillRect(0, 0, TOP_WHITE_KEY_WIDE_WIDTH, TOP_KEY_HEIGHT); // Top white part
        leftKeyGraphic.fillRect(0, TOP_KEY_HEIGHT, KEY_WIDTH, KEY_HEIGHT); //Bottom white part
        leftKeyGraphic.setColor(Color.BLACK);
        //leftKeyGraphic.drawRect(-1, -1, KEY_WIDTH, KEY_HEIGHT); //make into draw line later
        leftKeyGraphic.drawLine(KEY_WIDTH -1, TOP_KEY_HEIGHT, KEY_WIDTH -1, KEY_HEIGHT);
        leftKeyGraphic.drawLine(0, KEY_HEIGHT - 1, KEY_WIDTH, KEY_HEIGHT -1);
        Picture leftKeyPicture = new Picture(leftKeyImg);
        
        return leftKeyPicture;
    }
    
    private static Picture createRightKeyPicture(Color c) {
        Image rightKeyImg = BasicFrame.createImage(KEY_WIDTH, KEY_HEIGHT);
        Graphics rightKeyGraphic = rightKeyImg.getGraphics();
        rightKeyGraphic.setColor(c);
        rightKeyGraphic.fillRect(TOP_WHITE_KEY_SKINNY_WIDTH, 0, TOP_WHITE_KEY_WIDE_WIDTH, TOP_KEY_HEIGHT); 
        rightKeyGraphic.fillRect(0, TOP_KEY_HEIGHT, KEY_WIDTH, KEY_HEIGHT);
        rightKeyGraphic.setColor(Color.BLACK);
        rightKeyGraphic.drawLine(KEY_WIDTH -1, 0, KEY_WIDTH -1, KEY_HEIGHT);
        rightKeyGraphic.drawLine(0, KEY_HEIGHT - 1, KEY_WIDTH, KEY_HEIGHT -1);
        Picture rightKeyPicture = new Picture(rightKeyImg);
        
        return rightKeyPicture;
    }
    
    private static Picture createMiddleKeyPicture(Color c) {
        Image middleKeyImg = BasicFrame.createImage(KEY_WIDTH, KEY_HEIGHT);
        Graphics middleKeyGraphic = middleKeyImg.getGraphics();
        middleKeyGraphic.setColor(c);
        middleKeyGraphic.fillRect(TOP_WHITE_KEY_SKINNY_WIDTH, 0, TOP_WHITE_KEY_SKINNY_WIDTH, TOP_KEY_HEIGHT); 
        middleKeyGraphic.fillRect(0, TOP_KEY_HEIGHT, KEY_WIDTH, KEY_HEIGHT);
        middleKeyGraphic.setColor(Color.BLACK);
        middleKeyGraphic.drawLine(KEY_WIDTH -1, TOP_KEY_HEIGHT, KEY_WIDTH -1, KEY_HEIGHT);
        middleKeyGraphic.drawLine(0, KEY_HEIGHT - 1, KEY_WIDTH, KEY_HEIGHT -1);
        Picture middleKeyPicture = new Picture(middleKeyImg);
        
        return middleKeyPicture;
    }

    
    /*
    public static void main(String[] args) {
        System.out.println(KEY_WIDTH * WHITE_KEYS_IN_OCTAVE);
        BasicFrame frame = new BasicFrame("Test");
        final SpriteComponent screen = new SpriteComponent();
        screen.setPreferredSize(new Dimension(KEY_WIDTH * TOTAL_WHITE_KEYS, KEY_HEIGHT));
        String[][] LAYOUT = {{"piano"}};
        Key[] pianoKeys = new Key[TOTAL_KEYS];
        
        for(int i = 1; i <= TOTAL_KEYS; i++) {
            pianoKeys[i-1] = new Key(i, KEY_WIDTH);
            System.out.printf("%s: %d%n", pianoKeys[i-1].toString(), pianoKeys[i-1].getXPosition());
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
    
*/
}