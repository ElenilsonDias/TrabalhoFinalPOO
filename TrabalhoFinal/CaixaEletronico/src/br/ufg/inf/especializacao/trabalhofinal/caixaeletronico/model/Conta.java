/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ClienteDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ContaDAO;
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
     public void setSaque(double vl_saque) throws SQLException {
        if(vl_saque <=saldo){
            this.saldo = this.saldo - vl_saque;
            ContaDAO contaDAO = new ContaDAO ();
            ContaDAO.uptade(this);
            System.out.println("Saque realizado com sucesso. Por favor retire o cartão.");
        }
        else 
            System.out.println("Saldo insuficiente! ");
    }
    
     public void setTransferencia(double vl_transf,long conta_dest) throws SQLException {
          if(vl_transf <=saldo){
            this.saldo = this.saldo - vl_transf;
            ContaDAO contaDAO = new ContaDAO ();
            ContaDAO.uptade(this);
            
           Conta conta2 = null;
           
           conta2=contaDAO.getByCod(conta_dest);
           conta2.setSaldo(conta2.getSaldo()+vl_transf);
           
           ContaDAO.uptade(conta2);
           
            
            System.out.println("Transferência realizado com sucesso.");
        }
        else 
            System.out.println("Saldo insuficiente! ");
    }
}
