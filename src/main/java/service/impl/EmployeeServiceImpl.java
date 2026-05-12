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


    //??
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
        return repository.findAll().stream()
                .filter(emp -> emp.getId().equals(id))
                .map(mapper::toEmployeeResponse)
                .findFirst().orElseThrow(() -> new EmployeeException("Employee not found"));
    }


}
