package dao;
import Model.Medico;
import java.sql.Connection;
import utilitario.Conectar;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MedicoDAO {
    public void cadastrar( Medico m){
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO MEDICO(CRM,NOME,ESPECIALIDADE,EMAIL,TELEFONE)"
                +"VALUES (?,?,?,?,?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,m.getCrm());
            smt.setString(2,m.getNome());
            smt.setString(3,m.getEspecialidade());
            smt.setString(4,m.getEmail());
            smt.setString(5,m.getTelefone());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Medico cadastrado com sucesso!!!");
       }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar o Medico!!!"+e.getMessage());
        }
    }
    public void atualizar(Medico m){
        Connection con = Conectar.getConectar();
        String sql = "UPDATE MEDICO SET CRM= ?,NOME = ?, ESPECIALIDADE = ?, EMAIL = ?, TELEFONE = ? ";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,m.getCrm());
            smt.setString(2,m.getNome());
            smt.setString(3,m.getEspecialidade());
            smt.setString(4,m.getEmail());
            smt.setString(5,m.getTelefone());
            //smt.setInt(6,m.getId_medico());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Registro atualizado com suceeso!!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao atualizar o refistro"+ e.getMessage());
        }
    }
    public void excluir(Medico m){
        Connection con = Conectar.getConectar();
        String sql = "DELETE FROM MEDICO WHERE ID_MEDICO = ?";
        int opcao = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir"+m.getNome()+"?","Excluir", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1, m.getId_medico());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Registro Excluido com sucesso!!");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Erro ao excluir registro!!"+e.getMessage());
            }
        }
    }
    public List<Medico> listarTodos(){
        Connection con = Conectar.getConectar();
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM MEDICO ORDER BY NOME ";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet rs = smt.executeQuery();
            while(rs.next()){
                Medico m = new Medico();
                m.setId_medico(rs.getInt("id_medico"));
                m.setCrm(rs.getString("crm"));
                m.setNome(rs.getString("nome"));
                m.setEspecialidade(rs.getString("especialidade"));
                m.setEmail(rs.getString("email"));
                m.setTelefone(rs.getString("telefone"));
                lista.add(m);
            }
            smt.close();
            con.close();
        }catch (Exception e ){
            JOptionPane.showMessageDialog(null, "Erro ao listar Medicos!!!"+e.getMessage());
        }
        return lista;
    }
}
