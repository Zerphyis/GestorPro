package dev.Zerphyis.gestao.Repositorys;

import dev.Zerphyis.gestao.Entity.Login.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryLogin extends JpaRepository<Login,Long> {
    Optional<Login> findByEmail(String email);
}
