/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;


/**Mon May 18 00:00:00 EET 2020
 *
 * @author Mohamed atef
 */
public class IneventorySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product product  = new Product();
        ArrayList<Product> productList =   new ArrayList<>();
        productList = product.productlist();
        //System.out.println(productList);
        
        

        
        
        
//         product.addProduct("توت احمر",1000,285, 5, "towat");
        // System.out.println(String.format("A new candidate with id %d has been inserted.",id));

    }
    
}
