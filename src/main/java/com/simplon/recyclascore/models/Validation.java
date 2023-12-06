package com.simplon.recyclascore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Validation")
public class Validation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Instant creationDate;
  private Instant expire;
  private Instant Activation;
  private String code;

  @OneToOne(cascade = CascadeType.ALL)
  private Utilisateur utilisateur;


}
