package com.getjavajob.kovarnevm.phonebook.dao;

import com.getjavajob.kovarnevm.phonebook.common.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-context.xml", "classpath:dao-context-override.xml"})
@Transactional
abstract public class GenericDAOTest {

    protected Department it;
    protected Department accounting;
    protected Address addressMoscow;
    protected Address addressVladivostok;
    protected Phone workPhone;
    protected Phone personalPhone;
    protected Employee vasya;
    protected Employee petya;

    @Before
    public void setUpEntity() {
        //Employee
        vasya = new Employee();
        vasya.setFirstName("Kot Vasyliy");
        vasya.setLastName("Petrov");

        petya = new Employee();
        petya.setFirstName("Petya");
        petya.setLastName("Ivanov");

        //Department
        it = new Department();
        it.setName("IT");

        accounting = new Department();
        accounting.setName("Accounting");

        //Phone
        personalPhone = new Phone();
        personalPhone.setPhone("+79265771212");
        personalPhone.setType(ContactInfoType.PERSONAL);

        workPhone = new Phone();
        workPhone.setPhone("+79265771000");
        workPhone.setType(ContactInfoType.WORK);

        //Address
        addressMoscow = new Address();
        addressMoscow.setCity("Moscow");
        addressMoscow.setStreet("Lenina");
        addressMoscow.setType(ContactInfoType.PERSONAL);

        addressVladivostok = new Address();
        addressVladivostok.setCity("Vladivostok");
        addressVladivostok.setStreet("Marksa");
        addressVladivostok.setType(ContactInfoType.WORK);
    }
}
