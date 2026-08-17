package com.aulauniamerica.aula.semana2.dto.response;

import com.aulauniamerica.aula.semana2.entity.Login;

public record LoginCreateResponse(
        Long id,
        String nome,
        String mensagem
) {
    public static LoginCreateResponse de(Login login) {
        return new LoginCreateResponse(
                login.getId(),
                login.getNome(),
                "Usuário cadastrado com sucesso!"
        );
    }
}