package edu.jsu.mcis.cs310.tas_fa23;

/**
 *
 * @author Joshua Smith
 */

public class Employee {
    
    private String firstName, middleName, lastName;
    private final int id;
    private Badge theBadge;
    private Department theDepartment;
    //private final Shift theShift;
    private EmployeeType workType;
    

    public Employee(int id, String firstName, String middleName, String lastName, Badge theBadge, Department theDepartment, EmployeeType workType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.id = id;
        this.theBadge = theBadge;
        this.theDepartment = theDepartment;
        this.workType = workType;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        return s.toString();

    }
    
}
