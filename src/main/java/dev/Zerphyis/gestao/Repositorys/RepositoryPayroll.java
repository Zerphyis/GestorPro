package dev.Zerphyis.gestao.Repositorys;

import dev.Zerphyis.gestao.Entity.Payroll.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPayroll extends JpaRepository<Payroll,Long> {
    Payroll findByEmployeeId(Long employeeId);
}
