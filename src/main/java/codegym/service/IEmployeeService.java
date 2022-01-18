package codegym.service;

import codegym.model.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> findAll();
    public void save(Employee employee);
    public void delete(long id);
    public Employee findById(long id);
    public  List<Employee> sort();

}
