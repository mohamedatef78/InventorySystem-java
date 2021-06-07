/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.sql.*;

/**
 *
 * @author Mohamed atef
 */
public class User {
   private DatabaseConnection con = new DatabaseConnection();
   private String username ;
   private String password ;
   private String type ;
   
   public User(String username , String password  , String Type){
       this.username = username ;
       this.password = password;
       this.type = type ;
   }
   public String getusername() {
    return username;
   }
//   public String setusername(String username){
//      return this.username = username ;
//   }
   public String getpassword() {
    return password;
   }
//   public String setpassword(String password){
//      return this.password = password ;
//   }
   public String gettype() {
    return type;
   }
//   public String settype(String type){
//      return this.type = type ;
//   }  
   public boolean Login() throws SQLException{
       String query = "SELECT * FROM inventorymanagmentsystem.user  WHERE name = '"+this.username+"'AND password = '"+this.password+"'";
//       boolean rs =this.con.executeSQLQuery(query, "SELECT");
       ResultSet rs = (ResultSet) this.con.executeSQLQuery(query, "SELECT");


       if(rs.next()){
           System.out.println("you are Loged In");
           return true ;
       }else{
           System.out.println("please check password or user name");
           return false ;
       }


   }
 
   
   
   
   
   
}
