import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class ManageMotorcycles extends javax.swing.JPanel {

    public ManageMotorcycles() {
       UIManager.put("Button.focusWidth", 0);
        initComponents();
        setupEvents();
        
        cmbtypes.addItemListener(new java.awt.event.ItemListener() {
    public void itemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String selectedType = cmbtypes.getSelectedItem().toString();
            updateFieldsBasedOnType(selectedType);
        }
    }
});
    }
    private void setupEvents() {
        btnaddmotorcycle.addActionListener(e -> addMotorcycle());
        btnclear.addActionListener(e -> clearForm());
    }

    private void addMotorcycle() {
    String id = txtmotorid.getText().trim();
    String brand = txtbrand.getText().trim();
    String model = txtmodel.getText().trim();
    String yearStr = txtyear.getText().trim();
    String mileageStr = txtmileage.getText().trim();
    String rateStr = txtdailyrentalrate.getText().trim();

    if (id.isEmpty() || brand.isEmpty() || model.isEmpty() || yearStr.isEmpty() || mileageStr.isEmpty() || rateStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all primary fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (TrimotoSystem.findMotorcycleById(id) != null) {
        JOptionPane.showMessageDialog(this, "Motorcycle ID '" + id + "' already exists!", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        int year = Integer.parseInt(yearStr);
        int mileage = Integer.parseInt(mileageStr);
        double rate = Double.parseDouble(rateStr);
        String type = cmbtypes.getSelectedItem() != null ? cmbtypes.getSelectedItem().toString() : "Scooter";

        TrimotoSystem.motorcycles.add(new Motorcycle(id, brand, model, year, type, mileage, rate));

        java.awt.Window topWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (topWindow instanceof MainDashboard) {
            MainDashboard dashboard = (MainDashboard) topWindow;
            dashboard.refreshAllPanels();
        }

        JOptionPane.showMessageDialog(this, "Motorcycle Registered Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearForm();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Year, Mileage, and Daily Rate.", "Invalid Format", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearForm() {
        txtmotorid.setText("");
        txtbrand.setText("");
        txtmodel.setText("");
        txtyear.setText("");
        txtmileage.setText("");
        txtdailyrentalrate.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
    }
    @SuppressWarnings("unchecked")
    
    private void updateFieldsBasedOnType(String selectedType) {
    switch (selectedType) {
        case "Scooter":
            jLabel4.setText("Engine Displacement:");
            jLabel5.setText("Compartment Capacity:");
            jLabel6.setText("Fuel Capacity:");
            jLabel7.setText(" ");
            break;
            
        case "Standard Bike":
            jLabel4.setText("Engine Displacement:");
            jLabel5.setText("Fuel Capacity:");
            jLabel6.setText(" ");
            jLabel7.setText(" ");
            break;
            
        case "Electric Bike":
            jLabel4.setText("Motor Power:");
            jLabel5.setText("Battery Capacity:");
            jLabel6.setText("Charging Time:");
            jLabel7.setText(" ");
            break;
            
        case "Sports Bike":
            jLabel4.setText("Engine Displacement:");
            jLabel5.setText("Fuel Capacity:");
            jLabel6.setText(" ");
            jLabel7.setText(" ");
            
            break;
            
        case "Adventure Bike":
            jLabel4.setText("Engine Displacement:");
            jLabel5.setText("Compartment Capacity:");
            jLabel6.setText("Fuel Capacity:");
            jLabel7.setText("Ground Clearance:");
            break;
            
        default:
            jLabel4.setText(" ");
            jLabel5.setText(" ");
            jLabel6.setText(" ");
            jLabel7.setText(" ");
            break;
    }
}
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblMotorID = new javax.swing.JLabel();
        txtmotorid = new javax.swing.JTextField();
        brand = new javax.swing.JLabel();
        txtbrand = new javax.swing.JTextField();
        model = new javax.swing.JLabel();
        txtmodel = new javax.swing.JTextField();
        lblyear = new javax.swing.JLabel();
        txtyear = new javax.swing.JTextField();
        lblmileage = new javax.swing.JLabel();
        txtmileage = new javax.swing.JTextField();
        lbldailyrentalrate = new javax.swing.JLabel();
        txtdailyrentalrate = new javax.swing.JTextField();
        lblmotortype = new javax.swing.JLabel();
        cmbtypes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        btnclear = new javax.swing.JButton();
        btnaddmotorcycle = new javax.swing.JButton();

        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        setPreferredSize(new java.awt.Dimension(970, 551));
        setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setText("Manage Motorcycles");

        jLabel2.setText("Register a motorcycle in the system.");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridLayout(13, 2, 15, 15));

        lblMotorID.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblMotorID.setText("Motorcycle ID:");
        jPanel1.add(lblMotorID);

        txtmotorid.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtmotorid.addActionListener(this::txtmotoridActionPerformed);
        jPanel1.add(txtmotorid);

        brand.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        brand.setText("Brand:");
        jPanel1.add(brand);

        txtbrand.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(txtbrand);

        model.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        model.setText("Model:");
        jPanel1.add(model);

        txtmodel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(txtmodel);

        lblyear.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblyear.setText("Year:");
        jPanel1.add(lblyear);

        txtyear.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(txtyear);

        lblmileage.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblmileage.setText("Mileage (km):");
        jPanel1.add(lblmileage);

        txtmileage.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(txtmileage);

        lbldailyrentalrate.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lbldailyrentalrate.setText("Daily Rental Rate:");
        jPanel1.add(lbldailyrentalrate);

        txtdailyrentalrate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(txtdailyrentalrate);

        lblmotortype.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblmotortype.setText("Motorcycle Type");
        jPanel1.add(lblmotortype);

        cmbtypes.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cmbtypes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scooter", "Standard Bike", "Sports Bike", "Adventure Bike", "Electric Bike", " " }));
        cmbtypes.addActionListener(this::cmbtypesActionPerformed);
        jPanel1.add(cmbtypes);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Transmission Type:");
        jPanel1.add(jLabel3);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField1);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jLabel4);

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField2);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jLabel5);

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField3);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jLabel6);

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField4);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jLabel7);

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField5);

        btnclear.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnclear.setText("Clear");
        jPanel1.add(btnclear);

        btnaddmotorcycle.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnaddmotorcycle.setText("Add Motorcycle");
        jPanel1.add(btnaddmotorcycle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(791, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtmotoridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmotoridActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmotoridActionPerformed

    private void cmbtypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtypesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbtypesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brand;
    private javax.swing.JButton btnaddmotorcycle;
    private javax.swing.JButton btnclear;
    private javax.swing.JComboBox<String> cmbtypes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel lblMotorID;
    private javax.swing.JLabel lbldailyrentalrate;
    private javax.swing.JLabel lblmileage;
    private javax.swing.JLabel lblmotortype;
    private javax.swing.JLabel lblyear;
    private javax.swing.JLabel model;
    private javax.swing.JTextField txtbrand;
    private javax.swing.JTextField txtdailyrentalrate;
    private javax.swing.JTextField txtmileage;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtmotorid;
    private javax.swing.JTextField txtyear;
    // End of variables declaration//GEN-END:variables
}
