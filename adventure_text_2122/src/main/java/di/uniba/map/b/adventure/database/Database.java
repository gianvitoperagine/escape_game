/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class Database {
    
    public boolean existingNick = false;
    public String historian() {
        
        String stringa = "";
        try {
            Properties dbprops = new Properties();
            dbprops.setProperty("user", "user");
            dbprops.setProperty("password", "user");
                try (Connection conn = DriverManager.getConnection("jdbc:h2:.//resources//database1//user", dbprops)) {
                    
                    Statement stm = conn.createStatement();  
                    ResultSet rs = stm.executeQuery("SELECT * FROM Users");
                    
                    while (rs.next()) {
                        
                        stringa = stringa.concat(rs.getString(1) + "\t" + rs.getString(3) + "\t" + rs.getString(2) + "\n");
                    }
                    rs.close();
                    stm.close();
                    conn.close();
                }
                
        } catch (SQLException ex) {
            stringa = "Che fortuna! Nessuno ha ancora giocato, sei il primo utente! ";
        }
        return stringa;
    }
    
    public void setHistorian(String nick, int age) {
        int id = 0;
        
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Users (Id int NOT NULL PRIMARY KEY, nickname VARCHAR(20) NOT NULL, age INT NOT NULL)";
        
        try {
            Properties dbprops = new Properties();
            dbprops.setProperty("user", "user");
            dbprops.setProperty("password", "user");
                try (Connection conn = DriverManager.getConnection("jdbc:h2:.//resources//database1//user", dbprops)) {
                    Statement stm = conn.createStatement();
                    stm.executeUpdate(CREATE_TABLE);
                    stm.close();
                    
                    stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT count(*) FROM Users");
                    while (rs.next()) {
                        id = rs.getInt(1);
                    }
                    rs.close();
                    
                    PreparedStatement pstm= conn.prepareStatement("INSERT INTO Users VALUES (?,?,?)");
                    pstm.setInt(1,(id+1));
                    pstm.setString(2, nick);
                    pstm.setInt(3, age);
                    pstm.executeUpdate();
                    pstm.close();
                    
                    conn.close();
                }
                
                
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
     public boolean checkNick(String nick) {
        try {
            Properties dbprops = new Properties();
            dbprops.setProperty("user", "user");
            dbprops.setProperty("password", "user");
                try (Connection conn = DriverManager.getConnection("jdbc:h2:.//resources//database1//user", dbprops)) {
                    
                    Statement stm = conn.createStatement();  
                    ResultSet rs = stm.executeQuery("SELECT * FROM Users");
                    
                    while (rs.next()) {
                        
                        if(nick.equals(rs.getString(2)))
                        {
                            existingNick = true;
                        }
                    }
                    rs.close();
                    stm.close();
                    conn.close();
                }
                
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
        return existingNick;
    }
}
