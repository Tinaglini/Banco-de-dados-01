package src.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private static int contadorId = 1;
    private int id;
    private LocalDateTime dataHora;
    private Paciente paciente;
    private StatusConsulta status;

    public Consulta(LocalDateTime dataHora, Paciente paciente) {
        this.id = contadorId++;
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.status = StatusConsulta.AGENDADA;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    // Métodos de negócio
    public void confirmar() {
        this.status = StatusConsulta.CONFIRMADA;
    }

    public void cancelar() {
        this.status = StatusConsulta.CANCELADA;
    }

    public void reagendar(LocalDateTime novaDataHora) {
        this.dataHora = novaDataHora;
        this.status = StatusConsulta.AGENDADA;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("ID: %d | Data/Hora: %s | Status: %s\n   Paciente: %s",
                id, dataHora.format(formatter), status, paciente.getNome());
    }
}