package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Telefonia {
    private List<PrePago> prePagos;
    private List<PosPago> posPagos;

    public Telefonia() {
        this.prePagos = new ArrayList<>();
        this.posPagos = new ArrayList<>();
    }

    public void cadastrarAssinante() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o tipo de assinante (1 - Pré-pago, 2 - Pós-pago): ");
        int tipo = scanner.nextInt();

        System.out.println("Digite o CPF do assinante: ");
        long cpf = scanner.nextLong();

        System.out.println("Digite o nome do assinante: ");
        scanner.nextLine(); // Consumir nova linha
        String nome = scanner.nextLine();

        System.out.println("Digite o número do telefone do assinante: ");
        int numero = scanner.nextInt();

        if (tipo == 1) {
            prePagos.add(new PrePago(cpf, nome, numero));
            System.out.println("Assinante pré-pago cadastrado com sucesso.");
        } else if (tipo == 2) {
            System.out.println("Digite o valor da assinatura mensal: ");
            float assinatura = scanner.nextFloat();

            posPagos.add(new PosPago(cpf, nome, numero, assinatura));
            System.out.println("Assinante pós-pago cadastrado com sucesso.");
        } else {
            System.out.println("Tipo de assinante inválido.");
        }
    }

    public void listarAssinantes() {
        System.out.println("Assinantes Pré-Pagos:");
        for (PrePago prePago : prePagos) {
            System.out.println(prePago);
        }

        System.out.println("Assinantes Pós-Pagos:");
        for (PosPago posPago : posPagos) {
            System.out.println(posPago);
        }
    }

    public void fazerChamada() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o tipo de assinante (1 - Pré-pago, 2 - Pós-pago): ");
        int tipo = scanner.nextInt();

        System.out.println("Digite o CPF do assinante: ");
        long cpf = scanner.nextLong();

        System.out.println("Digite a data da chamada (formato dd/MM/yyyy): ");
        scanner.nextLine(); // Consumir nova linha
        String dataStr = scanner.nextLine();
        Date data = parseDate(dataStr);

        System.out.println("Digite a duração da chamada em minutos: ");
        int duracao = scanner.nextInt();

        if (tipo == 1) {
            PrePago assinante = localizarPrePago(cpf);
            if (assinante != null) {
                assinante.fazerChamada(data, duracao);
            } else {
                System.out.println("Assinante pré-pago não encontrado.");
            }
        } else if (tipo == 2) {
            PosPago assinante = localizarPosPago(cpf);
            if (assinante != null) {
                assinante.fazerChamada(data, duracao);
            } else {
                System.out.println("Assinante pós-pago não encontrado.");
            }
        } else {
            System.out.println("Tipo de assinante inválido.");
        }
    }

    public void fazerRecarga() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o CPF do assinante pré-pago: ");
        long cpf = scanner.nextLong();

        System.out.println("Digite a data da recarga (formato dd/MM/yyyy): ");
        scanner.nextLine(); // Consumir nova linha
        String dataStr = scanner.nextLine();
        Date data = parseDate(dataStr);

        System.out.println("Digite o valor da recarga: ");
        float valor = scanner.nextFloat();

        PrePago assinante = localizarPrePago(cpf);
        if (assinante != null) {
            assinante.recarregar(data, valor);
        } else {
            System.out.println("Assinante pré-pago não encontrado.");
        }
    }

    public void imprimirFaturas() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o mês da fatura (0 para Janeiro, 1 para Fevereiro, etc.): ");
        int mes = scanner.nextInt();

        System.out.println("Faturas Pré-Pagos:");
        for (PrePago prePago : prePagos) {
            prePago.imprimirFatura(mes);
        }

        System.out.println("Faturas Pós-Pagos:");
        for (PosPago posPago : posPagos) {
            posPago.imprimirFatura(mes);
        }
    }

    private PrePago localizarPrePago(long cpf) {
        for (PrePago prePago : prePagos) {
            if (prePago.getCpf() == cpf) {
                return prePago;
            }
        }
        return null;
    }

    private PosPago localizarPosPago(long cpf) {
        for (PosPago posPago : posPagos) {
            if (posPago.getCpf() == cpf) {
                return posPago;
            }
        }
        return null;
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Erro ao formatar a data. Use o formato dd/MM/yyyy.");
            return new Date(); // Retorna a data atual caso a formatação falhe
        }
    }

    public static void main(String[] args) {
        Telefonia telefonia = new Telefonia();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("----- MENU -----");
            System.out.println("1. Cadastrar Assinante");
            System.out.println("2. Listar Assinantes");
            System.out.println("3. Fazer Chamada");
            System.out.println("4. Fazer Recarga");
            System.out.println("5. Imprimir Faturas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    telefonia.cadastrarAssinante();
                    break;
                case 2:
                    telefonia.listarAssinantes();
                    break;
                case 3:
                    telefonia.fazerChamada();
                    break;
                case 4:
                    telefonia.fazerRecarga();
                    break;
                case 5:
                    telefonia.imprimirFaturas();
                    break;
                case 6:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 6);

        scanner.close();
    }
}
