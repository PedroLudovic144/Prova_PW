package br.com.etechas.models;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Banco {
    private List<ContaBancaria> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public Optional<ContaBancaria> buscarContaPorTitular(String titular) {
        return contas.stream()
                .filter(conta -> conta.getTitular().equals(titular))
                .findFirst();
    }

    public double calcularSaldoTotal() {
        return contas.stream()
                .mapToDouble(conta -> conta.getSaldo())
                .sum();
    }
    public double maiorSaldo(){
        return  contas.stream()
                .mapToDouble((conta -> conta.getSaldo()))
                .max()
                .orElseThrow(() -> new NoSuchElementException("Não há saldos disponíveis."));
    }
    public List<ContaBancaria> filtrarContasComSaldoMaiorQue(double salariominumo){
        return contas.stream()
                .filter(conta -> conta.getSaldo() >= salariominumo)
                .collect(Collectors.toList());
    }


}
