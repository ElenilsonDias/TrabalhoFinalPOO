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
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Elenilson
 */
public class CaixaEletronico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean sair = false;
        Scanner s = new Scanner(System.in);
        int opcao;
        
        while (!sair)
        {
            System.out.println("##### CAIXA ELETRONICO VIRTUAL #####");
            System.out.println("Escolha uma opção:");
            System.out.println("> 1. Cadastro de Clientes/Contas");
            System.out.println("> 2. Acessar Conta");
            System.out.println("> 3. Listar Clientes");
            System.out.println("> 4. Sair");
            
            
            try
            {
                
                opcao = s.nextInt();
                
                switch(opcao)
                {
                    case 1:
                    {
                        MenuCadCliConta menuCadCliConta = new MenuCadCliConta();
                        menuCadCliConta.showMenu();
                    }break;
                    case 2:
                    {
                        MenuLoginConta menuLoginConta = new MenuLoginConta();
                        Conta conta = menuLoginConta.Login();
                        
                        //if (conta != null)
                       // {
                            MenuOpcoesConta opcoesConta = new MenuOpcoesConta(conta);
                            opcoesConta.showMenu();
                        //}
                        
                        
                    }break;
                    case 3:
                    {
                        ClienteDAO clienteDAO = new ClienteDAO();
        
                        try {
                            List<Cliente> clientes = clienteDAO.getAll();
                            System.out.println();
                            for(Cliente umCliente : clientes){
                                System.out.println(umCliente);
                            }
                            System.out.println();
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }break;
                    case 4:
                    {
                        sair = true;
                    }break;
                    default:
                    {
                        throw new Exception("");
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println();
                System.out.println("Opção incorreta!");
                s = new Scanner(System.in);
                s.nextLine();
            }
        }
        
    }
    
    private static void menuCadastro()
    {
        boolean voltar = false;
        int opcao;
        Scanner s = new Scanner(System.in);
        Cliente cliente = null;
        
        while (!voltar)
        {
            try
            {
                System.out.println("##### CADASTRO DE CLIENTES/CONTAS #####");
                System.out.println("Escolha uma opção:");
                System.out.println("> 1. Cadastrar Cliente");
                System.out.println("> 2. Alterar Cliente");
                System.out.println("> 3. Cadastrar Conta");
                System.out.println("> 4. Voltar");
                
                opcao = s.nextInt();
                
                switch(opcao)
                {
                    case 1:
                    {
                        menuCadCliente();
                    }break;
                    case 2:
                    {
                       cliente = loginAltCliente();
                       
                       if (cliente != null)
                           menuAltCliente(cliente);
                    }break;
                    case 3:
                    {
                    
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
                
    }
    
    private static void menuCadCliente()
    {
        boolean cancelar = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        Cliente cliente = new Cliente();
        
        while (!cancelar)
        {
            try
            {
                System.out.println("##### INFORME OS DADOS DO CLIENTE #####");
                System.out.println("Tecle 'S' em seguida Enter para cancelar.");
                
                while (!cancelar)
                {
                    System.out.print("> CPF/CNPJ: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else
                        {
                            cliente.setCpfcnpj(Long.parseLong(entrada));
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
                    System.out.print("> NOME: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else
                        {
                            cliente.setNome(entrada.toUpperCase());
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
                    System.out.print("> ENDERECO: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else
                        {
                            cliente.setEndereco(entrada.toUpperCase());
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
                    System.out.print("> TELEFONE: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else
                        {
                            cliente.setTelefone(Long.parseLong(entrada));
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
                                                
                        
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else
                        {
                            cliente.setSenha(Long.parseLong(entrada));
                            
                            
                            while (!cancelar)
                            {
                                System.out.print("> CONFIRMA SENHA: ");
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("S"))
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
                                    return;
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
    }
    
    
    private static void menuAltCliente(Cliente cliente)
    {
        boolean cancelar = false;
        String entrada;
        Scanner s = new Scanner(System.in);
        
        while (!cancelar)
        {
            try
            {
                System.out.println("##### ALTERACAO - CADASTRO CLIENTE #####");
                System.out.println("Tecle 'S' em seguida Enter para cancelar.");
                
                
                
                while (!cancelar)
                {
                    System.out.print("> NOME ["+cliente.getNome()+"]: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else if (!entrada.trim().equals("")) 
                        {
                            cliente.setNome(entrada.toUpperCase());
                            break;
                        }
                        else
                            break;
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
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else if(!entrada.trim().equals(""))
                        {
                            cliente.setEndereco(entrada.toUpperCase());
                            break;
                        }
                        else
                            break;
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
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else if (!entrada.trim().equals(""))
                        {
                            cliente.setTelefone(Long.parseLong(entrada));
                            break;
                        }
                        else
                            break;
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
                        if (entrada.equalsIgnoreCase("S"))
                            cancelar = true;
                        else if (!entrada.trim().equals(""))
                        {
                            cliente.setSenha(Long.parseLong(entrada));
                            
                            while (!cancelar)
                            {
                                System.out.print("> CONFIRMA SENHA:");
                                entrada = s.nextLine();
                                
                                if (entrada.equalsIgnoreCase("S"))
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
                                        return;
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
                            return;
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
    }
    
    
    
    
    private static Cliente loginAltCliente()
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
                System.out.println("Tecle 'S' em seguida Enter para cancelar.");
                
                while (!cancelar)
                {
                    System.out.print("> CPF/CNPJ: ");
                    entrada = s.nextLine();
                    
                    try
                    {
                        if (entrada.equalsIgnoreCase("S"))
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
                        if (entrada.equalsIgnoreCase("S"))
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
