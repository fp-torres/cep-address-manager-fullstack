package com.torres.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank(message = "Nome is required")
    @Size(min = 3, max = 100, message = "Name must have between 3 and 100 characters")
    private String nome;

    @NotBlank(message = "CPF is required")
    @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
    private String cpf;

    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{8}", message = "CEP must contain exactly 8 digits")
    private String cep;

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getCep() { return cep; }
}