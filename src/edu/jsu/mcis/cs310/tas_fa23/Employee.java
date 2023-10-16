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

    public Employee(String id, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');

        return s.toString();

    }
    
}
