package com.dh.biblioteca.repository;

import com.dh.biblioteca.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

    Biblioteca findByNome(String nome);
}
