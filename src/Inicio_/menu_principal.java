package Inicio_;

public class menu_principal extends javax.swing.JFrame {

    public menu_principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        REGISTRARSE = new javax.swing.JButton();
        LOGIN = new javax.swing.JButton();
        SALIR = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setBackground(new java.awt.Color(0, 0, 0));
        titulo.setFont(new java.awt.Font("MS Gothic", 1, 48)); // NOI18N
        titulo.setText("SEQUENCE");
        titulo.setOpaque(true);
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 210, 90));

        REGISTRARSE.setBackground(new java.awt.Color(0, 0, 0));
        REGISTRARSE.setFont(new java.awt.Font("MS Gothic", 1, 18)); // NOI18N
        REGISTRARSE.setText("REGISTRARSE");
        REGISTRARSE.setOpaque(true);
        getContentPane().add(REGISTRARSE, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 160, 60));

        LOGIN.setBackground(new java.awt.Color(0, 0, 0));
        LOGIN.setFont(new java.awt.Font("MS Gothic", 1, 18)); // NOI18N
        LOGIN.setText("LOGIN");
        LOGIN.setOpaque(true);
        getContentPane().add(LOGIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 160, 60));

        SALIR.setBackground(new java.awt.Color(0, 0, 0));
        SALIR.setFont(new java.awt.Font("MS Gothic", 1, 18)); // NOI18N
        SALIR.setText("SALIR");
        SALIR.setOpaque(true);
        getContentPane().add(SALIR, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 160, 60));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/FONDO_MENUPSEQUENCE.jpg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LOGIN;
    private javax.swing.JButton REGISTRARSE;
    private javax.swing.JButton SALIR;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
