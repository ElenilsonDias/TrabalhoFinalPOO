/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ClienteDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.ModelException;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class MenuAltCliente implements MenuInterface{

    Cliente cliente = null;
    
    public MenuAltCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
      @Override
    public boolean showMenu() {
        boolean cancelar = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        
        while (!cancelar)
        {
            try
            {
                System.out.println("##### ALTERACAO - CADASTRO CLIENTE #####");
                System.out.println("Tecle 'C' em seguida Enter para cancelar.");
                
                
                
                while (!cancelar)
                {
                    System.out.print("> NOME ["+cliente.getNome()+"]: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else if (!entrada.trim().equals("")) 
                        {
                            cliente.setNome(entrada.toUpperCase());
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
                
                while (!cancelar)
                {
                    System.out.print("> ENDERECO["+cliente.getEndereco()+"]: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else if(!entrada.trim().equals(""))
                        {
                            cliente.setEndereco(entrada.toUpperCase());
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
                
                while (!cancelar)
                {
                    System.out.print("> TELEFONE["+cliente.getTelefone()+"]: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else if (!entrada.trim().equals(""))
                        {
                            cliente.setTelefone(Long.parseLong(entrada));
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
                
                while (!cancelar)
                {
                    System.out.print("> SENHA:");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else if (!entrada.trim().equals(""))
                        {
                            cliente.setSenha(Long.parseLong(entrada));
                            
                            while (!cancelar)
                            {
                                System.out.print("> CONFIRMA SENHA:");
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("C"))
                                    cancelar = true;
                                else if (cliente.getSenha() == Long.parseLong(entrada))
                                {
                                        ClienteDAO clienteDAO = new ClienteDAO();
                                        clienteDAO.update(cliente);

                                        System.out.println();
                                        System.out.println("Cliente atualizado com sucesso.");
                                        s = new Scanner(System.in);
                                        s.nextLine();
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
