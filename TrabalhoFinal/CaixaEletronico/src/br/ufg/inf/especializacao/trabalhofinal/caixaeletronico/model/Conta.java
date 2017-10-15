/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ContaDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.HistoricoDAO;
import java.sql.SQLException;

/**
 *
 * @author Renato
 */
public class Conta {
    private long conta;
    private long agencia;
    private long tipo;
    private long cpfcnpj;
    private double saldo;
    private long senha;

    public Conta (){
        //
    }
    
    public long getConta() {
        return conta;
    }

    public void setConta(long conta) {
        this.conta = conta;
    }

    public long getAgencia() {
        return agencia;
    }

    public void setAgencia(long agencia) {
        this.agencia = agencia;
    }

    public long getTipo() {
        return tipo;
    }

    public void setTipo(long tipo) {
        if(tipo<1 && tipo>2) {
            throw new ModelException("Tipo de conta inválido.");
        }else{
            this.tipo = tipo;
        }
    }

    public long getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(long cpfcnpj) {
        if (Long.toString(cpfcnpj).length() != 11 && Long.toString(cpfcnpj).length() != 14) {
            throw new ModelException("CPF/CNPJ inválido.");
        } else {
            this.cpfcnpj = cpfcnpj;
        } 
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public long getSenha() {
        return senha;
    }

    public void setSenha(long senha) {
        if(Long.toString(senha).length() != 6){
            throw new ModelException("A senha deve conter 6 digitos.");
        }
        this.senha = senha;
        
    }
    
    public void setDeposito(double valor) throws SQLException {
        this.saldo = this.saldo + valor;
        ContaDAO contaDAO = new ContaDAO ();
        HistoricoDAO historicoDAO = new HistoricoDAO(this);
        if(contaDAO.update(this)){
            historicoDAO.setTransacao("Depósito", valor);
            System.out.println("Depósito no valor de R$ " + String.format("%.2f",valor) + " realizado com sucesso.");
            System.out.println("> Saldo R$ " + String.format("%.2f",saldo));
        }
    }
    
    public void setSaque(double valor) throws SQLException {
        if(valor <= saldo){
            this.saldo = this.saldo - valor;
            ContaDAO contaDAO = new ContaDAO ();
            HistoricoDAO historicoDAO = new HistoricoDAO(this);
            if(contaDAO.update(this)){
                historicoDAO.setTransacao("Saque", valor);
                System.out.println("Saque no valor de R$ " + String.format("%.2f",valor) + " realizado com sucesso.");
                System.out.println("> Saldo R$ " + String.format("%.2f",saldo));
                System.out.println("Por favor retire o cartão.");
            }
        }
        else{
            System.out.println("Saldo insuficiente! ");
            System.out.println("> Saldo R$ " + String.format("%.2f",saldo));
        }
        
    }
    
     public void setTransferencia(long agencia_dest, long conta_dest, double valor) throws SQLException {
          if(valor <= saldo){
            this.saldo = this.saldo - valor;
            ContaDAO contaDAO = new ContaDAO ();
            HistoricoDAO historicoDAO = new HistoricoDAO(this);
            if(contaDAO.update(this)){
                historicoDAO.setTransacao("Transferência enviada - " + agencia_dest + " " + conta_dest, valor);
                
                Conta conta2 = null;

                conta2=contaDAO.getByCod(conta_dest);
                conta2.setSaldo(conta2.getSaldo()+valor);
                HistoricoDAO historicoDAO2 = new HistoricoDAO(conta2);
                if(contaDAO.update(conta2)){
                    historicoDAO2.setTransacao("Transferência recebida - " + agencia + " " + conta, valor);
                    System.out.println("Transferência no valor de R$ " + String.format("%.2f",valor) + " realizada com sucesso.");
                    System.out.println("> Saldo R$ " + String.format("%.2f",saldo));
                }
                
            }

        }
        else {
            System.out.println("Saldo insuficiente! ");
            System.out.println("> Saldo R$ " + String.format("%.2f",saldo));
        }
    }
}
