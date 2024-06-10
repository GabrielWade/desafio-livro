package com.example.demo.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collection;
import java.util.List;

public class ConverteDados implements IConverteDados{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterDadosLista(String json, Class<T> classe) {
        CollectionType Lista = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return objectMapper.readValue(json, Lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
