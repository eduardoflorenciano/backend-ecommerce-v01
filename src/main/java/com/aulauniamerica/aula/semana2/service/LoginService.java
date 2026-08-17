package com.aulauniamerica.aula.semana2.service;

import com.aulauniamerica.aula.semana2.dto.request.LoginAccountRequest;
import com.aulauniamerica.aula.semana2.dto.request.LoginCreateRequest;
import com.aulauniamerica.aula.semana2.dto.request.LoginUpdateRequest;
import com.aulauniamerica.aula.semana2.dto.response.LoginAccountResponse;
import com.aulauniamerica.aula.semana2.entity.Login;
import com.aulauniamerica.aula.semana2.repository.LoginRepository;
import com.aulauniamerica.aula.semana2.dto.response.LoginCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginCreateResponse salvar(LoginCreateRequest loginCreatRequest) {
        if (!loginCreatRequest.getSenha().equals(loginCreatRequest.getConfirmarSenha())) {
            throw new IllegalArgumentException("As senhas não coincidem!");
        }

        Login login = new Login();
        login.setNome(loginCreatRequest.getNome());
        login.setTelefone(loginCreatRequest.getTelefone());
        login.setSenha(loginCreatRequest.getSenha());
        login.setStatus(true);

        Login loginSalvo = this.loginRepository.save(login);

        return LoginCreateResponse.de(loginSalvo);
    }

    public Login buscarPorId(Long id){
        return this.loginRepository.findById(id)
                .orElseThrow( () ->new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Login não encontrado com id: " + id));
    }



    public Login atualizar(Long id, LoginUpdateRequest loginUpdateRequest) {
        Login login = this.buscarPorId(id);

        if (loginUpdateRequest.getNome() != null) {
            login.setNome(loginUpdateRequest.getNome());
        }
        if (loginUpdateRequest.getTelefone() != null) {
            login.setTelefone(loginUpdateRequest.getTelefone());
        }

        return this.loginRepository.save(login);
    }

    public void deletarPorId(Long id){
        Login login = this.buscarPorId(id);
        this.loginRepository.delete(login);
    }

    public List<Login> buscarDisponiveis(Boolean status){
        return this.loginRepository.findByStatus(status);
    }

    public LoginAccountResponse validarLogin(LoginAccountRequest loginAccountRequest){
        Login login = this.loginRepository.findByTelefone(loginAccountRequest.getTelefone())
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Telefone informado está incorreto"));
        if (!login.getStatus()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Conta desativada");
        }
        if (!login.getSenha().equals(loginAccountRequest.getSenha())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha está incorreta");
        }
        if(!login.getNome().equals(loginAccountRequest.getNome())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nome inserido está incorreto");
        }

        return new LoginAccountResponse(login.getNome(), "Login realizado com sucesso!");
    }
}
