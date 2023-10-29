package edu.jsu.mcis.cs310.tas_fa23.dao;

import edu.jsu.mcis.cs310.tas_fa23.Badge;
import edu.jsu.mcis.cs310.tas_fa23.Department;
import edu.jsu.mcis.cs310.tas_fa23.Employee;
import edu.jsu.mcis.cs310.tas_fa23.EmployeeType;
import edu.jsu.mcis.cs310.tas_fa23.Shift;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Joshua Smith
 */

public class EmployeeDAO {

    private static final String QUERY_FIND = "SELECT * FROM employee WHERE id = ?";
    
    private static final String QUERY_FIND2 = "SELECT * FROM employee WHERE badgeid = ?";
    
    private static final String QUERY_FIND3 = "SELECT * FROM department WHERE id = ?";

    private final DAOFactory daoFactory;

    EmployeeDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }

    public Employee find(int id) {

        Employee theEmployee = null;
        
        EmployeeType employeeType = null;
        
        StringBuilder myID = new StringBuilder();
        myID = myID.append(id);
        String theID = myID.toString();

        PreparedStatement ps = null;
        PreparedStatement getDepartmentStatement = null;
        ResultSet rs = null;
        ResultSet gds = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                getDepartmentStatement = conn.prepareStatement(QUERY_FIND3);
                ps.setString(1, theID);
                

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        
                        StringBuilder personDescription = new StringBuilder();

                        
                        String firstName = rs.getString("firstname");
                        String middleName = rs.getString("middlename");
                        String lastName = rs.getString("lastname");
                        personDescription.append(lastName).append(", ")
                        .append(firstName).append(" ").append(middleName);
                        
                        getDepartmentStatement.setString(1, rs.getString("departmentid"));
                        boolean hasresults2 = getDepartmentStatement.execute();
                        if (hasresults2) {
                            gds = getDepartmentStatement.getResultSet();
                            gds.next();
                        }
                        Department departmentid = new Department(rs.getInt("departmentid"), gds.getString("description"), gds.getInt("terminalid"));
                        
                        
                        int workType = rs.getInt("employeetypeid");
                        
                        switch(workType) {
                            case 0:
                                employeeType = employeeType.PART_TIME;
                                break;
                            case 1:
                                employeeType = employeeType.FULL_TIME;
                                break;
                        
                        }
                        
                        
                        int shiftid = rs.getInt("shiftid");
                        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
                        Shift theShift = shiftDAO.find(shiftid);
                        String activity = rs.getString("active");
                        
                        
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                        LocalTime active = LocalTime.parse(activity, formatter);
                        
                        String theDescription = personDescription.toString();
                        Badge badgeid = new Badge(rs.getString("badgeid"), theDescription);
                        
                        theEmployee = new Employee(id, firstName, middleName, lastName,
                 active, badgeid, departmentid, theShift, employeeType);

                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return theEmployee;

    }
    
    
    
    public Employee find(Badge theBadge) {

        Employee theEmployee = null;
        
        EmployeeType employeeType = null;
        
        StringBuilder myBadgeid = new StringBuilder();
        myBadgeid = myBadgeid.append(theBadge.getId());
        String badge = myBadgeid.toString();

        System.err.println(badge);
        PreparedStatement ps = null;
        PreparedStatement getDepartmentStatement = null;
        ResultSet rs = null;
        ResultSet gds = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND2);
                getDepartmentStatement = conn.prepareStatement(QUERY_FIND3);
                ps.setString(1, badge);
                

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        
                        StringBuilder personDescription = new StringBuilder();
                        int id = rs.getInt("id");
                        
                        String firstName = rs.getString("firstname");
                        String middleName = rs.getString("middlename");
                        String lastName = rs.getString("lastname");
                        personDescription.append(lastName).append(", ")
                        .append(firstName).append(" ").append(middleName);
                        
                        getDepartmentStatement.setString(1, rs.getString("departmentid"));
                        boolean hasresults2 = getDepartmentStatement.execute();
                        if (hasresults2) {
                            gds = getDepartmentStatement.getResultSet();
                            gds.next();
                        }
                        Department departmentid = new Department(rs.getInt("departmentid"), gds.getString("description"), gds.getInt("terminalid"));
                        
                        
                        int workType = rs.getInt("employeetypeid");
                        
                        switch(workType) {
                            case 0:
                                employeeType = employeeType.PART_TIME;
                                break;
                            case 1:
                                employeeType = employeeType.FULL_TIME;
                                break;
                        
                        }
                        
                        
                        int shiftid = rs.getInt("shiftid");
                        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
                        Shift theShift = shiftDAO.find(shiftid);
                        String activity = rs.getString("active");
                        
                        
                        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
                        LocalTime active = LocalTime.parse(activity, formatter);
                        
                        String theDescription = personDescription.toString();
                        
                        theEmployee = new Employee(id, firstName, middleName, lastName,
                 active, theBadge, departmentid, theShift, employeeType);

                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return theEmployee;

    }
    
    
    

}
