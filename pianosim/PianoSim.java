package pianosim;

import basicgraphics.BasicContainer;
import java.awt.Dimension;
import basicgraphics.BasicFrame;
import basicgraphics.Sprite;
import basicgraphics.SpriteComponent;
import java.awt.Color;
import java.awt.Dialog;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


/*
TODO:
fix bug where chords have one note being a half second off on start time
create player
create play method with delay and duration



*/

public class PianoSim {
    //piano constants
    final static int NUM_OCTAVES = 2;
    final static int WHITE_KEYS_IN_OCTAVE = 7;
    final static int KEYS_IN_OCTAVE = 12;
    final static int TOTAL_WHITE_KEYS = NUM_OCTAVES * WHITE_KEYS_IN_OCTAVE;
    final static int TOTAL_KEYS = NUM_OCTAVES * KEYS_IN_OCTAVE;
    
    //sizing constants
    final static int SCALE = 60;
    
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
    static boolean isRecording = false;
    static RecordedTrack currentRecordedTrack;
    
    static RecordedClip currentC1RecordedClip;
    static RecordedClip currentCSharp1RecordedClip;
    static RecordedClip currentD1RecordedClip;
    static RecordedClip currentDSharp1RecordedClip;
    static RecordedClip currentE1RecordedClip;
    static RecordedClip currentF1RecordedClip;
    static RecordedClip currentFSharp1RecordedClip;
    static RecordedClip currentG1RecordedClip;
    static RecordedClip currentGSharp1RecordedClip;
    static RecordedClip currentA1RecordedClip;
    static RecordedClip currentASharp1RecordedClip;
    static RecordedClip currentB1RecordedClip;
    static RecordedClip currentC2RecordedClip;
    static RecordedClip currentCSharp2RecordedClip;
    static RecordedClip currentD2RecordedClip;
    static RecordedClip currentDSharp2RecordedClip;
    static RecordedClip currentE2RecordedClip;
    static RecordedClip currentF2RecordedClip;
    static RecordedClip currentFSharp2RecordedClip;
    static RecordedClip currentG2RecordedClip;
    static RecordedClip currentGSharp2RecordedClip;
    static RecordedClip currentA2RecordedClip;
    static RecordedClip currentASharp2RecordedClip;
    static RecordedClip currentB2RecordedClip;
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        assert SCALE % 3 == 0;
        // Creating both frame and screen, with screen being in the frame
        BasicFrame frame = new BasicFrame("Piano Simulator");
        final SpriteComponent screen = new SpriteComponent();
        
        screen.setPreferredSize(FRAME_DIMENSION);
        final String[][] LAYOUT = 
        { {"help","control"},
            {"piano","piano"},
            {"record","load"}};
        
        final String controlDialog = "The computer keys on the left correspond to the piano key on the right: \n"
                + " z : C1\n s : C#1\n x : D1\n d : D#1\n c : E1\n v : F1\n g : F#1\n b : G1\n h : G#1\n n : A1\n j : A#1\n m : B1\n "
                + " q : C1\n 2 : C#1\n w : D1\n 3 : D#1\n e : E1\n r : F1\n 5 : F#1\n t : G1\n 6 : G#1\n y : A1\n 7 : A#1\n u : B1\n";
        final String helpDialog = "To record, hit the record button and enter what you would like to name your file. \n"
                + "When finished, hit the record button again to stop recording.\n"
                + "Files will be saved in the BasicGraphics package. \n"
                + "To load a file, place the file in the basic graphics package, \n"
                + "click the load button, and enter the name of the file.\n"
                + "ALL FILES ARE .txt EXTENSION";
        
        
        
        //redo and check this
        
