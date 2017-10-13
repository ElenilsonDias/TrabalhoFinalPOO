/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model;

/**
 *
 * @author Renato
 */
public class Conta {
    private long conta;
    private long agencia;
    private long tipo;
    private long cpfcnpj;
    private long saldo;
    private long senha;

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

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
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
}
