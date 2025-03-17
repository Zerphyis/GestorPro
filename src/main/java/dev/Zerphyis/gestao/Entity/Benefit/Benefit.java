package dev.Zerphyis.gestao.Entity.Benefit;

import dev.Zerphyis.gestao.Entity.Data.Benefit.DataBenefit;
import dev.Zerphyis.gestao.Entity.Payroll.Payroll;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_beneficios")
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameBenefit;
    @NotNull
    private BigDecimal benefitValue;


    @ManyToMany(mappedBy = "benefits")
    private List<Payroll> payrolls = new ArrayList<>();

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

    public List<Payroll> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(List<Payroll> payrolls) {
        this.payrolls = payrolls;
    }
}
