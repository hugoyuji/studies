package br.com.restaurant;

import br.com.restaurant.service.FuncionarioService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean menu = true;

        FuncionarioService service = new FuncionarioService();

        while (menu) {

            System.out.println("""
                    =======================
                    SISTEMA DE FUNCIONÁRIOS
                    =======================
                    
                    1 - Cadastrar funcionário
                    2 - Listar funcionários
                    3 - Alterar funcionário
                    4 - Deletar funcionário
                    5 - Sair
                    
                    Escolha uma opção:
                    """);

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {

                case 1:
                    service.cadastrarFuncionario();
                    break;

                case 2:
                    service.listarFuncionarios();
                    break;

                case 3:
                    service.alterarFuncionario();
                    break;

                case 4:
                    service.deletarFuncionario();
                    break;

                case 5:
                    menu = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}