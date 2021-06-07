/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;


//import java.sql.*;
import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed atef
 */
public class Product {
    private DatabaseConnection con = new DatabaseConnection();
    private int product_ID;
    protected String productName ;
    protected String productCode ;
    protected int quantity ;
    protected Date productionDate;
   // protected Date expiredDate;
    protected int price ;
    protected int weight ;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //String productName,int quantity,int price , int weight ,String productCode ,String productionDate
    public Product(){
//            this.productName = productName;
//            this.productCode = productCode ;
//            this.quantity = quantity;
//            this.price = price;
//            this.weight = weight;
//            this.productionDate = sdf.parse(productionDate);
        
    }
    
    public  void setproductName(String productName){
         this.productName = productName ;
    }
    public  void setproductID(int product_ID){
         this.product_ID = product_ID ;
    }
    public void setproductCode(String productCode){
         this.productCode = productCode ;
    }
    public void setproductquantity(int quantity){
         this.quantity = quantity ;
    }
    public void setweight(int weight){
         this.weight = weight ;
    }
    public void setprice(int price){
         this.price = price ;
    }
    public void setproductionDate(String productionDate) throws ParseException{
        
         this.productionDate = sdf.parse(productionDate) ;
    }
    public String getproductName() {
        return productName;
    }
    public String getproductCode() {
        return productCode;
    }
    public int getquantity() {
        return quantity;
    }
    public int getprice() {
        return price;
    }
    public int getweight() {
        return weight;
    }
    public Date getproductionDate() {
        return productionDate;
    }
    public int getproductionID() {
        return product_ID;
    }
    //Date productionDate,Date expiredDate,                       
    public boolean addProduct(String productName,int quantity,int price , int weight ,String productCode ,String productionDate){
        
        try {
             this.productName = productName;
            this.productCode = productCode ;
            this.quantity = quantity;
            this.price = price;
            this.weight = weight;
            this.productionDate = sdf.parse(productionDate);
        
            java.sql.Date sqlDate = new java.sql.Date(this.productionDate.getTime());
            String query = "INSERT INTO product(product_name,product_quantity,product_price,product_weight,product_code,produtcion_date) "
                    + "VALUES ('"+this.productName+"','"+this.quantity+"','"+this.price+"','"+this.weight+"','"+this.productCode+"','"+sqlDate+"')";
        
            this.con.executeSQLQuery(query, "INSERT");
            return true ;
        } catch ( Exception e) {
            System.out.println(e);
            return false ;
        }
        

    }
    
    
   public ArrayList<Product> productlist() {
        ArrayList<Product> productList = new ArrayList<Product>();
        String query = "SELECT * FROM product";
        ResultSet rs= null;
       
        Product product = null;
        try {
            rs =(ResultSet) this.con.executeSQLQuery(query, "SELECT");
            while(rs.next()){
                    product = new Product();
                    product.setproductID(rs.getInt("product_id"));
                    product.setproductName(rs.getString("product_name"));
                    product.setproductCode(rs.getString("product_code"));
                    product.setprice(rs.getInt("product_price"));
                    product.setproductquantity(rs.getInt("product_quantity"));
                    product.setweight(rs.getInt("product_weight"));
                    product.setproductionDate(rs.getString("produtcion_date"));
                    productList.add(product);
                    
            }
            
   
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList; 
       
   }
   
   public boolean updateProduct(){
      // Date date=this.productionDate = sdf.parse(productionDate);
      try{
            java.sql.Date sqlDate = new java.sql.Date(getproductionDate().getTime());
            String query = "UPDATE product SET "
                    + "product_name='" +this.productName+"'  , product_code='" +this.productCode+"' ,product_price='" +this.price+"',product_quantity='" +this.quantity+"' "
                    + ",product_weight='" +this.weight+"', produtcion_date = '"+sqlDate+"'where product_id="+this.product_ID ;
           this.con.executeSQLQuery(query, "UPDATE");
       } catch ( Exception e) {
            System.out.println(e);
            return false ;
        }
       
       return true;
   }

   public boolean DeleteProduct(){
       try{
           String query = "DELETE  From product where product_id="+this.product_ID;
           this.con.executeSQLQuery(query, "DELETE");
       } catch(Exception ex){
           System.out.println(ex);
           return false;
       }
        return true;
       
   }
   
  
    
    
}
