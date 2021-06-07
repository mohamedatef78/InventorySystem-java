/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed atef
 */
public class Sales {
    private int sales_ID;
    private int  product_ID;
    private  String product_Code ;
    private int  invoice_id;
    private int  quantity;
    private float  itemdiscount ;
    private int total ;
    private DatabaseConnection con = new DatabaseConnection();
    public ArrayList<Product> productList = new ArrayList<Product>();
    
    
    public  void setquantity(int quantity){
         this.quantity = quantity ;
    }
    public  void setitemdiscount(int itemdiscount){
         this.itemdiscount = itemdiscount ;
    }
    public int getproduct_ID() {
        return product_ID;
    }
    public int getsales_ID() {
        return sales_ID;
    }
    public int getinvoice_id() {
        return invoice_id;
    }
    public int getquantity() {
        return quantity;
    }
    public float getitemdiscount() {
        return itemdiscount;
    }
    public int gettotal() {
        return total;
    }
     public ArrayList<Product> fetchproduct(String productCode){
       
       String query = "SELECT * FROM inventorymanagmentsystem.product where  product_code= '"+productCode +"'";
       ResultSet rs= null;
       
       Product product = null;
        try {
            rs =(ResultSet) this.con.executeSQLQuery(query, "SELECT");
            while(rs.next()){
                    product = new Product();
                    this.product_ID = rs.getInt("product_id");
                    this.product_Code =rs.getString("product_code");
                    product.setproductID(rs.getInt("product_id"));
                    product.setproductName(rs.getString("product_name"));
                    product.setproductCode(rs.getString("product_code"));
                    product.setprice(rs.getInt("product_price"));
                    product.setproductquantity(rs.getInt("product_quantity"));
                    product.setweight(rs.getInt("product_weight"));
                    product.setproductionDate(rs.getString("produtcion_date"));
                    this.productList.add(product);
                    
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null ;
        } catch (ParseException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            return null ;
        }
        
        return this.productList;                
        
       
   }
     
   public boolean checkQuantityAvailability(){
        return this.productList.get(0).getquantity() >= this.quantity;
    }
   
   public int calcu(){
       if(this.itemdiscount!=0){
           float itemdiscount = this.itemdiscount / 100 ;
           int total = this.quantity *this.productList.get(0).getweight()*this.productList.get(0).getprice();
           this.total =(int) (total- (total * itemdiscount));
       } else {
           this.total =this.quantity *this.productList.get(0).getweight()*this.productList.get(0).getprice();
            
       }
       this.productList.clear();
       return this.total ;
       
   }
}
