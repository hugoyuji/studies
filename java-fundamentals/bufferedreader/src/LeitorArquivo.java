import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
    static void main() {
        int somaIdades = 0;
        int quantidadePessoas = 0;

        String nomeMaisVelho = "";
        int maiorIdade = -1;

        try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"))){
            String linha;

            while((linha = br.readLine()) != null){
                String[] partes = linha.split(",");
                String nome = partes[0];
                int idade = Integer.parseInt(partes[1]);

                System.out.println("Nome: " + nome + " | Idade: " + idade);

                somaIdades += idade;
                quantidadePessoas++;

                if(idade>maiorIdade){
                    maiorIdade = idade;
                    nomeMaisVelho = nome;
                }
            }

            double media = (double) somaIdades / quantidadePessoas;
            long mediaArredondada = Math.round(media);

            System.out.println("Média de idade: " + mediaArredondada + " anos.");
            System.out.println("Pessoa mais velha: " + nomeMaisVelho + " (" + maiorIdade + " anos)");
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Erro: idade inválida no arquivo. " + e.getMessage());
        }
    }
}
