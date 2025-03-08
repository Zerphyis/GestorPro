package dev.Zerphyis.gestao.Entity.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DataEmployee(Long id,
                           String name,
                           String document,
                           String email,
                           String phone,
                           LocalDate dateAdmission,
                           BigDecimal wage,
                           Long positionId,
                           List<Long> benefitsIds) {
}
