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
public class MenuCadCliente implements MenuInterface{

    @Override
    public boolean showMenu() {
        
        boolean cancelar = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        Cliente cliente = new Cliente();
        
        while (!cancelar)
        {
            try
            {
                System.out.println("##### INFORME OS DADOS DO CLIENTE #####");
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
                            cliente.setCpfcnpj(Long.parseLong(entrada));
                            break;
                        }
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
                    System.out.print("> NOME: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else
                        {
                            cliente.setNome(entrada.toUpperCase());
                            break;
                        }
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
                    System.out.print("> ENDERECO: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else
                        {
                            cliente.setEndereco(entrada.toUpperCase());
                            break;
                        }
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
                    System.out.print("> TELEFONE: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else
                        {
                            cliente.setTelefone(Long.parseLong(entrada));
                            break;
                        }
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
                    System.out.print("> SENHA: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                                                
                        
                        if (entrada.equalsIgnoreCase("C"))
                            cancelar = true;
                        else
                        {
                            cliente.setSenha(Long.parseLong(entrada));
                            
                            
                            while (!cancelar)
                            {
                                System.out.print("> CONFIRMA SENHA: ");
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("C"))
                                    cancelar = true;
                                else if (cliente.getSenha() == Long.parseLong(entrada))
                                {
                                    ClienteDAO clienteDAO = new ClienteDAO();
                                    clienteDAO.create(cliente);

                                    System.out.println();
                                    System.out.println("Cliente cadastrado com sucesso.");
                                    System.out.println();
                                    s = new Scanner(System.in);
                                    s.nextLine();
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
                    }
                    catch(ModelException ex)
                    {
                        System.out.println(ex.getMessage());
                        System.out.println();
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Valor inválido");
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
