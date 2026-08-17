package com.aulauniamerica.aula.semana2.dto.response;

import com.aulauniamerica.aula.semana2.entity.Login;

public record LoginUpdateResponse(
        Long id,
        String nome,
        String telefone,
        String mensagem
) {
    public static LoginUpdateResponse de(Login login) {
        return new LoginUpdateResponse(
                login.getId(),
                login.getNome(),
                login.getTelefone(),
                "Dados do Usuario " + login.getNome() + " atualizado com sucesso!"
        );

    }
}
