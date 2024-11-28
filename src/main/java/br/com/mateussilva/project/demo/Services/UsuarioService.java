package br.com.mateussilva.project.demo.Services;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mateussilva.project.demo.Entity.Usuario;
import br.com.mateussilva.project.demo.Repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Carrega um usuário pelo nome de usuário para o Spring Security
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Retorna um objeto User do Spring Security com as informações do banco de dados
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER") // Papel fixo (pode ser customizado)
                .build();
    }

    /**
     * Salva um novo usuário com a senha criptografada
     */
    public Usuario salvar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); // Criptografa a senha
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca um usuário pelo nome de usuário
     */
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

     
}
