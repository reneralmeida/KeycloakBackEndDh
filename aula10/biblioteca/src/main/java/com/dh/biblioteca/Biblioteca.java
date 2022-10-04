package com.dh.biblioteca;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="biblioteca")
@Data
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
