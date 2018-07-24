package inventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernest
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window
     */
    DefaultTableModel dm;
    
    public Main_Window() {
        initComponents();
        getConnection();       
        CreateColumns();
        Populate_Table();
        JTable_Products.setAutoCreateRowSorter(true);
        
    }
      
    public Connection getConnection()
    {
       Connection con = null;
       
       try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/system_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean checkInputs_mat()
    {
        if(
           mat_name.getText() == null 
        || mat_loc.getText() == null
        || mat_unit.getText() == null
        || mat_rmk.getText() == null
        ){
            return false;
        }else{
            try{
                Double.parseDouble(mat_qty.getText());
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
                
    }
    
    public boolean checkInputs_tae()
    {
        if(
           tae_name.getText() == null 
        || tae_loc.getText() == null
        || tae_amount.getText() == null
        || tae_hty.getText() == null
        || tae_stat.getText() == null
        || tae_rmk.getText() == null
        || tae_dop.getDate() == null
        ){
            return false;
        }else{
            try{
                Double.parseDouble(tae_amount.getText());
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
                
    }
 
    public boolean checkInputs_faf()
    {
        if(
           faf_name.getText() == null 
        || faf_loc.getText() == null
        || faf_type.getText() == null
        || faf_stat.getText() == null
        || faf_rmk.getText() == null
        ){
            return false;
        }else{
            try{
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
                
    }
        
    // Display Data in JTable:
    
    // 1 - Fill ArrayList With the Data
    
    public ArrayList<matConstructor> getMaterialList()
    {
            ArrayList<matConstructor> productList = new ArrayList <matConstructor>();
            Connection con = getConnection();
            String query = "SELECT * FROM mat_inv";
            
            Statement st;
            ResultSet rs;
            
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            matConstructor product;
            
            while(rs.next())
            {
                product = new matConstructor(rs.getInt("id"),rs.getString("name"),rs.getString("location"),Double.parseDouble(rs.getString("quantity")),
                        rs.getString("unit"),rs.getString("remarks"), rs.getString("prefix"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
        
    }

    public ArrayList<toolConstructor> getToolsEquipmentList()
    {
            ArrayList<toolConstructor> productList = new ArrayList <toolConstructor>();
            Connection con = getConnection();
            String query = "SELECT * FROM tool_inv";
            
            Statement st;
            ResultSet rs;
            
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            toolConstructor product;
            
            while(rs.next())
            {
                product = new toolConstructor(rs.getInt("id"),rs.getString("name"),rs.getString("location"),
                        rs.getString("dop"),Double.parseDouble(rs.getString("amount")),rs.getString("history"),
                        rs.getString("status"),rs.getString("remarks"),rs.getString("prefix"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
        
    }

    public ArrayList<furnConstructor> getFurnFixturesList()
    {
            ArrayList<furnConstructor> productList = new ArrayList <furnConstructor>();
            Connection con = getConnection();
            String query = "SELECT * FROM furn_inv";
            
            Statement st;
            ResultSet rs;
            
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            furnConstructor product;
            
            while(rs.next())
            {
                product = new furnConstructor(rs.getInt("id"),rs.getString("name"),rs.getString("location"),rs.getString("status"),
                        rs.getString("type"),rs.getString("remarks"),rs.getString("prefix"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
        
    }
    
    // 2- Populate the JTable
    public void Material_Constructor()
    {
        ArrayList<matConstructor> materialList = getMaterialList();
        dm = (DefaultTableModel)JTable_Products.getModel();
        
        Object[] row = new Object[12];
        for(int i = 0; i < materialList.size(); i++)
        {

            row[0] = materialList.get(i).getPrefix();
            row[1] = materialList.get(i).getId();
            row[2] = materialList.get(i).getName();
            row[3] = materialList.get(i).getLocation();
            row[4] = materialList.get(i).getQuantity();
            row[5] = materialList.get(i).getUnit();
            row[11] = materialList.get(i).getRemarks();

            dm.addRow(row); 
        }
    }
    public void ToolsEquipment_Constructor()
    {
        ArrayList<toolConstructor> toolList = getToolsEquipmentList();
        dm = (DefaultTableModel)JTable_Products.getModel();
        
        Object[] rows = new Object[12];
        for(int i = 0; i < toolList.size(); i++)
        {

            rows[0] = toolList.get(i).getPrefix();
            rows[1] = toolList.get(i).getId();
            rows[2] = toolList.get(i).getName();
            rows[3] = toolList.get(i).getLocation();
            rows[6] = toolList.get(i).getDop();
            rows[7] = toolList.get(i).getAmount();
            rows[8] = toolList.get(i).getHistory();
            rows[9] = toolList.get(i).getStatus();
            rows[11] = toolList.get(i).getRemarks();

            dm.addRow(rows);

        }
    }
    public void FurnFixtures_Constructor()
    {
        ArrayList<furnConstructor> furnList = getFurnFixturesList();
        dm = (DefaultTableModel)JTable_Products.getModel();
        
        Object[] rowss = new Object[12];
        for(int i = 0; i < furnList.size(); i++)
        {

            rowss[0] = furnList.get(i).getPrefix();
            rowss[1] = furnList.get(i).getId();
            rowss[2] = furnList.get(i).getName();
            rowss[3] = furnList.get(i).getLocation();
            rowss[9] = furnList.get(i).getStatus();
            rowss[10] = furnList.get(i).getType();
            rowss[11] = furnList.get(i).getRemarks(); 

            dm.addRow(rowss);          

        }
    }
    public void Populate_Table()
    {
        if(chkbox_mat.isSelected() == true && (chkbox_tae.isSelected() == false && chkbox_faf.isSelected() == false))
        {
            dm.setRowCount(0);
            Material_Constructor();
  
        } else if (chkbox_tae.isSelected() == true && (chkbox_mat.isSelected() == false && chkbox_faf.isSelected() == false))
        {
            dm.setRowCount(0);
            ToolsEquipment_Constructor();
            

        } else if (chkbox_faf.isSelected() == true && (chkbox_mat.isSelected() == false && chkbox_tae.isSelected() == false)) 
        {
            dm.setRowCount(0);
            FurnFixtures_Constructor();
 
        } else if ((chkbox_mat.isSelected() == true && chkbox_tae.isSelected() == true) && chkbox_faf.isSelected() == false)
        {
            dm.setRowCount(0);           
            Material_Constructor();
            ToolsEquipment_Constructor();
            
        } else if ((chkbox_mat.isSelected() == true && chkbox_faf.isSelected() == true) && chkbox_tae.isSelected() == false)
        {
            dm.setRowCount(0);
            Material_Constructor();
            FurnFixtures_Constructor();
        } else if (( chkbox_tae.isSelected() == true && chkbox_faf.isSelected() == true) && chkbox_mat.isSelected() == false)
        {
            dm.setRowCount(0);           
            ToolsEquipment_Constructor();
            FurnFixtures_Constructor();
        } else if ((chkbox_mat.isSelected() == true && chkbox_tae.isSelected() == true && chkbox_faf.isSelected() == true) || (chkbox_mat.isSelected() == false && chkbox_tae.isSelected() == false && chkbox_faf.isSelected() == false))
        {
            dm.setRowCount(0);

            Material_Constructor();
            ToolsEquipment_Constructor();
            FurnFixtures_Constructor(); 
        }
        
    }
    
    public void ShowItem ()
    {       
        dm = (DefaultTableModel)JTable_Products.getModel();
        int selectedRowIndex = JTable_Products.getSelectedRow();
        inv_idtype.setText(dm.getValueAt(selectedRowIndex, 0).toString());
        inv_id.setText(dm.getValueAt(selectedRowIndex, 1).toString());
        inv_name.setText(dm.getValueAt(selectedRowIndex, 2).toString());
        inv_loc.setText(dm.getValueAt(selectedRowIndex, 3).toString());
        if(dm.getValueAt(selectedRowIndex, 4) == null) 
        {
            inv_qty.setText("");
        }else{
            inv_qty.setText(dm.getValueAt(selectedRowIndex, 4).toString());
        }
        if(dm.getValueAt(selectedRowIndex, 5) == null) 
        {
            inv_unit.setText("");
        }else{
            inv_unit.setText(dm.getValueAt(selectedRowIndex, 5).toString());
        }
        if(dm.getValueAt(selectedRowIndex, 6) == null) 
        {
            inv_dop.setText("");
        }else{
            inv_dop.setText(dm.getValueAt(selectedRowIndex, 6).toString());
        }
        if(dm.getValueAt(selectedRowIndex, 7) == null) 
        {
            inv_amount.setText("");
        }else{
            inv_amount.setText(dm.getValueAt(selectedRowIndex, 7).toString()); 
        }
        if(dm.getValueAt(selectedRowIndex, 8) == null) 
        {
            inv_hty.setText("");
        }else{
            inv_hty.setText(dm.getValueAt(selectedRowIndex, 8).toString()); 
        }
        if(dm.getValueAt(selectedRowIndex, 9) == null) 
        {
            inv_status.setText("");
        }else{
            inv_status.setText(dm.getValueAt(selectedRowIndex, 9).toString());
        }
        if(dm.getValueAt(selectedRowIndex, 10) == null) 
        {
            inv_type.setText("");
        }else{
            inv_type.setText(dm.getValueAt(selectedRowIndex, 10).toString());
        }
        inv_remarks.setText(dm.getValueAt(selectedRowIndex, 11).toString());
    }
    
    public void CreateColumns()
    {
        dm = (DefaultTableModel)JTable_Products.getModel();
        
        dm.addColumn("Prefix");
        dm.addColumn("ID");
        dm.addColumn("Name");
        dm.addColumn("Location");
        dm.addColumn("Quantity");
        dm.addColumn("Unit");
        dm.addColumn("Date of Purchase");
        dm.addColumn("Amount");
        dm.addColumn("History");
        dm.addColumn("Status");
        dm.addColumn("Type");
        dm.addColumn("Remarks");
    }               

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        mat_loc = new javax.swing.JTextField();
        mat_unit = new javax.swing.JTextField();
        mat_qty = new javax.swing.JTextField();
        mat_name = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        mat_rmk = new javax.swing.JTextArea();
        btn_matUpdate = new javax.swing.JButton();
        btn_matAdd = new javax.swing.JButton();
        btn_matDel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tae_dop = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tae_loc = new javax.swing.JTextField();
        tae_stat = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tae_rmk = new javax.swing.JTextArea();
        btn_taeAdd = new javax.swing.JButton();
        tae_name = new javax.swing.JTextField();
        tae_amount = new javax.swing.JTextField();
        btn_taeUpdate = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tae_hty = new javax.swing.JTextArea();
        btn_taeDel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        faf_name = new javax.swing.JTextField();
        faf_loc = new javax.swing.JTextField();
        faf_type = new javax.swing.JTextField();
        faf_stat = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        faf_rmk = new javax.swing.JTextArea();
        btn_fafUpdate = new javax.swing.JButton();
        btn_fafAdd = new javax.swing.JButton();
        btn_fafDel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        inv_unit = new javax.swing.JTextField();
        inv_loc = new javax.swing.JTextField();
        inv_qty = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        inv_remarks = new javax.swing.JTextArea();
        chkbox_mat = new javax.swing.JCheckBox();
        chkbox_faf = new javax.swing.JCheckBox();
        chkbox_tae = new javax.swing.JCheckBox();
        inv_name = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        inv_type = new javax.swing.JTextField();
        inv_amount = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        inv_hty = new javax.swing.JTextArea();
        inv_status = new javax.swing.JTextField();
        inv_id = new javax.swing.JTextField();
        inv_dop = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        inv_idtype = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        search_label = new javax.swing.JLabel();
        inv_search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventory");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("NAME:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("LOCATION:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("QUANTITY:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("UNIT:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("REMARKS:");

        mat_loc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat_unit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat_qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        mat_rmk.setColumns(20);
        mat_rmk.setRows(5);
        jScrollPane2.setViewportView(mat_rmk);

        btn_matUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_matUpdate.setText("Update");
        btn_matUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_matUpdateActionPerformed(evt);
            }
        });

        btn_matAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_matAdd.setText("Add");
        btn_matAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_matAddActionPerformed(evt);
            }
        });

        btn_matDel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_matDel.setText("Delete");
        btn_matDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_matDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mat_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mat_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mat_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btn_matAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_matUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_matDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mat_name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mat_name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mat_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mat_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mat_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_matAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_matUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_matDel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Materials", jPanel2);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("NAME:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("DOP:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("REMARKS:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("LOCATION:");

        tae_dop.setDateFormatString("yyyy-MM-dd");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("HISTORY");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("STATUS:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("AMOUNT:");

        tae_loc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tae_stat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tae_rmk.setColumns(20);
        tae_rmk.setRows(5);
        jScrollPane4.setViewportView(tae_rmk);

        btn_taeAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_taeAdd.setText("Add");
        btn_taeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taeAddActionPerformed(evt);
            }
        });

        tae_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        tae_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btn_taeUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_taeUpdate.setText("Update");
        btn_taeUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taeUpdateActionPerformed(evt);
            }
        });

        tae_hty.setColumns(20);
        tae_hty.setRows(5);
        jScrollPane6.setViewportView(tae_hty);

        btn_taeDel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_taeDel.setText("Delete");
        btn_taeDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taeDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tae_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tae_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(tae_name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tae_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(tae_dop, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btn_taeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_taeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_taeDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tae_name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tae_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tae_dop, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tae_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tae_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_taeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_taeUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_taeDel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Tools and Equipment", jPanel4);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("NAME:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("LOCATION:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("STATUS:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("REMARKS:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("TYPE:");

        faf_name.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        faf_loc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        faf_type.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        faf_stat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        faf_rmk.setColumns(20);
        faf_rmk.setRows(5);
        jScrollPane5.setViewportView(faf_rmk);

        btn_fafUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_fafUpdate.setText("Update");
        btn_fafUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fafUpdateActionPerformed(evt);
            }
        });

        btn_fafAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_fafAdd.setText("Add");
        btn_fafAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fafAddActionPerformed(evt);
            }
        });

        btn_fafDel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_fafDel.setText("Delete");
        btn_fafDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fafDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(faf_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(faf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(30, 30, 30)
                                .addComponent(faf_type, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_fafAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_fafUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_fafDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(faf_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(faf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(faf_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(faf_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(faf_type, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_fafAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_fafUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_fafDel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Furnitures and Fixtures", jPanel3);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Modify Items");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("Location:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("Quantity:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setText("Unit:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel13.setText("REMARKS:");

        inv_unit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_unit.setEnabled(false);

        inv_loc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_loc.setEnabled(false);

        inv_qty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_qty.setEnabled(false);

        inv_remarks.setColumns(20);
        inv_remarks.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_remarks.setRows(5);
        inv_remarks.setEnabled(false);
        jScrollPane3.setViewportView(inv_remarks);

        chkbox_mat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkbox_mat.setText("Materials");
        chkbox_mat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbox_matActionPerformed(evt);
            }
        });

        chkbox_faf.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkbox_faf.setText("Furnitures and Fixtures");
        chkbox_faf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbox_fafActionPerformed(evt);
            }
        });

        chkbox_tae.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        chkbox_tae.setText("Tools and Equipment");
        chkbox_tae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbox_taeActionPerformed(evt);
            }
        });

        inv_name.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        inv_name.setEnabled(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel25.setText("Amount:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel26.setText("D.O.P:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel27.setText("History:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel28.setText("Type:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel29.setText("Status:");

        inv_type.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        inv_type.setEnabled(false);

        inv_amount.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        inv_amount.setEnabled(false);

        inv_hty.setColumns(20);
        inv_hty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_hty.setRows(5);
        inv_hty.setEnabled(false);
        jScrollPane7.setViewportView(inv_hty);

        inv_status.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        inv_status.setEnabled(false);

        inv_id.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_id.setEnabled(false);

        inv_dop.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        inv_dop.setEnabled(false);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("ID:");

        inv_idtype.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        inv_idtype.setEnabled(false);

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel30.setText("-");

        search_label.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search_label.setText("Search:");

        inv_search.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inv_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inv_searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkbox_mat)
                                .addGap(30, 30, 30)
                                .addComponent(chkbox_tae)
                                .addGap(28, 28, 28)
                                .addComponent(chkbox_faf))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(search_label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inv_search, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addGap(13, 13, 13)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel2)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel31))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane7)
                        .addComponent(inv_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inv_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inv_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inv_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inv_status, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inv_type, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(inv_idtype, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inv_id, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(inv_name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(inv_dop, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_label)
                    .addComponent(inv_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkbox_mat)
                            .addComponent(chkbox_tae)
                            .addComponent(chkbox_faf))
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 5, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inv_id, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inv_idtype, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inv_name, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inv_loc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inv_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inv_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inv_dop, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inv_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inv_status, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inv_type, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_matAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_matAddActionPerformed
        if(checkInputs_mat() == true)
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO mat_inv(name,location,quantity,unit,remarks,prefix)"
                        + "values(?,?,?,?,?,'MAT') ");
                ps.setString(1, mat_name.getText());
                ps.setString(2, mat_loc.getText());
                ps.setString(3, mat_qty.getText());
                ps.setString(4, mat_unit.getText());
                ps.setString(5, mat_rmk.getText());
                
                ps.executeUpdate();
                
                Populate_Table();
                        
                JOptionPane.showMessageDialog(null, "Data Inserted");
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }else{
            JOptionPane.showMessageDialog(null, "Missing Fields");
        }
    }//GEN-LAST:event_btn_matAddActionPerformed

    private void btn_matUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_matUpdateActionPerformed
        if(checkInputs_mat() && inv_id.getText() != null && inv_idtype.getText().equals("MAT"))
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
                try {
                    UpdateQuery = "UPDATE mat_inv SET name = ?, location = ?"
                        + ", quantity = ?, unit = ?, remarks = ?, prefix = 'MAT' WHERE id = ?";
                    
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, mat_name.getText());
                    ps.setString(2, mat_loc.getText());
                    ps.setString(3, mat_qty.getText());
                    ps.setString(4, mat_unit.getText());
                    ps.setString(5, mat_rmk.getText());
                    ps.setInt(6, Integer.parseInt(inv_id.getText()));
                    
                    ps.executeUpdate();
                    
                    Populate_Table();
                    
                    JOptionPane.showMessageDialog(null, "Data Updated");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else if(!inv_idtype.getText().equals("MAT")) {
            JOptionPane.showMessageDialog(null ,"Wrong Item Prefix");            
        } else {
            JOptionPane.showMessageDialog(null ,"Missing Fields");
        }
    }//GEN-LAST:event_btn_matUpdateActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
                ShowItem();
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void btn_taeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taeAddActionPerformed
        if(checkInputs_tae() == true)
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO tool_inv(name,location,dop,amount,history,status,remarks,prefix)"
                        + "values(?,?,?,?,?,?,?,'T&E') ");
                ps.setString(1, tae_name.getText());
                ps.setString(2, tae_loc.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(tae_dop.getDate());
                ps.setString(3, addDate);
                ps.setString(4, tae_amount.getText());
                ps.setString(5, tae_hty.getText());
                ps.setString(6, tae_stat.getText());
                ps.setString(7, tae_rmk.getText());
                
                ps.executeUpdate();
                
                Populate_Table();
                        
                JOptionPane.showMessageDialog(null, "Data Inserted");
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }else{
                    
            JOptionPane.showMessageDialog(null, "Missing Fields");
        }
    }//GEN-LAST:event_btn_taeAddActionPerformed

    private void btn_taeUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taeUpdateActionPerformed
        if(checkInputs_tae() && inv_id.getText() != null && inv_idtype.getText().equals("T&E"))
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
                try {
                    UpdateQuery = "UPDATE tool_inv SET name = ?, location = ?"
                        + ", dop = ?, amount = ?, history = ? , status = ?, remarks = ?, prefix = 'T&E' WHERE id = ?";
                    
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, tae_name.getText());
                    ps.setString(2, tae_loc.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(tae_dop.getDate());
                    ps.setString(3, addDate);
                    ps.setString(4, tae_amount.getText());
                    ps.setString(5, tae_hty.getText());
                    ps.setString(6, tae_stat.getText());
                    ps.setString(7, tae_rmk.getText());
                    ps.setInt(8, Integer.parseInt(inv_id.getText()));
                    
                    ps.executeUpdate();
                    
                    Populate_Table();
                    
                    JOptionPane.showMessageDialog(null, "Data Updated");
                    
                } catch (SQLException ex) {

                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else if(!inv_idtype.getText().equals("T&E")) {
            JOptionPane.showMessageDialog(null ,"Wrong Item Prefix");            
        } else {
            JOptionPane.showMessageDialog(null ,"Missing Fields");
        }
    }//GEN-LAST:event_btn_taeUpdateActionPerformed

    private void btn_fafAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fafAddActionPerformed
        if(checkInputs_faf() == true)
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO furn_inv(name,location,status,type,remarks,prefix)"
                        + "values(?,?,?,?,?,'F&F') ");
                ps.setString(1, faf_name.getText());
                ps.setString(2, faf_loc.getText());
                ps.setString(3, faf_stat.getText());
                ps.setString(4, faf_type.getText());
                ps.setString(5, faf_rmk.getText());
                
                ps.executeUpdate();
                
                Populate_Table();
                        
                JOptionPane.showMessageDialog(null, "Data Inserted");
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }else{
                    
            JOptionPane.showMessageDialog(null, "Missing Fields");
        }
    }//GEN-LAST:event_btn_fafAddActionPerformed

    private void btn_fafUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fafUpdateActionPerformed
        if(checkInputs_faf() && inv_id.getText() != null && inv_idtype.getText().equals("F&F"))
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
                try {
                    UpdateQuery = "UPDATE furn_inv SET name = ?, location = ?"
                        + ", status = ?, type = ?, remarks = ?, prefix='F&F' WHERE id = ?";
                    
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, faf_name.getText());
                    ps.setString(2, faf_loc.getText());
                    ps.setString(3, faf_stat.getText());
                    ps.setString(4, faf_type.getText());
                    ps.setString(5, faf_rmk.getText());
                    ps.setInt(6, Integer.parseInt(inv_id.getText()));
                    
                    ps.executeUpdate();
                    
                    Populate_Table();
                    
                    JOptionPane.showMessageDialog(null, "Data Updated");
                    
                } catch (SQLException ex) {

                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
        } else if(!inv_idtype.getText().equals("F&F")) {
            JOptionPane.showMessageDialog(null ,"Wrong Item Prefix");            
        } else {
            JOptionPane.showMessageDialog(null ,"Missing Fields");
        }
    }//GEN-LAST:event_btn_fafUpdateActionPerformed

    private void chkbox_matActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbox_matActionPerformed
            Populate_Table();
    }//GEN-LAST:event_chkbox_matActionPerformed

    private void chkbox_taeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbox_taeActionPerformed
            Populate_Table();
    }//GEN-LAST:event_chkbox_taeActionPerformed

    private void chkbox_fafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbox_fafActionPerformed
            Populate_Table();
    }//GEN-LAST:event_chkbox_fafActionPerformed

    private void btn_matDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_matDelActionPerformed
        if(!inv_id.getText().equals("") && inv_idtype.getText().equals("MAT"))
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM mat_inv WHERE id = ?");
                int id = Integer.parseInt(inv_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                
                Populate_Table();
                        
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id to Delete");
        }
    }//GEN-LAST:event_btn_matDelActionPerformed

    private void btn_fafDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fafDelActionPerformed
        if(!inv_id.getText().equals("") && inv_idtype.getText().equals("F&F"))
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM tool_inv WHERE id = ?");
                int id = Integer.parseInt(inv_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                
                Populate_Table();
                        
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id to Delete");
        }
    }//GEN-LAST:event_btn_fafDelActionPerformed

    private void btn_taeDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taeDelActionPerformed
        if(!inv_id.getText().equals("") && inv_idtype.getText().equals("T&E"))
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM tool_inv WHERE id = ?");
                int id = Integer.parseInt(inv_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                
                Populate_Table();
                        
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id to Delete");
        }
    }//GEN-LAST:event_btn_taeDelActionPerformed

    private void inv_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inv_searchKeyReleased
        DefaultTableModel dm = (DefaultTableModel)JTable_Products.getModel();
        
        String query = inv_search.getText();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dm);
        JTable_Products.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));      
    }//GEN-LAST:event_inv_searchKeyReleased

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
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton btn_fafAdd;
    private javax.swing.JButton btn_fafDel;
    private javax.swing.JButton btn_fafUpdate;
    private javax.swing.JButton btn_matAdd;
    private javax.swing.JButton btn_matDel;
    private javax.swing.JButton btn_matUpdate;
    private javax.swing.JButton btn_taeAdd;
    private javax.swing.JButton btn_taeDel;
    private javax.swing.JButton btn_taeUpdate;
    private javax.swing.JCheckBox chkbox_faf;
    private javax.swing.JCheckBox chkbox_mat;
    private javax.swing.JCheckBox chkbox_tae;
    private javax.swing.JTextField faf_loc;
    private javax.swing.JTextField faf_name;
    private javax.swing.JTextArea faf_rmk;
    private javax.swing.JTextField faf_stat;
    private javax.swing.JTextField faf_type;
    private javax.swing.JTextField inv_amount;
    private javax.swing.JTextField inv_dop;
    private javax.swing.JTextArea inv_hty;
    private javax.swing.JTextField inv_id;
    private javax.swing.JTextField inv_idtype;
    private javax.swing.JTextField inv_loc;
    private javax.swing.JTextField inv_name;
    private javax.swing.JTextField inv_qty;
    private javax.swing.JTextArea inv_remarks;
    private javax.swing.JTextField inv_search;
    private javax.swing.JTextField inv_status;
    private javax.swing.JTextField inv_type;
    private javax.swing.JTextField inv_unit;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField mat_loc;
    private javax.swing.JTextField mat_name;
    private javax.swing.JTextField mat_qty;
    private javax.swing.JTextArea mat_rmk;
    private javax.swing.JTextField mat_unit;
    private javax.swing.JLabel search_label;
    private javax.swing.JTextField tae_amount;
    private com.toedter.calendar.JDateChooser tae_dop;
    private javax.swing.JTextArea tae_hty;
    private javax.swing.JTextField tae_loc;
    private javax.swing.JTextField tae_name;
    private javax.swing.JTextArea tae_rmk;
    private javax.swing.JTextField tae_stat;
    // End of variables declaration//GEN-END:variables
}
