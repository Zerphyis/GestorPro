package dev.Zerphyis.gestao.Security;

import dev.Zerphyis.gestao.Service.ServiceLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private ServiceToken tokenService;

    @Autowired
    private ServiceLogin loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            String login = tokenService.validateToken(token);
            if (!login.isEmpty()) {
                UserDetails user = loginService.loadUserByUsername(login);
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    System.out.println("Usuário não encontrado: " + login);
                }
            } else {
                System.out.println("Token inválido ou expirado.");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        String[] parts = authHeader.split(" ");
        if (parts.length == 2 && "Bearer".equalsIgnoreCase(parts[0])) {
            return parts[1].trim();
        }
        return null;
    }
}
