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
         * Com esse comando nos chamamos uma caixa, na qual podemos colocar um Input de chamada, tipo um Scanner pra impressao, mas ele ja vem com o nome
         * e completo, nos so precisamos adicionar o que queremos na caixa de mensagens.
         */

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

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por virgula, comforme exemplo: Nome, SPF, Telefone, etc... ", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            }
        }

    }

    private static void cadastrar(String dados) {
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

}
