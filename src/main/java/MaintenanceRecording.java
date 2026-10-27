import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MaintenanceRecording extends javax.swing.JPanel {

    public MaintenanceRecording() {
        UIManager.put("Button.focusWidth", 0);
        initComponents();
        setupEvents();
    }
    
    public void refreshMotorcycleDropdown() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Motorcycle m : TrimotoSystem.motorcycles) {
            model.addElement(m.id + " - " + m.brand + " " + m.model);
        }
        jComboBox1.setModel(model);
    }

    private void setupEvents() {
        txtmaintedate.setText(java.time.LocalDate.now().toString());
        refreshMotorcycleDropdown();
        btnclear.addActionListener(e -> clearForm());
}

    private void clearForm() {
        txtmaintedate.setText(java.time.LocalDate.now().toString());
        jTextField12.setText("");
        jTextField19.setText("");
        jTextField9.setText("");
        jTextField11.setText("");
        jTextField15.setText("");
        jTextField6.setText("");
        jTextField13.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblmotor = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblmaintenancedate = new javax.swing.JLabel();
        txtmaintedate = new javax.swing.JTextField();
        lblmaintenancetype = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        btnsavemaintenance = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 550));

        title.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title.setText("Maintenance Recording");

        description.setText("Record repairs, servicing, and maintenance expenses");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridLayout(12, 2, 15, 15));

        lblmotor.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblmotor.setText("Motorcycle:");
        jPanel1.add(lblmotor);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);

        lblmaintenancedate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblmaintenancedate.setText("Maintenance Date:");
        jPanel1.add(lblmaintenancedate);

        txtmaintedate.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(txtmaintedate);

        lblmaintenancetype.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblmaintenancetype.setText("Maintenance Type:");
        jPanel1.add(lblmaintenancetype);

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preventive", "Corrective" }));
        jPanel1.add(jComboBox4);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel12.setText("Service Category");
        jPanel1.add(jLabel12);

        jTextField12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField12);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel16.setText("Work Performed:");
        jPanel1.add(jLabel16);

        jTextField19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField19);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Parts Replaced:");
        jPanel1.add(jLabel7);

        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField9);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel17.setText("Odometer Reading(km):");
        jPanel1.add(jLabel17);

        jTextField11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField11);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Maintenance Cost:");
        jPanel1.add(jLabel2);

        jTextField15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField15);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Next Service Mileage:");
        jPanel1.add(jLabel3);

        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField6);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel13.setText("Maintenance Status:");
        jPanel1.add(jLabel13);

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "In Progress", "Completed" }));
        jPanel1.add(jComboBox2);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel21.setText("Motorcycle Availability:");
        jPanel1.add(jLabel21);

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Under Maintenance" }));
        jPanel1.add(jComboBox3);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel15.setText("Remarks");
        jPanel1.add(jLabel15);

        jTextField13.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField13);

        btnsavemaintenance.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnsavemaintenance.setText("Save Maintenance Record");
        btnsavemaintenance.addActionListener(this::btnsavemaintenanceActionPerformed);

        btnclear.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnclear.setText("Clear");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title)
                            .addComponent(description)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnsavemaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsavemaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsavemaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsavemaintenanceActionPerformed
      if (jComboBox1.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Please select a motorcycle.");
        return;
    }

    String costText = jTextField15.getText().trim();
    if (costText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter the maintenance cost.");
        return;
    }

    try {
        String motorId = jComboBox1.getSelectedItem().toString().split(" - ")[0];
        String date = txtmaintedate.getText().trim();
        String type = jComboBox4.getSelectedItem().toString();
        String category = jTextField12.getText().trim();
        String workPerformed = jTextField19.getText().trim();
        String partsReplaced = jTextField9.getText().trim();
        String odometer = jTextField11.getText().trim();
        double cost = Double.parseDouble(costText);
        String nextServiceMileage = jTextField6.getText().trim();
        String status = jComboBox2.getSelectedItem().toString();
        String availability = jComboBox3.getSelectedItem().toString();
        String remarks = jTextField13.getText().trim();

        String mntId = "MNT-" + (TrimotoSystem.maintenanceRecords.size() + 1001);
        TrimotoSystem.maintenanceRecords.add(new MaintenanceRecord(
        mntId, motorId, date, type, category, workPerformed, partsReplaced, 
        odometer, cost, nextServiceMileage, status, availability, remarks
    ));
       Motorcycle m = TrimotoSystem.findMotorcycleById(motorId);
    if (m != null) {
        m.status = availability;
    }

    java.awt.Window topWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
    if (topWindow instanceof MainDashboard) {
        ((MainDashboard) topWindow).refreshAllPanels();
    }

    JOptionPane.showMessageDialog(this, "Maintenance Record Saved!");
    clearForm();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Maintenance cost must be a valid number (e.g., 1500 or 1500.50).");
    }

    }//GEN-LAST:event_btnsavemaintenanceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnsavemaintenance;
    private javax.swing.JLabel description;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblmaintenancedate;
    private javax.swing.JLabel lblmaintenancetype;
    private javax.swing.JLabel lblmotor;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtmaintedate;
    // End of variables declaration//GEN-END:variables
}
