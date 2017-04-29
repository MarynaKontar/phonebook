package com.getjavajob.kovarnevm.phonebook.dao;

import com.getjavajob.kovarnevm.phonebook.common.ContactInfoType;
import com.getjavajob.kovarnevm.phonebook.common.Employee;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class JpaCascadeTest extends GenericDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private PhoneDAO phoneDAO;

    @Test
    public void addWithPhones() throws DAOException {
        vasya.getPhones().add(personalPhone);
        vasya.getPhones().add(workPhone);
        vasya = employeeDAO.add(vasya);
        assertEquals(2, employeeDAO.get(vasya.getId()).getPhones().size());
        assertEquals(2, phoneDAO.getAll().size());
    }

    @Test
    public void addWithAddresses() throws DAOException {
        vasya.getAddresses().add(addressMoscow);
        vasya.getAddresses().add(addressVladivostok);
        vasya = employeeDAO.add(vasya);
        assertEquals(2, employeeDAO.get(vasya.getId()).getAddresses().size());
        assertEquals("Moscow", employeeDAO.get(vasya.getId()).getAddresses().get(0).getCity());
        assertEquals("Vladivostok", employeeDAO.get(vasya.getId()).getAddresses().get(1).getCity());
        assertEquals(2, addressDAO.getAll().size());
    }

    @Test
    public void addPhone() throws DAOException {
        vasya = employeeDAO.add(vasya);
        vasya.getPhones().add(personalPhone);
        employeeDAO.update(vasya);
        assertEquals(1, employeeDAO.get(vasya.getId()).getPhones().size());
        assertEquals(1, phoneDAO.getAll().size());
    }

    @Test
    public void addAddress() throws DAOException {
        vasya = employeeDAO.add(vasya);
        vasya.getAddresses().add(addressMoscow);
        employeeDAO.update(vasya);
        assertEquals(1, employeeDAO.get(vasya.getId()).getAddresses().size());
        assertEquals(1, addressDAO.getAll().size());
    }

    @Test
    public void updatePhone() throws DAOException {
        vasya.getPhones().add(personalPhone);
        vasya.getPhones().add(workPhone);
        vasya = employeeDAO.add(vasya);
        vasya.getPhones().get(0).setPhone("77777777777");
        vasya = employeeDAO.update(vasya);
        assertEquals(2, employeeDAO.get(vasya.getId()).getPhones().size());
        assertEquals("77777777777", employeeDAO.get(vasya.getId()).getPhones().get(0).getPhone());
        assertEquals(2, phoneDAO.getAll().size());
    }

    @Test
    public void updateAddress() throws DAOException {
        vasya.getAddresses().add(addressMoscow);
        vasya.getAddresses().add(addressVladivostok);
        vasya = employeeDAO.add(vasya);
        vasya.getAddresses().get(0).setCity("London");
        vasya = employeeDAO.update(vasya);
        assertEquals(2, employeeDAO.get(vasya.getId()).getAddresses().size());
        assertEquals("London", employeeDAO.get(vasya.getId()).getAddresses().get(0).getCity());
        assertEquals(2, addressDAO.getAll().size());
    }

    @Test
    public void removeAndAddPhone() throws DAOException {
        vasya.getPhones().add(personalPhone);
        vasya.getPhones().add(workPhone);
        vasya = employeeDAO.add(vasya);
        vasya.getPhones().remove(0);
        vasya.getPhones().add(personalPhone);
        vasya.getPhones().add(personalPhone);
        vasya = employeeDAO.update(vasya);
        assertEquals(3, employeeDAO.get(vasya.getId()).getPhones().size());
        assertEquals("+79265771000", employeeDAO.get(vasya.getId()).getPhones().get(0).getPhone());
        assertEquals("+79265771212", employeeDAO.get(vasya.getId()).getPhones().get(1).getPhone());
        assertEquals("+79265771212", employeeDAO.get(vasya.getId()).getPhones().get(2).getPhone());
        assertEquals(3, phoneDAO.getAll().size());
    }

    @Test
    public void removeAndAddAddress() throws DAOException {
        vasya.getAddresses().add(addressMoscow);
        vasya.getAddresses().add(addressVladivostok);
        vasya = employeeDAO.add(vasya);
        vasya.getAddresses().remove(0);
        vasya.getAddresses().add(addressMoscow);
        vasya.getAddresses().add(addressMoscow);
        vasya = employeeDAO.update(vasya);
        assertEquals(3, employeeDAO.get(vasya.getId()).getAddresses().size());
        assertEquals("Vladivostok", employeeDAO.get(vasya.getId()).getAddresses().get(0).getCity());
        assertEquals("Moscow", employeeDAO.get(vasya.getId()).getAddresses().get(1).getCity());
        assertEquals("Moscow", employeeDAO.get(vasya.getId()).getAddresses().get(2).getCity());
        assertEquals(3, addressDAO.getAll().size());
    }

    @Test
    public void addDepartmentWithHead() throws DAOException {
        vasya = employeeDAO.add(vasya);
        it.setHead(vasya);
        it = departmentDAO.add(it);
        Employee headOfIT = employeeDAO.get(departmentDAO.get(it.getId()).getHead().getId());
        assertNotNull(departmentDAO.get(it.getId()));
        assertEquals("Kot Vasyliy", headOfIT.getFirstName());
    }

    @Ignore
    @Test
    public void removeHeadOfDepartment() throws DAOException {
        vasya = employeeDAO.add(vasya);
        it.setHead(vasya);
        it = departmentDAO.add(it);
        Employee headOfIT = employeeDAO.get(departmentDAO.get(it.getId()).getHead().getId());
        assertNotNull(departmentDAO.get(it.getId()));
        assertEquals("Kot Vasyliy", headOfIT.getFirstName());
        employeeDAO.delete(headOfIT);
        assertNull(employeeDAO.get(vasya.getId()));
        it = departmentDAO.get(it.getId());
        assertNull(departmentDAO.get(it.getId()).getHead());
    }

    @Ignore
    @Test
    public void removeDepartmentCascadeDepId() throws DAOException {
        it = departmentDAO.add(it);
        vasya.setDepartment(it);
        vasya = employeeDAO.add(vasya);
        departmentDAO.delete(it);
        assertNull(employeeDAO.get(vasya.getId()).getDepartment());
    }

    @Test
    public void addHeadToEmployee() throws DAOException {
        vasya = employeeDAO.add(vasya);
        petya.setChief(vasya);
        petya = employeeDAO.add(petya);
        assertEquals(2, employeeDAO.getAll().size());
        assertEquals("Kot Vasyliy", employeeDAO.get(petya.getId()).getChief().getFirstName());
    }

    @Ignore
    @Test
    public void removeHeadCascadeHeadId() throws DAOException {
        vasya = employeeDAO.add(vasya);
        petya.setChief(vasya);
        petya = employeeDAO.add(petya);
        employeeDAO.delete(vasya);
        assertNull(employeeDAO.get(vasya.getId()));
        List<Employee> all = employeeDAO.getAll();
        assertEquals(1, all.size());
        assertNull(employeeDAO.get(petya.getId()).getChief());
    }

    @Test
    public void testPreviousBugs() throws DAOException {
        vasya.getPhones().add(personalPhone);
        vasya.getPhones().add(workPhone);
        petya.getPhones().add(workPhone);
        petya.getPhones().add(personalPhone);
        vasya.getAddresses().add(addressMoscow);
        vasya.getAddresses().add(addressVladivostok);
        petya.getAddresses().add(addressMoscow);
        petya.getAddresses().add(addressVladivostok);
        vasya = employeeDAO.add(vasya);
        petya.setChief(vasya);
        petya = employeeDAO.add(petya);
        List<Employee> all = employeeDAO.getAll();
        assertEquals(2, all.size());

        Employee ivan = new Employee();
        ivan.setFirstName("Ivan");
        ivan.setLastName("Ivanovich");

        ivan.getPhones().add(personalPhone);
        ivan.getPhones().add(workPhone);
        ivan.getAddresses().add(addressMoscow);
        ivan.getAddresses().add(addressVladivostok);

        ivan = employeeDAO.add(ivan);
        vasya.setChief(ivan);
        vasya = employeeDAO.update(vasya);
        List<Employee> all1 = employeeDAO.getAll();
        assertEquals(3, all1.size());


        ivan.setChief(petya);
        ivan = employeeDAO.update(ivan);

        List<Employee> all2 = employeeDAO.getAll();
        assertEquals(3, all2.size());
    }

    @Test
    public void dateTest() throws DAOException {
        Date date = new Date();
        System.out.println("date.getTime() = " + date.getTime());
        SimpleDateFormat sdF = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("date = " + sdF.format(date));
        vasya.setBirthDay(new Date());
        vasya = employeeDAO.add(vasya);
        Employee employee = employeeDAO.get(vasya.getId());
        Date birthDay = employee.getBirthDay();
        System.out.println("birthDay = " + sdF.format(birthDay));


    }

    @Test
    public void customTest() throws DAOException {
        System.out.println(ContactInfoType.WORK);

    }
}
