package database;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDb {
    //create a list represent as database for storing data
    private final List<Employee> employees;

    //create a contructor for initialize value for employee list
    public EmployeeDb() {
        employees = new ArrayList<>(); //list mean data pel ke create object employeedb
    }

    public List<Employee> data() {
        return employees;
    } //get data ttul ban employee list

}
