package com.cadastro.dtos;

import com.cadastro.model.CadastroUser;

public record UsuarioResponseDTO(Long id, String nome, String foto, String email, String telefone, Integer idade) {

    public UsuarioResponseDTO(CadastroUser user){
        this(user.getId(), user.getNome(), user.getFoto(), user.getEmail(), user.getTelefone(), user.getIdade());
    }
}

