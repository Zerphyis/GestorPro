package dev.Zerphyis.gestao.Repositorys;

import dev.Zerphyis.gestao.Entity.ElectronicPoint.EletronicPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryElotronicPoints  extends JpaRepository<EletronicPoint, Long> {
}
