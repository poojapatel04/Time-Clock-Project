/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_fa23.dao;
import edu.jsu.mcis.cs310.tas_fa23.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




/**
 *
 * @author poojapatel
 */
public class PunchDAO {
    private static final String QUERY_FIND = "SELECT * FROM event where id = ?";
    private static final String QUERY_CREATE = "INSERT INTO event (terminalid, badgeid, timestamp, eventtypeid) VALUES ( ?, ?, ?, ?)";
    
    private final DAOFactory daoFactory;
    PunchDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
      
    }
    public Punch find(int id) {
        
        Punch punch = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conn = daoFactory.getConnection();
            
            if(conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, Integer.toString(id));
         
         boolean hasresults = ps.execute();
         
         if(hasresults) {
             
             rs = ps.getResultSet();
             
             while (rs.next()) {
                 
            int terminalid;
            String badgeid;
            EventType punchtype;
            LocalDateTime originaltimestamp;
                 
            terminalid = rs.getInt("terminalid");
                 
            badgeid = rs.getString("badgeid");
            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
            Badge b = badgeDAO.find(badgeid);
                 
            punchtype = EventType.values()[rs.getInt("eventtypeid")];
                 
            originaltimestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                 
            punch = new Punch (id, terminalid, b, originaltimestamp, punchtype);
                 
             }
         }
    }
         
        } catch (SQLException e) {
             
             throw new DAOException(e.getMessage());
             
             
         } finally {
                 if (rs != null) {
                 try {
                 rs.close();
                 }catch (SQLException e) {
                 throw new DAOException(e.getMessage());
                 }
                    
        }
                 if (ps != null) {
                 try {
                 ps.close();
                 }catch (SQLException e) {
                 throw new DAOException(e.getMessage());
                 }
                 }
                 
                 }
                  return punch;   
    }
    
    public int create(Punch newPunch) {
        int punchId = 0;
 
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        Employee employee = employeeDAO.find(newPunch.getBadge());
        
        if(newPunch.getTerminalid() == employee.getDepartment().getTerminalId() || newPunch.getTerminalid() == 0) {
         
        try{
            
            Connection conn = daoFactory.getConnection();
        
            if(conn. isValid(0)) {
            
                String Date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(newPunch.getOriginaltimestamp());
                 int eventTypeid = EventType.valueOf(newPunch.getPunchtype().name()).ordinal();
            
            ps = conn.prepareStatement(QUERY_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, newPunch.getTerminalid());
            ps.setString(2, newPunch.getBadge().getId());
            ps.setString(3, Date);
            ps.setInt(4, eventTypeid);
            
            
            int result = ps.executeUpdate();
            if(result  == 1) {
                rs = ps.getGeneratedKeys();
                
                if(rs.next()) {
                    punchId = rs.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
             
             throw new DAOException(e.getMessage());
             
             
         } finally {
                 if (rs != null) {
                 try {
                 rs.close();
                 }catch (SQLException e) {
                 throw new DAOException(e.getMessage());
                 }
                    
        }
                 if (ps != null) {
                 try {
                 ps.close();
                 }catch (SQLException e) {
                 throw new DAOException(e.getMessage());
                 }
                 }
                 
                 }
}

return punchId;


    }
}




