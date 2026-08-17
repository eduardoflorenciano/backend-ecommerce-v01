package com.aulauniamerica.aula.semana2.dto.response;

import com.aulauniamerica.aula.semana2.entity.Login;

public record LoginDisponiveisResponse(
        Long id,
        String nome,
        Boolean status,
        String telefone
) {
    public static LoginDisponiveisResponse de(Login login) {
        return new LoginDisponiveisResponse(
                login.getId(),
                login.getNome(),
                login.getStatus(),
                login.getTelefone()
        );
    }
}