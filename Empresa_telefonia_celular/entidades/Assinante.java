package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    protected List<Chamada> chamadas;
    protected int numChamadas;

    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.chamadas = new ArrayList<>();
        this.numChamadas = 0;
    }

    public long getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Assinante{cpf=" + cpf + ", nome='" + nome + "', numero=" + numero + ", numChamadas=" + numChamadas + ", chamadas=" + chamadas + "}";
    }

    public abstract void fazerChamada(Date data, int duracao);
    public abstract void imprimirFatura(int mes);
}
