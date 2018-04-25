
package pianosim;


public class RecordedClip {
    MyClip clip;
    long startTime;
    long duration;
    
    private long startClockTime;
    private long endClockTime;
    
    private static long referenceStartClockTime;
    
    public RecordedClip(MyClip clip) {
        this.clip = clip;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(clip.toString());
        sb.append(" : ");
        sb.append(startTime);
        sb.append(" : ");
        sb.append(duration);
        
        return sb.toString();
    }
    
    public void startRecordingClip() {
        startClockTime  = System.currentTimeMillis();
        
        startTime = startClockTime - referenceStartClockTime;
    }
    
    public void endRecordingClip() {
        endClockTime = System.currentTimeMillis();
        
        duration = endClockTime - startClockTime;
    }
    
    public static void getReferenceTime() {
        referenceStartClockTime = System.currentTimeMillis();
    }
}

