
import java.sql.Connection;
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
public class BankaPersoneli extends Kisi {
    private String ad;
    private String soyad;
    private String tcNo;
    private String telefonNo;
    private String sifre;
    private int id;

    public BankaPersoneli(  int id, String tcNo, String ad, String soyad,  String telefonNo,  String sifre) {
        this.ad = ad;
        this.soyad = soyad;
        this.tcNo = tcNo;
        this.telefonNo = telefonNo;
        this.sifre = sifre;
        this.id = id;
    }
    
    public BankaPersoneli(){
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

 
     public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }


    public String getTelefonNo() {
        return telefonNo;
    }

    public void setTelefonNo(String telefonNo) {
        this.telefonNo = telefonNo;
    }
    
    
    
    
    @Override
    public void giris(String TCno, String sifre) {   //deneme asıl kod frm dosyalarının içinde
        ArrayList<Musteri> musteriler;
        
        try {
            musteriler = getMusteri();
        
        for(Musteri musteri : musteriler){
            Object[] tcno = { musteri.getTcNo() };
            Object[] sifre1 = {musteri.getSifre()};
            if(TCno.equals(tcno) && sifre.equals(sifre1)){
                
            }   }
        } catch (SQLException ex) {
            Logger.getLogger(BankaYoneticisi.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}
    
     
    