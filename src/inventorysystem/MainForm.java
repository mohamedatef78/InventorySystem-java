/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Mohamed atef
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public String productCode ;
    public String productName ;
    public int productPrice;
    public int productQuantity;
    public int productWeight;
    public String produtciondate;
    public int product_ID;
    public Product product  = new Product();
    public Customer customer = new Customer();
    public Invoice invoice = new Invoice();
    public Sales sales = new Sales();
    
    MatteBorder default_lBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, java.awt.Color.BLACK);
    MatteBorder WHITE_lBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, java.awt.Color.WHITE);
        // create an array of jlabels
        // create an array of jlabels
    JLabel[] menuLabels = new JLabel[7];
    
    // create an array of jpanels
    JPanel[] panels = new JPanel[4];
    


    public MainForm() throws ParseException {
        initComponents();
        MatteBorder container_lBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, java.awt.Color.BLACK);
        jPanel_container.setBorder(container_lBorder);
        //jtext.setText("");
        menuLabels[0] = jLabel_menuItem1;
        menuLabels[1] = jLabel_menuItem2;
        menuLabels[2] = jLabel_menuItem3; 
        menuLabels[3] = jLabel_menuItem4;
        menuLabels[4] = jLabel_menuItem5;
        
        panels[0] = dashboard_panel;
        panels[1] = jPanel_client;
        panels[2] = jPanel_product;
        panels[3] = jPanel_invoice;
        addActionToMenuLabels();
        jPanel_client.setVisible(false);
        jPanel_product.setVisible(false);
        jPanel_invoice.setVisible(false);   
    }
    public void showProductList(){
        ArrayList<Product> productList =   new ArrayList<>();
        productList = this.product.productlist();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Object[] row = new Object[7];
        for(int  i= 0 ; i<productList.size();i++){
           // System.out.println(productList);
            row[0] = productList.get(i).getproductionID();
            row[1] = productList.get(i).getproductCode();
            row[2] = productList.get(i).getproductName();
            row[3] = productList.get(i).getprice();
            row[4] = productList.get(i).getquantity();
            row[5] = productList.get(i).getweight();
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date =sdf.format(productList.get(i).getproductionDate());
            row[6] = date;
            model.addRow(row);
        }

    }
    public void showCustomerList(){
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList = this.customer.customerList();
        DefaultTableModel model = (DefaultTableModel) jTable_customerlist.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Object[] row = new Object[4];
        for(int i  = 0 ; i< customerList.size();i++){
            row[0]=customerList.get(i).getcustomer_ID();
            row[1]=customerList.get(i).getcustomerName();
            row[2]=customerList.get(i).getphoneNumber();
            row[3]=customerList.get(i).getcompanyAddress();
            model.addRow(row);
            
        }
    }
     private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // close this form
        this.dispose();

    }
    // create a function to show the selected panel
    public void showPanel(JPanel panel)
    {
        for (JPanel pnl : panels) 
        {
            pnl.setVisible(false);
        }
        panel.setVisible(true);
    }
     public void setLabelBackround(JLabel label)
    {
        // reset labels to their default design
        for (JLabel menuItem : menuLabels)
        {
           // change the jlabel background color to white
           menuItem.setBackground(java.awt.Color.DARK_GRAY);
           // change the jlabel Foreground color to blue
           menuItem.setForeground(java.awt.Color.white); 
        }
        
        // change the jlabel background color to white
        label.setBackground(java.awt.Color.white);
        // change the jlabel Foreground color to blue
        label.setForeground(java.awt.Color.white);
    }

    public void addActionToMenuLabels(){
        Component[] components =jPanel_menu.getComponents();
        
        for(Component component : components){
            if(component instanceof JLabel){
                JLabel label = (JLabel) component;
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                         
                        switch(label.getText().trim()) {
                            case "DashBoard" :
                                    showPanel(dashboard_panel);
                                    break;
                            case "Products" :
                                    showPanel(jPanel_product);
                                    clear();
                                    break;
                            case "Clients" :
                                    showPanel(jPanel_client);
                                    showCustomerList();
                                    break;
                            case "Invoice":
                                    showPanel(jPanel_invoice);
                                    break;
                        }
                        
                    }
                    @Override
                    public void mousePressed(MouseEvent me) { 
                     
                    } 
                    @Override
                    public void mouseReleased(MouseEvent e) {
                      }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // on the mouse entred event
                        // set the border to yellow
                        label.setBorder(WHITE_lBorder);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        // on the mouse exited event
                        // reset to the default border
                       label.setBorder(default_lBorder);
                        
                      }
                        
                      
                   });
            }
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel_container = new javax.swing.JPanel();
        jPanel_menu = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_appLogo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel_menuItem1 = new javax.swing.JLabel();
        jLabel_menuItem5 = new javax.swing.JLabel();
        jLabel_menuItem3 = new javax.swing.JLabel();
        jLabel_menuItem4 = new javax.swing.JLabel();
        jLabel_menuItem2 = new javax.swing.JLabel();
        dashboard_panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel_client = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_customername = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField_companyaddress = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField_customerphone = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField_companyname = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_customerlist = new javax.swing.JTable();
        jPanel_product = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_productcode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_productname = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_productquantity = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField_productprice = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_productweight = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jDate_production = new com.toedter.calendar.JDateChooser();
        Add_product = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_update = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jPanel_invoice = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        invoice_itemname = new javax.swing.JTextField();
        invoice_itemweight = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        invoice_itemprice = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        nvoice_itemdiscount = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        invoice_itemtotal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        invoicer_add = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_invoice = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton_delete1 = new javax.swing.JButton();
        jButton_calcu = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        Add_product2 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        invoice_itemproductcode = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jTextField_invoice_itemquantity = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_container.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_menu.setBackground(new java.awt.Color(0, 0, 0));
        jPanel_menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("AL SALAM");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_appLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_appLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel_menuItem1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel_menuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuItem1.setText("DashBoard");
        jLabel_menuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel_menuItem5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel_menuItem5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuItem5.setText("Purcash");

        jLabel_menuItem3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel_menuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuItem3.setText("Invoice");

        jLabel_menuItem4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel_menuItem4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuItem4.setText("Clients");
        jLabel_menuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_menuItem4MouseClicked(evt);
            }
        });

        jLabel_menuItem2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel_menuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItem2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_menuItem2.setText("Products");
        jLabel_menuItem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_menuItem3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_menuItem5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_menuItem4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel_menuItem2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jLabel_menuItem1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(76, 76, 76)
                .addComponent(jLabel_menuItem2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(57, 57, 57)
                .addComponent(jLabel_menuItem3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(62, 62, 62)
                .addComponent(jLabel_menuItem4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(74, 74, 74)
                .addComponent(jLabel_menuItem5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(208, 208, 208))
        );

        dashboard_panel.setBackground(new java.awt.Color(255, 255, 255));
        dashboard_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Products");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Products");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Products");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setPreferredSize(new java.awt.Dimension(314, 247));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Products");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setPreferredSize(new java.awt.Dimension(314, 247));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Products");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setPreferredSize(new java.awt.Dimension(314, 247));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Products");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(176, 176, 176))
        );

        javax.swing.GroupLayout dashboard_panelLayout = new javax.swing.GroupLayout(dashboard_panel);
        dashboard_panel.setLayout(dashboard_panelLayout);
        dashboard_panelLayout.setHorizontalGroup(
            dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboard_panelLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboard_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboard_panelLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))
                .addGap(124, 124, 124)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(61, 61, 61))
        );
        dashboard_panelLayout.setVerticalGroup(
            dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboard_panelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dashboard_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
        );

        jPanel_client.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_client.setPreferredSize(new java.awt.Dimension(1394, 481));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(" Customer Name :");

        jTextField_customername.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_customername.setText(" ");
        jTextField_customername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_customername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_customernameActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(" Address :");

        jTextField_companyaddress.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_companyaddress.setText(" ");
        jTextField_companyaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_companyaddressActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Phone number :");

        jTextField_customerphone.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_customerphone.setText(" ");
        jTextField_customerphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_customerphoneActionPerformed(evt);
            }
        });
        jTextField_customerphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_customerphoneKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Campony Name :");

        jTextField_companyname.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_companyname.setText(" ");
        jTextField_companyname.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_companyname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_companynameActionPerformed(evt);
            }
        });

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");

        jTable_customerlist.setAutoCreateRowSorter(true);
        jTable_customerlist.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTable_customerlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "phone", "address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_customerlist.setGridColor(new java.awt.Color(255, 255, 255));
        jTable_customerlist.setRowHeight(30);
        jScrollPane2.setViewportView(jTable_customerlist);
        if (jTable_customerlist.getColumnModel().getColumnCount() > 0) {
            jTable_customerlist.getColumnModel().getColumn(0).setMinWidth(50);
            jTable_customerlist.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable_customerlist.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel_clientLayout = new javax.swing.GroupLayout(jPanel_client);
        jPanel_client.setLayout(jPanel_clientLayout);
        jPanel_clientLayout.setHorizontalGroup(
            jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_clientLayout.createSequentialGroup()
                .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_clientLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_clientLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_clientLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_clientLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_customerphone, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_companyaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_clientLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_companyname, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_customername, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_clientLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE))
        );
        jPanel_clientLayout.setVerticalGroup(
            jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_clientLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_clientLayout.createSequentialGroup()
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_customername))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_companyname))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_companyaddress))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_customerphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addGroup(jPanel_clientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(112, 112, 112))
                    .addGroup(jPanel_clientLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );

        jPanel_product.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(" Code :");

        jTextField_productcode.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_productcode.setText(" ");
        jTextField_productcode.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_productcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_productcodeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(" Name :");

        jTextField_productname.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_productname.setText(" ");
        jTextField_productname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_productnameActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Quantity :");

        jTextField_productquantity.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_productquantity.setText(" ");
        jTextField_productquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_productquantityActionPerformed(evt);
            }
        });
        jTextField_productquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_productquantityKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Weight :");

        jTextField_productprice.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_productprice.setText(" ");
        jTextField_productprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_productpriceActionPerformed(evt);
            }
        });
        jTextField_productprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_productpriceKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Production Date:");

        jTextField_productweight.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_productweight.setText(" ");
        jTextField_productweight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_productweightActionPerformed(evt);
            }
        });
        jTextField_productweight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_productweightKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Price :");

        jDate_production.setDateFormatString("yyy-MM-dd");
        jDate_production.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Add_product.setBackground(new java.awt.Color(0, 0, 102));
        Add_product.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        Add_product.setForeground(new java.awt.Color(255, 255, 255));
        Add_product.setText("Add ");
        Add_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_productActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 120, 215));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "      ID", "        Code", "        Name", "        Price", "      Quantity", "      weight    ", "   Production Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFillsViewportHeight(true);
        jTable1.setRowHeight(50);
        jTable1.setRowMargin(5);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(4).setMinWidth(100);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(100);
            jTable1.getColumnModel().getColumn(5).setMinWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jButton_update.setBackground(new java.awt.Color(0, 0, 102));
        jButton_update.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(255, 255, 255));
        jButton_update.setText("Update");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 0, 153));
        jButton1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(204, 0, 0));
        jButton_delete.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jButton_delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete.setText("Delete");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_productLayout = new javax.swing.GroupLayout(jPanel_product);
        jPanel_product.setLayout(jPanel_productLayout);
        jPanel_productLayout.setHorizontalGroup(
            jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_productLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_productLayout.createSequentialGroup()
                        .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_productweight, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField_productname, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_productcode, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_productprice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_productquantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_productLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jDate_production, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_productLayout.createSequentialGroup()
                        .addComponent(Add_product, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
        );
        jPanel_productLayout.setVerticalGroup(
            jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_productLayout.createSequentialGroup()
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_productLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_productLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_productcode, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_productLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_productLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_productname, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGap(40, 40, 40)
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jTextField_productquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_productweight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_productprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addGroup(jPanel_productLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jDate_production, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(66, 66, 66)
                .addGroup(jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Add_product, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(134, 134, 134))
            .addGroup(jPanel_productLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel_invoice.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel1.setText("Product Name :");

        jLabel2.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel2.setText("Quantity :");

        invoice_itemname.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N

        invoice_itemweight.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        invoice_itemweight.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel20.setText("weight :");

        invoice_itemprice.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        invoice_itemprice.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel21.setText("Price :");

        nvoice_itemdiscount.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        nvoice_itemdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nvoice_itemdiscountActionPerformed(evt);
            }
        });
        nvoice_itemdiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nvoice_itemdiscountKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nvoice_itemdiscountKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel22.setText("Discount %");

        invoice_itemtotal.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        invoice_itemtotal.setEnabled(false);

        jLabel23.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel23.setText("Total :");

        invoicer_add.setBackground(new java.awt.Color(0, 0, 0));
        invoicer_add.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        invoicer_add.setForeground(new java.awt.Color(255, 255, 255));
        invoicer_add.setText("Add ");
        invoicer_add.setEnabled(false);
        invoicer_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoicer_addActionPerformed(evt);
            }
        });

        jTable_invoice.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 16)); // NOI18N
        jTable_invoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "اسم الصنف", "الكميه", "الوزن", "سعر الكيلو", "% خصم", "اجمالي المطلوب"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_invoice.setRowHeight(30);
        jScrollPane3.setViewportView(jTable_invoice);
        if (jTable_invoice.getColumnModel().getColumnCount() > 0) {
            jTable_invoice.getColumnModel().getColumn(0).setMinWidth(100);
            jTable_invoice.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable_invoice.getColumnModel().getColumn(0).setMaxWidth(100);
            jTable_invoice.getColumnModel().getColumn(1).setMinWidth(400);
            jTable_invoice.getColumnModel().getColumn(1).setPreferredWidth(400);
            jTable_invoice.getColumnModel().getColumn(1).setMaxWidth(400);
            jTable_invoice.getColumnModel().getColumn(2).setMinWidth(100);
            jTable_invoice.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable_invoice.getColumnModel().getColumn(2).setMaxWidth(200);
            jTable_invoice.getColumnModel().getColumn(3).setMinWidth(100);
            jTable_invoice.getColumnModel().getColumn(3).setPreferredWidth(200);
            jTable_invoice.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable_invoice.getColumnModel().getColumn(4).setMinWidth(100);
            jTable_invoice.getColumnModel().getColumn(4).setPreferredWidth(200);
            jTable_invoice.getColumnModel().getColumn(4).setMaxWidth(100);
            jTable_invoice.getColumnModel().getColumn(5).setMinWidth(100);
            jTable_invoice.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable_invoice.getColumnModel().getColumn(5).setMaxWidth(100);
            jTable_invoice.getColumnModel().getColumn(6).setMinWidth(150);
            jTable_invoice.getColumnModel().getColumn(6).setPreferredWidth(250);
            jTable_invoice.getColumnModel().getColumn(6).setMaxWidth(150);
        }

        jTextField8.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel25.setText("Company :");

        jButton_delete1.setBackground(new java.awt.Color(102, 0, 0));
        jButton_delete1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jButton_delete1.setForeground(new java.awt.Color(255, 255, 255));
        jButton_delete1.setText("Delete");
        jButton_delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_delete1ActionPerformed(evt);
            }
        });

        jButton_calcu.setBackground(new java.awt.Color(0, 0, 153));
        jButton_calcu.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jButton_calcu.setForeground(new java.awt.Color(255, 255, 255));
        jButton_calcu.setText("Calcu");
        jButton_calcu.setEnabled(false);
        jButton_calcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcuActionPerformed(evt);
            }
        });

        jTextField9.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jTextField9.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel26.setText("Grand total");

        Add_product2.setBackground(new java.awt.Color(0, 0, 0));
        Add_product2.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        Add_product2.setForeground(new java.awt.Color(255, 255, 255));
        Add_product2.setText("Save");
        Add_product2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_product2ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jLabel27.setText("Product code :");

        invoice_itemproductcode.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        invoice_itemproductcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                invoice_itemproductcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                invoice_itemproductcodeKeyReleased(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 0, 153));
        jButton5.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField_invoice_itemquantity.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jTextField_invoice_itemquantity.setText(" ");
        jTextField_invoice_itemquantity.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_invoice_itemquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_invoice_itemquantityActionPerformed(evt);
            }
        });
        jTextField_invoice_itemquantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_invoice_itemquantityKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_invoice_itemquantityKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel_invoiceLayout = new javax.swing.GroupLayout(jPanel_invoice);
        jPanel_invoice.setLayout(jPanel_invoiceLayout);
        jPanel_invoiceLayout.setHorizontalGroup(
            jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24)
                        .addComponent(invoice_itemname, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_invoice_itemquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(invoice_itemweight, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(invoice_itemprice, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(nvoice_itemdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(invoice_itemtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                        .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(226, 226, 226)
                                .addComponent(jLabel27)
                                .addGap(24, 24, 24)
                                .addComponent(invoice_itemproductcode, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                        .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField9))
                            .addComponent(Add_product2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                        .addGap(18, 28, Short.MAX_VALUE)
                        .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoicer_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_delete1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_invoiceLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_calcu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel_invoiceLayout.setVerticalGroup(
            jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(invoice_itemproductcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(invoice_itemname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invoice_itemweight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(invoice_itemprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(nvoice_itemdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(invoice_itemtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_calcu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_invoice_itemquantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoicer_add, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_invoiceLayout.createSequentialGroup()
                        .addComponent(jButton_delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addGroup(jPanel_invoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(41, 41, 41)
                        .addComponent(Add_product2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel_containerLayout = new javax.swing.GroupLayout(jPanel_container);
        jPanel_container.setLayout(jPanel_containerLayout);
        jPanel_containerLayout.setHorizontalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_containerLayout.createSequentialGroup()
                .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dashboard_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(285, 285, 285)
                    .addComponent(jPanel_client, javax.swing.GroupLayout.DEFAULT_SIZE, 1393, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(268, 268, 268)
                    .addComponent(jPanel_product, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 268, Short.MAX_VALUE)
                    .addComponent(jPanel_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel_containerLayout.setVerticalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dashboard_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_client, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_product, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_invoice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_menuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_menuItem4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel_menuItem4MouseClicked

    private void jTextField_productpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_productpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_productpriceActionPerformed

    private void jTextField_productweightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_productweightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_productweightActionPerformed

    private void Add_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_productActionPerformed
        Product product = null;
        this.productName = jTextField_productname.getText().trim();
        this.productCode = jTextField_productcode.getText().trim();
        this.productPrice = Integer.parseInt(jTextField_productprice.getText().trim());
        this.productQuantity = Integer.parseInt(jTextField_productquantity.getText().trim());
        this.productWeight = Integer.parseInt(jTextField_productweight.getText().trim());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.produtciondate =sdf.format(jDate_production.getDate());
       
        boolean check = this.product.addProduct(this.productName, this.productQuantity, this.productPrice, this.productWeight, this.productCode, this.produtciondate);
        if(check){
            clear();
        }
    }//GEN-LAST:event_Add_productActionPerformed
    private void clear(){
            showProductList();
            jTextField_productcode.setText("");
            jTextField_productname.setText("");
            jTextField_productprice.setText("");
            jTextField_productquantity.setText("");
            jTextField_productweight.setText("");
            Date date  = null;
            jDate_production.setDate(date);
            Add_product.setEnabled(true);
            
    }
    private void invoice_clear(){
        invoice_itemproductcode.setText("");
        invoice_itemname.setText("");
        jTextField_invoice_itemquantity.setText("");
        invoice_itemweight.setText("");
        invoice_itemprice.setText("");
        nvoice_itemdiscount.setText("");
        invoice_itemtotal.setText("");
        jButton_calcu.setEnabled(false);
        invoicer_add.setEnabled(false);
    }
    private void jTextField_productweightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_productweightKeyTyped
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==java.awt.event.KeyEvent.VK_BACK_SPACE)||c==java.awt.event.KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_productweightKeyTyped

    private void jTextField_productpriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_productpriceKeyTyped
        // TODO add your handling code here:
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==java.awt.event.KeyEvent.VK_BACK_SPACE)||c==java.awt.event.KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_productpriceKeyTyped

    private void jTextField_productquantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_productquantityKeyTyped
        // TODO add your handling code here:
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==java.awt.event.KeyEvent.VK_BACK_SPACE)||c==java.awt.event.KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }

    }//GEN-LAST:event_jTextField_productquantityKeyTyped

    private void jTextField_productquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_productquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_productquantityActionPerformed

    private void jTextField_productnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_productnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_productnameActionPerformed

    private void jTextField_productcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_productcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_productcodeActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            // TODO add your handling code here:
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int i  = jTable1.getSelectedRow();
            this.product_ID = Integer.parseInt(model.getValueAt(i, 0).toString());
            jTextField_productcode.setText(model.getValueAt(i,1).toString());
            jTextField_productname.setText(model.getValueAt(i,2).toString());
            jTextField_productprice.setText(model.getValueAt(i,3).toString());
            jTextField_productquantity.setText(model.getValueAt(i,4).toString());
            jTextField_productweight.setText(model.getValueAt(i,5).toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date productionDate = sdf.parse(model.getValueAt(i,6).toString());
            jDate_production.setDate(productionDate);
            Add_product.setEnabled(false);
          
            this.produtciondate =sdf.format(jDate_production.getDate());
        } catch (ParseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
        try {
            // TODO add your handling code here:
            this.product.setproductName(jTextField_productname.getText());
            this.product.setproductID(this.product_ID);
            this.product.setproductCode(jTextField_productcode.getText());
            this.product.setprice( Integer.parseInt(jTextField_productprice.getText().trim()));
            this.product.setproductquantity( Integer.parseInt(jTextField_productquantity.getText().trim()));
            this.product.setweight( Integer.parseInt(jTextField_productweight.getText().trim()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.product.setproductionDate(sdf.format(jDate_production.getDate()));
            boolean check = this.product.updateProduct();
            if(check){
                clear();
            }
        } catch (ParseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jTextField_customernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_customernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_customernameActionPerformed

    private void jTextField_companyaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_companyaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_companyaddressActionPerformed

    private void jTextField_customerphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_customerphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_customerphoneActionPerformed

    private void jTextField_customerphoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_customerphoneKeyTyped
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==java.awt.event.KeyEvent.VK_BACK_SPACE)||c==java.awt.event.KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_customerphoneKeyTyped

    private void jTextField_companynameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_companynameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_companynameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            this.customer.setcompanyName(jTextField_companyname.getText());
            this.customer.setcustomerName(jTextField_customername.getText());
            this.customer.setcompanyAddress(jTextField_companyaddress.getText());
            this.customer.setphoneNumber(Integer.parseInt(jTextField_customerphone.getText().trim()));
            boolean result =this.customer.addCustomer();
            if(result){
                jTextField_companyname.setText("");
                jTextField_customername.setText("");
                jTextField_companyaddress.setText("");
                jTextField_customerphone.setText("");
                showCustomerList();
                
            }
        }catch ( Exception e) {
            System.out.println(e);
            
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed
        // TODO add your handling code here:
        this.product.setproductID(this.product_ID);
        boolean result =this.product.DeleteProduct();
        if(result){
                clear();
            }
    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void invoicer_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoicer_addActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable_invoice.getModel();
        Object[] row = new Object[7];
            row[0]=this.sales.getproduct_ID();
           // System.out.println(invoice_itemname.getText());
            row[1]=this.productName;
            row[2]=this.sales.getquantity();
            row[3]= Integer.parseInt(invoice_itemweight.getText().trim());
            row[4]=Integer.parseInt( invoice_itemprice.getText().trim());
            row[5]=this.sales.getitemdiscount();
            row[6]=this.sales.gettotal();
            model.addRow(row);
            
         invoice_clear();

        
        
    }//GEN-LAST:event_invoicer_addActionPerformed

    private void jButton_delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_delete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_delete1ActionPerformed

    private void jButton_calcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcuActionPerformed
        // TODO add your handling code here
        this.sales.setquantity(Integer.parseInt(jTextField_invoice_itemquantity.getText().trim()));
        if(this.sales.checkQuantityAvailability()){
            
            if(nvoice_itemdiscount.getText().isEmpty()){

                this.sales.setitemdiscount(0);
            }else{
                this.sales.setitemdiscount(Integer.parseInt(nvoice_itemdiscount.getText()));

            }

            invoice_itemtotal.setText(Integer.toString(this.sales.calcu()));
            invoicer_add.setEnabled(true);
        }else{
          JOptionPane.showMessageDialog(this, "there is  no enough quantity");
        }
        
        
        
    }//GEN-LAST:event_jButton_calcuActionPerformed

    private void Add_product2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_product2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Add_product2ActionPerformed

    private void nvoice_itemdiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvoice_itemdiscountKeyPressed

    }//GEN-LAST:event_nvoice_itemdiscountKeyPressed

    private void invoice_itemproductcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoice_itemproductcodeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== java.awt.event.KeyEvent.VK_ENTER){
            ArrayList<Product> productitem =   new ArrayList<>();
            String productcode = invoice_itemproductcode.getText();
            productitem=this.sales.fetchproduct(productcode);
            if(productitem.isEmpty()){
                JOptionPane.showMessageDialog(this, "Poduct not found ");
                
            }
            
            for(int i = 0 ; i <productitem.size() ; i++){
                invoice_itemname.setText(productitem.get(i).getproductName());
                invoice_itemweight.setText(Integer.toString(productitem.get(i).getweight()));
                invoice_itemprice.setText(Integer.toString(productitem.get(i).getprice()));
                this.productQuantity = productitem.get(i).getquantity();  
                this.product_ID = productitem.get(i).getproductionID();
                this.productName = productitem.get(i).getproductName();
                jButton_calcu.setEnabled(true);
            } 
            
        }

    }//GEN-LAST:event_invoice_itemproductcodeKeyPressed

    private void invoice_itemproductcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoice_itemproductcodeKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_invoice_itemproductcodeKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        invoice_clear();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void nvoice_itemdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nvoice_itemdiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nvoice_itemdiscountActionPerformed

    private void jTextField_invoice_itemquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_invoice_itemquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_invoice_itemquantityActionPerformed

    private void jTextField_invoice_itemquantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_invoice_itemquantityKeyPressed
 
    }//GEN-LAST:event_jTextField_invoice_itemquantityKeyPressed

    private void jTextField_invoice_itemquantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_invoice_itemquantityKeyTyped
          // TODO add your handling code here:
         char c =evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==java.awt.event.KeyEvent.VK_BACK_SPACE)||c==java.awt.event.KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_invoice_itemquantityKeyTyped

    private void nvoice_itemdiscountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nvoice_itemdiscountKeyTyped
          // TODO add your handling code here:
         char c =evt.getKeyChar();
        if(!(Character.isDigit(c)||(c==java.awt.event.KeyEvent.VK_BACK_SPACE)||c==java.awt.event.KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_nvoice_itemdiscountKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainForm().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_product;
    private javax.swing.JButton Add_product2;
    private javax.swing.JPanel dashboard_panel;
    private javax.swing.JTextField invoice_itemname;
    private javax.swing.JTextField invoice_itemprice;
    private javax.swing.JTextField invoice_itemproductcode;
    private javax.swing.JTextField invoice_itemtotal;
    private javax.swing.JTextField invoice_itemweight;
    private javax.swing.JButton invoicer_add;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton_calcu;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_delete1;
    private javax.swing.JButton jButton_update;
    private com.toedter.calendar.JDateChooser jDate_production;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_appLogo;
    private javax.swing.JLabel jLabel_menuItem1;
    private javax.swing.JLabel jLabel_menuItem2;
    private javax.swing.JLabel jLabel_menuItem3;
    private javax.swing.JLabel jLabel_menuItem4;
    private javax.swing.JLabel jLabel_menuItem5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_client;
    private javax.swing.JPanel jPanel_container;
    private javax.swing.JPanel jPanel_invoice;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPanel jPanel_product;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_customerlist;
    private javax.swing.JTable jTable_invoice;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField_companyaddress;
    private javax.swing.JTextField jTextField_companyname;
    private javax.swing.JTextField jTextField_customername;
    private javax.swing.JTextField jTextField_customerphone;
    private javax.swing.JTextField jTextField_invoice_itemquantity;
    private javax.swing.JTextField jTextField_productcode;
    private javax.swing.JTextField jTextField_productname;
    private javax.swing.JTextField jTextField_productprice;
    private javax.swing.JTextField jTextField_productquantity;
    private javax.swing.JTextField jTextField_productweight;
    private javax.swing.JTextField nvoice_itemdiscount;
    // End of variables declaration//GEN-END:variables
}
