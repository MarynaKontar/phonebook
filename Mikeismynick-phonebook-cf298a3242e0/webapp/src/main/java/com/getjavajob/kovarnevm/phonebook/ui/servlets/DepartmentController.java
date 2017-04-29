package com.getjavajob.kovarnevm.phonebook.ui.servlets;

import com.getjavajob.kovarnevm.phonebook.common.Department;
import com.getjavajob.kovarnevm.phonebook.common.Employee;
import com.getjavajob.kovarnevm.phonebook.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {

    private CrudService<Employee> employeeService;
    private CrudService<Department> departmentService;

    @Autowired
    public DepartmentController(CrudService<Employee> employeeService, CrudService<Department> departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/showDepartments", method = RequestMethod.GET)
    public String showAllSearch(Model model) {
        List<Department> all = departmentService.getAll();
        model.addAttribute("departments", all);
        return "department/list";
    }

    @RequestMapping(value = "/department/showInfo", method = RequestMethod.GET)
    public String showEmployee(@RequestParam("id") int id, Model model) {
        Department department = departmentService.get(id);
        if (department == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Department not found");
        }
        model.addAttribute("department", department);
        return "department/departmentDetail";
    }

    @RequestMapping(value = "/showDepartments", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@ModelAttribute("departmentForm") Department department,
                                       @RequestParam("headId") String id,
                                       BindingResult result, final RedirectAttributes redirectAttributes) {
        // set department

        Employee head = employeeService.get(Integer.valueOf(id));
        department.setHead(head);

        if (result.hasErrors()) {
            System.out.println("department = " + department);
            for (ObjectError objectError : result.getAllErrors()) {
                System.out.println(objectError);
            }
        }

        redirectAttributes.addFlashAttribute("css", "success");
        if (department.getId() == 0) {
            redirectAttributes.addFlashAttribute("msg", "Department added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("msg", "Department updated successfully!");
        }
        department = departmentService.save(department);
        return "redirect:/department/showInfo?id=" + department.getId();
    }

    @RequestMapping(value = "/edit/department/add", method = RequestMethod.GET)
    public String showAddEmployeeForm(Model model) {
        Department department = new Department();
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("departmentForm", department);
        return "edit/department/departmentEditForm";
    }

    @RequestMapping(value = "/edit/department/update", method = RequestMethod.GET)
    public String showUpdateEmployeeForm(@RequestParam("id") int id, Model model) {
        Department department = departmentService.get(id);
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        model.addAttribute("departmentForm", department);
        return "edit/department/departmentEditForm";
    }

    @RequestMapping(value = "/edit/department/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("id") int id, final RedirectAttributes redirectAttributes) {
        Department department = departmentService.get(id);
        departmentService.remove(department);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Department is deleted!");
        return "redirect:/showDepartments";

    }
}
