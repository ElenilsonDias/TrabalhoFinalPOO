/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.data;

import br.ufg.inf.especializacao.trabalhofinal.caixaeletronico.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elenilson
 */
public class ClienteDAO {
     private final String TABLE_NAME = "CLIENTE";
    private final String COLUMN_CPFCNPJ = "CPFCNPJ";
    private final String COLUMN_NAME = "NOME";
    private final String COLUMN_ENDERECO = "ENDERECO";
    private final String COLUMN_TELEFONE = "TELEFONE";
    private final String COLUMN_SENHA = "SENHA";
    
    public void create(Cliente cliente) throws SQLException {
        
        final String SQL = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_CPFCNPJ 
                + "," + COLUMN_NAME + "," + COLUMN_ENDERECO + "," 
                + COLUMN_TELEFONE + "," + COLUMN_SENHA+ ") VALUES (" 
                + cliente.getCpfcnpj()+ ",\'" +  cliente.getNome() + "\'"
                +",\'"+cliente.getEndereco()+"\',"+cliente.getTelefone()+","
                +cliente.getSenha()+ ")";
        Connection connection = ConnectionUtils.getConnection();
        
        Statement comando = null;
        ResultSet resultado = null;
        try {
            comando = connection.createStatement();
            comando.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(comando !=null) {
                comando.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public Cliente getByCod(long cpfcnpj) throws SQLException {
        final String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_CPFCNPJ + "=" + cpfcnpj + ";";
        Connection connection = ConnectionUtils.getConnection();
        Statement comando = null;
        ResultSet resultado = null;
        try {
            comando = connection.createStatement();
            resultado = comando.executeQuery(QUERY);

            if (!resultado.next()) {
                throw new RuntimeException("Não existe Cliente com o CPF/CNPJ"+
                        " informado.");
            }

            Cliente cliente = new Cliente();
            cliente.setCpfcnpj(resultado.getLong(COLUMN_CPFCNPJ));
            cliente.setNome(resultado.getString(COLUMN_NAME));
            cliente.setEndereco(resultado.getString(COLUMN_ENDERECO));
            cliente.setTelefone(resultado.getLong(COLUMN_TELEFONE));
            cliente.setSenha(resultado.getLong(COLUMN_SENHA));
            return cliente;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
        throw new SQLException("Cliente não encontrada com o CPF/CNPJ: "
                + cpfcnpj);
    }
    
    public List<Cliente> getAll() throws SQLException{
        final String sql = "SELECT * from " + TABLE_NAME;
        Connection connection = null;
        PreparedStatement statement = null;
        
        List<Cliente> clientes = new ArrayList();
        
        try{
            connection = ConnectionUtils.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Cliente cliente = new Cliente();
                cliente.setCpfcnpj(result.getLong(COLUMN_CPFCNPJ));
                cliente.setNome(result.getString(COLUMN_NAME));
                cliente.setEndereco(result.getString(COLUMN_ENDERECO));
                cliente.setTelefone(result.getInt(COLUMN_TELEFONE));
                cliente.setSenha(result.getInt(COLUMN_SENHA));

                clientes.add(cliente);
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
        
        return clientes;
        
    }
    
    public boolean update(Cliente cliente) throws SQLException {
        String sqlUpdate = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME 
                + " = ?, "+COLUMN_ENDERECO+" = ?, "+COLUMN_TELEFONE+" = ?, "
                + COLUMN_SENHA + " = ?"
                +" WHERE " + COLUMN_CPFCNPJ + "= ?";
        Connection connection = null;
        PreparedStatement procedure = null;
            connection = ConnectionUtils.getConnection();
            try { 
                procedure = connection.prepareStatement(sqlUpdate);
                procedure.setString(1, cliente.getNome());               
                procedure.setString(2, cliente.getEndereco());
                procedure.setLong(3, cliente.getTelefone());
                procedure.setLong(4, cliente.getSenha());
                 procedure.setLong(5, cliente.getCpfcnpj());
                int result = procedure.executeUpdate();
                if(result == 1){
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
    
    public void delete(Cliente cliente) throws SQLException {
        String query = "DELETE from " + TABLE_NAME
                + " where " + COLUMN_CPFCNPJ + "=" + cliente.getCpfcnpj()+ ";";

        ConnectionUtils.executeVoidQuery(query);
    }

}
