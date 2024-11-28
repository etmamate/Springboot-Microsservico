package br.com.mateussilva.project.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateussilva.project.demo.Entity.Produto;
import br.com.mateussilva.project.demo.Services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar(){
        return produtoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvar(produto);
        return ResponseEntity.ok(novoProduto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para editar um produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProduto(
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado) {
        Produto produtoEditado = produtoService.editar(id, produtoAtualizado);
        return ResponseEntity.ok(produtoEditado);
    }

    // Método para excluir um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
