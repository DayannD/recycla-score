package com.simplon.recyclascore.controller;

import com.simplon.recyclascore.config.security.JwtService;

import com.simplon.recyclascore.services.IServices.IUtilisateurService;
import com.simplon.recyclascore.services.IServices.IValidationService;
import com.simplon.recyclascore.web.controller.UserController;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private IUtilisateurService utilisateurService;

  @Mock
  private IValidationService validationService;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtService jwtService;

  @Mock
  private HttpServletResponse response;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

//  @Test
//  public void createUtilisateur_HappyPath() throws MessagingException {
//    Utilisateur utilisateur = new Utilisateur();
//    doNothing().when(utilisateurService).createUtilisateur(utilisateur);
//    userController.createUtilisateur(utilisateur);
//    verify(utilisateurService, times(1)).createUtilisateur(utilisateur);
//  }
//
//  @Test
//  public void activateUtilisateur_HappyPath() {
//    String code = "1234";
//    doNothing().when(validationService).activateUtilisateur(code);
//    userController.activateUtilisateur(code);
//    verify(validationService, times(1)).activateUtilisateur(code);
//  }
//
//  @Test
//  public void connexionUtilisateur_HappyPath() {
//    ConnexionUserDTO connexionUserDTO = new ConnexionUserDTO("username", "password");
//    Authentication auth = mock(Authentication.class);
//    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
//    when(auth.isAuthenticated()).thenReturn(true);
//    when(utilisateurService.actifCompte(connexionUserDTO.username())).thenReturn(true);
//    Map<String, String> token = new HashMap<>();
//    token.put("Bearer", "token");
//    when(jwtService.createToken(connexionUserDTO.username())).thenReturn(token);
//    userController.connexionUtilisateur(connexionUserDTO, response);
//    verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
//    verify(utilisateurService, times(1)).actifCompte(connexionUserDTO.username());
//    verify(jwtService, times(1)).createToken(connexionUserDTO.username());
//  }
//
//  @Test
//  public void deconnexionUtilisateur_HappyPath() {
//    userController.deconnexionUtilisateur(response);
//    verify(response, times(1)).addCookie(any(Cookie.class));
//  }
}
