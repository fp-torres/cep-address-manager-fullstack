package com.torres.backend.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.torres.backend.entity.Usuario;
import com.torres.backend.repository.UsuarioRepository;
import com.torres.backend.dto.ViaCepResponse;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ViaCepService viaCepService;

    public UsuarioService(UsuarioRepository repository, ViaCepService viaCepService) {
        this.repository = repository;
        this.viaCepService = viaCepService;
    }

    public Usuario criar(Usuario usuario) {

        // Busca endereço automaticamente pelo CEP
        ViaCepResponse endereco = viaCepService.buscarEndereco(usuario.getCep());

        usuario.setLogradouro(endereco.getLogradouro());
        usuario.setBairro(endereco.getBairro());
        usuario.setCidade(endereco.getLocalidade());
        usuario.setEstado(endereco.getUf());

        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario atualizar(Long id, Usuario dados) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza endereço automaticamente caso o CEP mude
        ViaCepResponse endereco = viaCepService.buscarEndereco(dados.getCep());

        usuario.setNome(dados.getNome());
        usuario.setCpf(dados.getCpf());
        usuario.setCep(dados.getCep());
        usuario.setLogradouro(endereco.getLogradouro());
        usuario.setBairro(endereco.getBairro());
        usuario.setCidade(endereco.getLocalidade());
        usuario.setEstado(endereco.getUf());

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}