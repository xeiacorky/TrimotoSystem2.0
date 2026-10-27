import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MotorcycleInspection extends javax.swing.JPanel {

    public MotorcycleInspection() {
        UIManager.put("Button.focusWidth", 0);
        initComponents();
        setupEvents();
    }

    public void refreshMotorcycleDropdown() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Motorcycle m : TrimotoSystem.motorcycles) {
            model.addElement(m.id + " - " + m.brand + " " + m.model);
        }
        cmbmotorcycle.setModel(model);
    }

    private void setupEvents() {
        btnsaveinspect.addActionListener(e -> saveInspection());
        btnclear.addActionListener(e -> clearForm());
        txtinspectdate.setText(java.time.LocalDate.now().toString());
        refreshMotorcycleDropdown();
    }

    private void saveInspection() {
        if (cmbmotorcycle.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "No motorcycle selected.");
            return;
        }

        String selectedItem = cmbmotorcycle.getSelectedItem().toString();
        String motorId = selectedItem.split(" - ")[0];
        String date = txtinspectdate.getText().trim();
        String phase = cbinspectionphase.getSelectedItem().toString();
        String odometer = jTextField1.getText().trim();
        String fuel = txtfuel.getText().trim();
        String tires = jComboBox4.getSelectedItem().toString();
        String brakes = jComboBox2.getSelectedItem().toString();
        String headlight = cbheadlight.getSelectedItem().toString();
        String horns = jComboBox6.getSelectedItem().toString();
        String mirrors = jComboBox5.getSelectedItem().toString();
        String body = jComboBox3.getSelectedItem().toString();
        String engine = jComboBox1.getSelectedItem().toString();
        String condition = cbcondition.getSelectedItem().toString();
        String readiness = jComboBox7.getSelectedItem().toString();
        String notes = jTextField2.getText().trim();

        String insId = "INS-" + (TrimotoSystem.inspections.size() + 1001);
        TrimotoSystem.inspections.add(new InspectionRecord(
            insId, motorId, date, phase, odometer, fuel, tires, brakes, 
            headlight, horns, mirrors, body, engine, condition, readiness, notes
        ));

        Motorcycle m = TrimotoSystem.findMotorcycleById(motorId);
        if (m != null) {
            if ("Needs Repair".equals(condition) || "Unsafe".equals(condition)) {
                m.status = "Under Repair";
            } else if ("Minor Damage".equals(condition)) {
                m.status = "For Inspection";
            } else {
                m.status = "Available";
            }
        }

        java.awt.Window topWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (topWindow instanceof MainDashboard) {
            ((MainDashboard) topWindow).refreshAllPanels();
        }

        JOptionPane.showMessageDialog(this, "Inspection Saved Successfully!");
        clearForm();
    }

    private void clearForm() {
        txtinspectdate.setText(java.time.LocalDate.now().toString());
        txtfuel.setText("");
        jTextField1.setText("");
        jTextField2.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewinspect = new javax.swing.JDialog();
        title = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblmotor = new javax.swing.JLabel();
        cmbmotorcycle = new javax.swing.JComboBox<>();
        lblinspectiondate = new javax.swing.JLabel();
        txtinspectdate = new javax.swing.JTextField();
        lblInspectionphase = new javax.swing.JLabel();
        cbinspectionphase = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblfuel = new javax.swing.JLabel();
        txtfuel = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        lblheadlightandsignal = new javax.swing.JLabel();
        cbheadlight = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lbloverallcondi = new javax.swing.JLabel();
        cbcondition = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btnsaveinspect = new javax.swing.JButton();
        btncreatedmgreport = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout viewinspectLayout = new javax.swing.GroupLayout(viewinspect.getContentPane());
        viewinspect.getContentPane().setLayout(viewinspectLayout);
        viewinspectLayout.setHorizontalGroup(
            viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        viewinspectLayout.setVerticalGroup(
            viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        setPreferredSize(new java.awt.Dimension(900, 550));

        title.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title.setText("Motorcycle Inspection");

        description.setText("Document the motorcycles current condition and any damage");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridLayout(15, 2, 15, 15));

        lblmotor.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblmotor.setText("Motorcycle:");
        jPanel1.add(lblmotor);

        cmbmotorcycle.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cmbmotorcycle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbmotorcycle);

        lblinspectiondate.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblinspectiondate.setText("Inspection Date:");
        jPanel1.add(lblinspectiondate);

        txtinspectdate.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtinspectdate);

        lblInspectionphase.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblInspectionphase.setText("Inspection Phase:");
        jPanel1.add(lblInspectionphase);

        cbinspectionphase.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cbinspectionphase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pre-Rental", "Post-Rental" }));
        jPanel1.add(cbinspectionphase);

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel5.setText("Odometer Reading:");
        jPanel1.add(jLabel5);

        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(jTextField1);

        lblfuel.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblfuel.setText("Fuel Level:");
        jPanel1.add(lblfuel);

        txtfuel.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(txtfuel);

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel2.setText("Tire Condition:");
        jPanel1.add(jLabel2);

        jComboBox4.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Scratched", "Dented" }));
        jPanel1.add(jComboBox4);

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel3.setText("Brake Condition:");
        jPanel1.add(jLabel3);

        jComboBox2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Worn", "Damaged" }));
        jPanel1.add(jComboBox2);

        lblheadlightandsignal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblheadlightandsignal.setText("Headlight and Signal Lights:");
        jPanel1.add(lblheadlightandsignal);

        cbheadlight.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cbheadlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Functional", "Not Functional" }));
        jPanel1.add(cbheadlight);

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel6.setText("Horns");
        jPanel1.add(jLabel6);

        jComboBox6.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Functional", "Not Functional" }));
        jPanel1.add(jComboBox6);

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel8.setText("Mirrors");
        jPanel1.add(jLabel8);

        jComboBox5.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Complete", "Missing", "Damaged" }));
        jPanel1.add(jComboBox5);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel4.setText("Motorcycle Body:");
        jPanel1.add(jLabel4);

        jComboBox3.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Needs Repair" }));
        jPanel1.add(jComboBox3);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel1.setText("Engine Condition:");
        jPanel1.add(jLabel1);

        jComboBox1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Unusual Noise", "Other Issue" }));
        jPanel1.add(jComboBox1);

        lbloverallcondi.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lbloverallcondi.setText("Overall Condition:");
        jPanel1.add(lbloverallcondi);

        cbcondition.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cbcondition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Minor Damage", "Needs Repair", "Unsafe" }));
        jPanel1.add(cbcondition);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel7.setText("Readiness Status:");
        jPanel1.add(jLabel7);

        jComboBox7.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ready", "Not Ready" }));
        jPanel1.add(jComboBox7);

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel9.setText("Inspection Notes:");
        jPanel1.add(jLabel9);

        jTextField2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPanel1.add(jTextField2);

        btnsaveinspect.setText("Save Inspection");

        btncreatedmgreport.setText("Create Damage Report");
        btncreatedmgreport.addActionListener(this::btncreatedmgreportActionPerformed);

        jButton1.setText("Clear");

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
                                .addComponent(jButton1)
                                .addGap(221, 221, 221)
                                .addComponent(btncreatedmgreport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsaveinspect)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsaveinspect, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncreatedmgreport, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncreatedmgreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncreatedmgreportActionPerformed
        javax.swing.JDialog popupDialog = new javax.swing.JDialog(javax.swing.SwingUtilities.getWindowAncestor(this), "Damage Report", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        popupDialog.add(new ViewDamageReport());
        popupDialog.pack();
        popupDialog.setLocationRelativeTo(this);
        popupDialog.setVisible(true);
    }//GEN-LAST:event_btncreatedmgreportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncreatedmgreport;
    private javax.swing.JButton btnsaveinspect;
    private javax.swing.JComboBox<String> cbcondition;
    private javax.swing.JComboBox<String> cbheadlight;
    private javax.swing.JComboBox<String> cbinspectionphase;
    private javax.swing.JComboBox<String> cmbmotorcycle;
    private javax.swing.JLabel description;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblInspectionphase;
    private javax.swing.JLabel lblfuel;
    private javax.swing.JLabel lblheadlightandsignal;
    private javax.swing.JLabel lblinspectiondate;
    private javax.swing.JLabel lblmotor;
    private javax.swing.JLabel lbloverallcondi;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtfuel;
    private javax.swing.JTextField txtinspectdate;
    private javax.swing.JDialog viewinspect;
    // End of variables declaration//GEN-END:variables
}
