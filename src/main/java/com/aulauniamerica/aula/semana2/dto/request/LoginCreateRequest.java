package com.aulauniamerica.aula.semana2.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCreateRequest {
    private String nome;
    private String telefone;
    private String senha;
    private String confirmarSenha;
}
