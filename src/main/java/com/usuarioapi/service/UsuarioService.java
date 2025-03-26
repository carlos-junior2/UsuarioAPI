package com.usuarioapi.service;

import com.usuarioapi.model.Usuario;
import com.usuarioapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public void salvar(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscar(String nome){
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }

    public void atualizar(Usuario usuario){
        if(usuario.getId() == null){
            throw new IllegalArgumentException("Autor não encontrado!");
        }
        usuarioRepository.save(usuario);
    }

    public void deletar(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
}
