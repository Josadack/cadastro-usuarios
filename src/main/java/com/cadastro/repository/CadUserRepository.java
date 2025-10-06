package com.cadastro.repository;

import com.cadastro.model.CadastroUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CadUserRepository extends JpaRepository<CadastroUser, Long> {

    Page<CadastroUser> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT u.idade AS faixa, COUNT(u) AS quantidade FROM CadastroUser u GROUP BY u.idade")
    List<Object[]> contarUsuariosPorFaixaEtaria();

}
