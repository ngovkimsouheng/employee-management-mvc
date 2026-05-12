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
        return repository.findAll().stream().map(mapper::toEmployeeResponse).toList();
    }


}
