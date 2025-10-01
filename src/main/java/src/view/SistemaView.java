package src.view;

import src.controller.*;
import src.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class SistemaView {
    private Scanner scanner = new Scanner(System.in);
    private EnderecoController enderecoController;
    private PacienteController pacienteController;
    private ConsultaController consultaController;

    public SistemaView() {
        this.enderecoController = new EnderecoController();
        this.pacienteController = new PacienteController();
        this.consultaController = new ConsultaController();
    }

    public void iniciar() {
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuPacientes();
                case 2 -> menuEnderecos();
                case 3 -> menuConsultas();
                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE GERENCIAMENTO DE CONSULTAS ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Gerenciar Pacientes");
        System.out.println("2. Gerenciar Endereços");
        System.out.println("3. Gerenciar Consultas");
        System.out.println("0. Sair");
    }

    private void menuPacientes() {
        while (true) {
            System.out.println("\n=== GERENCIAR PACIENTES ===");
            System.out.println("1. Criar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Buscar Paciente");
            System.out.println("4. Atualizar Paciente");
            System.out.println("5. Deletar Paciente");
            System.out.println("0. Voltar");

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> criarPaciente();
                case 2 -> listarPacientes();
                case 3 -> buscarPaciente();
                case 4 -> atualizarPaciente();
                case 5 -> deletarPaciente();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void criarPaciente() {
        System.out.println("\n--- Criar Novo Paciente ---");
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("\n--- Endereço do Paciente ---");
        listarEnderecos();
        int idEndereco = lerInteiro("ID do Endereço (ou 0 para criar novo): ");

        Endereco endereco;
        if (idEndereco == 0) {
            endereco = criarEnderecoInterativo();
            enderecoController.criar(endereco);
            System.out.println("✓ Endereço criado com ID: " + endereco.getId());
        } else {
            endereco = enderecoController.buscar(idEndereco);
            if (endereco == null) {
                System.out.println("✗ Endereço não encontrado!");
                return;
            }
        }

        Paciente paciente = new Paciente(nome, cpf, email, telefone, endereco);
        if (pacienteController.criar(paciente)) {
            System.out.println("✓ Paciente criado com sucesso! ID: " + paciente.getId());
        } else {
            System.out.println("✗ Erro ao criar paciente!");
        }
    }

    private void listarPacientes() {
        List<Paciente> pacientes = pacienteController.listarTodos();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        System.out.println("\n=== PACIENTES CADASTRADOS ===");
        pacientes.forEach(System.out::println);
    }

    private void buscarPaciente() {
        int id = lerInteiro("ID do Paciente: ");
        Paciente paciente = pacienteController.buscar(id);
        if (paciente != null) {
            System.out.println("\n" + paciente);
        } else {
            System.out.println("✗ Paciente não encontrado!");
        }
    }

    private void atualizarPaciente() {
        int id = lerInteiro("ID do Paciente a atualizar: ");
        Paciente pacienteExistente = pacienteController.buscar(id);

        if (pacienteExistente == null) {
            System.out.println("✗ Paciente não encontrado!");
            return;
        }

        scanner.nextLine();

        System.out.print("Novo Nome (atual: " + pacienteExistente.getNome() + "): ");
        String nome = scanner.nextLine();

        System.out.print("Novo CPF (atual: " + pacienteExistente.getCpf() + "): ");
        String cpf = scanner.nextLine();

        System.out.print("Novo Email (atual: " + pacienteExistente.getEmail() + "): ");
        String email = scanner.nextLine();

        System.out.print("Novo Telefone (atual: " + pacienteExistente.getTelefone() + "): ");
        String telefone = scanner.nextLine();

        listarEnderecos();
        int idEndereco = lerInteiro("Novo ID do Endereço: ");
        Endereco endereco = enderecoController.buscar(idEndereco);

        if (endereco == null) {
            System.out.println("✗ Endereço não encontrado!");
            return;
        }

        Paciente pacienteAtualizado = new Paciente(nome, cpf, email, telefone, endereco);
        if (pacienteController.atualizar(id, pacienteAtualizado)) {
            System.out.println("✓ Paciente atualizado com sucesso!");
        } else {
            System.out.println("✗ Erro ao atualizar paciente!");
        }
    }

    private void deletarPaciente() {
        int id = lerInteiro("ID do Paciente a deletar: ");
        if (pacienteController.deletar(id)) {
            System.out.println("✓ Paciente deletado com sucesso!");
        } else {
            System.out.println("✗ Paciente não encontrado!");
        }
    }

    private void menuEnderecos() {
        while (true) {
            System.out.println("\n=== GERENCIAR ENDEREÇOS ===");
            System.out.println("1. Criar Endereço");
            System.out.println("2. Listar Endereços");
            System.out.println("3. Buscar Endereço");
            System.out.println("4. Atualizar Endereço");
            System.out.println("5. Deletar Endereço");
            System.out.println("0. Voltar");

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> {
                    Endereco endereco = criarEnderecoInterativo();
                    if (enderecoController.criar(endereco)) {
                        System.out.println("✓ Endereço criado com sucesso! ID: " + endereco.getId());
                    }
                }
                case 2 -> listarEnderecos();
                case 3 -> buscarEndereco();
                case 4 -> atualizarEndereco();
                case 5 -> deletarEndereco();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private Endereco criarEnderecoInterativo() {
        System.out.println("\n--- Criar Novo Endereço ---");
        scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        return new Endereco(estado, cidade, rua, numero, cep);
    }

    private void listarEnderecos() {
        List<Endereco> enderecos = enderecoController.listarTodos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.");
            return;
        }
        System.out.println("\n=== ENDEREÇOS CADASTRADOS ===");
        enderecos.forEach(System.out::println);
    }

    private void buscarEndereco() {
        int id = lerInteiro("ID do Endereço: ");
        Endereco endereco = enderecoController.buscar(id);
        if (endereco != null) {
            System.out.println("\n" + endereco);
        } else {
            System.out.println("✗ Endereço não encontrado!");
        }
    }

    private void atualizarEndereco() {
        int id = lerInteiro("ID do Endereço a atualizar: ");
        if (!enderecoController.existe(id)) {
            System.out.println("✗ Endereço não encontrado!");
            return;
        }

        Endereco enderecoAtualizado = criarEnderecoInterativo();
        if (enderecoController.atualizar(id, enderecoAtualizado)) {
            System.out.println("✓ Endereço atualizado com sucesso!");
        } else {
            System.out.println("✗ Erro ao atualizar endereço!");
        }
    }

    private void deletarEndereco() {
        int id = lerInteiro("ID do Endereço a deletar: ");
        if (enderecoController.deletar(id)) {
            System.out.println("✓ Endereço deletado com sucesso!");
        } else {
            System.out.println("✗ Endereço não encontrado!");
        }
    }

    private void menuConsultas() {
        while (true) {
            System.out.println("\n=== GERENCIAR CONSULTAS ===");
            System.out.println("1. Criar Consulta");
            System.out.println("2. Listar Consultas");
            System.out.println("3. Buscar Consulta");
            System.out.println("4. Atualizar Consulta");
            System.out.println("5. Deletar Consulta");
            System.out.println("6. Confirmar Consulta");
            System.out.println("7. Cancelar Consulta");
            System.out.println("8. Reagendar Consulta");
            System.out.println("0. Voltar");

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> criarConsulta();
                case 2 -> listarConsultas();
                case 3 -> buscarConsulta();
                case 4 -> atualizarConsulta();
                case 5 -> deletarConsulta();
                case 6 -> confirmarConsulta();
                case 7 -> cancelarConsulta();
                case 8 -> reagendarConsulta();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void criarConsulta() {
        System.out.println("\n--- Criar Nova Consulta ---");

        listarPacientes();
        int idPaciente = lerInteiro("ID do Paciente: ");
        Paciente paciente = pacienteController.buscar(idPaciente);

        if (paciente == null) {
            System.out.println("✗ Paciente não encontrado!");
            return;
        }

        scanner.nextLine();
        System.out.print("Data e Hora (formato: dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

            Consulta consulta = new Consulta(dataHora, paciente);
            if (consultaController.criar(consulta)) {
                System.out.println("✓ Consulta agendada com sucesso! ID: " + consulta.getId());
            } else {
                System.out.println("✗ Erro ao criar consulta!");
            }
        } catch (Exception e) {
            System.out.println("✗ Formato de data/hora inválido!");
        }
    }

    private void listarConsultas() {
        List<Consulta> consultas = consultaController.listarTodos();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
            return;
        }
        System.out.println("\n=== CONSULTAS CADASTRADAS ===");
        consultas.forEach(System.out::println);
    }

    private void buscarConsulta() {
        int id = lerInteiro("ID da Consulta: ");
        Consulta consulta = consultaController.buscar(id);
        if (consulta != null) {
            System.out.println("\n" + consulta);
        } else {
            System.out.println("✗ Consulta não encontrada!");
        }
    }

    private void atualizarConsulta() {
        int id = lerInteiro("ID da Consulta a atualizar: ");
        if (!consultaController.existe(id)) {
            System.out.println("✗ Consulta não encontrada!");
            return;
        }

        listarPacientes();
        int idPaciente = lerInteiro("Novo ID do Paciente: ");
        Paciente paciente = pacienteController.buscar(idPaciente);

        if (paciente == null) {
            System.out.println("✗ Paciente não encontrado!");
            return;
        }

        scanner.nextLine();
        System.out.print("Nova Data e Hora (formato: dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

            Consulta consultaAtualizada = new Consulta(dataHora, paciente);
            if (consultaController.atualizar(id, consultaAtualizada)) {
                System.out.println("✓ Consulta atualizada com sucesso!");
            } else {
                System.out.println("✗ Erro ao atualizar consulta!");
            }
        } catch (Exception e) {
            System.out.println("✗ Formato de data/hora inválido!");
        }
    }

    private void deletarConsulta() {
        int id = lerInteiro("ID da Consulta a deletar: ");
        if (consultaController.deletar(id)) {
            System.out.println("✓ Consulta deletada com sucesso!");
        } else {
            System.out.println("✗ Consulta não encontrada!");
        }
    }

    private void confirmarConsulta() {
        int id = lerInteiro("ID da Consulta a confirmar: ");
        if (consultaController.confirmar(id)) {
            System.out.println("✓ Consulta confirmada!");
        } else {
            System.out.println("✗ Consulta não encontrada!");
        }
    }

    private void cancelarConsulta() {
        int id = lerInteiro("ID da Consulta a cancelar: ");
        if (consultaController.cancelar(id)) {
            System.out.println("✓ Consulta cancelada!");
        } else {
            System.out.println("✗ Consulta não encontrada!");
        }
    }

    private void reagendarConsulta() {
        int id = lerInteiro("ID da Consulta a reagendar: ");

        scanner.nextLine();
        System.out.print("Nova Data e Hora (formato: dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

            if (consultaController.reagendar(id, dataHora)) {
                System.out.println("✓ Consulta reagendada!");
            } else {
                System.out.println("✗ Consulta não encontrada!");
            }
        } catch (Exception e) {
            System.out.println("✗ Formato de data/hora inválido!");
        }
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, insira um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}