package com.dh.jogos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "jogos")
@Data
public class Jogos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long idBiblioteca;
}
