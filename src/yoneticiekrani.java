
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class yoneticiekrani extends javax.swing.JFrame {

    static BankaYoneticisi yonetici = new BankaYoneticisi();
    DefaultTableModel tablo ;
    /**
     * Creates new form yoneticiekrani
     */
    
    yoneticiekrani(BankaYoneticisi yonetici) {
        
        initComponents();
        populateTable();
        
    }
    
    
    public void populateTable(){
        tablo=(DefaultTableModel)tblpersonel.getModel();
        tablo.setRowCount(0);
        try {
            ArrayList<BankaPersoneli> personeller = getPersoneller();
            for(BankaPersoneli personel : personeller){
                Object[] row= {personel.getId(), personel.getTcNo(), personel.getAd(), personel.getSoyad(), personel.getTelefonNo(), personel.getSifre()};
                tablo.addRow(row);
                
            }
        } catch (SQLException ex) {
            
        }
   } 
    
    public ArrayList<BankaPersoneli> getPersoneller() throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<BankaPersoneli> personeller= null;
        try{
           connection=dbHelper.getConnection();
           statement=connection.createStatement();
           resultSet= statement.executeQuery("select * from personeller");
           personeller = new ArrayList<BankaPersoneli>();
           while(resultSet.next()){
               personeller.add(new BankaPersoneli(resultSet.getInt("PERSONELID"),
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
        return personeller;
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tcnotext = new javax.swing.JTextField();
        adtext = new javax.swing.JTextField();
        soyadtext = new javax.swing.JTextField();
        telefonnotext = new javax.swing.JTextField();
        kullanıcıadıtext = new javax.swing.JTextField();
        sifretext = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpersonel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        aramatext = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ıdText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        siltext = new javax.swing.JTextField();
        geriBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("PERSONEL ADI");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("PERSONEL SOYADI");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("PERSONEL TELEFON NO");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("PERSONEL T.C. NO");

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("KULLANICI ADI");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("ŞİFRE");

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

        kullanıcıadıtext.setBackground(new java.awt.Color(51, 51, 51));
        kullanıcıadıtext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        kullanıcıadıtext.setForeground(new java.awt.Color(204, 204, 204));

        sifretext.setBackground(new java.awt.Color(51, 51, 51));
        sifretext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        sifretext.setForeground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setForeground(new java.awt.Color(204, 204, 204));

        tblpersonel.setBackground(new java.awt.Color(0, 0, 0));
        tblpersonel.setFont(new java.awt.Font("DialogInput", 3, 14)); // NOI18N
        tblpersonel.setForeground(new java.awt.Color(204, 204, 204));
        tblpersonel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PERSONELID", "TC_NUMARASI", "AD", "SOYAD", "TELEFON_NO", "SIFRE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblpersonel.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tblpersonelComponentAdded(evt);
            }
        });
        jScrollPane1.setViewportView(tblpersonel);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setText("EKLE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 204, 204));
        jButton2.setText("SİL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(204, 204, 204));
        jButton4.setText("ÇIKIŞ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("ARAMA");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("PERSONEL ID");

        ıdText.setBackground(new java.awt.Color(51, 51, 51));
        ıdText.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        ıdText.setForeground(new java.awt.Color(204, 204, 204));
        ıdText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ıdTextActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Personel ID");

        siltext.setBackground(new java.awt.Color(51, 51, 51));
        siltext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        siltext.setForeground(new java.awt.Color(204, 204, 204));

        geriBtn.setBackground(new java.awt.Color(0, 0, 0));
        geriBtn.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        geriBtn.setForeground(new java.awt.Color(204, 204, 204));
        geriBtn.setText("GERİ");
        geriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ıdText)
                        .addComponent(tcnotext, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(adtext, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(soyadtext, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(telefonnotext, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(kullanıcıadıtext, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(sifretext, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(siltext))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aramatext, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(502, 502, 502)
                        .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(ıdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(aramatext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(geriBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tcnotext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adtext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soyadtext))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefonnotext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kullanıcıadıtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sifretext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(siltext, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(55, 55, 55))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    
    private void tblpersonelComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblpersonelComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpersonelComponentAdded

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
            String sql="insert into personeller (PERSONELID,TC_NUMARASI,AD,SOYAD,TELEFON_NO,SIFRE) values(?,?,?,?,?,?)";
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
            String kayitmesaji= "Personel eklendi.";
            JOptionPane.showMessageDialog(rootPane, kayitmesaji);
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                
            }
        } }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        if(siltext.getText().length()==0){
            String mesaji= "Lütfen silmek istediğiniz personelin ID'sini giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
        
        try{
            connection=dbHelper.getConnection();
            String sql="DELETE FROM personeller WHERE personelıd=? ";
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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String cikismesaji= "ÇIKIŞ YAPILIYOR.";
        JOptionPane.showMessageDialog(rootPane, cikismesaji);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void aramatextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aramatextKeyReleased
        // TODO add your handling code here:
        String arama = aramatext.getText();
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(tablo);
        tblpersonel.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(arama));
        
    }//GEN-LAST:event_aramatextKeyReleased

    private void ıdTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ıdTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ıdTextActionPerformed

    private void aramatextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aramatextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aramatextActionPerformed

    private void geriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriBtnActionPerformed
        // TODO add your handling code here:
        giriss girisi = new giriss();
        girisi.setVisible(true);
        dispose();
    }//GEN-LAST:event_geriBtnActionPerformed

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
            java.util.logging.Logger.getLogger(yoneticiekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(yoneticiekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(yoneticiekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(yoneticiekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new yoneticiekrani(yonetici).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adtext;
    private javax.swing.JTextField aramatext;
    private javax.swing.JButton geriBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kullanıcıadıtext;
    private javax.swing.JTextField sifretext;
    private javax.swing.JTextField siltext;
    private javax.swing.JTextField soyadtext;
    private javax.swing.JTable tblpersonel;
    private javax.swing.JTextField tcnotext;
    private javax.swing.JTextField telefonnotext;
    private javax.swing.JTextField ıdText;
    // End of variables declaration//GEN-END:variables
}
