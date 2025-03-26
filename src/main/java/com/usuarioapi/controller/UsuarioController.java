package com.usuarioapi.controller;

import com.usuarioapi.controller.dto.UsuarioDTO;
import com.usuarioapi.mapper.UsuarioMapper;
import com.usuarioapi.model.Usuario;
import com.usuarioapi.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        System.out.println(usuario);
        usuarioService.salvar(usuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> lista = usuarioService.listar();
        System.out.println("Entrou no método Listar Todos os usuários");
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable("id") String id){
        UUID idUsuario = UUID.fromString(id);
        return usuarioService
                .buscarPorId(idUsuario)
                .map(usuario -> {
                    UsuarioDTO dto = usuarioMapper.toDTO(usuario);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(required = false) String nome){
        List<Usuario> lista = usuarioService.buscar(nome);
        System.out.println("Entrou no método buscar usuário");
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable("id") String id, @RequestBody UsuarioDTO usuarioDTO){
         UUID idUsuario = UUID.fromString(id);
         Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
         if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
         }

         Usuario usuario = usuarioOptional.get();
         usuario.setNome(usuarioDTO.nome());
         usuario.setEmail(usuarioDTO.email());

         usuarioService.atualizar(usuario);

         return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPorId(@PathVariable("id") String id){
        UUID idUsuario = UUID.fromString(id);
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(idUsuario);
        usuarioService.deletar(usuarioOptional.get());
        return ResponseEntity.noContent().build();
    }
}
