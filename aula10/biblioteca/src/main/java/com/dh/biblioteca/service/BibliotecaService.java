package com.dh.biblioteca.service;

import com.dh.biblioteca.dto.BibliotecaDTO;

public interface BibliotecaService {
    void save(BibliotecaDTO dto);
    Long findByNome(String nome);
}
