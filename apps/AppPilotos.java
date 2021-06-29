package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Pessoa;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        List<Pessoa> listaPilotos = new ArrayList<Pessoa>();
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                // Cadastre seu piloto aqui
                Pessoa piloto = new Pessoa();

                System.out.println("Digite um nome");
                piloto.setNome(in.nextLine());
                System.out.println("Digite um cpf");
                piloto.setCpf(in.nextLine());
                listaPilotos.add(piloto);
                qtdCadastrados++;

                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                } else {
                    System.out.println("\n****\nLista\n****\n");
                    for (int i = 0; i < listaPilotos.size(); i++) {
                        System.out.println(
                                "Nome: " + listaPilotos.get(i).getNome() + "\nCPF: " + listaPilotos.get(i).getCpf());
                    }
                    voltarMenu(in);
                }

                // Exiba os pilotos aqui

                voltarMenu(in);
            } else if (opcao == 3) {
                System.out.println("Informe o cpf para localização");
                String cpfDigitado = in.nextLine();
                for (Pessoa piloto : listaPilotos) {
                     if (piloto.getCpf().toString().equals(cpfDigitado)) {
                     System.out.printf("Piloto:  %s %s", piloto.getNome(), piloto.getCpf());
                    voltarMenu(in);
                    } 
                        
                };

                System.out.println("Cpf não encontrado!");
                voltarMenu(in);
                    
                

            } else if (opcao == 4) {
                System.out.println("Informe a quantidade desejada");
                String qtdDigitados;
                try{
                    qtdDigitados= in.nextLine();
                   }catch(Exception ex){
                        System.out.println("Por favor, digitar apenas números!");
                        in.nextLine();
                        continue;
                   }

                if (Integer.parseInt(qtdDigitados) < MAX_ELEMENTOS) {
                    System.out.println("Quantidade não pode ser menor que a cadastrada.");
                    voltarMenu(in);
                } else {
                     MAX_ELEMENTOS  = Integer.parseInt(qtdDigitados);
                    System.out.println("Quantidade atualiza com sucesso!");
                    voltarMenu(in);
                }

                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}