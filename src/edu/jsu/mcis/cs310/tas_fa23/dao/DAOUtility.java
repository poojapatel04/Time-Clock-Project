package edu.jsu.mcis.cs310.tas_fa23.dao;

import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import com.github.cliftonlabs.json_simple.*;
import edu.jsu.mcis.cs310.tas_fa23.Punch;

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
}
 