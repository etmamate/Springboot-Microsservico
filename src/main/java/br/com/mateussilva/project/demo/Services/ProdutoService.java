package br.com.mateussilva.project.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mateussilva.project.demo.Entity.Produto;
import br.com.mateussilva.project.demo.Repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(Long id){
        return produtoRepository.findById(id);
    }

    public Produto editar(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isEmpty()) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }

        Produto produto = produtoExistente.get();
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setDescricao(produtoAtualizado.getDescricao());

        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }
}
