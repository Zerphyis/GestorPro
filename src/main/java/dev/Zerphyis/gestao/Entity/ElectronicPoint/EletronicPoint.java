package dev.Zerphyis.gestao.Entity.ElectronicPoint;

import dev.Zerphyis.gestao.Entity.Employee.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_pontoEletronico")
public class EletronicPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Employee EmployeeId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public EletronicPoint(){

    }
    public EletronicPoint(Employee employee ){
        this.EmployeeId=employee;
        this.entryTime=LocalDateTime.now();


    }

    public Employee getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        EmployeeId = employeeId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}
