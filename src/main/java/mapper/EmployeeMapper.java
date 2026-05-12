package mapper;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import model.Employee;

public class EmployeeMapper {

    public Employee fromEmployeeCreateRequest(EmployeeCreateRequest request) {
        return new Employee(
                request.firstName(),
                request.lastName(),
                request.salary(),
                request.hireDate()
        );
    }

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getHireDate()

        );
    }
}
