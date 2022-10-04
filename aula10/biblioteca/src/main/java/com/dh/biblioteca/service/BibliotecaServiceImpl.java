package com.dh.biblioteca.service;

import com.dh.biblioteca.Biblioteca;
import com.dh.biblioteca.dto.BibliotecaDTO;
import com.dh.biblioteca.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;

    @Override
    public void save(BibliotecaDTO dto) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNome(dto.getNome());
        repository.save(biblioteca);
    }

    @Override
    public Long findByNome(String nome) {
        var biblioteca = repository.findByNome(nome);
        return biblioteca.getId();
    }
}
