package controller;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import exceptions.EmployeeException;
import service.EmployeeService;
import view.EmployeeView;

import java.util.List;

public class EmployeeController {

    private final EmployeeView view;
    private final EmployeeService service;

    public EmployeeController(EmployeeView view, EmployeeService service) {
        this.view = view;
        this.service = service;
    }


    // derm bey create trov mean request

    public void create() {

        try {
            EmployeeCreateRequest request = view.createEmployee();
            EmployeeResponse response = service.createEmployeeResponse(request);
            view.displayEmployeeResponse(response, "Created employee");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {

        try {
            Long id = view.inputId();

            // check if employee exists first
            service.getEmployeeById(id);

            // only ask for new data if employee exists
            EmployeeCreateRequest request = view.updateEmployee();
            EmployeeResponse response = service.updateEmployeeById(id, request);

            view.displayEmployeeResponse(response, "Updated Employee");

        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAll() {

        try {
            view.displayTableEmployee(service.getAllEmployees());
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getById() {
        try {
            Long id = view.inputId();
            EmployeeResponse response = service.getEmployeeById(id);
            view.displayEmployeeResponse(response, "Employee Details");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {

        try {
            Long id = view.inputId();
            EmployeeResponse response = service.deleteById(id);
            view.displayEmployeeResponse(response, "Deleted Employee");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        while (true) {
            int option = view.showMenuAndGetOption();
            if (option < 1 || option > 6) {
                System.out.println("Invalid option! Please choose a correct option from 1 to 6.");
                continue;
            }
            switch (option) {
                case 1 -> create();
                case 2 -> update();
                case 3 -> getAll();
                case 4 -> getById();
                case 5 -> delete();
                default -> System.out.println("Exiting...");
            }
        }
    }


}
