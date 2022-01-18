package codegym.service;

import codegym.model.Employee;
import codegym.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class EmplyeeService implements IEmployeeService{
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepo.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public void delete(long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepo.findById(id).get();
    }
    @Override
    public List<Employee> sort(){
        List<Employee> list = findAll();
        list.sort(Comparator.comparing(Employee::getSalary));
        return list;
    }
}
