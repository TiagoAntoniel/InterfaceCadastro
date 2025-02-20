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
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por virgula, conforme exemplo: Nome,CPF,Telefone,Endereco,Numero,Cidade,Estado etc... ",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o cpf ",
                        "consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            }

            //Repetir o loop apos a acao

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consulta, 3 para cadastro, 4 para alteracao ou 5 para ",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);

            while (isOpcaoSair(opcao)) {
                if (isOpcaoSair(opcao)) {
                    sair();
                }
            }
        }
    }

    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado com sucesso" + cliente.toString(),  "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {


            return true;
        }
        return false;
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");


        if (dadosSeparados.length >= 7) {
        /**
         * Validar se todos os campos estao preenchidos
         * Se nao tiver, passar null no construtor, onde o valor e nulo.
         *
         * Cliente cliente = new Cliente(
         *                 dadosSeparados[0],
         *                 null,
         *                 dadosSeparados[2],
         *                 null,
         *                 Integer.valueOf(dadosSeparados[4]),
         *                 dadosSeparados[5],
         *                 dadosSeparados[6]
         */
        Cliente cliente = new Cliente(
                dadosSeparados[0],
                dadosSeparados[1],
                dadosSeparados[2],
                dadosSeparados[3],
                Integer.valueOf(dadosSeparados[4]),
                dadosSeparados[5],
                dadosSeparados[6]
        );
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

            if (isCadastrado) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente.");
            }
            } else {
                JOptionPane.showMessageDialog(null, "Dados insuficientes");
            }
    }


    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }


    private static void sair() {
        JOptionPane.showInputDialog(null,
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

    private static boolean isOpcaoCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }


}
