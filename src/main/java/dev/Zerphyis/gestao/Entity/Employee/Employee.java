package dev.Zerphyis.gestao.Entity.Employee;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import dev.Zerphyis.gestao.Entity.Data.Employee.DataEmployee;
import dev.Zerphyis.gestao.Entity.Position.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_funcionarios")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(unique = true)
    private String document;

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(unique = true)
    private String phone;

    private LocalDate dateAdmission;

    @NotNull
    private BigDecimal wage;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Position position;

    @ManyToMany
    @JoinTable(
            name = "tb_funcionario_beneficio",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "beneficio_id")
    )
    private List<Benefit> benefits = new ArrayList<>();

    public Employee() {}

    public Employee(DataEmployee data, Position position, List<Benefit> benefits) {
        this.name = data.name();
        this.document = data.document();
        this.email = data.email();
        this.phone = data.phone();
        this.dateAdmission = data.dateAdmission();
        this.wage = data.wage();
        this.position = position;
        this.benefits = benefits;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(LocalDate dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    // Valida a lista de benefícios antes de atribuir
    public void setBenefits(List<Benefit> benefits) {
        if (benefits == null) {
            this.benefits = new ArrayList<>();
        } else {
            this.benefits = benefits;
        }
    }
}
