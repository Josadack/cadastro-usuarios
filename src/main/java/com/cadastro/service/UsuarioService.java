package com.cadastro.service;

import com.cadastro.dtos.UsuarioDetalheDTO;
import com.cadastro.dtos.UsuarioListDTO;
import com.cadastro.dtos.UsuarioRequestDTO;
import com.cadastro.dtos.UsuarioResponseDTO;
import com.cadastro.exception.ResourceNotFoundException;
import com.cadastro.model.CadastroUser;
import com.cadastro.repository.CadUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private CadUserRepository userRepository;

    //Criar Usuario
    public UsuarioResponseDTO criar(UsuarioRequestDTO userDtos){
        CadastroUser user = new CadastroUser();
        user.setNome(userDtos.nome());
        user.setFoto(userDtos.foto());
        user.setEmail(userDtos.email());
        user.setTelefone(userDtos.telefone());

        CadastroUser salvo = userRepository.save(user);
        return new UsuarioResponseDTO(salvo);
    }

    //Listar todos usuarios com os atributos do DTOS, com Paginação
    public Page<UsuarioListDTO> listarTodos(String nome, Pageable pageable){

        Page<CadastroUser> usuarios = (nome == null || nome.isEmpty())
                ? userRepository.findAll(pageable)
                : userRepository.findByNomeContainingIgnoreCase(nome, pageable);

        return usuarios
                .map( us -> new UsuarioListDTO(us.getNome(), us.getFoto()));
    }

    //Buscar pelo ID com o retorno do DTO.
    public UsuarioDetalheDTO buscarPorId(Long id){
        CadastroUser usuario = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado!"));

        return new UsuarioDetalheDTO(usuario.getNome(), usuario.getFoto(), usuario.getEmail(), usuario.getTelefone());
    }

    //Atualizar usuario
    @Transactional
    public CadastroUser atualizar(Long id, CadastroUser user){
        return userRepository.findById(id)
                .map(userExistente -> {
                    userExistente.setNome(user.getNome());
                    userExistente.setFoto(user.getFoto());
                    userExistente.setEmail(user.getEmail());
                    userExistente.setTelefone(user.getTelefone());
                    return userRepository.save(userExistente);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado!"));
    }

    //Deletar
    @Transactional
    public void deletar(Long id){
        CadastroUser userDeletar = userRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Usuário com ID " + id + " não encontrado!"));
        userRepository.delete(userDeletar);
    }
}
