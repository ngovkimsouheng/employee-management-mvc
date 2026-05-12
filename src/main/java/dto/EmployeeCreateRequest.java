package dto;

import java.time.LocalDate;
import java.util.Locale;

//ke use dto kom oy ke access tov doy ptorl jmouy employee
public record EmployeeCreateRequest(
        String firstName,
        String lastName,
        Double salary,
        LocalDate hireDate
) {
}
