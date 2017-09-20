/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model;

/**
 *
 * @author Elenilson
 */
public class Cliente {
    private long cpfcnpj;
    private String nome;
    private String endereco;
    private long telefone;
    private long senha;

    /**
     * @return the cpfcnpj
     */
    public long getCpfcnpj() {
        return cpfcnpj;
    }

    /**
     * @param cpfcnpj the cpfcnpj to set
     */
    public void setCpfcnpj(long cpfcnpj) {
        
        if (Long.toString(cpfcnpj).length() != 11 && Long.toString(cpfcnpj).length() != 14) {
            throw new ModelException("CPF/CNPJ inválido.");
        } else {
            this.cpfcnpj = cpfcnpj;
        }
         
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        if(nome == null || "".equals(nome)){
            throw new ModelException("Nome do cliente não pode ser "
                    + "vazio ou nulo");
        }
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        if(endereco == null || "".equals(endereco)){
            throw new ModelException("Endereço do cliente não pode ser "
                    + "vazio ou nulo");
        }

        this.endereco = endereco;
    }

    /**
     * @return the telefone
     */
    public long getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(long telefone) {
        
        if(Long.toString(telefone).length() != 10 
                && Long.toString(telefone).length() != 11  ){
            throw new ModelException("O telefone do cliente deve ter 10 ou 11"
                    +"digitos.");
        }
        this.telefone = telefone;
    }

    /**
     * @return the senha
     */
    public long getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(long senha) {
        if(Long.toString(senha).length() != 6){
            throw new ModelException("A senha deve conter 6 digitos.");
        }
        this.senha = senha;
    }
    
    
     @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("CPF/CNPJ: "+getCpfcnpj());
        builder.append(" - NOME: ");
        builder.append(getNome());
        
        return builder.toString();
    }
    
    
}
