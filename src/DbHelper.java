/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Havvanur BOZÖMEROĞLU
 */
import java.sql.*;

public class DbHelper {
    private String username="USER1";
    private String password="060515";
    private String dbUrl="jdbc:oracle:thin:@localhost:1521/XEPDB1";
    //private String dbUrl=" jdbc:derby://localhost:1527/XEPDB1;create=true";
    //private String dbUrl = "jdbc:sqlite:banka_otomasyon.db";
    
   
    
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl,username,password);
        //return DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
              

    }
    
    public void showErrorMessage(SQLException exception){
        System.out.println("Error: " + exception.getMessage());
        System.out.println("Error Code: " + exception.getErrorCode());
    }
}