package edu.jsu.mcis.cs310.tas_fa23;


import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




/**
 *
 * @author aseel
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
    
    public Shift(int shiftid, String desc, LocalTime shiftstart, LocalTime shiftstop, int roundInterval, 
                 int gracePeriod, int dockPenalty, LocalTime lunchstart, LocalTime lunchstop, int lunchThreshold) {
        this.shiftid = shiftid;
        this.desc = desc;
        this.shiftstart = shiftstart;
        this.shiftstop = shiftstop;
        this.roundInterval = roundInterval;
        this.gracePeriod = gracePeriod;
        this.dockPenalty = dockPenalty;
        this.lunchstart = lunchstart;
        this.lunchstop = lunchstop;
        this.lunchThreshold = lunchThreshold;
    }

    public  int getShiftId (){
        return shiftid;
}
    public String getDesc(){
        return desc;
    }
    public LocalTime getShiftStart(){
        return shiftstart;
    }
    public LocalTime getShiftStop(){
        return shiftstop;
        
    }
    
    public Shift(Map<String, String> shiftInfo) {
        this.shiftid = Integer.parseInt(shiftInfo.get("shiftid"));
        this.desc = shiftInfo.get("desc");
        this.shiftstart = LocalTime.parse(shiftInfo.get("shiftstart"));
        this.shiftstop = LocalTime.parse(shiftInfo.get("shiftstop"));
        this.roundInterval = Integer.parseInt(shiftInfo.get("roundinterval"));
        this.gracePeriod = Integer.parseInt(shiftInfo.get("graceperiod"));
        this.dockPenalty = Integer.parseInt(shiftInfo.get("dockpenalty"));
        this.lunchstart = LocalTime.parse(shiftInfo.get("lunchstart"));
        this.lunchstop = LocalTime.parse(shiftInfo.get("lunchstop"));
        this.lunchThreshold = Integer.parseInt(shiftInfo.get("lunchthreshold"));
    }
    @Override
    public String toString() {
        return "Shift{" +
                "id=" + shiftid +
                ", description='" + desc + '\'' +
                ", shiftStart=" + shiftstart +
                ", shiftStop=" + shiftstop +
                ", roundInterval=" + roundInterval +
                ", gracePeriod=" + gracePeriod +
                ", dockPenalty=" + dockPenalty +
                ", lunchStart=" + lunchstart +
                ", lunchStop=" + lunchstop +
                ", lunchThreshold=" + lunchThreshold +
                '}';
        
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






