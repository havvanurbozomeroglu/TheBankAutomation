
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class musteriekrani extends javax.swing.JFrame {

    static Musteri musteri = new Musteri();
    
    DefaultTableModel hesaptablo ;
    DefaultTableModel bankakarttablo ;
    DefaultTableModel kredikarttablo ;
    
     
    
    
    /**
     * Creates new form musteriekrani
     */
    
    public musteriekrani(Musteri musteri) {
        initComponents();

        populateHesap(musteri);
        populateBankaKarti(musteri);
        populateKrediKarti(musteri);
        
        comboBox(musteri);
        KredicomboBox(musteri);

    }

    public void populateHesap(Musteri musteri){
        hesaptablo=(DefaultTableModel)tblhesap.getModel();
        hesaptablo.setRowCount(0);
        try {
            ArrayList<Hesap> hesaplar = getHesaplar();
            for(Hesap hesap : hesaplar){
                if(musteri.getId()==hesap.getId()){
                    Object[] row= {hesap.getHesapNo() , hesap.getBakiye()};
                    hesaptablo.addRow(row);

                }  
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void populateBankaKarti(Musteri musteri){
        bankakarttablo=(DefaultTableModel)tblbankakart.getModel();
        bankakarttablo.setRowCount(0);
        try {
            ArrayList<BankaKartı> bankaKartlari = getBankaKartı();
            for(BankaKartı bankakarti : bankaKartlari){
                if(musteri.getId()==bankakarti.getMusteriid()){
                    Object[] row= {bankakarti.getKartNo() , bankakarti.getBakiye()};
                    bankakarttablo.addRow(row);
                }  
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void populateKrediKarti(Musteri musteri){
        kredikarttablo=(DefaultTableModel)tblkredikart.getModel();
        kredikarttablo.setRowCount(0);
        try {
            ArrayList<KrediKartı> krediKartlari = getKrediKartı();
            for(KrediKartı kredikarti : krediKartlari){
                if(musteri.getId()==kredikarti.getMusteriid()){
                    Object[] row= {kredikarti.getKartNo() , kredikarti.getBakiye(),  kredikarti.getLimit(),kredikarti.getBorc()};
                    kredikarttablo.addRow(row);
                    
                }  
            }
        } catch (SQLException ex) {
            
        }   
    }
   
    public ArrayList<Hesap> getHesaplar() throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<Hesap> hesaplar= null;
        try{
           connection=dbHelper.getConnection();
           statement=connection.createStatement();
           resultSet= statement.executeQuery("SELECT * FROM hesap");
           hesaplar = new ArrayList<Hesap>();
           while(resultSet.next()){
               hesaplar.add(new Hesap(resultSet.getString("HESAP_NO"),
                                      resultSet.getInt("MUSTERIID"),
                                      resultSet.getInt("BAKIYE")));
               
           }
           
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);        
        }finally{
            statement.close();
            connection.close();          
        }
        return hesaplar;
    }
    
    public ArrayList<BankaKartı> getBankaKartı() throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
       
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<BankaKartı> bankaKartlari= null;
        try{
           connection=dbHelper.getConnection();
           statement=connection.createStatement();
           resultSet= statement.executeQuery("SELECT * FROM banka_kartı");
           bankaKartlari = new ArrayList<BankaKartı>();
           while(resultSet.next()){
               bankaKartlari.add(new BankaKartı(resultSet.getString("BANKAKART_NO"),
                                                resultSet.getInt("MUSTERIID"),
                                                resultSet.getInt("BAKIYE")));
               
           }
           
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);        
        }finally{
            statement.close();
            connection.close();          
        }
        return bankaKartlari;
    }
    
    public ArrayList<KrediKartı> getKrediKartı() throws SQLException{
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        
        Statement statement = null;
        ResultSet resultSet;
        ArrayList<KrediKartı> krediKartlari= null;
        try{
           connection=dbHelper.getConnection();
           statement=connection.createStatement();
           resultSet= statement.executeQuery("SELECT * FROM kredı_kart");
           krediKartlari = new ArrayList<KrediKartı>();
           while(resultSet.next()){
               krediKartlari.add(new KrediKartı(resultSet.getString("KREDIKART_NO"),
                                                resultSet.getInt("MUSTERIID"),
                                                resultSet.getInt("BAKIYE"),
                                                resultSet.getInt("LIMIT"),
                                                resultSet.getInt("BORC")));
               
           }
           
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);        
        }finally{
            statement.close();
            connection.close();          
        }
        return krediKartlari;
    }
    
    public void comboBox(Musteri musteri){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection=dbHelper.getConnection();
            statement=connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM hesap");
            while(resultSet.next()){
                int musteriId =resultSet.getInt("MUSTERIID");
                if(musteri.getId()==musteriId){
                    String hesapno=resultSet.getString("HESAP_NO");
                    hesapCombobox.addItem(hesapno);
                    paraYatırmaComboBox.addItem(hesapno);
                    gonderenComboBox.addItem(hesapno);
                    odemeComboBox.addItem(hesapno);
                    jComboBox2.addItem(hesapno);

                }
            }   
        } catch (SQLException ex) {
            Logger.getLogger(musteriekrani.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(musteriekrani.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
        }
        
    }
     public void KredicomboBox(Musteri musteri){
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection=dbHelper.getConnection();
            statement=connection.createStatement();
            resultSet= statement.executeQuery("SELECT * FROM kredı_kart");
            while(resultSet.next()){
                int musteriId =resultSet.getInt("MUSTERIID");
                if(musteri.getId()==musteriId){
                    String hesapno=resultSet.getString("KREDIKART_NO");
                    krediComboBox.addItem(hesapno);
                    hesapCombobox.addItem(hesapno);
                }
            }   
        } catch (SQLException ex) {
            Logger.getLogger(musteriekrani.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(musteriekrani.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jMenu1 = new javax.swing.JMenu();
        jFrame1 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        popupMenu1 = new java.awt.PopupMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhesap = new javax.swing.JTable();
        cikisBtn = new javax.swing.JButton();
        geriBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbankakart = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblkredikart = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        miktartext = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        hesapCombobox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        miktartext2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        paraYatırmaComboBox = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        alicihesaptext = new javax.swing.JTextField();
        transfertext = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        gonderenComboBox = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        miktartext3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        miktartext4 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        odemeComboBox = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        krediComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        yenilimittext = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        miktartext9 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        eskisifretext = new javax.swing.JTextField();
        yenisifretext = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu1.setText("jMenu1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        popupMenu1.setLabel("popupMenu1");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblhesap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HESAP_NO", "BAKIYE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblhesap);

        cikisBtn.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        cikisBtn.setText("ÇIKIŞ");
        cikisBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisBtnActionPerformed(evt);
            }
        });

        geriBtn.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        geriBtn.setText("GERİ");
        geriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriBtnActionPerformed(evt);
            }
        });

        tblbankakart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BANKAKART_NO", "BAKIYE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblbankakart);

        tblkredikart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KREDIKART_NO", "BAKIYE", "LIMIT", "BORC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblkredikart);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cikisBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cikisBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("HESAPLAR", jPanel1);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel1.setText("MİKTAR");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LÜTFEN BİR HESAP SEÇİNİZ");

        jButton3.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton3.setText("PARAYI ÇEK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        hesapCombobox.setFont(new java.awt.Font("DialogInput", 3, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(miktartext))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hesapCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hesapCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(miktartext))
                .addGap(28, 28, 28)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PARA ÇEKME", jPanel2);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel5.setText("LÜTFEN BİR HESAP SEÇİNİZ.");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel6.setText("MİKTAR");

        miktartext2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        jButton5.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton5.setText("ONAYLA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        paraYatırmaComboBox.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(miktartext2))
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paraYatırmaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paraYatırmaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(miktartext2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PARA YATIRMA", jPanel3);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel7.setText("ALICI HESAP");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel8.setText("GÖNDEREN HESAP");

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel9.setText("MİKTAR");

        jButton6.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton6.setText("ONAYLA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        gonderenComboBox.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(alicihesaptext)
                    .addComponent(transfertext)
                    .addComponent(gonderenComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(alicihesaptext)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gonderenComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(transfertext))
                .addGap(26, 26, 26)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PARA TRANSFERİ", jPanel4);

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel10.setText("FATURA NUMARASI");

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel11.setText("MİKTAR");

        jButton7.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton7.setText("FATURA ÖDEMEYİ ONAYLA");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel12.setText("VERGİ NUMARASI");

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel13.setText("MİKTAR");

        jButton8.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton8.setText("VERGİ ÖDEMESİNİ ONAYLA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        odemeComboBox.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel14.setText("LÜTFEN HESAP SEÇİNİZ.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(miktartext3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 37, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(miktartext4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(odemeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addGap(228, 228, 228))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(odemeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(miktartext4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(miktartext3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ÖDEME", jPanel5);

        krediComboBox.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Limit Güncelleme");

        jLabel16.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel16.setText("Yeni Limit");

        yenilimittext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton1.setText("ONAYLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel17.setText("LÜTFEN KREDİ HESABI SEÇİNİZ");

        jLabel18.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Kredi Borcu Ödeme");

        jLabel19.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel19.setText("Miktar");

        miktartext9.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        jButton2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton2.setText("ONAYLA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(krediComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(224, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yenilimittext))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(miktartext9))
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(krediComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(miktartext9)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yenilimittext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(109, 109, 109))
        );

        jTabbedPane1.addTab("KREDİ İŞLEMLERİ", jPanel6);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel3.setText("ESKİ ŞİFRE");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabel4.setText("YENİ ŞİFRE");

        eskisifretext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        yenisifretext.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N

        jButton4.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jButton4.setText("ONAYLA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(eskisifretext)
                    .addComponent(yenisifretext, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eskisifretext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yenisifretext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ŞİFRE İŞLEMLERİ", jPanel7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cikisBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisBtnActionPerformed
        // TODO add your handling code here:
        String cikismesaji= "ÇIKIŞ YAPILIYOR.";
        JOptionPane.showMessageDialog(rootPane, cikismesaji);
        dispose();
    }//GEN-LAST:event_cikisBtnActionPerformed

    private void geriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriBtnActionPerformed
        // TODO add your handling code here:
        giriss girisi = new giriss();
        girisi.setVisible(true);
        dispose();
    }//GEN-LAST:event_geriBtnActionPerformed
///////////////////////////////////////////////////////////////////////
    ////////////////////////PARA ÇEKME/////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        int miktar = 0; 
        int musteriHesapNoindex= hesapCombobox.getSelectedIndex();
        int musteriHesapNo= Integer.valueOf(hesapCombobox.getItemAt(musteriHesapNoindex));
        int hesapmusteriId=0 , hesapbakiye= 0, kredimusteriId=0, krediborc=0, kredilimit=0, kredibakiye=0;
        String hesapNo = "", kredihesapNo="";
        if(miktartext.getText().length()==0){
            String mesaji= "Lütfen çekmek istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            miktar = Integer.valueOf(miktartext.getText());
            if(miktar<0){
                String mesaji= "Lütfen geçerli bir miktar giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else if(miktar>15000){
                String mesaji= "Tek seferde en fazla 15000 çekebilirsiniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    ArrayList<Hesap> hesaplar = getHesaplar();
                    for(Hesap hesap : hesaplar){
                        if(musteriHesapNo==Integer.valueOf(hesap.getHesapNo())){
                            hesapNo=hesap.getHesapNo();
                            hesapbakiye=hesap.getBakiye();
                            hesapmusteriId = hesap.getId();
                            if(hesapbakiye<miktar){
                                String mesaji= "Hesabınızda " + miktar + " TL bulunmamaktatır.";
                                JOptionPane.showMessageDialog(rootPane, mesaji);
                            }else{
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result = statement.executeUpdate();
                
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result2 = statement.executeUpdate();
                                
                                ArrayList<Musteri> musteriler = getMusteriler();
                                for(Musteri musteri : musteriler){
                                    if(hesapmusteriId==musteri.getId()){
                                        populateHesap( musteri);
                                        populateBankaKarti(musteri);
                                        String mesaji= "Hesabınızdan " + miktar + " TL çekilmiştir.";
                                        JOptionPane.showMessageDialog(rootPane, mesaji);
                                    }
                                }
                            }catch(SQLException ex){
                                
                                }
                            }
                        } 
                    }
                    ArrayList<KrediKartı> krediKartlari = getKrediKartı();
                        for(KrediKartı kredikarti : krediKartlari){
                            if(musteriHesapNo==Integer.valueOf(kredikarti.getKartNo())){
                                kredimusteriId=kredikarti.getMusteriid();
                                kredibakiye=kredikarti.getBakiye();
                                kredilimit=kredikarti.getLimit();
                                krediborc=kredikarti.getBorc();
                                kredihesapNo=kredikarti.getKartNo();
                                 if(kredibakiye<miktar){
                                    String mesaji= "Kredi hesabınızda " + miktar + " TL bulunmamaktatır.";
                                    JOptionPane.showMessageDialog(rootPane, mesaji);
                                }else{
                                    connection=dbHelper.getConnection();
                                    String sql3="update kredı_kart set MUSTERIID=?,BAKIYE=?,LIMIT=?,BORC=? where KREDIKART_NO=? ";
                                    statement = connection.prepareStatement(sql3);
                                    statement.setString(5,kredihesapNo);
                                    statement.setInt(1,kredimusteriId);
                                    statement.setInt(2,kredibakiye-miktar);
                                    statement.setInt(3, kredilimit);
                                    statement.setInt(4, krediborc+miktar);
                                    int result3 = statement.executeUpdate();
                                    ArrayList<Musteri> musteriler = getMusteriler();
                                    for(Musteri musteri : musteriler){
                                        if(kredimusteriId==musteri.getId()){
                                            populateKrediKarti(musteri);
                                            String mesaji= "Kredi hesabınızdan " + miktar + " TL para çekilmiştir.";
                                            JOptionPane.showMessageDialog(rootPane, mesaji);
                                    }
                                }
                                
                        }  
                            }  }
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
        }
       
        
    }//GEN-LAST:event_jButton3ActionPerformed
    /////////////////////////////////////////////////////////////////////////
    ////////////////////PARA YATIRMA//////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;                                
        
        int miktar = 0; 
        int musteriHesapNoindex= hesapCombobox.getSelectedIndex();
        int musteriHesapNo= Integer.valueOf(hesapCombobox.getItemAt(musteriHesapNoindex));
        int hesapmusteriId=0 , hesapbakiye= 0;
        String hesapNo = "";                                          
        if(miktartext2.getText().length()==0){
            String mesaji= "Lütfen yatırmak istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            miktar = Integer.valueOf(miktartext2.getText());
            if(miktar<0){
                String mesaji= "Lütfen geçerli bir miktar giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else if(miktar>15000){
                String mesaji= "Tek seferde en fazla 15000 yatırabilirsiniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    System.out.println("basarı 1");
                    ArrayList<Hesap> hesaplar = getHesaplar();
                    for(Hesap hesap : hesaplar){
                        if(musteriHesapNo==Integer.valueOf(hesap.getHesapNo())){
                            hesapNo=hesap.getHesapNo();
                            hesapbakiye=hesap.getBakiye();
                            hesapmusteriId = hesap.getId();
                             System.out.println("basarı 2");
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye+miktar);
                                int result = statement.executeUpdate();
                                 System.out.println("basarı 3");
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye+miktar);
                                int result2 = statement.executeUpdate();
                                
                                ArrayList<Musteri> musteriler = getMusteriler();
                                for(Musteri musteri : musteriler){
                                    if(hesapmusteriId==musteri.getId()){
                                         System.out.println("basarı 4");
                                        populateHesap( musteri);
                                        populateBankaKarti(musteri);
                                        String mesaji= "Hesabınıza " + miktar + " TL yatırılmıştır.";
                                        JOptionPane.showMessageDialog(rootPane, mesaji);
                                    }
                                }
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
                        } 
                    
                }catch(SQLException ex){
                   
               }                           
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////PARA TRANSFERİ///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        int alicihesapno=Integer.valueOf(alicihesaptext.getText());
        int miktar = 0; 
        int musteriHesapNoindex= gonderenComboBox.getSelectedIndex();
        int musteriHesapNo= Integer.valueOf(gonderenComboBox.getItemAt(musteriHesapNoindex));
        int hesapmusteriId=0 , hesapbakiye= 0, alicihesapId=0, alicihesapbakiye=0;
        String hesapNo = "", alicihesapNo="";
        if(transfertext.getText().length()==0){
            String mesaji= "Lütfen yatırmak istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            miktar = Integer.valueOf(transfertext.getText());
            if(miktar<0){
                String mesaji= "Lütfen geçerli bir miktar giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else if(miktar>15000){
                String mesaji= "Tek seferde en fazla 15000 gönderebilirsiniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    ArrayList<Hesap> hesaplar = getHesaplar();
                    for(Hesap hesap : hesaplar){
                        if(musteriHesapNo==Integer.valueOf(hesap.getHesapNo())){
                            hesapNo=hesap.getHesapNo();
                            hesapbakiye=hesap.getBakiye();
                            hesapmusteriId = hesap.getId();
                            
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result = statement.executeUpdate();
                
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result2 = statement.executeUpdate();
                                
                                ArrayList<Musteri> musteriler = getMusteriler();
                                for(Musteri musteri : musteriler){
                                    if(hesapmusteriId==musteri.getId()){
                                        populateHesap( musteri);
                                        populateBankaKarti(musteri);
                                        String mesaji= alicihesapno +" " + miktar + " TL göderilmiştir.";
                                        JOptionPane.showMessageDialog(rootPane, mesaji);
                                        
                                    }
                                }
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
                        if(alicihesapno==Integer.valueOf(hesap.getHesapNo()) ){
                            alicihesapNo=hesap.getHesapNo();
                            alicihesapbakiye=hesap.getBakiye();
                            alicihesapId = hesap.getId();
                           
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,alicihesapNo);
                                statement.setInt(1,alicihesapId);
                                statement.setInt(2,alicihesapbakiye+miktar);
                                int result = statement.executeUpdate();
                
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,alicihesapNo);
                                statement.setInt(1,alicihesapId);
                                statement.setInt(2,alicihesapbakiye+miktar);
                                int result2 = statement.executeUpdate();
                                
                                   
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
                        } 
                    
                }catch(SQLException ex){
                   
               }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed
 ////////////////// FATURA ÖDEME/////////////////////
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;                                
        
        int miktar = 0; 
        int musteriHesapNoindex= odemeComboBox.getSelectedIndex();
        int musteriHesapNo= Integer.valueOf(odemeComboBox.getItemAt(musteriHesapNoindex));
        int hesapmusteriId=0 , hesapbakiye= 0;
        String hesapNo = "";                                          
        if(miktartext3.getText().length()==0){
            String mesaji= "Lütfen yatırmak istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            miktar = Integer.valueOf(miktartext3.getText());
            if(miktar<0){
                String mesaji= "Lütfen geçerli bir miktar giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else if(miktar>15000){
                String mesaji= "Tek seferde en fazla 15000 ödeyebilirsiniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    ArrayList<Hesap> hesaplar = getHesaplar();
                    for(Hesap hesap : hesaplar){
                        if(musteriHesapNo==Integer.valueOf(hesap.getHesapNo())){
                            hesapNo=hesap.getHesapNo();
                            hesapbakiye=hesap.getBakiye();
                            hesapmusteriId = hesap.getId();
                            
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result = statement.executeUpdate();
                
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result2 = statement.executeUpdate();
                                
                                ArrayList<Musteri> musteriler = getMusteriler();
                                for(Musteri musteri : musteriler){
                                    if(hesapmusteriId==musteri.getId()){
                                        populateHesap( musteri);
                                        populateBankaKarti(musteri);
                                        String mesaji= "Faturanız ödenmiştir.";
                                        JOptionPane.showMessageDialog(rootPane, mesaji);
                                    }
                                }
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
                        } 
                    
                }catch(SQLException ex){
                   
               }                           
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed
////////////////VERGİ ÖDEMESİ///////////////////////////
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;                                
        
        int miktar = 0; 
        int musteriHesapNoindex= odemeComboBox.getSelectedIndex();
        int musteriHesapNo= Integer.valueOf(odemeComboBox.getItemAt(musteriHesapNoindex));
        int hesapmusteriId=0 , hesapbakiye= 0;
        String hesapNo = "";                                          
        if(miktartext4.getText().length()==0){
            String mesaji= "Lütfen yatırmak istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            miktar = Integer.valueOf(miktartext4.getText());
            if(miktar<0){
                String mesaji= "Lütfen geçerli bir miktar giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else if(miktar>15000){
                String mesaji= "Tek seferde en fazla 15000 ödeyebilirsiniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    ArrayList<Hesap> hesaplar = getHesaplar();
                    for(Hesap hesap : hesaplar){
                        if(musteriHesapNo==Integer.valueOf(hesap.getHesapNo())){
                            hesapNo=hesap.getHesapNo();
                            hesapbakiye=hesap.getBakiye();
                            hesapmusteriId = hesap.getId();
                            
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result = statement.executeUpdate();
                
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result2 = statement.executeUpdate();
                                
                                ArrayList<Musteri> musteriler = getMusteriler();
                                for(Musteri musteri : musteriler){
                                    if(hesapmusteriId==musteri.getId()){
                                        populateHesap( musteri);
                                        populateBankaKarti(musteri);
                                        String mesaji= "Vergi ödenmenizi bitmiştir.";
                                        JOptionPane.showMessageDialog(rootPane, mesaji);
                                    }
                                }
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
                        } 
                    
                }catch(SQLException ex){
                   
               }                           
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed
////////////////////////şifre işlemleri//////////////////////////
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null; 
        String eskisifre = eskisifretext.getText();
        String yenisifre = yenisifretext.getText();

        if(eskisifretext.getText().length()==0 || yenisifretext.getText().length()==0){
            String mesaji= "Lütfen boş alan bırakmayınız..";
            JOptionPane.showMessageDialog(rootPane, mesaji);
        }else{
        try {
            ArrayList<Musteri> musteriler;
            musteriler = getMusteriler();
            for(Musteri musteri : musteriler){
                if(eskisifre.equalsIgnoreCase(musteri.getSifre())){
                    try{
                        connection=dbHelper.getConnection();
                        String sql="update musterı set TC_NUMARASI=?,AD=?,SOYAD=?,TELEFON_NO=?,SIFRE=? where MUSTERIID=?";
                        statement = connection.prepareStatement(sql);
            
                        statement.setInt(6,musteri.getId());
                        statement.setString(1,musteri.getTcNo());
                        statement.setString(2,musteri.getAd());
                        statement.setString(3,musteri.getSoyad());
                        statement.setString(4,musteri.getTelefonNo());
                        statement.setString(5,yenisifre);
                        int result = statement.executeUpdate();
            
                        String mesaji= "Şifreniz başarıyla değiştirilmiştir.";
                        JOptionPane.showMessageDialog(rootPane, mesaji);
           
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(musteriekrani.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed
//////////////KREDİ BORCU/////////////////
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        
        int miktar = 0; 
        int kredihesapnoindex=krediComboBox.getSelectedIndex();
        int kredihesapno=Integer.valueOf(krediComboBox.getItemAt(kredihesapnoindex));
        int musteriHesapNoindex= jComboBox2.getSelectedIndex();
        int musteriHesapNo= Integer.valueOf(jComboBox2.getItemAt(musteriHesapNoindex));
        int hesapmusteriId=0 , hesapbakiye= 0, kredimusteriId=0, kredilimit=0, kredibakiye=0, krediborc=0;
        String hesapNo = "", kredihesapNo="";
        if(krediComboBox.getItemAt(kredihesapnoindex).length()==0){
            String mesaji= "Lütfen çekmek istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            miktar = Integer.valueOf(miktartext9.getText());
            if(miktar<0){
                String mesaji= "Lütfen geçerli bir miktar giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    ArrayList<Hesap> hesaplar = getHesaplar();
                    for(Hesap hesap : hesaplar){
                        if(musteriHesapNo==Integer.valueOf(hesap.getHesapNo())){
                            hesapNo=hesap.getHesapNo();
                            hesapbakiye=hesap.getBakiye();
                            hesapmusteriId = hesap.getId();
                            if(hesapbakiye<miktar){
                                String mesaji= "Hesabınızda " + miktar + " TL bulunmamaktatır.";
                                JOptionPane.showMessageDialog(rootPane, mesaji);
                            }else{
                                try {
                                connection=dbHelper.getConnection();
                                String sql="update hesap set MUSTERIID=?,BAKIYE=? where HESAP_NO=?";
                                statement = connection.prepareStatement(sql);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result = statement.executeUpdate();
                
                                String sql2="update banka_kartı set MUSTERIID=?,BAKIYE=? where BANKAKART_NO=? ";
                                statement = connection.prepareStatement(sql2);
                                statement.setString(3,hesapNo);
                                statement.setInt(1,hesapmusteriId);
                                statement.setInt(2,hesapbakiye-miktar);
                                int result2 = statement.executeUpdate();
                                
                                try {
                                    ArrayList<KrediKartı> krediKartlari = getKrediKartı();
                                    for(KrediKartı kredikarti : krediKartlari){
                                       
                                        if(kredihesapno==Integer.valueOf(kredikarti.getKartNo())){
                                            kredimusteriId=kredikarti.getMusteriid();
                                            kredibakiye=kredikarti.getBakiye();
                                            kredilimit=kredikarti.getLimit();
                                            krediborc=kredikarti.getBorc();
                                            kredihesapNo=kredikarti.getKartNo();
                                            String sql3="update kredı_kart set MUSTERIID=?,BAKIYE=?,LIMIT=?,BORC=? where KREDIKART_NO=? ";
                                            statement = connection.prepareStatement(sql3);
                                            statement.setString(5,kredihesapNo);
                                            statement.setInt(1,kredimusteriId);
                                            statement.setInt(2,kredibakiye+miktar);
                                            statement.setInt(3, kredilimit);
                                            statement.setInt(4, krediborc-miktar);
                                            
                                            int result3 = statement.executeUpdate();
                    
                                        }  
                                    }
                                } catch (SQLException ex) {
            
                                }   
                                
                                
                                ArrayList<Musteri> musteriler = getMusteriler();
                                for(Musteri musteri : musteriler){
                                    if(hesapmusteriId==musteri.getId()){
                                        populateHesap( musteri);
                                        populateBankaKarti(musteri);
                                        populateKrediKarti(musteri);
                                        String mesaji= "Hesabınızdaki " + miktar + " TL kredi borcu ödenmiştir.";
                                        JOptionPane.showMessageDialog(rootPane, mesaji);
                                    }
                                }
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
                        } 
                    }
                }catch(SQLException ex){
                   
               }
            }}
        
    }//GEN-LAST:event_jButton2ActionPerformed
///////////////////////////////YENİ LİMİT GÜNCELLE///////////////////////////////
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;
        
        
        int yenilimit = 0; 
        int kredihesapnoindex=krediComboBox.getSelectedIndex();
        int kredihesapno=Integer.valueOf(krediComboBox.getItemAt(kredihesapnoindex));
        int kredimusteriId=0, kredilimit=0, kredibakiye=0, krediborc=0;
        String kredihesapNo;
        if(yenilimittext.getText().length()==0){
            String mesaji= "Lütfen çekmek istediğiniz para miktarını giriniz.";
            JOptionPane.showMessageDialog(rootPane, mesaji);
            
        }else{
            yenilimit = Integer.valueOf(yenilimittext.getText());
            if(yenilimit<0){
                String mesaji= "Lütfen geçerli bir limit giriniz.";
                JOptionPane.showMessageDialog(rootPane, mesaji);
            }else{
                try {
                    ArrayList<KrediKartı> krediKartlari = getKrediKartı();
                    for(KrediKartı kredikarti : krediKartlari){
                        if(kredihesapno==Integer.valueOf(kredikarti.getKartNo())){
                            kredimusteriId=kredikarti.getMusteriid();
                            kredibakiye=kredikarti.getBakiye();
                            kredilimit=kredikarti.getLimit();
                            krediborc=kredikarti.getBorc();
                            kredihesapNo=kredikarti.getKartNo();
                            if(krediborc>=yenilimit){
                                String mesaji= "Geçerli bir limit giriniz.";
                                JOptionPane.showMessageDialog(rootPane, mesaji);
                            }else{
                                try {
                                    connection=dbHelper.getConnection();
                                    String sql3="update kredı_kart set MUSTERIID=?,BAKIYE=?,LIMIT=?,BORC=? where KREDIKART_NO=? ";
                                    kredibakiye = yenilimit-krediborc;
                                    statement = connection.prepareStatement(sql3);
                                    statement.setString(5,kredihesapNo);
                                    statement.setInt(1,kredimusteriId);
                                    statement.setInt(2,kredibakiye);
                                    statement.setInt(3, yenilimit);
                                    statement.setInt(4, krediborc);
                                    int result3 = statement.executeUpdate();
                                } catch (SQLException ex) {
            
                                }   
                            ArrayList<Musteri> musteriler = getMusteriler();
                            for(Musteri musteri : musteriler){
                                if(kredimusteriId==musteri.getId()){
                                    populateKrediKarti(musteri);
                                    String mesaji= "Limit " + yenilimit + " olarak güncellenmiştir.";
                                    JOptionPane.showMessageDialog(rootPane, mesaji);
                                }
                            }
                            }
                        }
                    }
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
        } 
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
            java.util.logging.Logger.getLogger(musteriekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(musteriekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(musteriekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(musteriekrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new musteriekrani(musteri).setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alicihesaptext;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cikisBtn;
    private javax.swing.JTextField eskisifretext;
    private javax.swing.JButton geriBtn;
    private javax.swing.JComboBox<String> gonderenComboBox;
    private javax.swing.JComboBox<String> hesapCombobox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<String> krediComboBox;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JTextField miktartext;
    private javax.swing.JTextField miktartext2;
    private javax.swing.JTextField miktartext3;
    private javax.swing.JTextField miktartext4;
    private javax.swing.JTextField miktartext9;
    private javax.swing.JComboBox<String> odemeComboBox;
    private javax.swing.JComboBox<String> paraYatırmaComboBox;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable tblbankakart;
    private javax.swing.JTable tblhesap;
    private javax.swing.JTable tblkredikart;
    private javax.swing.JTextField transfertext;
    private javax.swing.JTextField yenilimittext;
    private javax.swing.JTextField yenisifretext;
    // End of variables declaration//GEN-END:variables
}
