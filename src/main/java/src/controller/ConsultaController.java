package src.controller;

import src.model.Consulta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultaController {
    private Map<Integer, Consulta> consultas = new HashMap<>();

    public boolean criar(Consulta consulta) {
        consultas.put(consulta.getId(), consulta);
        return true;
    }

    public Consulta buscar(int id) {
        return consultas.get(id);
    }

    public boolean atualizar(int id, Consulta consultaAtualizada) {
        if (consultas.containsKey(id)) {
            Consulta consulta = consultas.get(id);
            consulta.setDataHora(consultaAtualizada.getDataHora());
            consulta.setPaciente(consultaAtualizada.getPaciente());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return consultas.remove(id) != null;
    }

    public boolean confirmar(int id) {
        Consulta consulta = consultas.get(id);
        if (consulta != null) {
            consulta.confirmar();
            return true;
        }
        return false;
    }

    public boolean cancelar(int id) {
        Consulta consulta = consultas.get(id);
        if (consulta != null) {
            consulta.cancelar();
            return true;
        }
        return false;
    }

    public boolean reagendar(int id, LocalDateTime novaDataHora) {
        Consulta consulta = consultas.get(id);
        if (consulta != null) {
            consulta.reagendar(novaDataHora);
            return true;
        }
        return false;
    }

    public List<Consulta> listarTodos() {
        return new ArrayList<>(consultas.values());
    }

    public boolean existe(int id) {
        return consultas.containsKey(id);
    }
}