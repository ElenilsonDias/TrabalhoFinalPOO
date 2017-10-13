/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Conta;
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
public class ContaDAO {
    private final String TABLE_NAME = "CONTA";
    private final String COLUMN_CONTA = "CONTA";
    private final String COLUMN_AGENCIA = "AGENCIA";
    private final String COLUMN_TIPO = "TIPO";
    private final String COLUMN_CPFCNPJ = "CPFCNPJ";
    private final String COLUMN_SALDO = "SALDO";
    private final String COLUMN_SENHA = "SENHA";
    
    public long create(Conta conta) throws SQLException {
        long retConta = 0;
        
        final String SQL = "INSERT INTO " + TABLE_NAME + "(" 
                //+ COLUMN_CONTA + "," 
                + COLUMN_AGENCIA + "," 
                + COLUMN_TIPO + "," 
                + COLUMN_CPFCNPJ + "," 
                + COLUMN_SALDO + "," 
                + COLUMN_SENHA 
                + ") VALUES (" 
                //+ conta.getConta() + "," 
                + conta.getAgencia() + "," 
                + conta.getTipo() + ","
                + conta.getCpfcnpj() + ","
                + conta.getSaldo() + ","
                + conta.getSenha() + ")";
        Connection connection = ConnectionUtils.getConnection();
        
        Statement cmd = null;
        ResultSet res;
        
        try {
            cmd = connection.createStatement();
            cmd.executeUpdate(SQL);
            
            res = cmd.executeQuery("SELECT last_insert_rowid()");
            if (res.next()) {
                retConta = res.getLong(1);
            }
            
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
        return retConta; //  Retorna Conta Autoincrement
    }
    
    public Conta getByCod(long nconta) throws SQLException {
        final String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_CONTA + "=" + nconta + ";";
        Connection connection = ConnectionUtils.getConnection();
        Statement cmd = null;
        ResultSet res = null;
        try {
            cmd = connection.createStatement();
            res = cmd.executeQuery(QUERY);

            if (!res.next()) {
                throw new RuntimeException("Conta inexistente.");
            }
            
            Conta conta = new Conta();
            //conta.setConta(res.getLong(COLUMN_CONTA));
            conta.setAgencia(res.getLong(COLUMN_AGENCIA));
            conta.setTipo(res.getLong(COLUMN_TIPO));
            conta.setCpfcnpj(res.getLong(COLUMN_CPFCNPJ));
            conta.setSaldo(res.getLong(COLUMN_SALDO));
            conta.setSenha(res.getLong(COLUMN_SENHA));
            return conta;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        throw new SQLException("Conta nº " + nconta + " não encontrada!");
    }
    
    public List<Conta> getAll() throws SQLException{
        final String sql = "SELECT * FROM " + TABLE_NAME;
        Connection connection = null;
        PreparedStatement statement = null;
        
        List<Conta> contas = new ArrayList();
        
        try{
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Conta conta = new Conta();
                //conta.setConta(res.getLong(COLUMN_CONTA));
                conta.setAgencia(res.getLong(COLUMN_AGENCIA));
                conta.setTipo(res.getLong(COLUMN_TIPO));
                conta.setCpfcnpj(res.getLong(COLUMN_CPFCNPJ));
                conta.setSaldo(res.getLong(COLUMN_SALDO));
                conta.setSenha(res.getLong(COLUMN_SENHA));
                contas.add(conta);
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
        return contas;
        
    }
    
    public boolean update(Conta conta) throws SQLException {
        String sqlUpdate = "UPDATE " + TABLE_NAME + " SET " 
                //+ COLUMN_CONTA + " = ?, "
                + COLUMN_AGENCIA + " = ?, "
                + COLUMN_TIPO + " = ?, "
                + COLUMN_CPFCNPJ + " = ?, "
                + COLUMN_SALDO + " = ?, "
                + COLUMN_SENHA + " = ?"
                +" WHERE " + COLUMN_CONTA + "= ?";
        Connection connection = null;
        PreparedStatement procedure = null;
            connection = ConnectionUtils.getConnection();
            try { 
                procedure = connection.prepareStatement(sqlUpdate);
                //procedure.setLong(1, conta.getConta());               
                procedure.setLong(1, conta.getAgencia());
                procedure.setLong(2, conta.getTipo());
                procedure.setLong(3, conta.getCpfcnpj());
                procedure.setLong(4, conta.getSaldo());
                procedure.setLong(5, conta.getSenha());
                
                int res = procedure.executeUpdate();
                if(res == 1){
                    connection.commit();
                    return true;
                }     
            }catch (SQLException e) {
                throw new SQLException(e.getMessage());
            } finally {
                if(procedure != null){
                    procedure.close();
                }
                if(connection != null){
                    connection.close();
                }
            }
            return false;
    }
    
    public void delete(Conta conta) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME
                + " WHERE " + COLUMN_CONTA + "=" + conta.getConta() + ";";
        ConnectionUtils.executeVoidQuery(query);
    }
    
}