        final MyClip c1Track = new MyClip("C1.wav");
        final MyClip cSharp1Track = new MyClip("CSharp1.wav");
        final MyClip d1Track = new MyClip("D1.wav");
        final MyClip dSharp1Track = new MyClip("DSharp1.wav");
        final MyClip e1Track = new MyClip("E1.wav");
        final MyClip f1Track = new MyClip("F1.wav");
        final MyClip fSharp1Track = new MyClip("FSharp1.wav");
        final MyClip g1Track = new MyClip("G1.wav");
        final MyClip gSharp1Track = new MyClip("GSharp1.wav");
        final MyClip a1Track = new MyClip("A1.wav");
        final MyClip aSharp1Track = new MyClip("ASharp1.wav");
        final MyClip b1Track = new MyClip("B1.wav");
        final MyClip c2Track = new MyClip("C2.wav");
        final MyClip cSharp2Track = new MyClip("CSharp2.wav");
        final MyClip d2Track = new MyClip("D2.wav");
        final MyClip dSharp2Track = new MyClip("DSharp2.wav");
        final MyClip e2Track = new MyClip("E2.wav");
        final MyClip f2Track = new MyClip("F2.wav");
        final MyClip fSharp2Track = new MyClip("FSharp2.wav");
        final MyClip g2Track = new MyClip("G2.wav");
        final MyClip gSharp2Track = new MyClip("GSharp2.wav");
        final MyClip a2Track = new MyClip("A2.wav");
        final MyClip aSharp2Track = new MyClip("ASharp2.wav");
        final MyClip b2Track = new MyClip("B2.wav");
        
        MyClip[] trackList = {c1Track, cSharp1Track, d1Track, dSharp1Track, e1Track, f1Track, fSharp1Track, g1Track, gSharp1Track, a1Track, aSharp1Track, b1Track,
        c2Track, cSharp2Track, d2Track, dSharp2Track, e2Track, f2Track, fSharp2Track, g2Track, gSharp2Track, a2Track, aSharp2Track, b2Track};
        
