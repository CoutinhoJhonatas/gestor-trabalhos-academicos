package com.git.gestor_academico.controllers;

import com.git.gestor_academico.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
@AllArgsConstructor
public class EmailTestController {

    private final EmailService emailService;

    @GetMapping
    public String test() {
        emailService.sendSimpleMessage(
                "jhonatas.cjw@gmail.com",
                "Modificações no trabalho GESTOR DE TRABALHOS ACADÊMICOS",
                "Trabalho com o tema: GESTOR DE TRABALHOS ACADÊMICOS enviado para verificação.");

        return "Enviado";
    }

}
