package com.simplon.recyclascore.config.security;

import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpResponseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 * Filtre pour vérifier le token
 */
@Service
@Slf4j
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
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String token = null;
    String username = null;
    boolean isTokenExpired = false;

    final Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("token")) {
          token = cookie.getValue();
          try {
            isTokenExpired = jwtService.isTokenExpired(token);
          } catch (Exception e) {
            log.error("Error while checking if token is expired", e);
            throw new HttpResponseException(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
          }
          username = jwtService.lireUsername(token);
        }
      }
    }

    if (isTokenExpired) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expiré ou invalide");
    }
    if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = utilisateurService.loadUserByUsername(username);
      log.warn("UserDetails : " + userDetails.getAuthorities());
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }
}
