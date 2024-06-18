package br.com.etechas.models;

public class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double quantia) {
        if (quantia <= 0) {
            throw new IllegalArgumentException("A quantia a ser depositada deve ser positiva.");
        }
        if(quantia > 10000.0){
            throw  new IllegalArgumentException("Valor muito alto");
        }
        saldo += quantia;
    }

    public void sacar(double quantia) throws SaldoInsuficienteException {
        if (quantia <= 0) {
            throw new IllegalArgumentException("A quantia a ser sacada deve ser positiva.");
        }
        if (saldo < quantia) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }
        saldo -= quantia;
    }

    @Override
    public String toString() {
        return "ContaBancaria:" +
                "titular:'" + titular + '\'' +
                ", saldo:" + saldo;
    }
}
