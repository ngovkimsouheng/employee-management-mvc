package service.impl;

import Repository.EmployeeRepository;
import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import mapper.EmployeeMapper;
import model.Employee;
import service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {


    //service depend on  repo

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;



    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createEmployeeResponse(EmployeeCreateRequest request) throws EmployeeException {

        Employee employee = mapper.fromEmployeeCreateRequest(request);

        repository.save(employee);

        return mapper.toEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() throws EmployeeException {


        if (repository.findAll().isEmpty()) {
            throw new EmployeeException("No data yet.");
        }

        return repository.findAll().stream().map(mapper::toEmployeeResponse).toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) throws EmployeeException {
        Employee employee = repository.findById(id);
        return mapper.toEmployeeResponse(employee);
    }


    @Override
    public EmployeeResponse updateEmployeeById(Long id, EmployeeCreateRequest request) throws EmployeeException {

        if (id == null) {
            throw new EmployeeException("Employee id cannot be null");
        }

        if (request == null) {
            throw new EmployeeException("Request cannot be null");
        }

        if (request.salary() <= 0) {
            throw new EmployeeException("Salary must be greater than 0");
        }

        Employee employee = mapper.fromEmployeeCreateRequest(request);
        employee.setId(id);

        Employee updatedEmployee = repository.update(id, employee);

        return mapper.toEmployeeResponse(updatedEmployee);
    }

    @Override
    public EmployeeResponse deleteById(Long id) throws EmployeeException {

        Employee employee = repository.findById(id);

        repository.deleteById(id);
        
        return mapper.toEmployeeResponse(employee);
    }


}
