package com.cadastro.repository;

import com.cadastro.model.CadastroUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CadUserRepository extends JpaRepository<CadastroUser, Long> {


    @Query("SELECT u.idade AS faixa, COUNT(u) AS quantidade FROM CadastroUser u GROUP BY u.idade")
    List<Object[]> contarUsuariosPorFaixaEtaria();
}
