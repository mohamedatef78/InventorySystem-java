
package inventorysystem;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Mohamed atef
 */
public class Customer {
    private int customer_ID ;
    private String customerName;
    private String companyName;
    private String companyAddress;
    private int  phoneNumber;
    private DatabaseConnection con = new DatabaseConnection();
    
    
    
    public  void setcustomer_ID(int customer_ID){
         this.customer_ID = customer_ID ;
    }
    
    public  void setcustomerName(String customerName){
         this.customerName = customerName ;
    }
    public  void setcompanyName(String companyName){
         this.companyName = companyName ;
    }
    public  void setcompanyAddress(String companyAddress){
         this.companyAddress = companyAddress ;
    }
    public  void setphoneNumber(int phoneNumber){
         this.phoneNumber = phoneNumber ;
    }
    public  int getcustomer_ID(){
         return customer_ID ;
    }
    public  String getcustomerName(){
         return customerName ;
    }
    public  String getcompanyName(){
         return companyName ;
    }
    public  String getcompanyAddress(){
         return companyAddress ;
    }
    public  int getphoneNumber(){
         return phoneNumber ;
    }
    
    public boolean addCustomer(){
        try{
            String query = "INSERT INTO customer(customer_name,company_name,company_address,customer_phone) "
                 + "VALUES ('"+this.customerName+"','"+this.companyName+"','"+this.companyAddress+"','"+this.phoneNumber+"')";
            this.con.executeSQLQuery(query, "INSERT");
            return true ;
        }catch ( Exception e) {
            System.out.println(e);
            return false ;
        }
        
    }
    
    public ArrayList<Customer> customerList(){
        
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        String query = "SELECT * FROM customer";
        ResultSet rs= null;
       
        Customer customer = null;
        try{ 
            rs =(ResultSet) this.con.executeSQLQuery(query, "SELECT");
            while(rs.next()){
                customer = new Customer();
                customer.setcustomer_ID(rs.getInt("id"));
                customer.setcustomerName(rs.getString("customer_name"));
                customer.setcompanyName(rs.getString("company_name"));
                customer.setcompanyAddress(rs.getString("company_address"));
                customer.setphoneNumber(rs.getInt("customer_phone"));
                customerList.add(customer);
            }
            
        }catch (Exception ex){
            System.out.println(ex);
            return null;
        }
        return customerList;
        
    }
}
