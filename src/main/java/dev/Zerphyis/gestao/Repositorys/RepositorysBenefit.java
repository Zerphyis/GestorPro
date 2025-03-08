package dev.Zerphyis.gestao.Repositorys;

import dev.Zerphyis.gestao.Entity.Benefit.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorysBenefit extends JpaRepository<Benefit,Long> {
}
