package com.cadastro.controller;

import com.cadastro.dtos.UsuarioDetalheDTO;
import com.cadastro.dtos.UsuarioListDTO;
import com.cadastro.dtos.UsuarioRequestDTO;
import com.cadastro.dtos.UsuarioResponseDTO;
import com.cadastro.model.CadastroUser;
import com.cadastro.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO userDto){
        UsuarioResponseDTO user = usuarioService.criar(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioListDTO>> listar(@RequestParam(required = false) String nome,
                                                       @PageableDefault(size = 10, sort = "nome") Pageable pageable){
        return ResponseEntity.ok(usuarioService.listarTodos(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalheDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastroUser> atualizar(@PathVariable Long id, @RequestBody CadastroUser userAtual){
        return ResponseEntity.ok(usuarioService.atualizar(id, userAtual));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}