package dao;

import Model.Consulta;
import Model.Funcionario;
import Model.Medico;
import Model.Paciente;
import utilitario.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ConsultaDAO {
    
    public void cadastrar(Consulta c){
        Connection con = Conectar.getConectar();
        String sql = "INSERT INTO CONSULTA (DATA_ATEND,HORARIO,ID_FUNC,ID_PACIENTE,ID_MEDICO) VALUES(?,?,?,?,?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, c.getData_atendimento());
            smt.setString(2, c.getHorario());
            smt.setInt(3, c.getFuncionario().getId_funcionario());
            smt.setInt(4,c.getPaciente().getId_paciente());
            smt.setInt(5, c.getMedico().getId_medico());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Consulta Cadastrada com sucesso!!!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Errro ao cadastrar consulta!!!"+ex.getMessage());
        }
    }
    public void Atualizar(Consulta c){
        Connection con = Conectar.getConectar();
        String sql = "UPDATE CONSULTA SET DATA_ATEND =?, HORARIO = ?, ID_FUNC = ?, ID_PACIENTE = ?, ID_MEDICO = ? WHERE ID_CONSULTA = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, c.getData_atendimento());
            smt.setString(2, c.getHorario());
            smt.setInt(3, c.getFuncionario().getId_funcionario());
            smt.setInt(4, c.getPaciente().getId_paciente());
            smt.setInt(5, c.getMedico().getId_medico());
            smt.setInt(6, c.getId_consulta());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Consulta stualizada com sucesso!!!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Errro ao atualizar consulta!!!"+ex.getMessage());
        }
    }
    public void excluir(Consulta c){
         Connection con = Conectar.getConectar();
         String sql = "DELETE FROM CONSULTA WHERE ID_CONSULTA = ?";
         int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o resgistro "+c.getId_consulta()+"?","Excluir",JOptionPane.YES_NO_OPTION);
         if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1,c.getId_consulta());
                smt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Consulta excluida com suceeso!!");
                smt.close();
                con.close();
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao excluir o refistro"+ e.getMessage());
            }
         }
    }
    public List <Consulta> listarTodos(){
        Connection con = Conectar.getConectar();
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA C right JOIN PACIENTE P \n" +
"              ON P.ID_PACIENTE = C.ID_PACIENTE right JOIN MEDICO M \n" +
"			  ON M.ID_MEDICO = C.ID_MEDICO right JOIN FUNCIONARIO F \n" +
"			  ON F.ID_FUNC = C.ID_FUNC";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet rs = smt.executeQuery();
            while (rs.next()){
                Consulta c = new Consulta();
                 
                c.setId_consulta(rs.getInt("id_consulta"));
                c.setData_atendimento(rs.getString("data_atend"));
                c.setHorario(rs.getString("horario"));
                
                Funcionario f = new Funcionario();
                f.setId_funcionario(rs.getInt("id_func"));
                f.setNome(rs.getString("nome"));
                c.setFuncionario(f);    
                   
                Paciente p = new Paciente();
                p.setId_paciente(rs.getInt("id_paciente"));
                p.setNome(rs.getString("nome"));
                c.setPaciente(p);
                
                Medico m = new Medico();
                m.setId_medico(rs.getInt("id_medico"));
                m.setNome(rs.getString("nome"));
                c.setMedico(m);
                
                lista.add(c);
            }
            smt.close();
            con.close();
             
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao listar os refistros!! "+ e.getMessage());
            }
        return lista;
    }
    public List<Consulta> buscarData(String datapesquisa){
        Connection con = Conectar.getConectar();
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA C right JOIN PACIENTE P \n" +
"              ON P.ID_PACIENTE = C.ID_PACIENTE right JOIN MEDICO M \n" +
"			  ON M.ID_MEDICO = C.ID_MEDICO right JOIN FUNCIONARIO F \n" +
"			  ON F.ID_FUNC = C.ID_FUNC where data_atend = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,datapesquisa);
            ResultSet rs = smt.executeQuery();
            while (rs.next()){
                Consulta c = new Consulta();
                 
                c.setId_consulta(rs.getInt("id_consulta"));
                c.setData_atendimento(rs.getString("data_atend"));
                c.setHorario(rs.getString("horario"));
                
                Funcionario f = new Funcionario();
                f.setId_funcionario(rs.getInt("id_func"));
                f.setNome(rs.getString("nome"));
                c.setFuncionario(f);    
                   
                Paciente p = new Paciente();
                p.setId_paciente(rs.getInt("id_paciente"));
                p.setNome(rs.getString("nome"));
                c.setPaciente(p);
                
                Medico m = new Medico();
                m.setId_medico(rs.getInt("id_medico"));
                m.setNome(rs.getString("nome"));
                c.setMedico(m);
                
                lista.add(c);
            }
            smt.close();
            con.close();
             
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao listar os refistros!! "+ e.getMessage());
            }
        return lista;
    }
    
        
    }


