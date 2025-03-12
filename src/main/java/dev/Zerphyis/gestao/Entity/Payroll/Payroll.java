package dev.Zerphyis.gestao.Entity.Payroll;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Employee.Employee;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "folha_pagamento")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "funcionario_id")
    @ManyToOne
    private Employee employee;

    private BigDecimal baseSalary;
    private BigDecimal discount;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "folha_pagamento_beneficio",
            joinColumns = @JoinColumn(name = "folha_pagamento_id"),
            inverseJoinColumns = @JoinColumn(name = "tb_beneficio_id")
    )
    private List<Benefit> benefits = new ArrayList<>();



    private BigDecimal wage;

    public Payroll() {
        this.baseSalary = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.wage = BigDecimal.ZERO;
    }

    public Payroll(Employee employee, BigDecimal baseSalary, BigDecimal discount, List<Benefit> benefits) {
        this.employee = employee;
        this.baseSalary = baseSalary;
        this.discount = discount;
        this.wage = BigDecimal.ZERO;
        this.benefits = benefits != null ? new ArrayList<>(benefits) : new ArrayList<>();
    }

    public void calculateWage() {
        BigDecimal totalBenefits = benefits.stream()
                .map(Benefit::getBenefitValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discount = calculateDiscount(benefits.size(), baseSalary);
        this.wage = baseSalary.add(totalBenefits).subtract(discount);
    }

    private BigDecimal calculateDiscount(int numBenefits, BigDecimal baseSalary) {
        BigDecimal discountPercentage = new BigDecimal("0.04"); // 4% de desconto por benefício
        return baseSalary.multiply(discountPercentage).multiply(new BigDecimal(numBenefits));
    }

    public void addBenefit(Benefit benefit) {
        if (!this.benefits.contains(benefit)) {
            this.benefits.add(benefit);
        }
    }

    public void removeBenefit(Benefit benefit) {
        this.benefits.remove(benefit);
    }

    // Getters e Setters
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

    public List<Benefit> getBenefits() {
        return benefits;
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

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = new ArrayList<>(benefits);
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }
}


