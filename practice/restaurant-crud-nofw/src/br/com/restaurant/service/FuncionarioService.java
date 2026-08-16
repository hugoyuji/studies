package br.com.restaurant.service;

import br.com.restaurant.model.FuncionarioEntidade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FuncionarioService {

    List<FuncionarioEntidade> funcionarioLista = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public FuncionarioEntidade cadastrarFuncionario (){
        FuncionarioEntidade funcionario = new FuncionarioEntidade();
        Long idContador = 1L;

        System.out.println("Digite o nome do funcionário: ");
        funcionario.setNome(scanner.nextLine());

        System.out.println("Digite o CPF do funcionário: ");
        funcionario.setCPF(scanner.nextLine());

        System.out.println("Digite o telefone do funcionário: ");
        funcionario.setTelefone(scanner.nextLine());

        System.out.println("Digite o cargo do funcionário: ");
        funcionario.setCargo(scanner.nextLine());

        System.out.println("Digite o salário do funcionário: ");
        funcionario.setSalario(scanner.nextLine());

        System.out.println("Digite a data de contratação do funcionário: ");
        funcionario.setDataContratacao(scanner.nextLine());

        System.out.println("Confirmar contratação? (s para Sim/n para Não)");
        String respostaContratacao = scanner.nextLine();

        funcionario.setStatus(respostaContratacao.equals("s"));

        funcionario.setId(idContador);
        idContador++;

        funcionarioLista.add(funcionario);

        return funcionario;
    }

    public void listarFuncionarios (){
        System.out.println("""
                =====================
                NOME DOS FUNCIONÁRIOS
                =====================
                """);

        for (FuncionarioEntidade funcionarioUnidade : funcionarioLista) {
            System.out.println(funcionarioUnidade.getNome());
        }

    }

    public FuncionarioEntidade alterarFuncionario (){

        System.out.println("Digite o ID do funcionário que deseja alterar: ");
        Long id = Long.parseLong(scanner.nextLine());

        for (FuncionarioEntidade funcionario : funcionarioLista){

            if(funcionario.getId().equals(id)) {

                System.out.println("Digite o novo nome: ");
                funcionario.setNome(scanner.nextLine());

                System.out.println("Digite o novo CPF: ");
                funcionario.setCPF(scanner.nextLine());

                System.out.println("Digite o novo telefone: ");
                funcionario.setTelefone(scanner.nextLine());

                System.out.println("Digite o novo cargo: ");
                funcionario.setCargo(scanner.nextLine());

                System.out.println("Digite o novo salário: ");
                funcionario.setSalario(scanner.nextLine());

                System.out.println("Digite a data de contratação correta: ");
                funcionario.setDataContratacao(scanner.nextLine());

                System.out.println("O funcionário segue com o contrato ativo? (s para Sim/n para Não)");
                String respostaContratacao = scanner.nextLine();

                funcionario.setStatus(respostaContratacao.equals("s"));
            }
        }
        System.out.println("Funcionário não encontrado.");
        return null;
    }

    public void deletarFuncionario (){

        System.out.println("Digite o ID do funcionário que você deseja deletar: ");
        Long id = Long.parseLong(scanner.nextLine());

        Iterator<FuncionarioEntidade> iterator = funcionarioLista.iterator();

        while(iterator.hasNext()){
            FuncionarioEntidade funcionario = iterator.next();

            if (funcionario.getId().equals(id)){
                iterator.remove();

                System.out.println("Funcionário deletado com sucesso!");
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }
}
