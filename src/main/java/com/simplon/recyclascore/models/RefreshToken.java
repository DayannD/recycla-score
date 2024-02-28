package com.simplon.recyclascore.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "`RefreshToken`")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  private Instant expiryDate;

  @OneToOne
  @JoinColumn(name = "ID_Utilisateur", referencedColumnName = "ID_Utilisateur")
  private Utilisateur user;
}
