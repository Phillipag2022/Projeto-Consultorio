package dao;

import Model.Paciente;

import java.sql.Connection;
import utilitario.Conectar;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;




public class PacienteDAO {
    public void cadastrar(Paciente p) {
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO PACIENTE (NOME,CPF,EMAIL,DATA_NASC,SEXO,TELEFONE)"
        +"VALUES(?,?,?,?,?,?)";
        
        try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setString(1, p.getNome());
                smt.setString(2, p.getCpf());
                smt.setString(3, p.getEmail());
                smt.setString(4, p.getDatanasc());
                smt.setString(5, p.getSexo());
                smt.setString(6, p.getTelefone());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Paciente cadastrado com suceeso!!");
         } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar paciente"+ e);
         }
     }
    public void atualizar(Paciente p){
          Connection con = Conectar.getConectar();
          String sql = "UPDATE PACIENTE SET NOME = ?, CPF = ?, EMAIL = ?, DATA_NASC = ?, SEXO = ?, TELEFONE = ? WHERE ID_PACIENTE = ?";
          
           try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1,p.getId_paciente());
                smt.setString(2, p.getNome());
                smt.setString(3, p.getCpf());
                smt.setString(4, p.getEmail());
                smt.setString(5, p.getDatanasc());
                smt.setString(6, p.getSexo());
                smt.setString(7, p.getTelefone());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Paciente atualizado com suceeso!!");
         } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao atualizar o refistro"+ e);
         }
     }
    public void excluir(Paciente p){
         Connection con = Conectar.getConectar();
         String sql = "DELETE FROM PACIENTE WHERE ID_PACIENTE = ?";
         int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o resgistro "+p.getNome()+"?","Excluir",JOptionPane.YES_NO_OPTION);
         if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1,p.getId_paciente());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Paciente excluido com suceeso!!");
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao excluir o refistro"+ e.getMessage());
            }
         }
    }
    public List<Paciente> listarTodos(){
        Connection con = Conectar.getConectar();
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE ORDER BY NOME";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet rs = smt.executeQuery();
            while (rs.next()){
                Paciente p = new Paciente();
                 
                p.setId_paciente(rs.getInt("id_paciente"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setEmail(rs.getString("email"));
                p.setDatanasc(rs.getString("data_nasc"));
                p.setSexo(rs.getString("sexo"));
                p.setTelefone(rs.getString("telefone"));
                lista.add(p);
            }
            smt.close();
            con.close();
             
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao excluir o refistro"+ e.getMessage());
            }
        return lista;
}
    }
        

