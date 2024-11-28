package br.com.mateussilva.project.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mateussilva.project.demo.Entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
} 
