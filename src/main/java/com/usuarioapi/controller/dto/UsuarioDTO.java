package com.usuarioapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
        @NotBlank(message = "campo Obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String nome,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String email,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 4, max = 255, message = "Campo fora do tamanho padrão")
        String senha)
{
}
