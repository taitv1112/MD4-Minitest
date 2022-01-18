package codegym.controller;

import codegym.model.Branch;
import codegym.model.Employee;
import codegym.service.IBranchService;
import codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    IBranchService branchService;

    @Autowired
    IEmployeeService employeeService;
    static int countSalary = 1;

    @GetMapping("/employees")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("employees", employeeService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("branchs", branchService.findAll());
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }
    @PostMapping("/create")
    public String create(@ModelAttribute(value = "employee") Employee employee, @RequestParam long idBranch){
        Branch branch = new Branch();
        branch.setId(idBranch);
        employee.setBranch(branch);
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/edit")
    public ModelAndView editForm(@RequestParam long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("branchs", branchService.findAll());
        modelAndView.addObject("employee", employeeService.findById(id));
        return modelAndView;
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute(value = "employee") Employee employee, @RequestParam long idBranch){
        Branch branch = new Branch();
        branch.setId(idBranch);
        employee.setBranch(branch);
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam int id){
        employeeService.delete(id);
        return "redirect:/employees";
    }
    @GetMapping("/detail")
    public ModelAndView detailForm(@RequestParam long id){
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("employee", employeeService.findById(id));
        return modelAndView;
    }
    @GetMapping ("/sort")
    public ModelAndView sort() {
        ++countSalary;
        ModelAndView modelAndView = new ModelAndView("/show");
        if(countSalary%2==0){
                    List<Employee> sortSalary = employeeService.sort();
                    modelAndView.addObject("employees",sortSalary);
                    return modelAndView;
                }else {
                    modelAndView.addObject("employees", employeeService.findAll());
                    return modelAndView;
                }

    }
}
