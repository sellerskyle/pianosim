package pianosim;

import basicgraphics.SpriteComponent;
import java.awt.Dimension;

public class Piano {
    final static int NUM_OCTAVES = 2;
    final static int TOTAL_KEYS_IN_OCTAVE = 12;
    final static int TOTAL_WHITE_KEYS_IN_OCTAVE = 7;
    
    //assert (PIANO_WIDTH % (TOTAL_WHITE_KEYS_IN_OCTAVE * NUM_OCTAVES) == 0);
    
    final static int PIANO_WIDTH = 798;
    final static int PIANO_HEIGHT = 600;
    final static Dimension PIANO_DIMENSIONS = new Dimension(PIANO_WIDTH, PIANO_HEIGHT);
    final static SpriteComponent PIANO_SCREEN = new SpriteComponent();
    
    
    
    static String[] layout = generatePianoLayout();
            
    
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
    
    
    
    public static void main(String[] args) {
        
        for(String s : layout){
            System.out.println(s);
        }
    }
    
}
