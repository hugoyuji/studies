import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome: ");
        String nome = sc.nextLine();

        System.out.println("Digite a idade: ");
        String idade = sc.nextLine();

        String linha = nome + " | " + idade;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dados.txt", true))) {
            bw.write(linha);
            bw.newLine();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e){
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        sc.close();
    }
}
