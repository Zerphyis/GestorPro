package dev.Zerphyis.gestao.Entity.Payroll;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Employee.Employee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "folhaPagamento")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "funcionario_id")
    @ManyToOne
    private Employee employee;

    private BigDecimal baseSalary;
    private BigDecimal discount;
    @ManyToMany
    private List<Benefit> benefit;
    private BigDecimal wage;

    public Payroll() {
        this.baseSalary = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.benefit = new ArrayList<>();
        this.wage = BigDecimal.ZERO;
    }

    public Payroll(Employee employee, BigDecimal baseSalary, BigDecimal discount, List<Benefit> benefit) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.discount = discount;
        this.benefit = benefit != null ? benefit : new ArrayList<>();
        this.wage = BigDecimal.ZERO;
    }


    public void calculateWage() {
        BigDecimal totalBenefits = BigDecimal.ZERO;

        if (benefit != null && !benefit.isEmpty()) {
            for (Benefit b : benefit) {
                totalBenefits = totalBenefits.add(b.getBenefitValue());
            }
        }

        BigDecimal finalSalary = baseSalary.add(totalBenefits).subtract(discount);

        this.wage = finalSalary;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public List<Benefit> getBenefit() {
        return benefit;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setBenefit(List<Benefit> benefit) {
        this.benefit = benefit;
    }
}


