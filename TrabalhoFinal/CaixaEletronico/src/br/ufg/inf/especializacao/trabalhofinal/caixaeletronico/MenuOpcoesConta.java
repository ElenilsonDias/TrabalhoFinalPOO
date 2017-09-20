/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
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
        int opcao;
        Scanner s = new Scanner(System.in);
        Cliente cliente = null;
        
        while (!voltar)
        {
            try
            {
                System.out.println("##### OPERAÇÕES CONTA #####");
                System.out.println("Escolha uma opção:");
                System.out.println("> 1. Exibir Saldo");
                System.out.println("> 2. Extrato por Período");
                System.out.println("> 3. Transferência");
                System.out.println("> 4. Voltar");
                
                opcao = s.nextInt();
                
                switch(opcao)
                {
                    case 1:
                    {
                        MenuCadCliente menuCadCliente = new MenuCadCliente();
                        menuCadCliente.showMenu();
                    }break;
                    case 2:
                    {
                       MenuLoginCliente menuLoginCliente = new MenuLoginCliente();                    
                       cliente = menuLoginCliente.Login();
                       
                       if (cliente != null)
                       {
                           MenuAltCliente menuAltCliente = new MenuAltCliente(cliente);
                           menuAltCliente.showMenu();
                       }
                    }break;
                    case 3:
                    {
                       MenuLoginCliente menuLoginCliente = new MenuLoginCliente();                    
                       cliente = menuLoginCliente.Login();
                       
                       if (cliente != null)
                       {
                           MenuCadConta menuCadConta = new MenuCadConta(cliente);
                           menuCadConta.showMenu();
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
