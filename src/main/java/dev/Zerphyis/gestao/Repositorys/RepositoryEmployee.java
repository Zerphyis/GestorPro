package dev.Zerphyis.gestao.Repositorys;

import dev.Zerphyis.gestao.Entity.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEmployee extends JpaRepository<Employee,Long> {
}
