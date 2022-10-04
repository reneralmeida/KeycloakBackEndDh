package com.dh.biblioteca.controller;

import com.dh.biblioteca.dto.BibliotecaDTO;
import com.dh.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaService service;

    @PostMapping()
    public ResponseEntity<Void> save(@RequestBody() BibliotecaDTO dto){
        service.save(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{nome}")
    public Long findByNome(@PathVariable String nome){
        return service.findByNome(nome);
    }
}
