package dev.Zerphyis.gestao.Entity.Data.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DataResponseEmployee(Long id,
                                   String name,
                                   String document,
                                   String email,
                                   String phone,
                                   LocalDate dateAdmission,
                                   BigDecimal wage,
                                   String positionName,
                                   List<String> benefitsNames) {
}