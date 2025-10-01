package src.model;

public class Paciente {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;

    public Paciente(String nome, String cpf, String email, String telefone, Endereco endereco) {
        this.id = contadorId++;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | CPF: %s | Email: %s | Tel: %s\n   Endereço: %s",
                id, nome, cpf, email, telefone, endereco);
    }
}