
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class personelekrani extends javax.swing.JFrame {

    static BankaPersoneli personel = new BankaPersoneli();
    DefaultTableModel tablo ;
    
    /**
     * Creates new form personelekrani
     */
    

    personelekrani(BankaPersoneli personel) {
         initComponents();
        populateTable();

    }

   

    
    public void populateTable(){
        tablo=(DefaultTableModel)tblmusteri.getModel();
        tablo.setRowCount(0);
        try {
            ArrayList<Musteri> musteriler = getMusteriler();
            for(Musteri musteri : musteriler){
                Object[] row= {musteri.getId(), musteri.getTcNo(), musteri.getAd(), musteri.getSoyad(), musteri.getTelefonNo(), musteri.getSifre()};
                tablo.addRow(row);
                
            }
        } catch (SQLException ex) {
            
        }
   } 
    
    public ArrayList<Musteri> getMusteriler() throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Musteri> musteriler= null;
        try{
           connection=dbHelper.getConnection();
           statement=connection.createStatement();
           resultSet= statement.executeQuery("select * from musterı");
           musteriler = new ArrayList<Musteri>();
           while(resultSet.next()){
               musteriler.add(new Musteri(resultSet.getInt("MUSTERIID"),
                                   resultSet.getString("TC_NUMARASI"),
                                   resultSet.getString("AD"),
                                   resultSet.getString("SOYAD"),
                                   resultSet.getString("TELEFON_NO"),
                                   resultSet.getString("SIFRE") ));
               
           }
           
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);        
        }finally{
            statement.close();
            connection.close();          
        }
        return musteriler;
    }

   
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ıdText = new javax.swing.JTextField();
        tcnotext = new javax.swing.JTextField();
        adtext = new javax.swing.JTextField();
        soyadtext = new javax.swing.JTextField();
        telefonnotext = new javax.swing.JTextField();
        sifretext = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        siltext = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        aramatext = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmusteri = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("MÜŞTERİ ID");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("MÜŞTERİNİN T.C. NUMARASI");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("MÜŞTERİNİN ADI");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("MÜŞTERİNİN SOYADI");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("MÜŞTERİNİN TELEFON NO");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("MÜŞTERİNİN ŞİFRESİ");

        ıdText.setBackground(new java.awt.Color(51, 51, 51));
        ıdText.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        ıdText.setForeground(new java.awt.Color(204, 204, 204));

        tcnotext.setBackground(new java.awt.Color(51, 51, 51));
        tcnotext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        tcnotext.setForeground(new java.awt.Color(204, 204, 204));

        adtext.setBackground(new java.awt.Color(51, 51, 51));
        adtext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        adtext.setForeground(new java.awt.Color(204, 204, 204));

        soyadtext.setBackground(new java.awt.Color(51, 51, 51));
        soyadtext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        soyadtext.setForeground(new java.awt.Color(204, 204, 204));

        telefonnotext.setBackground(new java.awt.Color(51, 51, 51));
        telefonnotext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        telefonnotext.setForeground(new java.awt.Color(204, 204, 204));

        sifretext.setBackground(new java.awt.Color(51, 51, 51));
        sifretext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        sifretext.setForeground(new java.awt.Color(204, 204, 204));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton1.setText("EKLE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        siltext.setBackground(new java.awt.Color(51, 51, 51));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton2.setText("SİL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("MÜŞTERİ ID");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel8.setText("ARAMA");

        aramatext.setBackground(new java.awt.Color(51, 51, 51));
        aramatext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        aramatext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aramatextActionPerformed(evt);
            }
        });
        aramatext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                aramatextKeyReleased(evt);
            }
        });

        tblmusteri.setBackground(new java.awt.Color(204, 204, 204));
        tblmusteri.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        tblmusteri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MUSTERIID", "TC_NUMARASI", "AD", "SOYAD", "TELEFON_NO", "SIFRE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblmusteri);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton3.setText("ÇIKIŞ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton4.setText("GERİ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siltext)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(sifretext)
                            .addComponent(ıdText)
                            .addComponent(tcnotext)
                            .addComponent(adtext)
                            .addComponent(soyadtext)
                            .addComponent(telefonnotext))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aramatext, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 305, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(aramatext, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ıdText, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tcnotext, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adtext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soyadtext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefonnotext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sifretext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(siltext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        if(tcnotext.getText().length()==0 || ıdText.getText().length()==0 || adtext.getText().length()==0 || soyadtext.getText().length()==0 || telefonnotext.getText().length()==0 || sifretext.getText().length()==0){
            String mesaji= "Lütfen boş alan bırakmayınız.";
            JOptionPane.showMessageDialog(rootPane, mesaji); 
        }else{
        
        try{
            connection=dbHelper.getConnection();
            String sql="insert into musterı (MUSTERIID,TC_NUMARASI,AD,SOYAD,TELEFON_NO,SIFRE) values(?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            if(tcnotext.getText().length()==11 && telefonnotext.getText().length()==11){
            statement.setInt(1,Integer.valueOf(ıdText.getText()));
            statement.setString(2,tcnotext.getText());
            statement.setString(3,adtext.getText());
            statement.setString(4,soyadtext.getText());
            statement.setString(5,telefonnotext.getText());
            statement.setString(6,sifretext.getText());
            int result = statement.executeUpdate();
            }else{
                String mesaji= "Telefon numarası ve T.C. numrası 11 rakamdan oluşmalıdır.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }
            populateTable();
            String kayitmesaji= "Müşteri eklendi.";
            JOptionPane.showMessageDialog(rootPane, kayitmesaji);
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                
            }
        }
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        if(siltext.getText().length()==0){
            String mesaji= "Lütfen silmek istediğiniz müşterinin ID'sini giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
        
        try{
            connection=dbHelper.getConnection();
            String sql="DELETE FROM musterı WHERE musterııd=? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,Integer.valueOf(siltext.getText()));
            int result = statement.executeUpdate();
            populateTable();
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                
            }
        }
        
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String cikismesaji= "ÇIKIŞ YAPILIYOR.";
        JOptionPane.showMessageDialog(rootPane, cikismesaji);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void aramatextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aramatextActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_aramatextActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        giriss girisi = new giriss();
        girisi.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void aramatextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aramatextKeyReleased
        // TODO add your handling code here:
        String arama = aramatext.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(tablo);
        tblmusteri.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(arama));
        
    }//GEN-LAST:event_aramatextKeyReleased

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
            java.util.logging.Logger.getLogger(personelekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(personelekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(personelekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(personelekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personelekrani(personel).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adtext;
    private javax.swing.JTextField aramatext;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField sifretext;
    private javax.swing.JTextField siltext;
    private javax.swing.JTextField soyadtext;
    private javax.swing.JTable tblmusteri;
    private javax.swing.JTextField tcnotext;
    private javax.swing.JTextField telefonnotext;
    private javax.swing.JTextField ıdText;
    // End of variables declaration//GEN-END:variables
}
