package dev.Zerphyis.gestao.Repositorys;

import dev.Zerphyis.gestao.Entity.Position.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPosition extends JpaRepository<Position,Long> {
}
