package view;

import dto.EmployeeCreateRequest;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class EmployeeView {

    private final Scanner scanner = new Scanner(System.in);

    public EmployeeCreateRequest createEmployee() {

        System.out.println("=== [[Employee Creation ]] ===");
        System.out.println("[+] Input firstName : ");
        String firstName = scanner.nextLine();
        System.out.println("[+] Input lastName : ");
        String lastName = scanner.nextLine();
        System.out.println("[+] Input salary : ");
        Double salary = Double.parseDouble(scanner.nextLine());
        System.out.println("[+] Input hireDate  : ");
        String hireDate = scanner.nextLine();

        String[] parts = hireDate.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int dateOfMonth = Integer.parseInt(parts[2]);

        LocalDate hire = LocalDate.of(year, month, dateOfMonth);
        return new EmployeeCreateRequest(firstName, lastName, salary, hire);

    }

}
