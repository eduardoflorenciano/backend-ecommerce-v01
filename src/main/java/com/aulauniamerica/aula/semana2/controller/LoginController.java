package com.aulauniamerica.aula.semana2.controller;

import com.aulauniamerica.aula.semana2.dto.request.LoginAccountRequest;
import com.aulauniamerica.aula.semana2.dto.request.LoginCreateRequest;
import com.aulauniamerica.aula.semana2.dto.request.LoginUpdateRequest;
import com.aulauniamerica.aula.semana2.dto.response.LoginAccountResponse;
import com.aulauniamerica.aula.semana2.dto.response.LoginCreateResponse;
import com.aulauniamerica.aula.semana2.dto.response.LoginDisponiveisResponse;
import com.aulauniamerica.aula.semana2.dto.response.LoginUpdateResponse;
import com.aulauniamerica.aula.semana2.entity.Login;
import com.aulauniamerica.aula.semana2.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/criar/usuarios")
    public ResponseEntity<LoginCreateResponse> salvar(@RequestBody LoginCreateRequest loginCreateRequest){
        LoginCreateResponse response = this.loginService.salvar(loginCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LoginUpdateResponse> atualizar(
            @PathVariable Long id,
            @RequestBody LoginUpdateRequest loginUpdateRequest
    ) {
        Login loginAtualizado = this.loginService.atualizar(id, loginUpdateRequest);
        return ResponseEntity.ok(LoginUpdateResponse.de(loginAtualizado));
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<LoginDisponiveisResponse> buscarPorId(@PathVariable Long id) {
        Login buscaUsuario = this.loginService.buscarPorId(id);
        return ResponseEntity.ok(LoginDisponiveisResponse.de(buscaUsuario));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<LoginDisponiveisResponse>> buscarDisponiveis(@RequestParam(defaultValue = "true") Boolean status)  {
        List<LoginDisponiveisResponse> disponiveis = this.loginService.buscarDisponiveis(status)
                .stream()
                .map(LoginDisponiveisResponse::de)
                .toList();
        return new ResponseEntity<>(disponiveis, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoginAccountResponse>usuarioLogin(@RequestBody LoginAccountRequest loginAccountRequest){
        LoginAccountResponse loginAccount = this.loginService.validarLogin(loginAccountRequest);
        return new ResponseEntity<>(loginAccount, HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        this.loginService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
