/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_fa23;
import static edu.jsu.mcis.cs310.tas_fa23.EventType.*;
import java.time.LocalDate;
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
 
        this.adjustedtimestamp = originaltimestamp;
        this.adjustmenttype = PunchAdjustmentType.NONE;
        
    }
    public Punch(int id,int terminalid, Badge badge, LocalDateTime origtimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.originaltimestamp = origtimestamp;
        this.adjustedtimestamp = originaltimestamp;
        this.adjustmenttype = PunchAdjustmentType.NONE;
        
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
        LocalDate punchDay = thepunch.toLocalDate();
        LocalTime roundTimeUnder = null;
        LocalTime punchTime = thepunch.toLocalTime();
        roundTimeUnder = punchTime;
        roundTimeUnder = roundTimeUnder.withHour(punchTime.getHour()).withMinute(60-theRoundInterval).withSecond(00);
        LocalTime roundTimeAfter = null;
        roundTimeAfter = punchTime;
        roundTimeAfter = roundTimeAfter.withHour(punchTime.getHour()).withMinute(theRoundInterval).withSecond(00);
        LocalTime roundFromMiddle = null;
        roundFromMiddle = punchTime;
        roundFromMiddle = roundFromMiddle.withHour(theShiftEnd.getHour()).withMinute(30+theRoundInterval).withSecond(0);
        
 
 
        
 
        
        
        LocalTime shiftStartGrace = theShiftStart.plusMinutes(theGracePeriod);
        LocalTime shiftStartDock = theShiftStart.minusMinutes(theDockPenalty);
 
        LocalTime shiftStopGrace = theShiftStart.plusMinutes(theGracePeriod);
        LocalTime shiftStopDock = theShiftStart.minusMinutes(theDockPenalty);
 
         adjustedtimestamp = null;
         adjustmenttype = null;
        
        //WHICH RULES BOOLS
        boolean isBeforeStart = punchTime.isBefore(theShiftStart);
        boolean withinRoundingUnder = punchTime.isAfter(roundTimeUnder);
        boolean withinRoundingOver = punchTime.isBefore(roundTimeAfter);
        boolean withinRoundingMiddle = punchTime.isBefore(roundFromMiddle);
        boolean isAfterStart = punchTime.isAfter(theShiftStart);
        boolean isAfterEnd = punchTime.isAfter(theShiftEnd);
        boolean isBeforeLunchStart = punchTime.isBefore(theLunchStart);
        boolean isAfterLunchStart = punchTime.isAfter(theLunchStart);
        boolean isBeforeLunchEnd = punchTime.isBefore(theLunchEnd);
        //System.out.println(withinRoundingUnder + "  " + punchTime + " " + roundTimeUnder);
        //System.out.println(isBeforeLunchEnd + "  " + theLunchEnd + " " + punchTime);
        System.out.println(withinRoundingUnder + "  " + roundTimeUnder + " " + punchTime);
        boolean isAfterLunchEnd = punchTime.isAfter(theLunchEnd);
        boolean isBeforeGrace = punchTime.isBefore(shiftStopGrace);
 
        
        //SPECIAL CASE BOOLS
        boolean weekend = this.isWeekend(punchDay);
        boolean ifTimeOut = this.TimeOut();
        
        
        //TEST SHOWS BOTH SHIFT AND PUNCH TIME, NEXT TEST IS THE BOOLEANS FOR BEFORE AND AFTER [PUNCH IN/OUT TIME HERE]
        //System.err.println(theShiftStart.format(formatter) + " " + punchTime.format(formatter));
        //System.err.println(isBeforeTime + " " + isAfterTime);
        //System.err.println(weekend + " " + ifTimeOut);
        
        //START OF RULES
        this.adjustedtimestamp = thepunch;
     
        
        if (weekend || ifTimeOut) {
            thepunch = thepunch.withSecond(00);
        }
        
        else {
            //START OF MAIN SET OF RULES
            
            if (punchTime.isAfter(shiftStartGrace) && punchTime.isBefore(shiftStartDock.plusSeconds(1))) {
                this.adjustedtimestamp = thepunch.with(shiftStartDock);
                adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
            }
            
            else if (punchTime.isBefore(shiftStopGrace) && punchTime.isAfter(shiftStopDock.minusSeconds(1))) {
                this.adjustedtimestamp = thepunch.with(shiftStopDock);
                adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
            }
            //SHIFT START
            if (isBeforeStart && withinRoundingUnder || isAfterStart && isBeforeGrace) {
                
                this.adjustedtimestamp = this.adjustedtimestamp.withSecond(00);
                this.adjustedtimestamp = this.adjustedtimestamp.withMinute(0);
                this.adjustedtimestamp = this.adjustedtimestamp.withHour(this.adjustedtimestamp.getHour()+1);
                this.adjustmenttype = PunchAdjustmentType.SHIFT_START;
                }
 
            //SHIFT STOP
            else if (isAfterEnd && withinRoundingUnder || isAfterEnd && withinRoundingMiddle) {
                
                this.adjustedtimestamp = this.adjustedtimestamp.withSecond(00);
                this.adjustedtimestamp = this.adjustedtimestamp.withMinute(theShiftEnd.getMinute());
                this.adjustedtimestamp = this.adjustedtimestamp.withHour(this.adjustedtimestamp.getHour());
                this.adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
            }
            
            //LUNCH START
            else if ((isBeforeLunchStart && withinRoundingUnder) || (isAfterLunchStart && withinRoundingOver)) {
            
                this.adjustedtimestamp = this.adjustedtimestamp.withSecond(00);
                this.adjustedtimestamp = this.adjustedtimestamp.withMinute(0);
                this.adjustedtimestamp = this.adjustedtimestamp.withHour(this.adjustedtimestamp.getHour());
                this.adjustmenttype = PunchAdjustmentType.LUNCH_START;
            }
            
            //LUNCH STOP
            else if ((isBeforeLunchEnd && isAfterLunchStart)) {
                this.adjustedtimestamp = this.adjustedtimestamp.withSecond(00);
                this.adjustedtimestamp = this.adjustedtimestamp.withMinute(theLunchEnd.getMinute());
                this.adjustedtimestamp = this.adjustedtimestamp.withHour(this.adjustedtimestamp.getHour());
                this.adjustmenttype = PunchAdjustmentType.LUNCH_STOP;
            }
            
            else {
                this.adjustedtimestamp = this.adjustedtimestamp.withSecond(00);
                this.adjustmenttype = PunchAdjustmentType.NONE;
            }
        
            
     
        }
        
        
            if (adjustedtimestamp != null && adjustmenttype != null) {
                return;
            } 
            if ((thepunch.getMinute() % 15) != 0) {
                int adjustedminute;
                int minute = thepunch.getMinute();
                
                if ((minute % theRoundInterval) < (theRoundInterval) / 2) {
                    adjustedminute = (Math.round(minute / theRoundInterval) * theRoundInterval);
                }
                
                else {
                    adjustedminute = (Math.round(minute / theRoundInterval) * theRoundInterval) + theRoundInterval; 
                    
                }
                
                if (adjustedminute == 0) {
                    this.adjustedtimestamp = thepunch
                    .withMinute(adjustedminute)
                    .withSecond(0)
                    .withNano(0);
                }
                
                else if((60 / adjustedminute) == 2 || (60 / adjustedminute) == 4 ) {
                    this.adjustedtimestamp = thepunch
                            .withHour(thepunch.getHour())
                            .plusHours(0)
                            .withMinute(adjustedminute)
                            .withSecond(0)     
                            .withNano(0);
                            
                } 
                
                else if ((60.000 / adjustedminute) == 1) {
                    this.adjustedtimestamp = thepunch
                            .withHour(thepunch.getHour())
                            .plusHours(1)
                            .withMinute(0)
                            .withSecond(0)     
                            .withNano(0);
                }
                
                
                
                else {
                    this.adjustedtimestamp = thepunch
                            .withMinute(adjustedminute)
                            .withSecond(0)
                            .withNano(0);
                    
                }
                this.adjustmenttype  = PunchAdjustmentType.INTERVAL_ROUND;
            }
            
            if (adjustedtimestamp == null) {
                this.adjustedtimestamp = thepunch.withSecond(0).withNano(0);
                this.adjustmenttype = PunchAdjustmentType.NONE;
            }
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
    
    public boolean isWeekend(LocalDate theDate) {
        switch(theDate.getDayOfWeek()) {
            case SATURDAY:
                return true;
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }
    
    public boolean TimeOut() {
        switch(this.getPunchtype()) {
            case TIME_OUT:
                return true;
            default:
                return false;
            
        }
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
        s.append(this.badge.getId()).append(" ");
        s.append(this.punchtype).append(": ");
        s.append(this.adjustedtimestamp.format(formatter).toUpperCase());
        s.append(" (").append(this.adjustmenttype).append(")");
 
        
        return s.toString();
    }
}