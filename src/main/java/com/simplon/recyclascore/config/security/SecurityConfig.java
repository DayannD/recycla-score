package com.simplon.recyclascore.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuration de la sécurité
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final ConfigurationCryptageMotDePasse cryptageMotDePasse;
  /**
   * Constructeur
   * @param jwtFilter
   * @param cryptageMotDePasse
   */
  public SecurityConfig(JwtFilter jwtFilter, ConfigurationCryptageMotDePasse cryptageMotDePasse) {
    this.jwtFilter = jwtFilter;
    this.cryptageMotDePasse = cryptageMotDePasse;
  }

  /**
   * Configure les règles de CORS
   */
  @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                  .allowedOrigins("http://localhost:4200")
                  .allowedMethods("*");
            }
        };
    }

    /**
     * Configure les règles de sécurité
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
          httpSecurity
            // Disable CSRF protection since tokens are immune to it
            // CSRF cross-site request forgery (falsification de requête intersite)
            .csrf(AbstractHttpConfigurer::disable)
            // Authorise les requêtes
            .authorizeHttpRequests(
              authorize ->
                //authorise tout le monde à accéder à la page d'inscription
                authorize
                  .requestMatchers("/inscription").permitAll()
                  .requestMatchers("/connexion").permitAll()
                  .requestMatchers("/activation").permitAll()
                  .anyRequest().authenticated())
            //
            .sessionManagement(sessionManagement ->
              sessionManagement
                // Disable session creation
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Ajoute le filtre JWT avant le filtre d'authentification ce qui permet de vérifier le token avant de vérifier l'authentification
            // et donc de ne pas avoir à vérifier l'authentification si le token est invalide
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    /**
     * Configure l'authentification
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

  /**
   *
   * @param userDetailsService
   * @return
   */
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(this.cryptageMotDePasse.passwordEncoder());
        return daoAuthenticationProvider;
    }

}
