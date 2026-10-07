package com.scarlet.demoApiRest.service;

import com.scarlet.demoApiRest.config.JwtService;
import com.scarlet.demoApiRest.dto.AuthResponse;
import com.scarlet.demoApiRest.dto.LoginRequest;
import com.scarlet.demoApiRest.dto.RegisterRequest;
import com.scarlet.demoApiRest.dto.UtilisateurResponse;
import com.scarlet.demoApiRest.entity.Utilisateur;
import com.scarlet.demoApiRest.exception.BusinessRuleException;
import com.scarlet.demoApiRest.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Transactional
    public UtilisateurResponse register(RegisterRequest req) {

        String email = req.email().trim().toLowerCase();

        if (utilisateurRepository.existsByEmail(email)) {
            throw new BusinessRuleException("Un compte existe déjà avec cet email.");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(passwordEncoder.encode(req.motDePasse()));
        // Le rôle n'est PAS fourni par le client : il vaut USER par défaut.

        Utilisateur sauve = utilisateurRepository.save(utilisateur);
        return new UtilisateurResponse(sauve.getId(), sauve.getEmail(), sauve.getRole());
    }

    public AuthResponse login(LoginRequest req) {
        String email = req.email().trim().toLowerCase();

        // Vérifie email + mot de passe ; lève BadCredentialsException si c'est faux
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, req.motDePasse()));

        UserDetails user = userDetailsService.loadUserByUsername(email);
        return new AuthResponse(jwtService.genererToken(user), "Bearer");
    }
}
