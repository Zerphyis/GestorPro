package dev.Zerphyis.gestao.Service;

import dev.Zerphyis.gestao.Entity.Login.Login;
import dev.Zerphyis.gestao.Repositorys.RepositoryLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogin implements UserDetailsService {
    @Autowired
    RepositoryLogin repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        return login;
    }

    public Login createPerson(Login person) {
        return repository.save(person);
    }
}
