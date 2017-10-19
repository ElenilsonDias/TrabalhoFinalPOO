/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.ContaDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data.HistoricoDAO;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Historico;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.sqlite.util.StringUtils;

/**
 *
 * @author Elenilson
 */
public class MenuSaldoExtrato implements MenuInterface{

    private Conta conta = null;
    
    public MenuSaldoExtrato(Conta conta)
    {
        this.conta = conta;
        
    }
 
    @Override
    public boolean showMenu() {
        boolean voltar = false, sair = false;
        int opcao;
        String entrada;
        Scanner s = new Scanner(System.in);
        
        SimpleDateFormat dtTimeBR = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat dtBR = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dtUS = new SimpleDateFormat("yyyy-MM-dd"); 
        String strData, linhaImpressao;
        dtTimeBR.setLenient(false);
        
        String dataInicial=null, dataFinal=null,dataInicialUS=null, dataFinalUS=null;;
        
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
                        System.out.println("##### SALDO #####");
                        System.out.println("> R$ " + String.format("%.2f",conta.getSaldo()));
                        s.nextLine();
                        
                    }break;
                    case 2:
                    {
                        HistoricoDAO historicoDAO = new HistoricoDAO(conta);
                        try {
                            List<Historico> historicoList = historicoDAO.getExtrato(null,null);
                            System.out.println();
                            System.out.println();
                            
                            
                            linhaImpressao = String.format("| %-20s|", "Data/Hora")+
                                    String.format(" %-40s|", "Operação") +
                                    String.format(" %-12s|", "Valor(R$)");
                            
                            System.out.println(String.join("", Collections.nCopies(linhaImpressao.length()/2-4,"#"))
                                    +" EXTRATO "
                                    +String.join("", Collections.nCopies(linhaImpressao.length()/2-4,"#")));
                            
                            System.out.println(String.join("", Collections.nCopies(linhaImpressao.length(),"-")));
                            System.out.println(linhaImpressao);
                            System.out.println(String.join("", Collections.nCopies(linhaImpressao.length(),"-")));
                            
                            for(Historico umHistorico : historicoList){
                                strData = umHistorico.getData();
                                strData = dtTimeBR.format(dtUS.parse(strData));
                                System.out.println(String.format("| %-20s|", strData)+
                                       String.format(" %-40s|", umHistorico.getOperacao())+
                                       String.format(" %-12s|", String.format("%.2f",umHistorico.getValor())));
                                
                                System.out.println(String.join("", Collections.nCopies(linhaImpressao.length(),"-")));
                            }
                            System.out.println();
                            
                            s = new Scanner(System.in);
                            s.nextLine();
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
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
                                  dataInicial = entrada.trim();
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
                                    dataFinal = entrada.trim();
                                    
                                    //System.out.println();
                                    //System.out.println("##### EXTRATO DE "+dataInicial+" ATE "+dataFinal+" #####");
                                    
                                    dataInicialUS = dtUS.format((Date)dtBR.parse(dataInicial));
                                    dataFinalUS = dtUS.format((Date)dtBR.parse(dataFinal));
                                    
                                    HistoricoDAO historicoDAO = new HistoricoDAO(conta);
                                    try {
                                        List<Historico> historicoList = historicoDAO.getExtrato(dataInicialUS,dataFinalUS);
                                        
                                        
                                        /*for(Historico umHistorico : historicoList){
                                            //strData = umHistorico.getData();
                                            //strData = dtBr.format((Date)dtUS.parse(strData));
                                            strData = dtBR.format(dtUS.parse(umHistorico.getData()));
                                            System.out.println(strData+" - "+umHistorico.getOperacao()+" - "+String.format("%.2f",umHistorico.getValor()));
                                        }
                                        System.out.println();*/
                                        
                                        
                                        System.out.println();
                                        System.out.println();


                                        linhaImpressao = String.format("| %-20s|", "Data/Hora")+
                                                String.format(" %-40s|", "Operação") +
                                                String.format(" %-12s|", "Valor(R$)");

                                    
                                        System.out.println(String.join("", Collections.nCopies(linhaImpressao.length()/2-19,"#"))
                                                +" EXTRATO DE "+dataInicial+" ATE "+dataFinal+" "
                                                +String.join("", Collections.nCopies(linhaImpressao.length()/2-19,"#")));

                                        System.out.println(String.join("", Collections.nCopies(linhaImpressao.length(),"-")));
                                        System.out.println(linhaImpressao);
                                        System.out.println(String.join("", Collections.nCopies(linhaImpressao.length(),"-")));

                                        for(Historico umHistorico : historicoList){
                                            strData = umHistorico.getData();
                                            strData = dtTimeBR.format(dtUS.parse(strData));
                                            System.out.println(String.format("| %-20s|", strData)+
                                                   String.format(" %-40s|", umHistorico.getOperacao())+
                                                   String.format(" %-12s|", String.format("%.2f",umHistorico.getValor())));

                                            System.out.println(String.join("", Collections.nCopies(linhaImpressao.length(),"-")));
                                        }
                                        System.out.println();

                                        s = new Scanner(System.in);
                                        s.nextLine();
                                        
                                        
                                    } catch (SQLException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    
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
