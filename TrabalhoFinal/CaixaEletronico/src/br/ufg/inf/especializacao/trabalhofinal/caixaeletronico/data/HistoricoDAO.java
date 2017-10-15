/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Historico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renato
 */
public class HistoricoDAO {

    private final String TABLE_NAME = "HISTCONTA";
    private final String COLUMN_ID = "ID";
    private final String COLUMN_CONTA = "CONTA";
    private final String COLUMN_AGENCIA = "AGENCIA";
    private final String COLUMN_OPERACAO = "OPERACAO";
    private final String COLUMN_VALOR = "VALOR";
    private final String COLUMN_DATA = "DATA";
    
    private Conta conta = null;
    
    public HistoricoDAO(Conta conta) {
        this.conta = conta;
    }
    
    public void setTransacao(String operacao, double valor) throws SQLException {
       
        final String SQL = "INSERT INTO " + TABLE_NAME + "(" 
                + COLUMN_CONTA + "," 
                + COLUMN_AGENCIA + "," 
                + COLUMN_OPERACAO + "," 
                + COLUMN_VALOR 
                + ") VALUES (" 
                + conta.getConta() + "," 
                + conta.getAgencia() + "," 
                + "'" + operacao + "',"
                + valor + ")";
        Connection connection = ConnectionUtils.getConnection();
        
        Statement cmd = null;
        ResultSet res;
        
        try {
            cmd = connection.createStatement();
            cmd.executeUpdate(SQL);          
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(cmd !=null) {
                cmd.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public List getExtrato(String dtIni, String dtFim) throws SQLException{
        String sql = "SELECT * FROM " + TABLE_NAME 
                + " WHERE " + COLUMN_CONTA + " = " + conta.getConta();
        if(dtIni!=null && dtFim!=null){
            sql = sql + " AND " + COLUMN_DATA + " >= '" + dtIni + " 00:00:00'";
            sql = sql + " AND " + COLUMN_DATA + " <= '" + dtFim + " 23:59:59'";
        }else{        
            sql = sql + " LIMIT 5";
        }
        Connection connection = null;
        PreparedStatement statement = null;
        
        List historicoList = new ArrayList();
        
        try{
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                Historico historico = new Historico();
                
                historico.setId(result.getLong(COLUMN_ID));
                historico.setAgencia(result.getLong(COLUMN_AGENCIA));
                historico.setConta(result.getLong(COLUMN_CONTA));
                historico.setOperacao(result.getString(COLUMN_OPERACAO));
                historico.setValor(result.getDouble(COLUMN_VALOR));
                historico.setData(result.getString(COLUMN_DATA));

                historicoList.add(historico);
            }

        } catch (SQLException e){
            throw e;
        } finally{
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        
        return historicoList;
        
    }
    
}
