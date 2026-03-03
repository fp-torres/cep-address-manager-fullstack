package com.torres.backend.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.torres.backend.entity.Usuario;
import com.torres.backend.repository.UsuarioRepository;
import com.torres.backend.dto.UsuarioRequestDTO;
import com.torres.backend.dto.ViaCepResponse;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ViaCepService viaCepService;

    public UsuarioService(UsuarioRepository repository, ViaCepService viaCepService) {
        this.repository = repository;
        this.viaCepService = viaCepService;
    }

    // =========================
    // CREATE
    // =========================
    public Usuario criar(UsuarioRequestDTO dto) {

        if (repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF already registered");
        }

        ViaCepResponse endereco = viaCepService.buscarEndereco(dto.getCep());

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setCep(dto.getCep());
        usuario.setLogradouro(endereco.getLogradouro());
        usuario.setBairro(endereco.getBairro());
        usuario.setCidade(endereco.getLocalidade());
        usuario.setEstado(endereco.getUf());

        return repository.save(usuario);
    }

    // =========================
    // READ
    // =========================
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // =========================
    // UPDATE
    // =========================
    public Usuario atualizar(Long id, UsuarioRequestDTO dto) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica CPF duplicado (caso esteja alterando CPF)
        if (!usuario.getCpf().equals(dto.getCpf()) &&
                repository.existsByCpf(dto.getCpf())) {

            throw new RuntimeException("CPF already registered");
        }

        ViaCepResponse endereco = viaCepService.buscarEndereco(dto.getCep());

        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setCep(dto.getCep());
        usuario.setLogradouro(endereco.getLogradouro());
        usuario.setBairro(endereco.getBairro());
        usuario.setCidade(endereco.getLocalidade());
        usuario.setEstado(endereco.getUf());

        return repository.save(usuario);
    }

    // =========================
    // DELETE
    // =========================
    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }

        repository.deleteById(id);
    }
}