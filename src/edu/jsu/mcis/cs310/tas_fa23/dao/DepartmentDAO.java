/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jsu.mcis.cs310.tas_fa23.dao;

import edu.jsu.mcis.cs310.tas_fa23.Department; //Doesn't exist yet [Update when Department.java is added]
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Joshua Smith
 */
public class DepartmentDAO {
    
    private static final String QUERY_FIND = "SELECT * FROM department WHERE id = ?";

    private final DAOFactory daoFactory;

    DepartmentDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    
    /*

    TODO [JOSHUA SMITH]: 
    
    ✔️ CREATED GETDEPARTMENTDAO() METHOD IN DAOFACTORY.JAVA
    ✔️ CHANGED NAMES AROUND IN GLOBAL VARIABLE FIELD
    ✔️ CHANGE OTHER NAMES TO NEW CLASS
    ✔️ CHANGE VARIABLES TO ACCEPT DEPARTMENT CLASS IN
    ✔️ CHANGE METHODS IN ORDER TO ACCOUNT FOR THE NEW TABLE INFORMATION BEING PASSED IN
    AWAIT CHANGES AND CREATION OF DEPARTMENT.JAVA AND UPDATE COMMENT AT TOP WHEN MADE
    CREATE NEW TESTS FOR THE DEPARTMENT AND DEPARTMENTDAO

    */

    public Department find(String id) {

        Department department = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        String description = rs.getString("description");
                        String termid = rs.getString("terminalid");
                        department = new Department(id, description, termid);

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

        return department;

    }
    
}
