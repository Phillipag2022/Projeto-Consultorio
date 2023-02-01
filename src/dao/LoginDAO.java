/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.Funcionario;
import java.sql.Connection;
import utilitario.Conectar;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
/**
 *
 * @author PC-ADMIN
 */
public class LoginDAO {
    
    public ResultSet AutenticacaoFuncionario(Funcionario f){
    Connection con = Conectar.getConectar();
    
    try{ 
    String sql = "SELECT * FROM FUNCIONARIO WHERE  CPF = ? AND SENHA = ?";
    
    PreparedStatement smt = con.prepareStatement(sql);
    smt.setString(1, f.getCpf());
    smt.setString(2, f.getSenha());
    
    ResultSet rs = smt.executeQuery();
    return rs;
    
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Erro ao tentar fazer login!!" + ex.getMessage());
    }
        return null;
}
}
