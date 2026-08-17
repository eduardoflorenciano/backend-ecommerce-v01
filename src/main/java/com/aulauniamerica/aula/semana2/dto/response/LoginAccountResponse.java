package com.aulauniamerica.aula.semana2.dto.response;

import com.aulauniamerica.aula.semana2.entity.Login;

public record LoginAccountResponse(
        String nome,
        String mensagem)
{
    public static LoginAccountResponse de(Login login){
        return new LoginAccountResponse(
                login.getNome(),
                "k"
        );
    }
}
