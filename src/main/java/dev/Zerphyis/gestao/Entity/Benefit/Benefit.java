package dev.Zerphyis.gestao.Entity.Benefit;

import dev.Zerphyis.gestao.Entity.Data.DataBenefit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_Beneficios")
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameBenefit;
    @NotNull
    private BigDecimal benefitValue;

    public Benefit(){

    }
    public Benefit(DataBenefit data ){
        this.nameBenefit= data.nameBenefit();
        this.benefitValue=data.benefitValue();
    }

    public String getNameBenefit() {
        return nameBenefit;
    }

    public void setNameBenefit(String nameBenefit) {
        this.nameBenefit = nameBenefit;
    }

    public BigDecimal getBenefitValue() {
        return benefitValue;
    }

    public void setBenefitValue(BigDecimal benefitValue) {
        this.benefitValue = benefitValue;
    }
}
