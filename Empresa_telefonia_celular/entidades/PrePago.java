package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrePago extends Assinante {
    private float creditos;
    private List<Recarga> recargas;
    private int numRecargas;

    public PrePago(long cpf, String nome, int numero) {
        super(cpf, nome, numero);
        this.creditos = 0;
        this.recargas = new ArrayList<>();
        this.numRecargas = 0;
    }

    @Override
    public void fazerChamada(Date data, int duracao) {
        float custo = duracao * 1.45f;
        if (creditos >= custo) {
            chamadas.add(new Chamada(data, duracao));
            numChamadas++;
            creditos -= custo;
        } else {
            System.out.println("Créditos insuficientes para realizar a chamada.");
        }
    }

    public void recarregar(Date data, float valor) {
        recargas.add(new Recarga(data, valor));
        numRecargas++;
        creditos += valor;
    }

    @Override
    public void imprimirFatura(int mes) {
        System.out.println("Fatura Pré-Pago - CPF: " + getCpf());
        chamadas.stream()
                .filter(chamada -> chamada.getData().getMonth() == mes - 1)
                .forEach(System.out::println);
        recargas.stream()
                .filter(recarga -> recarga.getData().getMonth() == mes - 1)
                .forEach(System.out::println);
        System.out.println("Créditos restantes: R$ " + creditos);
    }
}
