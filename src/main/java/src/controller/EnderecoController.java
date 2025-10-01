package src.controller;

import src.model.Endereco;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnderecoController {
    private Map<Integer, Endereco> enderecos = new HashMap<>();

    public boolean criar(Endereco endereco) {
        enderecos.put(endereco.getId(), endereco);
        return true;
    }

    public Endereco buscar(int id) {
        return enderecos.get(id);
    }

    public boolean atualizar(int id, Endereco enderecoAtualizado) {
        if (enderecos.containsKey(id)) {
            Endereco endereco = enderecos.get(id);
            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setCep(enderecoAtualizado.getCep());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return enderecos.remove(id) != null;
    }

    public List<Endereco> listarTodos() {
        return new ArrayList<>(enderecos.values());
    }

    public boolean existe(int id) {
        return enderecos.containsKey(id);
    }
}