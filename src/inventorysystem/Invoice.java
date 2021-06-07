/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;

/**
 *
 * @author Mohamed atef
 */
public class Invoice {
    
    protected int invoice_ID ;
    protected Date invoice_date;
    protected int subtotal;
    protected int discout_total;
    protected int customer_invoice_id ;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private DatabaseConnection con = new DatabaseConnection();
    
    
    public  void setinvoice_ID(int invoice_ID){
         this.invoice_ID = invoice_ID ;
    }
    //sdf.parse(productionDate)
    public  void setinvoice_date(Date invoice_date){
         this.invoice_date = invoice_date ;
    }
    public  void setsubtotal(int subtotal){
         this.subtotal = subtotal ;
    }
    public  void setdiscout_total(int discout_total){
         this.discout_total = discout_total ;
    }
    public  void setcustomer_invoice_id(int customer_invoice_id){
         this.customer_invoice_id = customer_invoice_id ;
    }
    //getter
    public int getinvoice_ID() {
        return invoice_ID;
    }
    public Date getinvoice_date() {
        return invoice_date;
    }
    public int getsubtotal() {
        return subtotal;
    }
    public int getdiscout_total() {
        return discout_total;
    }
    public int getcustomer_invoice_id() {
        return customer_invoice_id;
    }
    
    
    
    
    
    public ArrayList<Invoice> productsale(){
        return null;
        
    }
    
}
