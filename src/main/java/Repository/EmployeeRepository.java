package Repository;

import database.EmployeeDb;
import model.Employee;

import java.util.List;

//this layer connects to database
public class EmployeeRepository {
    private final EmployeeDb employeeDb;


    //ke bos db mao ban repo process ban , oy vea depend ler ke , style dependency injection
    public EmployeeRepository(EmployeeDb employeeDb) {
        this.employeeDb = employeeDb;
    }

    //save data , hx data del ke bos mao chea employee object
    public void save(Employee employee) {
        employeeDb.data().add(employee);
    }

    public List<Employee> findAll() {
        return employeeDb.data(); //get all  data
    }


    // check trov ka ID , hx use mthod anyMatch  (shortcircirt)
    public boolean existsByID(Long id) {
        return employeeDb.data().stream().anyMatch(emp -> emp.getId().equals(id));
    }

}
