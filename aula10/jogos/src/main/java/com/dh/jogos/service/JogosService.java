package com.dh.jogos.service;

import com.dh.jogos.dto.JogosDTO;

public interface JogosService {
    void save(JogosDTO dto);
    JogosDTO findByBiblioteca(String nomeBiblioteca);
}
