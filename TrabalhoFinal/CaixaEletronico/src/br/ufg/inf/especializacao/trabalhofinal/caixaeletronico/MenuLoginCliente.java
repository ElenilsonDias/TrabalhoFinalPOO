/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ClienteDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class MenuLoginCliente implements MenuInterface{

    @Override
    public boolean showMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Cliente Login()
    {
        boolean cancelar = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        Cliente cliente =null;
        
        while (!cancelar)
        {
            try
            {
                System.out.println("##### LOGIN - DIGITE O CPF/CNPJ E SENHA #####");
                System.out.println("Tecle 'C' em seguida Enter para cancelar.");
                
                while (!cancelar)
                {
                    System.out.print("> CPF/CNPJ: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else
                        {
                            
                            ClienteDAO clienteDAO = new ClienteDAO();
                            cliente = clienteDAO.getByCod(Long.parseLong(entrada));
                            
                            if (cliente == null )
                            {
                                System.out.println("CPF/CNPJ não cadastrado.");
                                s = new Scanner(System.in);
                                s.nextLine();
                            }
                            else
                                break;
                        }
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                }
                
                while (!cancelar)
                {
                    System.out.print("> SENHA: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else
                        {
                            if (cliente.getSenha() ==  Long.parseLong(entrada))                      
                                return cliente;
                            else
                            {
                               System.out.println("Senha incorreta.");
                               s = new Scanner(System.in);
                               s.nextLine();
                            }
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
