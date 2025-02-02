package DAO;

import DTO.ProcessosDTO;
import LOG.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessosDAO{
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProcessosDTO> tabela = new ArrayList<>();
    private int[] processos;
    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);
    
    public ResultSet listarProcessos(){
        conn = new ConexaoDAO().conectaDB();
        String sql = "SELECT * FROM processos ORDER BY id_processo";
        
        try{
            pstm = conn.prepareStatement(sql);
            LOGGER.info("Os processos foram carregados com sucesso.");
            return pstm.executeQuery();
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro em Listar Processos!" + erro);
            LOGGER.error("Os processos não foram carregados");
            return null;
        }
    }
    
    public ArrayList<ProcessosDTO> pesquisarProcessos(){
        String sql = "SELECT * FROM processos";
        conn = new ConexaoDAO().conectaDB();
        
        try{
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                
                for(int i=0;i< CredencialDAO.qProcessos; i++)
                {
                    if(CredencialDAO.processos[i] == rs.getInt("id_processo"))
                    {                        
                        ProcessosDTO prodto = new ProcessosDTO();
                        prodto.setId_processo(rs.getInt("id_processo"));
                        prodto.setCliente(rs.getString("cliente"));
                        prodto.setUsuario_associado(rs.getString("usuario_associado"));
                        tabela.add(prodto);
                        break;
                    }
                    
                }
            }
            
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "ProcessosDAO" + erro);
        }
        return tabela;
    }
    
    public void cadastrarProcessos(ProcessosDTO prodto){
        String sql = "INSERT INTO processos(id_processo, cliente, usuario_associado)"
                + "values (?,?,?)";
        
        conn = new ConexaoDAO().conectaDB();
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, prodto.getId_processo());
            pstm.setString(2, prodto.getCliente());
            pstm.setString(3, prodto.getUsuario_associado());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Processo cadastrado com sucesso!");
            LOGGER.info("Um processo foi cadastrado com sucesso.");
            pstm.close();
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "CadastrarProcessosDAO: " + erro);
            LOGGER.error("O processo não foi cadastrado.");
        }
    }
    
}