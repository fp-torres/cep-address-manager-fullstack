package com.torres.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.torres.backend.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

}