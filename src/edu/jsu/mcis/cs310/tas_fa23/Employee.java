package edu.jsu.mcis.cs310.tas_fa23;

import java.time.LocalTime;

/**
 *
 * @author Joshua Smith
 */

public class Employee {
    
    private String firstName, middleName, lastName;
    private int id;
    private Badge theBadge;
    private Department theDepartment;
    private Shift theShift;
    private EmployeeType workType;
    private final LocalTime activity;
    

    public Employee(int id, String firstName, String middleName, String lastName, LocalTime activity,
                    Badge theBadge, Department theDepartment, Shift theShift, EmployeeType workType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.id = id;
        this.theBadge = theBadge;
        this.theDepartment = theDepartment;
        this.workType = workType;
        this.theShift = theShift;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public Badge getBadge() {
        return theBadge;
    }
    
    public Department getDepartment() {
        return theDepartment;
    }
    
    public EmployeeType getEmployeeType() {
        return workType;
    }
    
    public LocalTime getActivity() {
        return activity;
    }
    

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        
        s.append("ID #").append(this.getId()).append(": ")
        .append(this.getLastName()).append(", ")
        .append(this.getFirstName()).append(" ")
        .append(this.getMiddleName()).append(" (#").append(this.getBadge())
        .append("), Type: ").append(this.getEmployeeType()).append(", Department: ")
        .append(this.getDepartment()).append(", Active: ").append(this.getActivity());

        return s.toString();

    }
    
}
