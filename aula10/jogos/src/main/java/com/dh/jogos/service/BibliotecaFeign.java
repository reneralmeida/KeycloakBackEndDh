package com.dh.jogos.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "BIBLIOTECA")
public interface BibliotecaFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/biblioteca/{nome}")
    Long findByNome(@PathVariable(value = "nome") String nomeBiblioteca);

}
