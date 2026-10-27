import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class RentalProcessing extends javax.swing.JPanel {

    public RentalProcessing() {
        UIManager.put("Button.focusWidth", 0);
        initComponents();
        setupEvents();
    }

    public void refreshMotorcycleDropdown() {
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    for (Motorcycle m : TrimotoSystem.motorcycles) {
        if ("Available".equalsIgnoreCase(m.status)) {
            model.addElement(m.id + " - " + m.brand + " " + m.model + " (₱" + m.dailyRate + "/day)");
        }
    }
    motor.setModel(model);
}

    private void setupEvents() {
        btnprocessrental.addActionListener(e -> processRental());
        btnclear.addActionListener(e -> clearForm());
        cbpartial.addActionListener(e -> { if(cbpartial.isSelected()) cbfull.setSelected(false); });
        cbfull.addActionListener(e -> { if(cbfull.isSelected()) cbpartial.setSelected(false); });
        refreshMotorcycleDropdown();
    }

    private void processRental() {
    if (motor.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "No available motorcycle selected.");
        return;
    }

    try {
        String motorId = motor.getSelectedItem().toString().split(" - ")[0];
        String renter = txtname.getText().trim();
        String age = txtage.getText().trim();
        String address = txtaddress.getText().trim();
        String contactNumber = txtcontnumber.getText().trim();
        int days = Integer.parseInt(lbldura.getText().trim());
        String licenseNumber = txtdriverslicense.getText().trim();
        double payment = Double.parseDouble(txtamount.getText().trim());
        String returnDate = txtreturn.getText().trim();

        String paymentType = cbpartial.isSelected() ? "Partial" : (cbfull.isSelected() ? "Full" : "Standard");
        
        Motorcycle m = TrimotoSystem.findMotorcycleById(motorId);
        if (m == null) return;

        double totalCost = days * m.dailyRate;
        String transId = "TRX-" + (TrimotoSystem.rentals.size() + 1001);
        String date = java.time.LocalDate.now().toString();

        if (returnDate.isEmpty()) {
            returnDate = java.time.LocalDate.now().plusDays(days).toString();
        }

        TrimotoSystem.rentals.add(new RentalTransaction(
            transId, motorId, renter, address, contactNumber, 
            licenseNumber, days, totalCost, payment, paymentType, 
            date, returnDate, "Active"
        ));

        m.status = "Rented";

        java.awt.Window topWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (topWindow instanceof MainDashboard) {
            ((MainDashboard) topWindow).refreshAllPanels();
        }

        JOptionPane.showMessageDialog(this, "Rental Processed Successfully!\nTransaction ID: " + transId + "\nTotal Cost: ₱" + totalCost);
        clearForm();
        refreshMotorcycleDropdown();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid entry for Duration or Payment Amount.");
    }
}

    private void clearForm() {
        txtname.setText("");
        txtage.setText("");
        txtaddress.setText("");
        txtcontnumber.setText("");
        lbldura.setText("");
        txtdriverslicense.setText("");
        txtamount.setText("");
        txtreturn.setText("");
        cbpartial.setSelected(false);
        cbfull.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblmotor = new javax.swing.JLabel();
        motor = new javax.swing.JComboBox<>();
        lblrenter = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        lblage = new javax.swing.JLabel();
        txtage = new javax.swing.JTextField();
        lbladdress = new javax.swing.JLabel();
        txtaddress = new javax.swing.JTextField();
        lblnumber = new javax.swing.JLabel();
        txtcontnumber = new javax.swing.JTextField();
        lblduration = new javax.swing.JLabel();
        lbldura = new javax.swing.JTextField();
        driverlicense = new javax.swing.JLabel();
        txtdriverslicense = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblamount = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cbpartial = new javax.swing.JCheckBox();
        cbfull = new javax.swing.JCheckBox();
        btnprocessrental = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(900, 550));

        title.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title.setText("Rental Processing");

        description.setText("Record repairs, servicing, and maintenance expenses");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridLayout(10, 2, 15, 15));

        lblmotor.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblmotor.setText("Motorcycle:");
        jPanel1.add(lblmotor);

        motor.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        motor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(motor);

        lblrenter.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblrenter.setText("Renter Name:");
        jPanel1.add(lblrenter);

        txtname.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtname);

        lblage.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblage.setText("Age:");
        jPanel1.add(lblage);

        txtage.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtage);

        lbladdress.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lbladdress.setText("Address:");
        jPanel1.add(lbladdress);

        txtaddress.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtaddress.addActionListener(this::txtaddressActionPerformed);
        jPanel1.add(txtaddress);

        lblnumber.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblnumber.setText("Contact Number:");
        jPanel1.add(lblnumber);

        txtcontnumber.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtcontnumber);

        lblduration.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblduration.setText("Rental Duration (Days):");
        jPanel1.add(lblduration);

        lbldura.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(lbldura);

        driverlicense.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        driverlicense.setText("Driver's License #:");
        jPanel1.add(driverlicense);

        txtdriverslicense.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtdriverslicense);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel1.setText("Payment Type:");
        jPanel1.add(jLabel1);

        jComboBox1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Bank Transfer", "Card", "E-Wallet" }));
        jPanel1.add(jComboBox1);

        lblamount.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblamount.setText("Enter Amount:");
        jPanel1.add(lblamount);

        txtamount.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtamount);

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(jLabel6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        cbpartial.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cbpartial.setText("Partial Payment");
        jPanel3.add(cbpartial);

        cbfull.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cbfull.setText("Full Payment");
        jPanel3.add(cbfull);

        jPanel1.add(jPanel3);

        btnprocessrental.setText("Process Rental");
        btnprocessrental.addActionListener(this::btnprocessrentalActionPerformed);

        jButton1.setText("Calculate Cost");

        jButton2.setText("Clear");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description)
                            .addComponent(title)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(227, 227, 227)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(btnprocessrental, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnprocessrental)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnprocessrentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprocessrentalActionPerformed
    }//GEN-LAST:event_btnprocessrentalActionPerformed

    private void txtaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddressActionPerformed
    }//GEN-LAST:event_txtaddressActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnprocessrental;
    private javax.swing.JCheckBox cbfull;
    private javax.swing.JCheckBox cbpartial;
    private javax.swing.JLabel description;
    private javax.swing.JLabel driverlicense;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbladdress;
    private javax.swing.JLabel lblage;
    private javax.swing.JLabel lblamount;
    private javax.swing.JTextField lbldura;
    private javax.swing.JLabel lblduration;
    private javax.swing.JLabel lblmotor;
    private javax.swing.JLabel lblnumber;
    private javax.swing.JLabel lblrenter;
    private javax.swing.JComboBox<String> motor;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtage;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtcontnumber;
    private javax.swing.JTextField txtdriverslicense;
    private javax.swing.JTextField txtname;
    // End of variables declaration//GEN-END:variables
}
