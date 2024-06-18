package br.com.etechas.test;

import br.com.etechas.models.Banco;
import br.com.etechas.models.ContaBancaria;
import br.com.etechas.models.SaldoInsuficienteException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            ContaBancaria conta = new ContaBancaria("João", 1000.0);
            System.out.println("Titular:" + conta.getTitular());
            System.out.println("Saldo:" + conta.getSaldo());

            conta.depositar(500);
            System.out.println("Após depósito: " + conta.getSaldo());

            conta.sacar(300);
            System.out.println("Após saque: " + conta.getSaldo());

        } catch (SaldoInsuficienteException e) {
            System.err.println(e.getMessage());
        }

        Banco banco = new Banco();
        banco.adicionarConta(new ContaBancaria("João", 1000.0));
        banco.adicionarConta(new ContaBancaria("Maria", 1500.0));
        banco.adicionarConta(new ContaBancaria("Ana", 2000.0));

        System.out.println("Saldo total no banco: " + banco.calcularSaldoTotal());

        try {
            Optional<ContaBancaria> contaOptional = banco.buscarContaPorTitular("Maria");
            contaOptional.ifPresent(conta -> {
                System.out.println("Conta encontrada:" + conta.getTitular());
                System.out.println(conta.getSaldo());
            });
        } catch (NoSuchElementException e) {
            System.err.println("Conta não encontrada.");
        }
        try {
            ContaBancaria conta = new ContaBancaria("João", 1000.0);
            conta.sacar(1200.0); // Tentativa de sacar um valor maior que o saldo
        } catch (SaldoInsuficienteException e) {
            System.err.println(e.getMessage());
        }
        double maiorSaldo = banco.maiorSaldo();
        System.out.println("Maior saldo das contas: " + maiorSaldo);

        List<ContaBancaria> maior = banco.filtrarContasComSaldoMaiorQue(1000);
        System.out.println("Contas com saldo maiores que salario minimo:" + maior);

    }

}
