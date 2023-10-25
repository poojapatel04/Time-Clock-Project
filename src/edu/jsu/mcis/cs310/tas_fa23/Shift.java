package edu.jsu.mcis.cs310.tas_fa23;


import static java.lang.Math.abs;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Joshua Smith
 */
public class Shift{
    private int shiftid;
    private String desc;
    private LocalTime shiftstart;
    private LocalTime shiftstop;
    private int roundInterval;
    private int gracePeriod;
    private int dockPenalty;
    private LocalTime lunchstart;
    private LocalTime lunchstop;
    private int lunchThreshold;
    private Map<String, Object> shiftInfo;
    private final HashMap<String, Object> theMap;
    
    public Shift(HashMap<String, Object> theMap) {
        this.theMap = theMap;
    }

    
    public  int getShiftId (){
        return (int)theMap.get("id");
    }
    
    public String getDesc(){
        return theMap.get("description").toString();
    }
    
    public LocalTime getShiftStart(){
        return (LocalTime)theMap.get("shiftstart");
    }
    
    public LocalTime getShiftStop(){
        return (LocalTime)theMap.get("shiftstop");
    }
    
    public int getRoundInterval(){
        return (int)theMap.get("roundinterval");
    }
    
    public int getGracePeriod(){
        return (int)theMap.get("graceperiod");
    }
    
    public int getDockPenalty(){
        return (int)theMap.get("dockpenalty");
    }
    
    public LocalTime getLunchStart(){
        return (LocalTime)theMap.get("lunchstart");
    }
    
    public LocalTime getLunchStop(){
        return (LocalTime)theMap.get("lunchstop");
    }
    
    public int getLunchThreshhold(){
        return (int)theMap.get("lunchthreshold");
    }
    
    public int getShiftTime(){
        
        int[] timeStart = new int[3];
        int[] timeEnd = new int[3];
        int startTotal = 0;
        int endTotal = 0;
        int total = 0;
        int minutesWorked = 0;
        
        
        timeStart[0] = (this.getShiftStart().getHour())*60;
        timeStart[1] = (this.getShiftStart().getMinute());
        timeStart[2] = (this.getShiftStart().getSecond()/60);
        startTotal = timeStart[0] + timeStart[1] + timeStart[2];
        
        timeEnd[0] = (this.getShiftStart().getHour())*60;
        timeEnd[1] = (this.getShiftStart().getMinute());
        timeEnd[2] = (this.getShiftStart().getSecond()/60);
        endTotal = timeEnd[0] + timeEnd[1] + timeEnd[2];
        
        total = endTotal - startTotal;
        
        if (total < 0) {
            
            int minutesInDay = abs(total/1440);
            
             while (minutesInDay != 1) {
                 
                 minutesInDay = minutesInDay + (1/1440);
                 minutesWorked++;
                 
             }
        
             total = minutesWorked;
             
        }
        
        
        return total;
    }
    
    public int getLunchTime(){
        
        int[] lunchStart = new int[3];
        int[] lunchEnd = new int[3];
        int startTotal = 0;
        int endTotal = 0;
        int total = 0;
        int minutesWorked = 0;
        
        
        lunchStart[0] = (this.getShiftStart().getHour())*60;
        lunchStart[1] = (this.getShiftStart().getMinute());
        lunchStart[2] = (this.getShiftStart().getSecond()/60);
        startTotal = lunchStart[0] + lunchStart[1] + lunchStart[2];
        
        lunchEnd[0] = (this.getShiftStart().getHour())*60;
        lunchEnd[1] = (this.getShiftStart().getMinute());
        lunchEnd[2] = (this.getShiftStart().getSecond()/60);
        endTotal = lunchEnd[0] + lunchEnd[1] + lunchEnd[2];
        
        total = endTotal - startTotal;
        
        if (total < 0) {
            
            long minutesInDay = abs(total/1440);
            
             while (minutesInDay >= 1) { //Provides leeway on if number is exactly 1
                 
                 minutesInDay = minutesInDay + (1/1440);
                 minutesWorked++;
                 
             }
        
             total = minutesWorked;
             
        }
        
        
        return total;
    
    }
  
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getDesc()).append(": ").append(this.getShiftStart())
        .append(" - ").append(this.getShiftStop()).append(" (").append(this.getShiftTime())
        .append(" minutes); Lunch: ").append(this.getLunchStart()).append(" - ")
        .append(this.getLunchStop()).append(" (").append(" minutes)");
        
        String totalStr = s.toString();
        
        return totalStr;
    }
     /* public static Shift createFromSQLInfo(String[] sqlInfo) {
        Map<String, String> shiftInfo = new HashMap<>();
        shiftInfo.put("shiftid", sqlInfo[0]);
        shiftInfo.put("desc", sqlInfo[1]);
        shiftInfo.put("shiftstart", sqlInfo[2]);
        shiftInfo.put("shiftstop", sqlInfo[3]);
        shiftInfo.put("roundinterval", sqlInfo[4]);
        shiftInfo.put("graceperiod", sqlInfo[5]);
        shiftInfo.put("dockpenalty", sqlInfo[6]);
        shiftInfo.put("lunchstart", sqlInfo[7]);
        shiftInfo.put("lunchstop", sqlInfo[8]);
        shiftInfo.put("lunchthreshold", sqlInfo[9]);

        return new Shift(shiftInfo);
    }*/
}






