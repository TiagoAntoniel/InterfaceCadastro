package br.com.tiago;

import dao.ClienteMapDAO;
import dao.IClienteDAO;

import javax.swing.*;
import java.util.Locale;
import java.util.Scanner;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        iClienteDAO = new ClienteMapDAO();

        /**
         * Com esse comando nos chamamos uma caixa, na qual podemos colocar um Input de chamada, tipo um Scanner pra
         * impressao, mas ele ja vem com o nome
         * e completo, nos so precisamos adicionar o que queremos na caixa de mensagens.
         */

        //Caixa de entrada inicial

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusao, 4 para alteracao ou 5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opcao invalida digite 1 para cadastro, 2 para consultar, 3 para exclusao, 4 para alteracao ou 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        //Loop principal

        while (isOpcaoValida(opcao)) {

            if (isOpcaoSair(opcao)) {
                sair();
                return;
            } else if (isCadastro(opcao)) {
                cadastrar();
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o cpf ",
                        "consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que deseja excluir:",
                        "Excluir Dados", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            }else if (isAlterar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que deseja alterar:",
                        "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);
                alterar(dados);
            }

            //Repetir o loop apos a acao

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para exclusao, 4 para alteracao ou 5 para sair  ",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);

            while (isOpcaoSair(opcao)) {
                if (isOpcaoSair(opcao)) {
                    sair();
                }
            }
        }
    }

    private static void alterar(String dados) {

        // Verificar se o CPF foi fornecido (não pode ser vazio)
        if (dados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você precisa digitar o CPF!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;  // Sai do método se não tiver CPF
        }

        Long cpfLong = Long.parseLong(dados); // Converte CPF para Long

        // Consultar o cliente pelo CPF
        Cliente clienteExistente = iClienteDAO.consultar(cpfLong);

        if (clienteExistente != null) {
            // Se o cliente for encontrado, solicitar os novos dados
            String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome do cliente (deixe em branco para manter o nome atual):", "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);

            // Se o nome estiver vazio, mantemos o nome original
            if (novoNome.isEmpty()) {
                novoNome = clienteExistente.getNome(); // Mantém o nome original
            }

            // Se o cliente for encontrado, solicitar os novos dados
            String novoTelefone = JOptionPane.showInputDialog(null, "Digite o novo telefone:", "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);
            String novoEndereco = JOptionPane.showInputDialog(null, "Digite o novo endereço:", "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);
            String novoNumero = JOptionPane.showInputDialog(null, "Digite o novo número da casa:", "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);
            String novaCidade = JOptionPane.showInputDialog(null, "Digite a nova cidade:", "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);
            String novoEstado = JOptionPane.showInputDialog(null, "Digite o novo estado:", "Alterar Dados", JOptionPane.INFORMATION_MESSAGE);

            // Atualizar os dados do cliente
            Cliente clienteAlterado = new Cliente(
                    novoNome,         // Nome
                    clienteExistente.getCpf().toString(), // Manter o CPF original
                    novoTelefone,     // Telefone
                    novoEndereco,     // Endereço
                    Integer.valueOf(novoNumero),  // Número
                    novaCidade,       // Cidade
                    novoEstado        // Estado
            );

            // Chama o método de alteração no DAO
            iClienteDAO.alterar(clienteAlterado);

            JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado para alteração.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static boolean isAlterar(String opcao) {
        return "4".equals(opcao);
    }



    private static void excluir(String dados) {
        // Verificar se o CPF foi fornecido (não pode ser vazio)
        if (dados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você precisa digitar o CPF!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;  // Sai do método se não tiver CPF
        }

        Long cpfLong = Long.parseLong(dados); // Converte CPF para Long

        // Consultar o cliente pelo CPF
        Cliente clienteExistente = iClienteDAO.consultar(cpfLong);

        if (clienteExistente != null) {
            // Cliente encontrado, proceder com a exclusao
            iClienteDAO.excluir(cpfLong); //Chama o metodo de exclusao do DAO

            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso.");
        } else { // Cliente nao encontrado
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado para exclusao.");
        }

    }

    private static boolean isExclusao(String opcao) {
        return "3".equals(opcao);
    }

    // Método que realiza a alteração do cliente


    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado com sucesso" + cliente.toString(),  "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        return "2".equals(opcao);
    }

    private static void cadastrar() {

        // Solicitar os dados do cliente via caixas de entrada

        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente: ", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String endereco = JOptionPane.showInputDialog(null, "Digite o endereço do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String numero = JOptionPane.showInputDialog(null, "Digite o número da casa:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String cidade = JOptionPane.showInputDialog(null, "Digite a cidade do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        String estado = JOptionPane.showInputDialog(null, "Digite o estado do cliente:", "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        // Verificar se todos os campos foram preenchidos

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || numero.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Converter CPF para Long
        Long cpfLong = Long.parseLong(cpf);

        //Criar o objeto Cliente com os dados fornecidos
        Cliente cliente = new Cliente(
                nome,
                cpf,
                telefone,
                endereco,Integer.valueOf(numero), //Converter numero para Integer
                cidade,
                estado
        );

        // Chamar o metodo de cadastro no DAO
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente.");
        }
    }


    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }


    private static void sair() {
        JOptionPane.showMessageDialog(null,
                "Ate logo",
                "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }
}
