package VIEW;
import DAO.UsuariosDAO;
import DTO.UsuariosDTO;
import LOG.Log;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;

public class DARKListaUsuarios extends javax.swing.JFrame {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Log.class);
    
    public DARKListaUsuarios() {
        initComponents();
        listarUsuarios();
        setLayout(new FlowLayout());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lista de Usuários");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        tabelaUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tabelaUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "E-mail", "Cargo", "Processos"
            }
        ));
        tabelaUsuarios.setCellSelectionEnabled(true);
        tabelaUsuarios.setGridColor(new java.awt.Color(255, 255, 255));
        tabelaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaUsuarios);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuariosMouseClicked
        if(evt.getClickCount()==2){    
            DARKJanelaDadosUsuario jaldados = new DARKJanelaDadosUsuario();
            LOGGER.info("O administrador irá editar os dados de um usuário.");
            jaldados.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_tabelaUsuariosMouseClicked

    private void listarUsuarios(){
        try{
            UsuariosDAO usudao = new UsuariosDAO();
            DefaultTableModel model = (DefaultTableModel) tabelaUsuarios.getModel();
            model.setNumRows(0);
            ArrayList<UsuariosDTO> lista = usudao.pesquisarUsuario();
            
            for(int i=0;i<lista.size();i++){
                model.addRow(new Object[]{
                   lista.get(i).getNome_usuario(),
                   lista.get(i).getEmail_usuario(),
                   lista.get(i).getCargo_usuario(),
                   lista.get(i).getId_processo_associado()
                });
            }
            
            LOGGER.info("Os usuários cadastrados foram listados com sucesso.");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, "Listar Usuarios" + erro);
            LOGGER.error("Os usuáriso cadastrados não foram listados.");
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaUsuarios;
    // End of variables declaration//GEN-END:variables
}
