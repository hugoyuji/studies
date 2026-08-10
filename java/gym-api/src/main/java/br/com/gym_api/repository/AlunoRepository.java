package br.com.gym_api.repository;

import br.com.gym_api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByEmail (String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
