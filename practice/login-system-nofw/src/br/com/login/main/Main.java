package br.com.login.main;

import br.com.login.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() {

        List<User> usersList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean authenticateSuccess = false;

        usersList.add(new User("Hugo", "root"));
        usersList.add(new User("Yuji", "toor"));
        usersList.add(new User("Ijuy", "0000"));


        System.out.println("""
                    ----------------------
                    ENTRE COM A SUA CONTA!
                    ----------------------\n
                    """);

        do {

            System.out.print("Login: ");
            String loginReceived = scanner.next();

            System.out.print("Senha: ");
            String passwordReceived = scanner.next();

            for (User user : usersList) {
                if (user.authenticate(loginReceived, passwordReceived)) {
                    authenticateSuccess = true;
                    break;
                }
            }

            if (authenticateSuccess) {
                System.out.println("Login bem-sucedido!");
            } else {
                System.out.println("Login inválido.");
                System.out.println("Credenciais não coincidem com nenhuma conta no sistema.");
            }

        } while (!authenticateSuccess);

        scanner.close();
    }
}
