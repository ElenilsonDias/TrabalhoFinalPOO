/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ClienteDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.ModelException;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class MenuCadConta implements MenuInterface{

    public static final int AGENCIA = 133;
    Cliente cliente = null;
    
    public MenuCadConta(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
      @Override
    public boolean showMenu() {
        boolean sair = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        
        while (!sair)
        {
            try
            {
                System.out.println("##### CADASTRO DE CONTA #####");
                System.out.println("Para Cancelar, tecle 'S' e em seguida Enter.");
                
                
                
                while (!sair)
                {
                    System.out.print("> TIPO DE CONTA ['C'- CONTA CORRENTE, 'P' - CONTA POUPANÇA] : ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (!entrada.trim().equals("")) 
                        {
                            
                            break;
                        }
                        else
                            break;
                    }
                    catch(ModelException ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                    catch(Exception ex)
                    {
                         System.out.println(ex.getMessage());
                        System.out.println();
                    }
                }
                
                
                
                while (!sair)
                {
                    System.out.print("> SENHA DA CONTA:");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            sair = true;
                        else if (!entrada.trim().equals(""))
                        {
                            //conta.setSenha(Long.parseLong(entrada));
                            
                            while (!sair)
                            {
                                System.out.print("> CONFIRMA SENHA:");
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (
                                            true
                                        //conta.getSenha() == Long.parseLong(entrada)
                                        )
                                {
                                        //Insere conta no BD AQUI
                                    
                                    //Exibe a mensagem de sucesso
                                    System.out.println("Conta Cadastrada com Sucesso.");
                                    System.out.println();
                                    return true;
                                }
                                else
                                {
                                    System.out.println("As senhas digitadas não conferem.");
                                    s = new Scanner(System.in);
                                    s.nextLine();
                                    break;
                                }
                            }
                        }
                        else
                            return true;
                    }
                    catch(ModelException ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                    catch(Exception ex)
                    {
                         System.out.println(ex.getMessage());
                        System.out.println();
                    }
                }
                
            }
            catch(Exception ex)
            {
                System.out.println("Opção incorreta.");
                System.out.println();
                s = new Scanner(System.in);
                s.nextLine();
            }
        }
        
        return false;
    }
    
}
