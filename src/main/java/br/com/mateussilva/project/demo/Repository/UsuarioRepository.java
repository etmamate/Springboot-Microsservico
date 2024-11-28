package br.com.mateussilva.project.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mateussilva.project.demo.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}