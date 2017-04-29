package com.getjavajob.kovarnevm.phonebook.common;

import javax.persistence.*;

@Entity
@Table(name = "dep_tbl")
public class Department extends BaseEntity {
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_id")
    private Employee head;

    public Department() {
    }

    public Department(String name, Employee head) {
        this.name = name;
        this.head = head;
    }

    public boolean isNew() {
        return getId() == 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        //noinspection SimplifiableIfStatement
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return head != null ? head.equals(that.head) : that.head == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (head != null ? head.hashCode() : 0);
        return result;
    }
}
