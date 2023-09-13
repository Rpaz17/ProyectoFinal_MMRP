
package Juego;

import Configuracion.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MIguel
 */
public class Login extends javax.swing.JFrame {
    DatosUsuario datos;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setSize(818, 538);
        setLocationRelativeTo(this);
        this.datos=new DatosUsuario();
        try {
            datos.addJUgadorArray();
        } catch (IOException e) {
        }
    }
    
    public DatosUsuario getDatos(){
        return datos;
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        btnCreatePlayer = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsername);
        txtUsername.setBounds(300, 200, 240, 30);

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword);
        txtPassword.setBounds(300, 280, 240, 30);

        btnCreatePlayer.setBackground(new java.awt.Color(255, 255, 255));
        btnCreatePlayer.setFont(new java.awt.Font("Consolas", 3, 18)); // NOI18N
        btnCreatePlayer.setText("Crear Jugador");
        btnCreatePlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePlayerActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreatePlayer);
        btnCreatePlayer.setBounds(80, 380, 220, 40);

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Consolas", 3, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(600, 240, 180, 40);

        jLabel3.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(80, 280, 200, 50);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 60, 0, 0);

        jLabel4.setFont(new java.awt.Font("Consolas", 3, 24)); // NOI18N
        jLabel4.setText("Nombre Usuario:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(80, 200, 230, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(240, 90, 230, 30);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Consolas", 3, 18)); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(380, 380, 150, 40);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Menuu.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(170, 20, 510, 110);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnCreatePlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePlayerActionPerformed
        // TODO add your handling code here:;
        
        Create_Player player=new Create_Player(this,datos);
        player.setVisible(true);
         txtUsername.setText("");
         txtPassword.setText("");
        this.dispose();
    }//GEN-LAST:event_btnCreatePlayerActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        //Validacion no dejar espacios vacios
         if (txtUsername.getText().isEmpty()||txtPassword.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Llene todos los campos.");
    } else {
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        
        Usuarios user=datos.buscarUsuario(username,0);
        //validacion de usuario y contrasena para luego abrir menu principal
        if(user !=null){
            if(user.getPassword().equals(password)){
                Menu menu=new Menu(this,datos);
                menu.setVisible(true);
                this.datos.UsuarioLogeado=username;
                txtUsername.setText("");
                txtPassword.setText("");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "La contrasena es incorrecta");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El username no existe");
        }
         }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreatePlayer;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
