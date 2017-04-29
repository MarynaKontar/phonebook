package com.getjavajob.kovarnevm.phonebook.common;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "phone_tbl")
public class Phone extends BaseEntity {

    private String phone;
    @Enumerated(EnumType.STRING)
    private ContactInfoType type;

    public Phone() {
    }

    public Phone(String phone, ContactInfoType type) {
        this.phone = phone;
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContactInfoType getType() {
        return type;
    }

    public void setType(ContactInfoType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone1 = (Phone) o;

        //noinspection SimplifiableIfStatement
        if (phone != null ? !phone.equals(phone1.phone) : phone1.phone != null) return false;
        return type == phone1.type;
    }

    @Override
    public int hashCode() {
        int result = phone != null ? phone.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "type=" + type +
                ", phone='" + phone + '\'' +
                '}';
    }
}