        frame.addKeyListener(new KeyAdapter() {   
            @Override
            public void keyPressed(KeyEvent ke) {
                try {
                    char key = ke.getKeyChar();
                    System.out.println(key);
                    switch(key){
                        case 'z':
                            if(!c1Track.isPlaying()){
                                c1Track.play(); 
                                if(isRecording){
                                    currentC1RecordedClip = new RecordedClip(c1Track);
                                    currentC1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 's': 
                            if(!cSharp1Track.isPlaying()){
                                cSharp1Track.play();
                                if(isRecording){
                                    currentCSharp1RecordedClip = new RecordedClip(cSharp1Track);
                                    currentCSharp1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'x': 
                            if(!d1Track.isPlaying()){
                                d1Track.play();
                                if(isRecording){
                                    currentD1RecordedClip = new RecordedClip(d1Track);
                                    currentD1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'd': 
                            if(!dSharp1Track.isPlaying()){
                                dSharp1Track.play();
                                if(isRecording){
                                    currentDSharp1RecordedClip = new RecordedClip(dSharp1Track);
                                    currentDSharp1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'c': 
                            if(!e1Track.isPlaying()){
                                e1Track.play();
                                if(isRecording){
                                    currentE1RecordedClip = new RecordedClip(e1Track);
                                    currentE1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'v': 
                            if(!f1Track.isPlaying()){
                                f1Track.play();
                                if(isRecording){
                                    currentF1RecordedClip = new RecordedClip(f1Track);
                                    currentF1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'g': 
                            if(!fSharp1Track.isPlaying()){
                                fSharp1Track.play();
                                if(isRecording){
                                    currentFSharp1RecordedClip = new RecordedClip(fSharp1Track);
                                    currentFSharp1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'b':
                            if(!g1Track.isPlaying()){
                                g1Track.play();
                                if(isRecording){
                                    currentG1RecordedClip = new RecordedClip(g1Track);
                                    currentG1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'h': 
                            if(!gSharp1Track.isPlaying()){
                                gSharp1Track.play();
                                if(isRecording){
                                    currentGSharp1RecordedClip = new RecordedClip(gSharp1Track);
                                    currentGSharp1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'n':
                            if(!a1Track.isPlaying()){
                                a1Track.play();
                                if(isRecording){
                                    currentA1RecordedClip = new RecordedClip(a1Track);
                                    currentA1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'j': 
                            if(!aSharp1Track.isPlaying()){
                                aSharp1Track.play();
                                if(isRecording){
                                    currentASharp1RecordedClip = new RecordedClip(aSharp1Track);
                                    currentASharp1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'm': 
                            if(!b1Track.isPlaying()){
                                b1Track.play();
                                if(isRecording){
                                    currentB1RecordedClip = new RecordedClip(b1Track);
                                    currentB1RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'q': 
                            if(!c2Track.isPlaying()){
                                c2Track.play();
                                if(isRecording){
                                    currentC2RecordedClip = new RecordedClip(c2Track);
                                    currentC2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case '2': 
                            if(!cSharp2Track.isPlaying()){
                                cSharp2Track.play();
                                if(isRecording){
                                    currentCSharp2RecordedClip = new RecordedClip(cSharp2Track);
                                    currentCSharp2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'w': 
                            if(!d2Track.isPlaying()){
                                d2Track.play();
                                if(isRecording){
                                    currentD2RecordedClip = new RecordedClip(d2Track);
                                    currentD2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case '3': 
                            if(!dSharp2Track.isPlaying()){
                                dSharp2Track.play();
                                if(isRecording){
                                    currentDSharp2RecordedClip = new RecordedClip(dSharp2Track);
                                    currentDSharp2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'e': 
                            if(!e2Track.isPlaying()){
                                e2Track.play();
                                if(isRecording){
                                    currentE2RecordedClip = new RecordedClip(e2Track);
                                    currentE2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'r': 
                            if(!f2Track.isPlaying()){
                                f2Track.play();
                                if(isRecording){
                                    currentF2RecordedClip = new RecordedClip(f2Track);
                                    currentF2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case '5': 
                            if(!fSharp2Track.isPlaying()){
                                fSharp2Track.play();
                                if(isRecording){
                                    currentFSharp2RecordedClip = new RecordedClip(fSharp2Track);
                                    currentFSharp2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 't': 
                            if(!g2Track.isPlaying()){
                                g2Track.play();
                                if(isRecording){
                                    currentG2RecordedClip = new RecordedClip(g2Track);
                                    currentG2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case '6': 
                            if(!gSharp2Track.isPlaying()){
                                gSharp2Track.play();
                                if(isRecording){
                                    currentGSharp2RecordedClip = new RecordedClip(gSharp2Track);
                                    currentGSharp2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'y': 
                            if(!a2Track.isPlaying()){
                                a2Track.play();
                                if(isRecording){
                                    currentA2RecordedClip = new RecordedClip(a2Track);
                                    currentA2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case '7': 
                            if(!aSharp2Track.isPlaying()){
                                aSharp2Track.play();
                                if(isRecording){
                                    currentASharp2RecordedClip = new RecordedClip(aSharp2Track);
                                    currentASharp2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        case 'u': 
                            if(!b2Track.isPlaying()){
                                b2Track.play();
                                if(isRecording){
                                    currentB2RecordedClip = new RecordedClip(b2Track);
                                    currentB2RecordedClip.startRecordingClip();
                                }
                            }
                        break;
                        default: ;
                        break;
                    }
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           
            public void keyReleased(KeyEvent ke) {
                char key = ke.getKeyChar(); 
                System.out.println(key);
                switch(key){
                case 'z': 
                    c1Track.stop(); 
                    if(isRecording) {
                        currentC1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentC1RecordedClip);
                    }
                break;
                case 's': 
                    cSharp1Track.stop();
                    if(isRecording) {
                        currentCSharp1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentCSharp1RecordedClip);
                    }
                break;
                case 'x':
                    d1Track.stop();
                    if(isRecording) {
                        currentD1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentD1RecordedClip);
                    }
                break;
                case 'd': 
                    dSharp1Track.stop();
                    if(isRecording) {
                        currentDSharp1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentDSharp1RecordedClip);
                    }
                break;
                case 'c': 
                    e1Track.stop();
                    if(isRecording) {
                        currentE1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentE1RecordedClip);
                    }
                break;
                case 'v': 
                    f1Track.stop();
                    if(isRecording) {
                        currentF1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentF1RecordedClip);
                    }
                break;
                case 'g': 
                    fSharp1Track.stop();
                    if(isRecording) {
                        currentFSharp1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentFSharp1RecordedClip);
                    }
                break;
                case 'b': 
                    g1Track.stop();
                    if(isRecording) {
                        currentG1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentG1RecordedClip);
                    }
                break;
                case 'h': 
                    gSharp1Track.stop();
                    if(isRecording) {
                        currentGSharp1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentGSharp1RecordedClip);
                    }
                break; 
                case 'n': 
                    a1Track.stop();
                    if(isRecording) {
                        currentA1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentA1RecordedClip);
                    }
                break; 
                case 'j': 
                    aSharp1Track.stop();
                    if(isRecording) {
                        currentASharp1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentASharp1RecordedClip);
                    }
                break;  
                case 'm': 
                    b1Track.stop();
                    if(isRecording) {
                        currentB1RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentB1RecordedClip);
                    }
                break;
                case 'q': 
                    c2Track.stop();
                    if(isRecording) {
                        currentC2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentC2RecordedClip);
                    }
                break;
                case '2': 
                    cSharp2Track.stop();
                    if(isRecording) {
                        currentCSharp2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentCSharp2RecordedClip);
                    }
                break;
                case 'w':
                    d2Track.stop();
                    if(isRecording) {
                        currentD2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentD2RecordedClip);
                    }
                break;
                case '3': 
                    dSharp2Track.stop();
                    if(isRecording) {
                        currentDSharp2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentDSharp2RecordedClip);
                    }
                break;
                case 'e': 
                    e2Track.stop();
                    if(isRecording) {
                        currentE2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentE2RecordedClip);
                    }
                break;
                case 'r': 
                    f2Track.stop();
                    if(isRecording) {
                        currentF2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentF2RecordedClip);
                    }
                break;
                case '5': 
                    fSharp2Track.stop();
                    if(isRecording) {
                        currentFSharp2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentFSharp2RecordedClip);
                    }
                break;
                case 't': 
                    g2Track.stop();
                    if(isRecording) {
                        currentG2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentG2RecordedClip);
                    }
                break;
                case '6':
                    gSharp2Track.stop();
                    if(isRecording) {
                        currentGSharp2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentGSharp2RecordedClip);
                    }
                break; 
                case 'y': 
                    a2Track.stop();
                    if(isRecording) {
                        currentA2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentA2RecordedClip);
                    }
                break; 
                case '7': 
                    aSharp2Track.stop();
                    if(isRecording) {
                        currentASharp2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentASharp2RecordedClip);
                    }
                break;  
                case 'u': 
                    b2Track.stop();
                    if(isRecording) {
                        currentB2RecordedClip.endRecordingClip();
                        currentRecordedTrack.add(currentB2RecordedClip);
                    }
                break;
                default: ;
                break;
                } 
            }
            
            
        });
        
        
        screen.addMouseListener(new MouseAdapter() {
            MyClip currentTrack;
            RecordedClip currentClipBeingRecorded;
            @Override
            public void mousePressed(MouseEvent me) {
                try{
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
                        case 254: 
                            c1Track.play();
                            currentTrack = c1Track;
                            if(isRecording){
                                currentC1RecordedClip = new RecordedClip(c1Track);
                                currentC1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentC1RecordedClip;
                            }
                        break;
                        case 1:
                            cSharp1Track.play();
                            currentTrack = cSharp1Track;
                            if(isRecording){
                                currentCSharp1RecordedClip = new RecordedClip(cSharp1Track);
                                currentCSharp1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentCSharp1RecordedClip;
                            }
                        break;
                        case 253: 
                            d1Track.play();
                            currentTrack = d1Track;
                            if(isRecording){
                                currentD1RecordedClip = new RecordedClip(d1Track);
                                currentD1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentD1RecordedClip;
                            }
                        break;
                        case 2:
                            dSharp1Track.play();
                            currentTrack = dSharp1Track;
                            if(isRecording){
                                currentDSharp1RecordedClip = new RecordedClip(dSharp1Track);
                                currentDSharp1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentDSharp1RecordedClip;
                            }
                        break;
                        case 252:
                            e1Track.play();
                            currentTrack = e1Track;
                            if(isRecording){
                                currentE1RecordedClip = new RecordedClip(e1Track);
                                currentE1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentE1RecordedClip;
                            }
                        break;
                        case 251: 
                            f1Track.play();
                            currentTrack = f1Track;
                            if(isRecording){
                                currentF1RecordedClip = new RecordedClip(f1Track);
                                currentF1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentF1RecordedClip;
                            }
                        break;
                        case 3: 
                            fSharp1Track.play();
                            currentTrack = fSharp1Track;
                            if(isRecording){
                                currentFSharp1RecordedClip = new RecordedClip(fSharp1Track);
                                currentFSharp1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentFSharp1RecordedClip;
                            }
                        break;
                        case 250: 
                            g1Track.play();
                            currentTrack = g1Track;
                            if(isRecording){
                                currentG1RecordedClip = new RecordedClip(g1Track);
                                currentG1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentG1RecordedClip;
                            }
                        break;
                        case 4:
                            gSharp1Track.play();
                            currentTrack = gSharp1Track;
                            if(isRecording){
                                currentGSharp1RecordedClip = new RecordedClip(gSharp1Track);
                                currentGSharp1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentGSharp1RecordedClip;
                            }
                        break;
                        case 249:
                            a1Track.play();
                            currentTrack = a1Track;
                            if(isRecording){
                                currentA1RecordedClip = new RecordedClip(a1Track);
                                currentA1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentA1RecordedClip;
                            }
                        break;
                        case 5:
                            aSharp1Track.play();
                            currentTrack = aSharp1Track;
                            if(isRecording){
                                currentASharp1RecordedClip = new RecordedClip(aSharp1Track);
                                currentASharp1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentASharp1RecordedClip;
                            }
                        break;
                        case 248:
                            b1Track.play();
                            currentTrack = b1Track;
                            if(isRecording){
                                currentB1RecordedClip = new RecordedClip(b1Track);
                                currentB1RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentB1RecordedClip;
                            }
                        break;
                        case 247: 
                            c2Track.play();
                            currentTrack = c2Track;
                            if(isRecording){
                                currentC2RecordedClip = new RecordedClip(c2Track);
                                currentC2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentC2RecordedClip;
                            }
                        break;
                        case 6:
                            cSharp2Track.play();
                            currentTrack = cSharp2Track;
                            if(isRecording){
                                currentCSharp2RecordedClip = new RecordedClip(cSharp2Track);
                                currentCSharp2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentCSharp2RecordedClip;
                            }
                        break;
                        case 246:
                            d2Track.play();
                            currentTrack = d2Track;
                            if(isRecording){
                                currentD2RecordedClip = new RecordedClip(d2Track);
                                currentD2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentD2RecordedClip;
                            }
                        break;
                        case 7: 
                            dSharp2Track.play();
                            currentTrack = dSharp2Track;
                            if(isRecording){
                                currentDSharp2RecordedClip = new RecordedClip(dSharp2Track);
                                currentDSharp2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentDSharp2RecordedClip;
                            }
                        break;
                        case 245: 
                            e2Track.play();
                            currentTrack = e2Track;
                            if(isRecording){
                                currentE2RecordedClip = new RecordedClip(e2Track);
                                currentE2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentE2RecordedClip;
                            }
                        break;
                        case 244: 
                            f2Track.play();
                            currentTrack = f2Track;
                            if(isRecording){
                                currentF2RecordedClip = new RecordedClip(f2Track);
                                currentF2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentF2RecordedClip;
                            }
                        break;
                        case 8:
                            fSharp2Track.play();
                            currentTrack = fSharp2Track;
                            if(isRecording){
                                currentFSharp2RecordedClip = new RecordedClip(fSharp2Track);
                                currentFSharp2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentFSharp2RecordedClip;
                            }
                        break;
                        case 243:
                            g2Track.play();
                            currentTrack = g2Track;
                            if(isRecording){
                                currentG2RecordedClip = new RecordedClip(g2Track);
                                currentG2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentG2RecordedClip;
                            }
                        break;
                        case 9: 
                            gSharp2Track.play();
                            currentTrack = gSharp2Track;
                            if(isRecording){
                                currentGSharp2RecordedClip = new RecordedClip(gSharp2Track);
                                currentGSharp2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentGSharp2RecordedClip;
                            }
                        break;
                        case 242: 
                            a2Track.play();
                            currentTrack = a2Track;
                            if(isRecording){
                                currentA2RecordedClip = new RecordedClip(a2Track);
                                currentA2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentA2RecordedClip;
                            }
                        break;
                        case 10: 
                            aSharp2Track.play();
                            currentTrack = aSharp2Track;
                            if(isRecording){
                                currentASharp2RecordedClip = new RecordedClip(aSharp2Track);
                                currentASharp2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentASharp2RecordedClip;
                            }
                        break;
                        case 241: 
                            b2Track.play();
                            currentTrack = b2Track;
                            if(isRecording){
                                currentB2RecordedClip = new RecordedClip(b2Track);
                                currentB2RecordedClip.startRecordingClip();
                                currentClipBeingRecorded = currentB2RecordedClip;
                            }
                        break;
                        default: ;
                        break;
                    }
                }catch(LineUnavailableException ex) {
                    Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                currentTrack.stop();
                if (isRecording){
                    currentClipBeingRecorded.endRecordingClip();
                    currentRecordedTrack.add(currentClipBeingRecorded);
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
        
        final JButton controlButton = new JButton("Controls");
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(controlButton, controlDialog);
            }
        });
        frame.add(LAYOUT,"control", controlButton);
        
        final JButton recordButton = new JButton("Record");
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRecording){
                    final JDialog jd = new JDialog(
                        BasicFrame.getFrame(),
                        "Get File",
                        Dialog.ModalityType.APPLICATION_MODAL);
                    BasicContainer bc = new BasicContainer();
                    String[][] layout = {
                        {"File"  ,"Input"},
                        {"Submit","Submit"}
                    };
                    JButton submit = new JButton("Submit");
                    final JTextField input = new JTextField(".txt");
                    bc.add(layout,"File",new JLabel("File"));
                    bc.add(layout,"Input",input);
                    bc.add(layout,"Submit",submit);
                    jd.add(bc);
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String fileName = input.getText();
                            currentRecordedTrack = new RecordedTrack(fileName);
                            RecordedClip.getReferenceTime();
                            jd.dispose();
                        }
                    });
                    jd.pack();
                    jd.setVisible(true);
                }
                isRecording = !isRecording;
                if (!isRecording) {
                    try {
                        currentRecordedTrack.toFile();
                    } catch (IOException ex) {
                        Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(recordButton, "Recording Stopped");
                }
            }
        });
        frame.add(LAYOUT,"record", recordButton);
        
        final JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog jd = new JDialog(
                        BasicFrame.getFrame(),
                        "Get File",
                        Dialog.ModalityType.APPLICATION_MODAL);
                    BasicContainer bc = new BasicContainer();
                    String[][] layout = {
                        {"File"  ,"Input"},
                        {"Submit","Submit"}
                    };
                    JButton submit = new JButton("Submit");
                    final JTextField input = new JTextField(".txt");
                    bc.add(layout,"File",new JLabel("File"));
                    bc.add(layout,"Input",input);
                    bc.add(layout,"Submit",submit);
                    jd.add(bc);
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String fileName = input.getText();
                            try {
                                RecordedTrack.playFromFile(fileName);
                            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                                Logger.getLogger(PianoSim.class.getName()).log(Level.SEVERE, null, ex);
                            } finally {
                                jd.dispose();
                            }
                        }
                    });
                    jd.pack();
                    jd.setVisible(true);
            }
        });
        frame.add(LAYOUT,"load", loadButton);
        
        controlButton.setFocusable(false);
        helpButton.setFocusable(false);
        recordButton.setFocusable(false);
        loadButton.setFocusable(false);
        
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
        
        int frameCounter = 0;
        
    }
    
    
}