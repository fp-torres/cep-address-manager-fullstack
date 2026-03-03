package com.torres.backend.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.torres.backend.entity.Usuario;
import com.torres.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario criar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dados.getNome());
        usuario.setCpf(dados.getCpf());
        usuario.setCep(dados.getCep());
        usuario.setLogradouro(dados.getLogradouro());
        usuario.setBairro(dados.getBairro());
        usuario.setCidade(dados.getCidade());
        usuario.setEstado(dados.getEstado());

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}