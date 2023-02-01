package dao;

import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilitario.Conectar;


public class FuncionarioDAO {
    public void cadastrar( Funcionario f){
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO FUNCIONARIO (NOME,CPF,DATA_ADMISSAO,EMAIL,TELEFONE,SENHA) VALUES (?,?,?,?,?,?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,f.getNome());
            smt.setString(2,f.getCpf());
            smt.setString(3,f.getData_admissao());
            smt.setString(4,f.getEmail());
            smt.setString(5,f.getTelefone());
            smt.setString(6,f.getSenha());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Medico cadastrado com sucesso!!!");
       }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar o funcionario!!!"+e.getMessage());
        }
    }
    public void atualizar(Funcionario f){
        Connection con = Conectar.getConectar();
        String sql = "UPDATE  SET NOME= ?,CPF = ?, DATA_ADMISSAO = ?, EMAIL = ?, TELEFONE = ?, SENHA = ?, ID_FUNCIONARIO = ? ";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,f.getNome());
            smt.setString(2,f.getCpf());
            smt.setString(3,f.getData_admissao());
            smt.setString(4,f.getEmail());
            smt.setString(5,f.getTelefone());
            smt.setString(6,f.getSenha());
            smt.setInt(7,f.getId_funcionario());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Registro atualizado com suceeso!!");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar o refistro"+ e.getMessage());
        }
    }
    public void excluir(Funcionario f){
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM FUNCIONARIO WHERE ID_FUNC = ?";
        int opcao = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir"+f.getNome()+"?","Excluir", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1, f.getId_funcionario());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Registro Excluido com sucesso!!");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Erro ao excluir registro!!"+e.getMessage());
            }
        }
    }
    public List<Funcionario> listarTodos()  {
        Connection con = Conectar.getConectar();
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM FUNCIONARIO ORDER BY NOME";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet rs = smt.executeQuery();
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setId_funcionario(rs.getInt("id_func"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setData_admissao(rs.getString("data_admissao"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getString("telefone"));
                f.setSenha(rs.getString("senha"));
                lista.add(f);
            }
            smt.close();
            con.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar todos os registros!!!"+e.getMessage());
        }
        
       return lista;         
    }
    
}
