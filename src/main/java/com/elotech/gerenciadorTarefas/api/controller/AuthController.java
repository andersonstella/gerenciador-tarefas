package com.elotech.gerenciadorTarefas.api.controller;

import com.elotech.gerenciadorTarefas.api.dto.auth.LoginRequestDTO;
import com.elotech.gerenciadorTarefas.api.dto.auth.LoginResponseDTO;
import com.elotech.gerenciadorTarefas.application.auth.AutenticacaoApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AutenticacaoApplicationService autenticacaoApplicationService;

    public AuthController(final AutenticacaoApplicationService autenticacaoApplicationService) {
        this.autenticacaoApplicationService = autenticacaoApplicationService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody final LoginRequestDTO request) {

        final String token = autenticacaoApplicationService.autenticar(request.email(), request.senha());

        return new LoginResponseDTO(token);
    }

}
