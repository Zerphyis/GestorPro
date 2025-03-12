package dev.Zerphyis.gestao.Entity.ElectronicPoint;

import dev.Zerphyis.gestao.Entity.Employee.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "tb_ponto_eletronico")
public class EletronicPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Employee EmployeeId;
    private LocalTime entryTime;
    private  LocalDate date;
    private LocalTime exitTime;

    public EletronicPoint(){

    }
    public EletronicPoint(Employee employee , LocalDate date ){
        this.EmployeeId=employee;
        this.date=date;
        this.entryTime=LocalTime.now();


    }

    public Employee getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        EmployeeId = employeeId;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }
}
