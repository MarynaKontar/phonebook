package com.getjavajob.kovarnevm.phonebook.dto;

public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;

    public EmployeeDTO() {
    }

    public String getFullName() {
        String mName = this.middleName == null ? "" : " " + middleName;
        return this.lastName + " " + this.firstName + mName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
