package com.simplon.recyclascore.config.security;

import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 * Filtre pour vérifier le token
 */
@Service
public class JwtFilter extends OncePerRequestFilter {

  private final IUtilisateurService utilisateurService;
  private final JwtService jwtService;

  /**
   *
   * @param utilisateurService
   * @param jwtService
   */
  public JwtFilter(IUtilisateurService utilisateurService, JwtService jwtService) {
    this.utilisateurService = utilisateurService;
    this.jwtService = jwtService;
  }

  /**
   * Vérifie le token et l'authentifie
   * @param request
   * @param response
   * @param filterChain
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = null;
    String username = null;
    boolean isTokenExpired = true;

    final String authorization = request.getHeader("Authorization");
    if (authorization != null && authorization.startsWith("Bearer ")) {
      token = authorization.substring(7);
      isTokenExpired = jwtService.isTokenExpired(token);
      username = jwtService.lireUsername(token);
    }

    if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = utilisateurService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }
}
