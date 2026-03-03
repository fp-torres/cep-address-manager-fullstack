package com.torres.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.torres.backend.dto.ViaCepResponse;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ViaCepResponse buscarEndereco(String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        ViaCepResponse response =
                restTemplate.getForObject(url, ViaCepResponse.class);

        if (response == null || Boolean.TRUE.equals(response.getErro())) {
            throw new RuntimeException("CEP inválido");
        }

        return response;
    }
}