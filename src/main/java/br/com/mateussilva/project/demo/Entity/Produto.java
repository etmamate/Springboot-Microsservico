package br.com.mateussilva.project.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidade;

    private String descricao;
}
