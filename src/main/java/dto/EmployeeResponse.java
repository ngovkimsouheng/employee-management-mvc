package dto;

import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        Double salary,
        LocalDate hireDate

) {
}
