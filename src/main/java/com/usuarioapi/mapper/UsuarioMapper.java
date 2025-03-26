package com.usuarioapi.mapper;

import com.usuarioapi.controller.dto.UsuarioDTO;
import com.usuarioapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario usuario);
}
