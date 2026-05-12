package service;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;

import java.util.List;

public interface EmployeeService {
    //CRUD operation
    //pel create ke  request , so yerng trov bos request dae yerng
    EmployeeResponse createEmployeeResponse(EmployeeCreateRequest request) throws EmployeeException;

    List<EmployeeResponse> getAllEmployees() throws EmployeeException;



}
