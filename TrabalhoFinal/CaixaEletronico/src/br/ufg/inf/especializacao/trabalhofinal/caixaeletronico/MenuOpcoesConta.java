/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.ModelException;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class MenuOpcoesConta implements MenuInterface{

    private Conta conta = null;
    
    public MenuOpcoesConta(Conta conta)
    {
        this.conta = conta;
        
    }
    
    @Override
    public boolean showMenu() {
        
        boolean voltar = false;
        boolean sair = false;
        int opcao;
        String entrada;
        Scanner s = new Scanner(System.in);
        
        while (!voltar)
        {
            try
            {
                System.out.println("##### OPERAÇÕES CONTA #####");
                System.out.println("Escolha uma opção:");
                System.out.println("> 1. Saldo/Extrato");
                System.out.println("> 2. Saque");
                System.out.println("> 3. Depósito");
                System.out.println("> 4. Transferência");
                System.out.println("> 5. Voltar");
                
                opcao = s.nextInt();
                
                switch(opcao)
                {
                    case 1:
                    {
                        MenuSaldoExtrato menuSaldoExtrato = new MenuSaldoExtrato();
                        menuSaldoExtrato.showMenu();
                    }break;
                    case 2:
                    {
                        s = new Scanner(System.in);
                        System.out.println("##### INFORME O VALOR DO SAQUE #####");
                        System.out.println("Para Cancelar, tecle 'S' e em seguida Enter.");
                        

                        while (!sair)
                        {
                           System.out.print("Valor [R$]:");
                           
                            try
                            {
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
                                {
                                  double valorSaque = Double.parseDouble(entrada);
                                    
                                  System.out.print("Confirma o saque de R$ "+String.format("%.2f",valorSaque)+"? ['S'=SIM - 'N'=NÃO]: ");
                                  entrada = s.nextLine();
                                  
                                  if (entrada.equalsIgnoreCase("S"))
                                  {
                                      System.out.println("Saque realizado com sucesso. Por favor retire o cartão.");
                                    s.nextLine();
                                  }
                                  else
                                  {
                                    System.out.println("Operação não realizada.");
                                    s.nextLine();
                                  }
                                  
                                  break;
                                }                                
                                                           
                            }
                            catch(Exception ex)
                            {
                                System.out.println("Valor Inválido!");
                            }                                                        
                        }
                    }break;
                    case 3:
                    {
                        s = new Scanner(System.in);
                        System.out.println("##### INFORME O VALOR DO DEPÓSITO #####");
                        System.out.println("Para Cancelar, tecle 'S' e em seguida Enter.");
                        

                        while (!sair)
                        {
                           System.out.print("Valor [R$]:");
                           
                            try
                            {
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
                                {
                                  double valorSaque = Double.parseDouble(entrada);
                                    
                                  System.out.print("Confirma o deposito de R$ "+String.format("%.2f",valorSaque)+"? ['S'=SIM - 'N'=NÃO]: ");
                                  entrada = s.nextLine();
                                  
                                  if (entrada.equalsIgnoreCase("S"))
                                  {
                                      System.out.println("Depósito realizado com sucesso.");
                                    s.nextLine();
                                  }
                                  else
                                  {
                                    System.out.println("Operação não realizada.");
                                    s.nextLine();
                                  }
                                  
                                  break;
                                }                                
                                                           
                            }
                            catch(Exception ex)
                            {
                                System.out.println("Valor Inválido!");
                            }                                                        
                        }
                    }break;
                    case 4:
                    {
                        s = new Scanner(System.in);
                        System.out.println("##### TRANSFERÊNCIA #####");
                        System.out.println("Para Cancelar, tecle 'S' e em seguida Enter.");
                         
                        while (!sair)
                        {
                            System.out.print("> AGENCIA DESTINO: ");
                            entrada = s.nextLine();

                            try
                            {
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
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
                            System.out.print("> CONTA DESTINO: ");
                            entrada = s.nextLine();

                            try
                            {
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
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
                           System.out.print("Valor [R$]:");
                           
                            try
                            {
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
                                {
                                  double valorSaque = Double.parseDouble(entrada);
                                    
                                  System.out.print("Confirma a transferência de R$ "+String.format("%.2f",valorSaque)+"? ['S'=SIM - 'N'=NÃO]: ");
                                  entrada = s.nextLine();
                                  
                                  if (entrada.equalsIgnoreCase("S"))
                                  {
                                      System.out.println("Transferência realizado com sucesso.");
                                    s.nextLine();
                                  }
                                  else
                                  {
                                    System.out.println("Operação não realizada.");
                                    s.nextLine();
                                  }
                                  
                                  break;
                                }                                
                                                           
                            }
                            catch(Exception ex)
                            {
                                System.out.println("Valor Inválido!");
                            }                                                        
                        }
                        
                    }break;
                    case 5:
                    {
                        voltar = true;
                    }break;
                    default:
                    {
                        throw new Exception("");
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
        
        return true;
        
    }
    
}
