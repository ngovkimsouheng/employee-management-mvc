package controller;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import service.EmployeeService;
import view.EmployeeView;

public class EmployeeController {

    private final EmployeeView view;
    private final EmployeeService service;

    public EmployeeController(EmployeeView view, EmployeeService service) {
        this.view = view;
        this.service = service;
    }

    public void create() {

        EmployeeCreateRequest request = view.createEmployee();

        EmployeeResponse response = service.createEmployeeResponse(request);

        System.out.println(request);

        System.out.println(response);

    }

}
