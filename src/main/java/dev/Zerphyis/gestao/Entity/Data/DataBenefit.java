package dev.Zerphyis.gestao.Entity.Data;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataBenefit(String nameBenefit,
         BigDecimal benefitValue) {

}
