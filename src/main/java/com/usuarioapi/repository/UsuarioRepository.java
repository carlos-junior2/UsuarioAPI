package com.usuarioapi.repository;

import com.usuarioapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
