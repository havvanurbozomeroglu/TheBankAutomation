
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
public class ATMislemleri implements Islemler  {

    @Override
    public void sifreDegistirme() {  //deneme asıl kod frm dosyalarının içinde
      Connection connection = null;
        DbHelper dbHelper = new DbHelper();
        PreparedStatement statement = null; 
        String eskisifre = "";
        String yenisifre = "";

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
            Logger.getLogger(BankaIslemleri.class.getName()).log(Level.SEVERE, null, ex);
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
    }
    
