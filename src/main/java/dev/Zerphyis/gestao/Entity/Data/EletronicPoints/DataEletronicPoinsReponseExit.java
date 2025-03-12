package dev.Zerphyis.gestao.Entity.Data.EletronicPoints;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataEletronicPoinsReponseExit(String employeeName, LocalTime entryTime, LocalTime exitTime, LocalDate date) {
}
