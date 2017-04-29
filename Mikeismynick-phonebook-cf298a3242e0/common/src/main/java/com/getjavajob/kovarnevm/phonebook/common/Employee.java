package com.getjavajob.kovarnevm.phonebook.common;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "emp_tbl")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private String middleName;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_to_address_tbl", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private List<Address> addresses = new ArrayList<>(0);
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_to_phone_tbl", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private List<Phone> phones = new ArrayList<>(0);
    private String email;
    private String icq;
    private String skype;
    @Column(name = "addInfo")
    private String additionalInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id")
    private Department department;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id")
    private Employee chief;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String middleName,
                    Date birthDay, List<Address> addresses, List<Phone> phones,
                    String email, String icq, String skype, String additionalInfo,
                    Department department, Employee chief) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDay = birthDay;
        this.addresses = addresses;
        this.phones = phones;
        this.email = email;
        this.icq = icq;
        this.skype = skype;
        this.additionalInfo = additionalInfo;
        this.department = department;
        this.chief = chief;
    }

    public boolean isNew() {
        return getId() == 0;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getChief() {
        return chief;
    }

    public void setChief(Employee chief) {
        this.chief = chief;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDay=" + birthDay +
                ", addresses=" + addresses +
                ", phones=" + phones +
                ", email='" + email + '\'' +
                ", icq='" + icq + '\'' +
                ", skype='" + skype + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", department=" + (department != null ? department.getName() : "null") +
                ", chief=" + (chief != null ? chief.getFirstName() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (middleName != null ? !middleName.equals(employee.middleName) : employee.middleName != null) return false;
        if (birthDay != null ? !birthDay.equals(employee.birthDay) : employee.birthDay != null) return false;
        if (addresses != null ? !addresses.equals(employee.addresses) : employee.addresses != null) return false;
        if (phones != null ? !phones.equals(employee.phones) : employee.phones != null) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (icq != null ? !icq.equals(employee.icq) : employee.icq != null) return false;
        if (skype != null ? !skype.equals(employee.skype) : employee.skype != null) return false;
        if (additionalInfo != null ? !additionalInfo.equals(employee.additionalInfo) : employee.additionalInfo != null)
            return false;
        if (department != null ? !department.equals(employee.department) : employee.department != null) return false;
        return chief != null ? chief.equals(employee.chief) : employee.chief == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (icq != null ? icq.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (additionalInfo != null ? additionalInfo.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (chief != null ? chief.hashCode() : 0);
        return result;
    }
}
