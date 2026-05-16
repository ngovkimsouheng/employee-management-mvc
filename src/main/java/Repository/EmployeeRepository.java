package Repository;

import config.DbConnection;
import database.EmployeeDb;
import exceptions.EmployeeException;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//this layer connects to database
public class EmployeeRepository {


    public Employee save(Employee employee) throws EmployeeException {

        validateEmployee(employee);

        String sql = """
                insert into employees (first_name, last_name, salary, hire_date)
                values (?, ?, ?, ?) returning *;
                """;
        Employee savedRecord = new Employee();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pts = conn.prepareStatement(sql)) {

            pts.setString(1, employee.getFirstName().trim());
            pts.setString(2, employee.getLastName().trim());
            pts.setDouble(3, employee.getSalary());
            pts.setDate(4, Date.valueOf(employee.getHireDate()));

            try (ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    savedRecord = mapRowToEmployee(rs);
                }
            }

        } catch (SQLException e) {
            throw new EmployeeException("Error inserting employee into database: " + e.getMessage());
        }

        return savedRecord;
    }


    public List<Employee> findAll() throws EmployeeException {
        List<Employee> emps = new ArrayList<>();
        String sql = "select * from employees";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = mapRowToEmployee(resultSet);
                emps.add(employee);
            }

        } catch (SQLException e) {
            throw new EmployeeException("Error retrieving all employees: " + e.getMessage());
        }

        return emps;
    }


    public Employee findById(Long id) throws EmployeeException {

        if (id == null || id <= 0) {
            throw new EmployeeException("Invalid employee ID: ID must be greater than 0");
        }

        String sql = "select * from employees where id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToEmployee(resultSet);
                } else {
                    throw new EmployeeException("Employee not found with ID: " + id);
                }
            }

        } catch (SQLException e) {
            throw new EmployeeException("Error finding employee by ID: " + e.getMessage());
        }
    }


    public boolean existsById(Long id) {
        if (id == null || id <= 0) {
            return false;
        }

        String sql = "select 1 from employees where id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            System.out.println("Error checking employee existence: " + e.getMessage());
            return false;
        }
    }


    public Employee update(Long id, Employee employee) throws EmployeeException {

        if (id == null || id <= 0) {
            throw new EmployeeException("Invalid employee ID: ID must be greater than 0");
        }


        validateEmployee(employee);


        if (!existsById(id)) {
            throw new EmployeeException("Employee not found with ID: " + id);
        }

        String sql = """
                update employees 
                set first_name = ?, last_name = ?, salary = ?, hire_date = ?
                where id = ? returning *;
                """;
        Employee updatedRecord = new Employee();

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pts = conn.prepareStatement(sql)) {

            pts.setString(1, employee.getFirstName().trim());
            pts.setString(2, employee.getLastName().trim());
            pts.setDouble(3, employee.getSalary());
            pts.setDate(4, Date.valueOf(employee.getHireDate()));
            pts.setLong(5, id);

            try (ResultSet rs = pts.executeQuery()) {
                if (rs.next()) {
                    updatedRecord = mapRowToEmployee(rs);
                }
            }

        } catch (SQLException e) {
            throw new EmployeeException("Error updating employee: " + e.getMessage());
        }

        return updatedRecord;
    }


    public boolean deleteById(Long id) throws EmployeeException {

        if (id == null || id <= 0) {
            throw new EmployeeException("Invalid employee ID: ID must be greater than 0");
        }

        if (!existsById(id)) {
            throw new EmployeeException("Employee not found with ID: " + id);
        }

        String sql = "delete from employees where id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pts = conn.prepareStatement(sql)) {

            pts.setLong(1, id);

            int rowsAffected = pts.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new EmployeeException("Failed to delete employee with ID: " + id);
            }

        } catch (SQLException e) {
            throw new EmployeeException("Error deleting employee: " + e.getMessage());
        }
    }

    private Employee mapRowToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setSalary(resultSet.getDouble("salary"));
        employee.setHireDate(resultSet.getDate("hire_date").toLocalDate());
        return employee;
    }


    private void validateEmployee(Employee employee) throws EmployeeException {

        if (employee == null) {
            throw new EmployeeException("Employee object cannot be null");
        }

        if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
            throw new EmployeeException("Employee first name cannot be empty");
        }

        if (employee.getFirstName().trim().length() < 2) {
            throw new EmployeeException("Employee first name must be at least 2 characters long");
        }

        if (employee.getFirstName().trim().length() > 100) {
            throw new EmployeeException("Employee first name cannot exceed 100 characters");
        }


        if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
            throw new EmployeeException("Employee last name cannot be empty");
        }

        if (employee.getLastName().trim().length() < 2) {
            throw new EmployeeException("Employee last name must be at least 2 characters long");
        }

        if (employee.getLastName().trim().length() > 50) {
            throw new EmployeeException("Employee last name cannot exceed 50 characters");
        }

        if (employee.getSalary() == null) {
            throw new EmployeeException("Employee salary cannot be null");
        }

        if (employee.getSalary() <= 0) {
            throw new EmployeeException("Employee salary must be greater than 0");
        }


        if (employee.getHireDate() == null) {
            throw new EmployeeException("Employee hire date cannot be null");
        }

        if (employee.getHireDate().isAfter(java.time.LocalDate.now())) {
            throw new EmployeeException("Employee hire date cannot be in the future");
        }
    }

}
