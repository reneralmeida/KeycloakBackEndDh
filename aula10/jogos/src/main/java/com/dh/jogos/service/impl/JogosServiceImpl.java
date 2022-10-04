package com.dh.jogos.service.impl;

import com.dh.jogos.dto.JogosDTO;
import com.dh.jogos.entity.Jogos;
import com.dh.jogos.repository.JogosRepository;
import com.dh.jogos.service.BibliotecaFeign;
import com.dh.jogos.service.JogosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogosServiceImpl implements JogosService {

    @Autowired
    private BibliotecaFeign biblioteca;

    @Autowired
    private JogosRepository repository;

    @Override
    public void save(JogosDTO dto) {
        Jogos jogos = new Jogos();
        jogos.setNome(dto.getNome());
        jogos.setIdBiblioteca(biblioteca.findByNome(dto.getNomeBiblioteca()));
        repository.save(jogos);
    }

    @Override
    public JogosDTO findByBiblioteca(String nomeBiblioteca) {
        return null;
    }
}
