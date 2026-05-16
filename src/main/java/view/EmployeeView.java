package view;

import dto.EmployeeCreateRequest;
import dto.EmployeeResponse;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeView {

    private final Scanner scanner = new Scanner(System.in);

    public EmployeeCreateRequest createEmployee() {


        System.out.println("=== [[Employee Creation ]] ===");
        String firstName = getStringInput(scanner, "Input FirstName : ");

        String lastName = getStringInput(scanner, "Input LastName :");

        Double salary = getDoubleInput(scanner, "Input Salary :");

        LocalDate hireDate = getDateTimeInput(scanner, "Input Hire Date :");

        return new EmployeeCreateRequest(firstName, lastName, salary, hireDate);

    }

    public EmployeeCreateRequest updateEmployee() {


        System.out.println("=== [[Employee Update ]] ===");
        String firstName = getStringInput(scanner, "Input FirstName : ");

        String lastName = getStringInput(scanner, "Input LastName :");

        Double salary = getDoubleInput(scanner, "Input Salary :");

        LocalDate hireDate = getDateTimeInput(scanner, "Input Hire Date :");

        return new EmployeeCreateRequest(firstName, lastName, salary, hireDate);

    }



    //method for handling mismatch input from user
    public String getStringInput(Scanner sc, String context) { //context use to make the message dynamic
        while (true) {
            System.out.print(context);
            String input = scanner.nextLine();
            if (input.isBlank() || !input.matches("[A-Za-z]{2,}")) {
                System.out.println("Invalid input .  Try again");
            } else {
                return input;
            }
        }
    }

    //method for handling number input
    public Double getDoubleInput(Scanner sc, String context) {

        while (true) {

            System.out.print(context);

            try {

                String input = sc.nextLine();

                // check starts with 0
                if (input.startsWith("0") && input.length() > 1 && input.charAt(1) != '.') {
                    System.out.println("Salary cannot start with 0.");
                    continue;
                }

                double salary = Double.parseDouble(input);

                // check negative or zero
                if (salary <= 0) {
                    System.out.println("Salary must be greater than 0.");
                    continue;
                }

                return salary;

            } catch (NumberFormatException e) {

                System.out.println("Invalid Double value. Try again.");

            }
        }
    }

    public LocalDate getDateTimeInput(Scanner sc, String context) {
        while (true) {
            System.out.print(context);
            String input = sc.nextLine();

            if (!input.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
                System.out.println("Invalid Format for Date (yyyy-MM-dd). Try Again.");
            } else {
                LocalDate result = LocalDate.parse(input);
                if (result.isAfter(LocalDate.now())) {
                    System.out.println("Cannot hire employee from the future");
                } else {
                    return result;
                }
            }

        }
    }

    //method for handling

    public void displayEmployeeResponse(EmployeeResponse response, String context) {

        Table table = new Table(
                3, BorderStyle.CLASSIC
        );

        table.addCell(context, 3);
        table.addCell("Id");
        table.addCell(response.id().toString(), 2);
        table.addCell("FirstName");
        table.addCell(response.firstName(), 2);
        table.addCell("lastname");
        table.addCell(response.lastName(), 2);
        table.addCell("Hire date");
        table.addCell(response.hireDate().toString(), 2);

        //render data as table

        System.out.println(
                table.render()
        );
    }

    public void displayTableEmployee(List<EmployeeResponse> responses) {
        Table table = new Table(
                5, BorderStyle.CLASSIC
        );
        String[] columns = {
                "ID", "FirstName", "LastName", "Salary", "Hire Date"
        };

        for (String column : columns) {
            table.addCell(column);
        }

        responses.forEach(user -> {
            table.addCell(user.id().toString());
            table.addCell(user.firstName());
            table.addCell(user.lastName());
            table.addCell(user.salary().toString());
            table.addCell(user.hireDate().toString());
        });
        System.out.println(table.render());
    }

    public int showMenuAndGetOption() {
        System.out.println("""
                =====[Employee Management]=====
                1.Create employee
                2.Update employee
                3.Get all employee
                4.Get employee by Id
                5.Delete Employee
                0.Exit
                """);
        while (true) {
            System.out.print("Choose an option (0-5): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Option cannot be empty. Try again.");
                continue;
            }

            try {
                int option = Integer.parseInt(input);
                if (option < 0 || option > 5) {
                    System.out.println("Invalid option. Please choose a number between 0 and 5.");
                    continue;
                }
                return option;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 5.");
            }
        }
    }

    public Long inputId() {

        while (true) {

            System.out.print("Input Id: ");

            try {

                String input = scanner.nextLine().trim();

                // check empty
                if (input.isEmpty()) {
                    System.out.println("Id cannot be empty.");
                    continue;
                }

                Long id = Long.parseLong(input);

                // check zero or negative
                if (id <= 0) {
                    System.out.println("Id must be greater than 0.");
                    continue;
                }

                return id;

            } catch (NumberFormatException e) {

                System.out.println("Invalid id format. Please input a valid number.");

            }
        }
    }

}
