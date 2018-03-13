package pianosim;

import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import pianosim.Key;

public class Piano {
    final static int NUM_OCTAVES = 2;
    final static int TOTAL_KEYS_IN_OCTAVE = 12;
    final static int TOTAL_WHITE_KEYS_IN_OCTAVE = 7;
    
    //assert (PIANO_WIDTH % (TOTAL_WHITE_KEYS_IN_OCTAVE * NUM_OCTAVES) == 0);
    
    final static int PIANO_WIDTH = 798;
    final static int PIANO_HEIGHT = 600;
    final static Dimension PIANO_DIMENSIONS = new Dimension(PIANO_WIDTH, PIANO_HEIGHT);
    final static SpriteComponent PIANO_SCREEN = new SpriteComponent();
    
    
    static String[][] pianoLayout = {{"pianoTop"},{"pianoBottom"}};
    static String[] keyLayout = generatePianoLayout();
    static String[] whiteKeyLayout = generateWhitePianoLayout();
            
    
    private static String[] generatePianoLayout(){
        String[] layout = new String[NUM_OCTAVES * TOTAL_KEYS_IN_OCTAVE];
        int index;
        
        assert NUM_OCTAVES >= 1;
        
        for (int i = 1; i <= NUM_OCTAVES; i++){
            for(int j = 0; j < TOTAL_KEYS_IN_OCTAVE; j++){
                index = (TOTAL_KEYS_IN_OCTAVE *(i-1))+j;
                
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
    
    private static String[] generateWhitePianoLayout(){
        String[] whiteLayout = new String[NUM_OCTAVES * TOTAL_WHITE_KEYS_IN_OCTAVE];
        int index;
        
        assert NUM_OCTAVES >= 1;
        
        for (int i = 1; i <= NUM_OCTAVES; i++){
            for(int j = 0; j < TOTAL_WHITE_KEYS_IN_OCTAVE; j++){
                index = (TOTAL_WHITE_KEYS_IN_OCTAVE *(i-1))+j;
                
                switch(j){
                case 0: whiteLayout[index] = "C" + i;
                        break;
                case 1: whiteLayout[index] = "D" + i;
                        break;
                case 2: whiteLayout[index] = "E" + i;
                        break;
                case 3: whiteLayout[index] = "F" + i;
                        break;
                case 4: whiteLayout[index] = "G" + i;
                        break;
                case 5: whiteLayout[index] = "A" + i;
                        break;
                case 6: whiteLayout[index] = "B" + i;
                        break;        
                default: whiteLayout[index] = "There was an error";
                        break;
                }
            }
        }
        return whiteLayout;
    }
    
    public void createPiano() {
        createPianoTop();
        createPianoBottom();
    }
    
    private void createPianoBottom() {
        Picture bottomWhiteKeys = Key.createBottomWhiteKey();
    }
    
    public static void main(String[] args) {
        
        for(String s : whiteLayout){
            System.out.println(s);
        }
    }
}
