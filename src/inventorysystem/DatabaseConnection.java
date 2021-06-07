/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import com.mysql.cj.xdevapi.Result;
import java.sql.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed atef
 */
public class DatabaseConnection {
    private String Url  ;
    private String username;
    private String password ;
//    private Connection con = null ;
    
    
    public  Connection getconnetion(){
         Connection con = null ;
         try {
            this.Url = "jdbc:mysql://localhost:3306/inventorymanagmentsystem";
            this.username = "root";
            this.password = "root";
            con = DriverManager.getConnection(this.Url, this.username, this.password);
            System.out.println("connected to database");
           
        } catch (SQLException ex) {
            System.out.println(" fail to connected to database");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
         return con;
    }
    
    public Object executeSQLQuery(String query , String type) throws SQLException{
        Connection con  = getconnetion();
        Statement st ;
        ResultSet rs;

        try{
            
            st  = con.createStatement();
            switch(type){
                case "SELECT":
                    rs  = st.executeQuery(query);
                    return rs;
                case "INSERT":
                    
                    if((st.executeUpdate(query))== 1){
                        System.out.println("Data"+type+"succesfuly");
                        return false  ;
                
                    }else{
                         System.out.println("Data"+type+"fail");
                     }
                    break;
                case "UPDATE":
                    if((st.executeUpdate(query))== 1){
                        System.out.println("Data"+type+"succesfuly");
                        return true  ;
                
                    }else{
                         System.out.println("Data"+type+"fail");
                     }
                    break;
                case "DELETE":
                    if((st.executeUpdate(query))== 1){
                        System.out.println("Data"+type+"succesfuly");
                        return true  ;
                
                    }else{
                         System.out.println("Data"+type+"fail");
                     }
                    break;
                
            }
            
        } catch(SQLException ex){
            System.out.println(ex);
        }
        con.close();
        return true;
        
    }
    
//    public boolean DBConnect(){
//        try {
//            this.Url = "jdbc:mysql://localhost:3306/inventorymanagmentsystem";
//            this.username = "root";
//            this.password = "root";
//            this.con= DriverManager.getConnection(this.Url, this.username, this.password);
//            
//            return true;
//        } catch (SQLException ex) {
//            
//            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//        
//    }
   
    
    
////    public  void Insert ()  {
////        try {
////            String query  = "insert into product valuses(rosse,sode25,1000,285,5)";
////            
////            PreparedStatement preparedStmt = this.con.prepareStatement(query);
////            preparedStmt.execute();
////            System.out.println("success");
////        ResultSet rs = null;
////        int candidateId = 0;
////        try {
////        PreparedStatement pstmt = this.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
////        int rowAffected = pstmt.executeUpdate();
////         if(rowAffected == 1){
////             rs = pstmt.getGeneratedKeys();
////             if(rs.next()) candidateId = rs.getInt(1);
////         }
////        } catch (SQLException ex) {
////            System.out.println(ex.getMessage());
////        } finally {
////            try {
////                if(rs != null)  rs.close();
////            } catch (SQLException e) {
////                System.out.println(e.getMessage());
////            }
////        }
////        return candidateId;
////        } catch (SQLException ex) {
////            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    }
//    
////    
    
}
