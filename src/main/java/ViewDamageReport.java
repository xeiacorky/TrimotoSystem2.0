import javax.swing.JOptionPane;

public class ViewDamageReport extends javax.swing.JPanel {

    public ViewDamageReport() {
        initComponents();
        setupEvents();
    }
private void setupEvents() {
        btndamagereport.addActionListener(e -> saveDamageReport());
        btnclear.addActionListener(e -> clearForm());
    }

    private void saveDamageReport() {
    String motorId = jComboBox1.getSelectedItem().toString();      
    String location = jTextField7.getText().trim();     
    String type = jComboBox5.getSelectedItem().toString(); 
    String desc = jTextField5.getText().trim();         
    String date = jTextField4.getText().trim();         
    String action = jComboBox6.getSelectedItem().toString();
    String status = jComboBox7.getSelectedItem().toString();
    String notes = jTextField2.getText().trim();        
    String cost = jTextField1.getText().trim();         

    if (motorId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Motorcycle ID required.", "Input Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String dmgId = "DMG-" + (TrimotoSystem.damageReports.size() + 1001);
    String formattedRecord = "Report ID: " + dmgId + "\n" +
                            "Motorcycle: " + motorId + "\n" +
                            "Date: " + (date.isEmpty() ? java.time.LocalDate.now().toString() : date) + "\n" +
                            "Location: " + (location.isEmpty() ? "-" : location) + "\n" +
                            "Type: " + type + "\n" +
                            "Description: " + (desc.isEmpty() ? "-" : desc) + "\n" +
                            "Action Required: " + action + "\n" +
                            "Status: " + status + "\n" +
                             "Notes: " + (notes.isEmpty() ? "-" : notes) + "\n" +
                            "Cost: " + (cost.isEmpty() ? "0" : cost);

    TrimotoSystem.damageReports.add(formattedRecord);
    JOptionPane.showMessageDialog(this, "Damage Report Saved Successfully!");
    clearForm();
}

    private void clearForm() {
        jTextField7.setText("");
        jTextField5.setText("");
        jTextField4.setText("");
        jTextField2.setText("");
        jTextField1.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        btnclear1 = new javax.swing.JButton();
        btnsavemaintenance1 = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnclear = new javax.swing.JButton();
        btndamagereport = new javax.swing.JButton();

        title1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title1.setText("Damage Report");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.setLayout(new java.awt.GridLayout(10, 2, 15, 15));

        jLabel11.setText("Motorcycle:");
        jPanel3.add(jLabel11);
        jPanel3.add(jTextField9);

        jLabel19.setText("Damage Location: ");
        jPanel3.add(jLabel19);
        jPanel3.add(jTextField10);

        jLabel9.setText("Damage Type:");
        jPanel3.add(jLabel9);

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scratch", "Dent", "Crack", "Broken Part" }));
        jPanel3.add(jComboBox8);

        jLabel21.setText("Damage Description:");
        jPanel3.add(jLabel21);
        jPanel3.add(jTextField6);

        jLabel7.setText("Date Reported:");
        jPanel3.add(jLabel7);
        jPanel3.add(jTextField11);

        jLabel12.setText("Action Required:");
        jPanel3.add(jLabel12);

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Repair", "Monitor", "No Action" }));
        jPanel3.add(jComboBox9);

        jLabel13.setText("Damage Status:");
        jPanel3.add(jLabel13);

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Under Repair", "Resolved" }));
        jPanel3.add(jComboBox10);

        jLabel3.setText("Additional Notes:");
        jPanel3.add(jLabel3);
        jPanel3.add(jTextField3);

        jLabel14.setText("Estimated Repair Cost:");
        jPanel3.add(jLabel14);
        jPanel3.add(jTextField12);

        btnclear1.setText("Clear");
        jPanel3.add(btnclear1);

        btnsavemaintenance1.setText("Save Maintenance Record");
        btnsavemaintenance1.addActionListener(this::btnsavemaintenance1ActionPerformed);
        jPanel3.add(btnsavemaintenance1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        title.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title.setText("Damage Report");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridLayout(10, 2, 15, 15));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setText("Motorcycle:");
        jPanel1.add(jLabel10);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);
        jPanel1.add(jComboBox1);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel18.setText("Damage Location: ");
        jPanel1.add(jLabel18);

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField7);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Damage Type:");
        jPanel1.add(jLabel8);

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scratch", "Dent", "Crack", "Broken Part" }));
        jPanel1.add(jComboBox5);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel20.setText("Damage Description:");
        jPanel1.add(jLabel20);

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField5);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Date Reported:");
        jPanel1.add(jLabel6);

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField4);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Action Required:");
        jPanel1.add(jLabel5);

        jComboBox6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Repair", "Monitor", "No Action" }));
        jPanel1.add(jComboBox6);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Damage Status:");
        jPanel1.add(jLabel4);

        jComboBox7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Under Repair", "Resolved" }));
        jPanel1.add(jComboBox7);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Additional Notes:");
        jPanel1.add(jLabel1);

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField2);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Estimated Repair Cost:");
        jPanel1.add(jLabel2);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jPanel1.add(jTextField1);

        btnclear.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnclear.setText("Clear");
        jPanel1.add(btnclear);

        btndamagereport.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btndamagereport.setText("Save Damage Report");
        btndamagereport.addActionListener(this::btndamagereportActionPerformed);
        jPanel1.add(btndamagereport);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsavemaintenance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsavemaintenance1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsavemaintenance1ActionPerformed

    private void btndamagereportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndamagereportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btndamagereportActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnclear1;
    private javax.swing.JButton btndamagereport;
    private javax.swing.JButton btnsavemaintenance1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    // End of variables declaration//GEN-END:variables
}
