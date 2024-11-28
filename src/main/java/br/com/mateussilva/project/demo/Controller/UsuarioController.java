package br.com.mateussilva.project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateussilva.project.demo.Entity.Usuario;
import br.com.mateussilva.project.demo.Services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.salvar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }
}
