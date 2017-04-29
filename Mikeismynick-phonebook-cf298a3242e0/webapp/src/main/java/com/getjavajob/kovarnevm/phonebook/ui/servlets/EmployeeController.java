package com.getjavajob.kovarnevm.phonebook.ui.servlets;

import com.getjavajob.kovarnevm.phonebook.common.*;
import com.getjavajob.kovarnevm.phonebook.dto.EmployeeDTO;
import com.getjavajob.kovarnevm.phonebook.dto.converters.ConverterDTO;
import com.getjavajob.kovarnevm.phonebook.dto.converters.EmployeeToDTO;
import com.getjavajob.kovarnevm.phonebook.service.CrudService;
import com.getjavajob.kovarnevm.phonebook.service.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    private CrudService<Employee> employeeService;
    private CrudService<Department> departmentService;

    @Autowired
    public EmployeeController(CrudService<Employee> employeeService, CrudService<Department> departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/showEmployees";
    }

    @RequestMapping(value = "/showEmployees", method = RequestMethod.GET)
    public String showAllSearch(Model model) {
        List<Employee> all = employeeService.getAll();
        model.addAttribute("employees", all);
        return "employee/list";
    }

    @RequestMapping(value = "/employee/showInfo", method = RequestMethod.GET)
    public String showEmployee(@RequestParam("id") int id, Model model) {
        Employee employee = employeeService.get(id);
        if (employee == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Employee not found");
        }
        model.addAttribute("employee", employee);
        return "employee/employeeDetail";
    }

    @RequestMapping(value = "/showEmployees", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@ModelAttribute("employeeForm") Employee employee,
                                       @RequestParam("departmentId") String depId,
                                       @RequestParam("chiefId") String chiefId,
                                       BindingResult result, final RedirectAttributes redirectAttributes) {

        Department department = departmentService.get(Integer.valueOf(depId));
        employee.setDepartment(department);
        Employee chief = employeeService.get(Integer.valueOf(chiefId));
        employee.setChief(chief);
        if (employee.getImage().getImage().isEmpty()) {
            employee.setImage(null);
        }

        employee.getAddresses().remove(0);
        List<Address> prepareAddressList = new ArrayList<>();
        for (Address address : employee.getAddresses()) {
            if (address.getType() != null) {
                prepareAddressList.add(address);
            }
        }
        employee.setAddresses(prepareAddressList);
        employee.getPhones().remove(0);
        List<Phone> preparePhoneList = new ArrayList<>();
        for (Phone phone : employee.getPhones()) {
            if (phone.getType() != null) {
                preparePhoneList.add(phone);
            }
        }
        employee.setPhones(preparePhoneList);

        if (result.hasErrors()) {
            System.out.println("employee = " + employee);
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println(objectError);
            }
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (employee.getId() == 0) {
            redirectAttributes.addFlashAttribute("msg", "Employee added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Employee updated successfully!");
        }
        employee = employeeService.save(employee);
        return "redirect:/employee/showInfo?id=" + employee.getId();
    }

    @InitBinder
    public void dateBinding(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    @RequestMapping(value = "/edit/employee/add", method = RequestMethod.GET)
    public String showAddEmployeeForm(Model model) {
        Employee employee = new Employee();
        List<ContactInfoType> contactTypes = new ArrayList<>(Arrays.asList(ContactInfoType.values()));
        List<Department> departments = departmentService.getAll();
        List<Employee> chiefs = employeeService.getAll();
        model.addAttribute("chiefs", chiefs);
        model.addAttribute("departments", departments);
        model.addAttribute("contactTypes", contactTypes);
        model.addAttribute("employeeForm", employee);
        return "edit/employee/employeeEditForm";
    }

    @RequestMapping(value = "/edit/employee/update", method = RequestMethod.GET)
    public String showUpdateEmployeeForm(@RequestParam("id") int id, Model model) {
        Employee employee = employeeService.get(id);
        List<ContactInfoType> contactTypes = new ArrayList<>(Arrays.asList(ContactInfoType.values()));
        List<Department> departments = departmentService.getAll();
        List<Employee> chiefs = employeeService.getAll();
        model.addAttribute("chiefs", chiefs);
        model.addAttribute("departments", departments);
        model.addAttribute("contactTypes", contactTypes);
        model.addAttribute("employeeForm", employee);
        return "edit/employee/employeeEditForm";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String showError() {
        throw new ServiceException("test");
    }

    @RequestMapping(value = "/edit/employee/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("id") int id, final RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.get(id);
        employeeService.remove(employee);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Employee is deleted!");
        return "redirect:/showEmployees";
    }

    @RequestMapping("/getEmployees")
    @ResponseBody
    public List<EmployeeDTO> getEmployees(final @RequestParam("filter") String filter) {
        List<Employee> employees = employeeService.getAll();
        CollectionUtils.filter(employees, new Predicate<Employee>() {
            @Override
            public boolean evaluate(Employee employee) {
                return employee.getLastName().contains(filter) || employee.getFirstName().contains(filter);
            }
        });
        ConverterDTO<EmployeeDTO> converterDTO = new EmployeeToDTO();
        return converterDTO.listToDTO(employees);
    }
}
