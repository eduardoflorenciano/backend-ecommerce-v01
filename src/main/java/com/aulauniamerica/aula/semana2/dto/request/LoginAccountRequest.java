package com.aulauniamerica.aula.semana2.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAccountRequest {
    private String nome;
    private String telefone;
    private String senha;
}
