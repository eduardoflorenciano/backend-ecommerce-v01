package com.aulauniamerica.aula.semana2.repository;

import com.aulauniamerica.aula.semana2.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    List<Login> findByStatus(Boolean status);
    Optional<Login> findByTelefone(String telefone);
}