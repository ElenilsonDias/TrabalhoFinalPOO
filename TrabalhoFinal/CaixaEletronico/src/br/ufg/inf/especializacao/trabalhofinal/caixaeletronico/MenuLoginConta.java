/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ClienteDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class MenuLoginConta implements MenuInterface{

    @Override
    public boolean showMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Conta Login()
    {
        boolean sair = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        Conta conta =null;
        
        while (!sair)
        {
            try
            {
                System.out.println("##### LOGIN - DIGITE A AGENCIA, CONTA E SENHA #####");
                System.out.println("Tecle 'S' em seguida Enter para sair.");
                
                while (!sair)
                {
                    System.out.print("> AGENCIA: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            sair = true;
                        else
                        {
                                                        
                            break;
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                }
                
                while (!sair)
                {
                    System.out.print("> CONTA: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            sair = true;
                        else
                        {
                              
                            //conta = conta.getConta
                            
                            break;
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                }
                
                while (!sair)
                {
                    System.out.print("> SENHA: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            sair = true;
                        else
                        {
                            /*if (conta.getSenha() ==  Long.parseLong(entrada))                      
                                return conta;
                            else
                            {
                               System.out.println("Senha incorreta.");
                               s = new Scanner(System.in);
                               s.nextLine();
                            }
                               */
                            
                            
                            return conta;
                        }
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
        
        return null;
    }
    
}
