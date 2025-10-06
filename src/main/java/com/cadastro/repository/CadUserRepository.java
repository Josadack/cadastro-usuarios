package com.cadastro.repository;

import com.cadastro.model.CadastroUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadUserRepository extends JpaRepository<CadastroUser, Long> {
    Page<CadastroUser> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
