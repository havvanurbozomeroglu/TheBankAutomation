
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class FaturaOdeme implements Odeme{
    private String odemeNo;

    public String getOdemeNo() {
        return odemeNo;
    }

    public void setOdemeNo(String odemeNo) {
        this.odemeNo = odemeNo;
    }
    
     
    
    @Override
    public void odeme() {
        Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null;                                
        
        int miktar = 0; 
        int musteriHesapNoindex= 0;
        int musteriHesapNo= 0;
        int hesapmusteriId=0 , hesapbakiye= 0;
        String hesapNo = "";                                          
        
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
    
    
     public ArrayList<Musteri> getMusteri() throws SQLException{
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
                                   resultSet.getString("SIFRE")));
               
           }
        }catch(SQLException exception){
            dbHelper.showErrorMessage(exception);        
        }finally{
            statement.close();
            connection.close();          
        }
        return musteriler;
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
    
}
