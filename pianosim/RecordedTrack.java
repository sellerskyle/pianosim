package pianosim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.regex.MatchResult;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Kyle
 */
public class RecordedTrack {
    String fileName;
    List<RecordedClip> clipArray = new ArrayList<>();
    RecordedClip referenceClip;
    

    
    public RecordedTrack(String fileName) {
        this.fileName = fileName;
    }
    
    
    public void toFile() throws IOException {
        File outputFile = new File(fileName);
        try(FileWriter fw = new FileWriter(outputFile)) {
            String toWrite;
            for ( RecordedClip rc : clipArray) {
                toWrite = rc.toString() + String.format("%n");
                fw.write(toWrite);
            }
        }
    }
    
    public void add(RecordedClip RecClip){
        clipArray.add(RecClip);
    }
    
    public static void playFromFile(String fileName) throws FileNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        File inputFile = new File(fileName);
        
        try(FileReader fr = new FileReader(inputFile)) {
        Scanner sr = new Scanner(fr);
        String pattern = "(\\w+).wav\\s+:\\s+(\\d+)\\s+:\\s+(\\d+)";
        while(sr.hasNextLine()) { // are there more lines?
        if(sr.findInLine(pattern) != null) { // does the pattern exist?
            MatchResult m = sr.match(); // get the result
            String clipName = m.group(1) + ".wav";
            long delay = Long.parseLong(m.group(2));
            long duration = Long.parseLong(m.group(3));
            
            MyClip mc = new MyClip(clipName);
            mc.play(delay, duration);
            
        }
        sr.nextLine();
      }
    }
        
    }
    /*
        public void initFromString(String input) {
        Scanner sr = new Scanner(input);
        String format = "Warrior\\s+(\\w+)\\s*,\\s*health\\s*=\\s*(\\d+)\\s*,\\s*max\\s+health\\s*=\\s*(\\d+)\\s*";
        //while(sr.hasNextLine()) {
            if(sr.findInLine(format) != null) {
                MatchResult m = sr.match();
                name = m.group(1);
                health = Integer.parseInt(m.group(2));
                maxHealth = Integer.parseInt(m.group(3));
                    
            } else {
                throw new PatternMatchException();
            }
            //sr.nextLine();
        //}
        }
    */
    
    public static void main(String[] args){
        
    }
}
