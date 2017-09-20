/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class MenuSaldoExtrato implements MenuInterface{

    @Override
    public boolean showMenu() {
        boolean voltar = false, sair = false;
        int opcao;
        String entrada;
        Scanner s = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicial=null,dataFinal=null;
        
        dateFormat.setLenient(false);
        while (!voltar)
        {
            sair = false;
            try
            {
                System.out.println("##### OPERAÇÕES CONTA #####");
                System.out.println("Escolha uma opção:");
                System.out.println("> 1. Saldo");
                System.out.println("> 2. Extrato");
                System.out.println("> 3. Extrato por Periodo");
                System.out.println("> 4. Voltar");
                
                opcao = s.nextInt();
                
                switch(opcao)
                {
                    case 1:
                    {

                    }break;
                    case 2:
                    {
  
                    }break;
                    case 3:
                    {
                         s = new Scanner(System.in);
                        System.out.println("##### INFORME A DATA INICIAL E FINAL #####");
                        System.out.println("Para Cancelar, tecle 'S' e em seguida Enter.");
                        

                        while (!sair)
                        {
                           System.out.print("Data Inicial [dd/mm/yyyy]:");
                           entrada = s.nextLine();

                            try
                            {
                                
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
                                {
                                  dataInicial = dateFormat.parse(entrada);
                                  break;
                                }                                
                                                           
                            }
                            catch(Exception ex)
                            {
                                System.out.println("Data Inválida!");
                            }                                                        
                        }
                        
                        
                        while (!sair)
                        {
                           System.out.print("Data Final [dd/mm/yyyy]:");
                           entrada = s.nextLine();

                            try
                            {
                                
                                if (entrada.equalsIgnoreCase("S"))
                                    sair = true;
                                else if (!entrada.trim().equals("")) 
                                {
                                    dataFinal = dateFormat.parse(entrada);
                                    System.out.println("------- Extrato de "+dataInicial.toString()+" a "+dataFinal.toString());
                                    s.nextLine();
                                    sair = true;
                                }                                
                                                           
                            }
                            catch(Exception ex)
                            {
                                System.out.println("Data Inválida!");
                            }                                                        
                        }                                                
                        
                    }break;
                    case 4:
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
