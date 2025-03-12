package dev.Zerphyis.gestao.Entity.Data.EletronicPoints;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record DataEletronicPointsReponseEntry(String EmployeeName, LocalTime entry, LocalDate date) {
}
