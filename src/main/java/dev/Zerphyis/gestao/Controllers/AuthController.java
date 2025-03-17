package dev.Zerphyis.gestao.Controllers;

import dev.Zerphyis.gestao.Entity.Data.Auth.AutenticationData;
import dev.Zerphyis.gestao.Entity.Data.Auth.LoginData;
import dev.Zerphyis.gestao.Entity.Data.Auth.ReponseToken;
import dev.Zerphyis.gestao.Entity.Login.Login;
import dev.Zerphyis.gestao.Security.ServiceToken;
import dev.Zerphyis.gestao.Service.ServiceLogin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthController {
    @Autowired
    private ServiceLogin serviceLogin;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceToken tokenService;

    @PostMapping("/register")
    public AutenticationData registerUser(@RequestBody @Valid Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        Login savedPerson = serviceLogin.createPerson(login);
        return new AutenticationData(savedPerson.getEmail(), savedPerson.getPassword(), savedPerson.getRole());
    }

    @PostMapping("/auth")
    public ResponseEntity<ReponseToken> login(@RequestBody LoginData loginRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Login authenticatedPerson = (Login) authentication.getPrincipal();

        if (authenticatedPerson.getEmail().equals(loginRequest.email())) {
            String token = tokenService.generateToken(authenticatedPerson);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ReponseToken(token));
        }

        // Retorna 401 se o email não corresponder
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}


