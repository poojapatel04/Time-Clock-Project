package edu.jsu.mcis.cs310.tas_fa23.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import com.github.cliftonlabs.json_simple.*;
import edu.jsu.mcis.cs310.tas_fa23.EventType;
import edu.jsu.mcis.cs310.tas_fa23.Punch;
import edu.jsu.mcis.cs310.tas_fa23.Shift;

/**
 * 
 * Utility class for DAOs.  This is a final, non-constructable class containing
 * common DAO logic and other repeated and/or standardized code, refactored into
 * individual static methods.
 * 
 */
public final class DAOUtility {
    
    public static String getPunchListsAsJSON(ArrayList<Punch> dailypunchlist) {
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        for(Punch punch: dailypunchlist) {
          HashMap<String, String>   punchData = new HashMap<>();
          
          punchData.put("id", String.valueOf(punch.getId()));
          punchData.put("badgeid", String.valueOf(punch.getBadge().getId()));
          punchData.put("terminalid",String.valueOf(punch.getTerminalid()));
          punchData.put("punchtype",String.valueOf(punch.getPunchtype()));
          punchData.put("adjustmenttype",String.valueOf(punch.getAdjustmenttype()));
          punchData.put("originaltimestamp",punch.getOriginaltimestamp().format(format).toUpperCase());
          punchData.put("adjustedtimestamp",punch.getAdjustedtimestamp().format(format).toUpperCase());

          jsonData.add(punchData);

        }
        String json = Jsoner.serialize(jsonData.toString());
        return json;

    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift) {
        
        LocalTime theShiftStart = shift.getShiftstart();
        LocalTime theShiftEnd = shift.getShiftstop();
        LocalTime theLunchStart = shift.getLunchstart();
        LocalTime theLunchEnd = shift.getLunchstop();
        
        Iterator<Punch> iterator = dailypunchlist.iterator();
        
        
        //TODO: CHANGE while loop to a loop that just removes the elements until nothing is left
        while (iterator.hasNext()) {
            Punch current = iterator.next();
            current.adjust(shift);
            dailypunchlist.remove(current);
            
            if (current.getPunchtype().equals(EventType.TIME_OUT)) {
                return 0;
            }
            
            if (dailypunchlist.isEmpty()) {
                System.out.println("hehe");

            }
            
            System.out.println(current.printAdjusted() + "\n");
            
            //Four Cases: No Clock Out, Time Out, Normal, and Only Clock In and Out
            
        }
        
        return 0;
    }
}
 