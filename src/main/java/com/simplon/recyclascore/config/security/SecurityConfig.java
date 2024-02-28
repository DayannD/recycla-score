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
   *
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
          .allowedHeaders("*")
          .allowedMethods("*")
          .exposedHeaders("Authorization")
          .allowCredentials(true);
        System.out.println("CORS configuration applied: " + registry.toString());
      }
    };
  }


  /**
   * Configure les règles de sécurité
   *
   * @param httpSecurity
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return
      httpSecurity
        .cors().and()
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
          authorize ->
            authorize
              .requestMatchers("/inscription").permitAll()
              .requestMatchers("/connexion").permitAll()
              .requestMatchers("/refresh-token").permitAll()
              .requestMatchers("/activation").permitAll()
              .requestMatchers("/deconnexion").permitAll()
              .requestMatchers("api/admin").hasRole("ADMIN")
              .requestMatchers("api/aws").hasRole("ADMIN")
              .anyRequest().authenticated())
        .sessionManagement(sessionManagement ->
          sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /**
   * Configure l'authentification
   *
   * @param authenticationConfiguration
   * @return
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * @param userDetailsService
   * @return
   */
  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    System.out.println("userDetailsService: " + userDetailsService);
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    daoAuthenticationProvider.setPasswordEncoder(this.cryptageMotDePasse.passwordEncoder());
    return daoAuthenticationProvider;
  }
}
