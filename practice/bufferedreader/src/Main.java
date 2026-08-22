import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static void main() {
        String caminho = "dados.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                String idade = partes[1];

                System.out.println("Nome: " + nome + "| Idade: " + idade);
            }
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}