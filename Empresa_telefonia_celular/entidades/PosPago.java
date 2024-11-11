package entidades;

import java.util.Date;

public class PosPago extends Assinante {
    private float assinatura;

    public PosPago(long cpf, String nome, int numero, float assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    @Override
    public void fazerChamada(Date data, int duracao) {
        chamadas.add(new Chamada(data, duracao));
        numChamadas++;
    }

    @Override
    public void imprimirFatura(int mes) {
        System.out.println("Fatura Pós-Pago - CPF: " + getCpf());
        float total = assinatura;
        for (Chamada chamada : chamadas) {
            if (chamada.getData().getMonth() == mes - 1) {
                System.out.println(chamada);
                total += chamada.getDuracao() * 1.04;
            }
        }
        System.out.println("Total da fatura: R$ " + total);
    }
}
