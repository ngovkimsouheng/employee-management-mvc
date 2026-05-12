package model;


import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @NonNull
    private static Long nextId = 1L;
//    private static DateTimeFormatter hireDateFmt = DateTimeFormatter.ofPattern("E-dd-MM-yyyy");
    private Long id;
    private String firstName;
    private String lastName;
    private Double salary;
    private LocalDate hireDate;


    public Employee(String firstName, String lastName, Double salary, LocalDate hireDate) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hireDate = hireDate; //LocalDate.parse(hireDate.format(hireDateFmt));
    }


}
