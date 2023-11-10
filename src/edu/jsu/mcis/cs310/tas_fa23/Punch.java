/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_fa23;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author poojapatel
 */
public class Punch {
    
    private final Integer id;     
    private final Integer terminalid;
    private final Badge badge;
    private final EventType punchtype;
    private final LocalDateTime originaltimestamp;
    private LocalDateTime adjustedtimestamp;
    private PunchAdjustmentType adjustmenttype;
    
    
    public Punch(int terminalid, Badge badge, EventType punchtype) {
        this.id = null;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.originaltimestamp = LocalDateTime.now();
        
        
    }
    public Punch(int id,int terminalid, Badge badge, LocalDateTime origtimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.originaltimestamp = origtimestamp;
        
    }
    
    public void adjust(Shift s) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        LocalTime theShiftStart = s.getShiftstart();
        LocalTime theShiftEnd = s.getShiftstop();
        LocalTime theLunchStart = s.getLunchstart();
        LocalTime theLunchEnd = s.getLunchstop();
        int theRoundInterval = s.getRoundInterval();
        int theGracePeriod = s.getGracePeriod();
        int theDockPenalty = s.getDockPenalty();
        LocalDateTime thepunch = this.getOriginaltimestamp();
        LocalTime punchTime = thepunch.toLocalTime();
        
        boolean isBeforeStart = punchTime.isBefore(theShiftStart);
        boolean isAfterStart = punchTime.isAfter(theShiftStart);
        boolean isBeforeEnd = punchTime.isBefore(theShiftEnd);
        boolean isAfterEnd = punchTime.isAfter(theShiftEnd);
        boolean isBeforeLunchStart = punchTime.isBefore(theLunchStart);
        boolean isAfterLunchStart = punchTime.isAfter(theLunchStart);
        boolean isBeforeLunchEnd = punchTime.isBefore(theLunchEnd);
        boolean isAfterLunchEnd = punchTime.isAfter(theLunchEnd);
        
        //TEST SHOWS BOTH SHIFT AND PUNCH TIME, NEXT TEST IS THE BOOLEANS FOR BEFORE AND AFTER [PUNCH IN/OUT TIME HERE]
        //System.err.println(theShiftStart.format(formatter) + " " + punchTime.format(formatter));
        //System.err.println(isBeforeTime + " " + isAfterTime);
        
        //START OF RULES
        
        
        
    }
    
    public Integer getId() {
        return id;
    }
         
    public Integer getTerminalid() {
        return terminalid;
    }
    public Badge getBadge() {
        return badge;
    }
    public EventType getPunchtype() {
        return punchtype;
    }
    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }
    public LocalDateTime getAdjustedtimestamp(){
        return adjustedtimestamp;
    }
    public PunchAdjustmentType getAdjustmenttype() {
        return adjustmenttype;
    }
         
         
    
    public String printOriginal() {
        
        StringBuilder s = new StringBuilder();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
       
        s.append('#');
        s.append(badge.getId()).append(" ");
        s.append(punchtype).append(": ");
        s.append(originaltimestamp.format(formatter).toUpperCase());
        
        return s.toString();
    }
    
    public String printAdjusted() {
        
        StringBuilder s = new StringBuilder();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
       
        s.append('#');
        s.append(badge.getId()).append(" ");
        s.append(punchtype).append(": ");
        s.append(originaltimestamp.format(formatter).toUpperCase());
        
        return s.toString();
    }
}
        
        
        
    
    
