package apps;

import java.io.IOException;
import java.util.Scanner;
import classes.Pessoa;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 2;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****MENU****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine();

            if (opcao == 1) {
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    System.out.println("\nAcesse a opção 4 para alterar o espaço.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("========CADASTRO========");

                try {
                    Pessoa piloto = new Pessoa();

                    System.out.print("Insira o nome do piloto: ");
                    piloto.setNome(in.nextLine()); 
                    System.out.print("Insira o CPF: ");
                    piloto.setCpf(in.nextLine());
                    System.out.print("Insira a idade: ");
                    piloto.setIdade(in.nextInt());
                    in.nextLine();

                    pilotos[qtdCadastrados] = piloto; 
                    qtdCadastrados++; 

                    System.out.println("\nPiloto cadastrado com sucesso.");
                    voltarMenu(in);
                } catch (Exception e) {
                    System.out.println("\nOps! Houve um problema ao cadastrar o piloto, tente novamente");
                    in.nextLine(); 
                }
            } else if (opcao == 2) {
                System.out.println("\n==============RELATÓRIO=================\n");

                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                for (Pessoa piloto : pilotos) {
                    if (piloto != null) {
                        System.out.printf("\nNome: %s CPF: %s Idade: %s ", piloto.getNome(),
                                piloto.getCpf(), piloto.getIdade());
                    }
                }
                voltarMenu(in);
            } else if (opcao == 3) {
                System.out.print("\n========PESQUISA========");
                System.out.print("\nDigite o CPF para busca: ");
                String CpfDigitado = in.nextLine(); 

                for (int i = 0; i < pilotos.length + 1; i++) {
                    try {
                        if (pilotos[i].getCpf().toString().equals(CpfDigitado)) {
                            System.out.printf("\nNº: %s Nome: %s CPF: %s Idade: %s ", i + 1,
                                    pilotos[i].getNome(), 
                                    pilotos[i].getCpf(), 
                                    pilotos[i].getIdade());
                            break;
                        } else if (i < pilotos.length + 1) {
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("\nPiloto não cadastrado, verifique o CPF e tente novamente.");
                        voltarMenu(in);
                    }
                }
            } else if (opcao == 4) {
                System.out.print("\nInsira a quantidade de registros a ser incrementada: ");
                int novaQtd = 0;
                try {
                    novaQtd = in.nextInt();
                    if (novaQtd < pilotos.length) { 
                        System.out.printf("Favor informar um numero maior que %s \n", pilotos.length);
                        in.nextLine();
                        voltarMenu(in);
                    } else { 
                        Pessoa novaListaPilotos[] = new Pessoa[pilotos.length + novaQtd];
                        for (int i = 0; i < pilotos.length; i++) {
                            novaListaPilotos[i] = pilotos[i];
                        }
                        MAX_ELEMENTOS = novaListaPilotos.length; 
                        pilotos = novaListaPilotos;
                        System.out.println("\nQuantidade incrementada com sucesso!");
                        voltarMenu(in);
                    }
                } catch (Exception e) {
                    System.out.println("Verifique a quantidade digitada e tente novamente.");
                    in.nextLine();
                    voltarMenu(in);
                }
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");
        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}
