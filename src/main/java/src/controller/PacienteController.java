package src.controller;

import src.model.Paciente;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacienteController {
    private Map<Integer, Paciente> pacientes = new HashMap<>();

    public boolean criar(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
        return true;
    }

    public Paciente buscar(int id) {
        return pacientes.get(id);
    }

    public boolean atualizar(int id, Paciente pacienteAtualizado) {
        if (pacientes.containsKey(id)) {
            Paciente paciente = pacientes.get(id);
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setCpf(pacienteAtualizado.getCpf());
            paciente.setEmail(pacienteAtualizado.getEmail());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            paciente.setEndereco(pacienteAtualizado.getEndereco());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return pacientes.remove(id) != null;
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes.values());
    }

    public boolean existe(int id) {
        return pacientes.containsKey(id);
    }
}