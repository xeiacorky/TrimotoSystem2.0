import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class ViewMotorcycleRecords extends javax.swing.JPanel {

    private javax.swing.JTable jTableMaintenanceView;
    private javax.swing.JTable jTableInspectionView;
    private javax.swing.JTable jTableDamageView;
    
    public ViewMotorcycleRecords() {
        UIManager.put("Button.focusWidth", 0);
        initComponents();
        setupEvents();
        loadTableData();
    }

    private void notifyDashboardRefresh() {
        java.awt.Window topWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (topWindow instanceof MainDashboard) {
            ((MainDashboard) topWindow).refreshAllPanels();
        }
    }

   private void setupEvents() {
    btnrefresh.addActionListener(e -> loadTableData());
    btnsearch.addActionListener(e -> searchRecord());
    btndelete.addActionListener(e -> deleteSelectedMotorcycle());
    btnedit.addActionListener(e -> editSelectedMotorcycle());
    
    btnviewinspectsave.addActionListener(e -> btnviewinspectsaveActionPerformed(e));
    btnsavemainte.addActionListener(e -> btnsavemainteActionPerformed(e));
}

    public void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) recordstable.getModel();
        model.setRowCount(0);

        for (Motorcycle m : TrimotoSystem.motorcycles) {
            model.addRow(new Object[]{
                m.id, m.brand, m.model, m.year, m.type, m.mileage, m.dailyRate, m.status
            });
        }
    }

    private void searchRecord() {
        String searchId = jTextField3.getText().trim();
        if (searchId.isEmpty()) {
            loadTableData();
            return;
        }

        DefaultTableModel model = (DefaultTableModel) recordstable.getModel();
        model.setRowCount(0);

        for (Motorcycle m : TrimotoSystem.motorcycles) {
            if (m.id.equalsIgnoreCase(searchId)) {
                model.addRow(new Object[]{
                    m.id, m.brand, m.model, m.year, m.type, m.mileage, m.dailyRate, m.status
                });
            }
        }
    }

    private void deleteSelectedMotorcycle() {
        int selectedRow = recordstable.getSelectedRow();
        if (selectedRow != -1) {
            String id = recordstable.getValueAt(selectedRow, 0).toString();
            TrimotoSystem.motorcycles.removeIf(m -> m.id.equals(id));
            
            loadTableData();
            notifyDashboardRefresh();
            
            JOptionPane.showMessageDialog(this, "Motorcycle deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a motorcycle row to delete.");
        }
    }

    private void editSelectedMotorcycle() {
        int selectedRow = recordstable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a motorcycle from the table to edit.");
            return;
        }

        String id = recordstable.getValueAt(selectedRow, 0).toString();
        Motorcycle m = TrimotoSystem.findMotorcycleById(id);

        if (m != null) {
            String newBrand = JOptionPane.showInputDialog(this, "Edit Brand:", m.brand);
            if (newBrand == null) return;

            String newModel = JOptionPane.showInputDialog(this, "Edit Model:", m.model);
            if (newModel == null) return;

            String yearStr = JOptionPane.showInputDialog(this, "Edit Year:", m.year);
            if (yearStr == null) return;

            String mileageStr = JOptionPane.showInputDialog(this, "Edit Mileage (km):", m.mileage);
            if (mileageStr == null) return;

            String rateStr = JOptionPane.showInputDialog(this, "Edit Daily Rate (₱):", m.dailyRate);
            if (rateStr == null) return;

            String newStatus = JOptionPane.showInputDialog(this, "Edit Status (Available/Rented/Under Maintenance/For Inspection/Under Repair):", m.status);
            if (newStatus == null) return;

            try {
                int year = Integer.parseInt(yearStr.trim());
                int mileage = Integer.parseInt(mileageStr.trim());
                double rate = Double.parseDouble(rateStr.trim());

                saveEditedMotorcycle(m.id, newBrand.trim(), newModel.trim(), year, m.type, mileage, rate, newStatus.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for Year, Mileage, or Daily Rate.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveEditedMotorcycle(String id, String brand, String model, int year, String type, int mileage, double rate, String status) {
        Motorcycle m = TrimotoSystem.findMotorcycleById(id);
        if (m != null) {
            m.brand = brand;
            m.model = model;
            m.year = year;
            m.type = type;
            m.mileage = mileage;
            m.dailyRate = rate;
            m.status = status;

            loadTableData();
            notifyDashboardRefresh();

            JOptionPane.showMessageDialog(this, "Motorcycle updated successfully!");
        }
    }

    private String extractField(String text, String key) {
        if (text == null) return "-";
        for (String line : text.split("\n")) {
            if (line.toLowerCase().contains(key.toLowerCase() + ":")) {
                return line.substring(line.indexOf(":") + 1).trim();
            }
        }
        return "-";
    }

    private void populateInspectionGui(InspectionRecord record) {
        if (record == null) return;

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Motorcycle m : TrimotoSystem.motorcycles) {
            model.addElement(m.id + " - " + m.brand + " " + m.model);
        }
        cmbmotorcycle.setModel(model);

        for (int i = 0; i < cmbmotorcycle.getItemCount(); i++) {
            if (cmbmotorcycle.getItemAt(i).toString().startsWith(record.motorcycleId)) {
                cmbmotorcycle.setSelectedIndex(i);
                break;
            }
        }

        txtinspectdate.setText(record.date);
        cbinspectionphase.setSelectedItem(record.phase);
        jTextField1.setText(record.odometer);
        txtfuel.setText(record.fuel);
        jComboBox8.setSelectedItem(record.tires);
        jComboBox9.setSelectedItem(record.brakes);
        cbheadlight.setSelectedItem(record.headlight);
        jComboBox10.setSelectedItem(record.horns);
        jComboBox11.setSelectedItem(record.mirrors);
        jComboBox12.setSelectedItem(record.body);
        jComboBox13.setSelectedItem(record.engine);
        cbcondition.setSelectedItem(record.condition);
        jComboBox14.setSelectedItem(record.readiness);
        jTextField2.setText(record.notes);
    }

    private void populateMaintenanceGui(MaintenanceRecord record) {
        if (record == null) return;

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Motorcycle m : TrimotoSystem.motorcycles) {
            model.addElement(m.id + " - " + m.brand + " " + m.model);
        }
        jComboBox1.setModel(model);

        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (jComboBox1.getItemAt(i).toString().startsWith(record.motorcycleId)) {
                jComboBox1.setSelectedIndex(i);
                break;
            }
        }

        txtmaintedate.setText(record.date);
        jComboBox4.setSelectedItem(record.type);
        jTextField12.setText(record.category);
        jTextField19.setText(record.workPerformed);
        jTextField11.setText(record.partsReplaced);
        jTextField13.setText(record.odometer);
        jTextField15.setText(String.valueOf(record.cost));
        jTextField14.setText(record.nextServiceMileage);
        jComboBox2.setSelectedItem(record.status);
        jComboBox3.setSelectedItem(record.availability);
        jTextField16.setText(record.remarks);
    }

    private void populateDamageGui(String record) {
        if (record == null) return;

        jTextField8.setText(extractField(record, "Motorcycle"));
        jTextField7.setText(extractField(record, "Location"));
        jComboBox5.setSelectedItem(extractField(record, "Type"));
        jTextField5.setText(extractField(record, "Description"));
        jTextField6.setText(extractField(record, "Date"));
        jComboBox6.setSelectedItem(extractField(record, "Action Required"));
        jComboBox7.setSelectedItem(extractField(record, "Status"));
        jTextField9.setText(extractField(record, "Notes"));
        jTextField10.setText(extractField(record, "Cost"));
    }

    private void packTableColumns(javax.swing.JTable table) {
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 85;
            
            Object headerValue = table.getColumnModel().getColumn(column).getHeaderValue();
            if (headerValue != null) {
                width = Math.max(width, table.getFontMetrics(table.getFont()).stringWidth(headerValue.toString()) + 30);
            }

            for (int row = 0; row < table.getRowCount(); row++) {
                Object cellValue = table.getValueAt(row, column);
                if (cellValue != null) {
                    int cellWidth = table.getFontMetrics(table.getFont()).stringWidth(cellValue.toString()) + 30;
                    width = Math.max(width, cellWidth);
                }
            }
            table.getColumnModel().getColumn(column).setPreferredWidth(width);
        }
    }

    

       
    private String getRecordField(Object obj, String fieldName) {
        if (obj == null) return "-";
        try {
            java.lang.reflect.Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object v = f.get(obj);
            return v != null ? v.toString() : "-";
        } catch (Exception e) {
            return "-";
        }
    }

    private void deleteSelectedInspection(String selectedMotorId) {
        int selectedRow = jTableInspectionView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(screen1viewinspect, "Please select an inspection row to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(screen1viewinspect, "Are you sure you want to delete this inspection record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String selectedRecId = jTableInspectionView.getValueAt(selectedRow, 0).toString();
        TrimotoSystem.inspections.removeIf(r -> selectedRecId.equalsIgnoreCase(getRecordField(r, "recordId")));

        JOptionPane.showMessageDialog(screen1viewinspect, "Inspection record deleted successfully.");
        btnviewinspectionrecordActionPerformed(null);
    }

    private void deleteSelectedMaintenance(String selectedMotorId) {
        int selectedRow = jTableMaintenanceView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(screen1viewmainte, "Please select a maintenance row to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(screen1viewmainte, "Are you sure you want to delete this maintenance record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String selectedRecId = jTableMaintenanceView.getValueAt(selectedRow, 0).toString();
        TrimotoSystem.maintenanceRecords.removeIf(r -> selectedRecId.equalsIgnoreCase(getRecordField(r, "recordId")));

        JOptionPane.showMessageDialog(screen1viewmainte, "Maintenance record deleted successfully.");
        btnviewmaintenancerecordActionPerformed(null);
    }

    private void deleteSelectedDamage(String selectedMotorId) {
        int selectedRow = jTableDamageView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(screen1viewdmgreport, "Please select a damage report row to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(screen1viewdmgreport, "Are you sure you want to delete this damage report?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String selectedReportId = jTableDamageView.getValueAt(selectedRow, 0).toString();
        TrimotoSystem.damageReports.removeIf(r -> r.contains("Report ID: " + selectedReportId));

        JOptionPane.showMessageDialog(screen1viewdmgreport, "Damage report deleted successfully.");
        btnviewdmgActionPerformed(null);
    }

    private void btnsavemainteActionPerformed(java.awt.event.ActionEvent evt) {
        int tableRow = (jTableMaintenanceView != null) ? jTableMaintenanceView.getSelectedRow() : -1;
        if (tableRow == -1) return;

        String targetRecId = jTableMaintenanceView.getValueAt(tableRow, 0).toString();
        for (MaintenanceRecord record : TrimotoSystem.maintenanceRecords) {
            if (targetRecId.equalsIgnoreCase(getRecordField(record, "recordId"))) {
                record.date = txtmaintedate.getText().trim();
                record.type = jComboBox4.getSelectedItem().toString();
                record.category = jTextField12.getText().trim();
                record.workPerformed = jTextField19.getText().trim();
                record.partsReplaced = jTextField11.getText().trim();
                record.odometer = jTextField13.getText().trim();
                try {
                    record.cost = Double.parseDouble(jTextField15.getText().trim());
                } catch (NumberFormatException ignored) {}
                record.nextServiceMileage = jTextField14.getText().trim();
                record.status = jComboBox2.getSelectedItem().toString();
                record.availability = jComboBox3.getSelectedItem().toString();
                record.remarks = jTextField16.getText().trim();
                break;
            }
        }

        JOptionPane.showMessageDialog(viewmainte, "Maintenance record updated!");
        viewmainte.dispose();
        btnviewmaintenancerecordActionPerformed(evt);
    }
    
    private void btnviewinspectsaveActionPerformed(java.awt.event.ActionEvent evt) {
        int tableRow = (jTableInspectionView != null) ? jTableInspectionView.getSelectedRow() : -1;
        if (tableRow == -1) return;

        String targetRecId = jTableInspectionView.getValueAt(tableRow, 0).toString();
        for (InspectionRecord record : TrimotoSystem.inspections) {
            if (targetRecId.equalsIgnoreCase(getRecordField(record, "recordId"))) {
                record.date = txtinspectdate.getText().trim();
                record.phase = cbinspectionphase.getSelectedItem().toString();
                record.odometer = jTextField1.getText().trim();
                record.fuel = txtfuel.getText().trim();
                record.tires = jComboBox8.getSelectedItem().toString();
                record.brakes = jComboBox9.getSelectedItem().toString();
                record.headlight = cbheadlight.getSelectedItem().toString();
                record.horns = jComboBox10.getSelectedItem().toString();
                record.mirrors = jComboBox11.getSelectedItem().toString();
                record.body = jComboBox12.getSelectedItem().toString();
                record.engine = jComboBox13.getSelectedItem().toString();
                record.condition = cbcondition.getSelectedItem().toString();
                record.readiness = jComboBox14.getSelectedItem().toString();
                record.notes = jTextField2.getText().trim();
                break;
            }
        }

        JOptionPane.showMessageDialog(viewinspect, "Inspection record updated!");
        viewinspect.dispose();
        btnviewinspectionrecordActionPerformed(evt);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewrecords = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnsave = new javax.swing.JButton();
        viewmainte = new javax.swing.JDialog();
        btnsavemainte = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblmotor2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblmaintenancedate = new javax.swing.JLabel();
        txtmaintedate = new javax.swing.JTextField();
        lblmaintenancetype = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        viewinspect = new javax.swing.JDialog();
        btnviewinspectsave = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblmotor = new javax.swing.JLabel();
        cmbmotorcycle = new javax.swing.JComboBox<>();
        lblinspectiondate = new javax.swing.JLabel();
        txtinspectdate = new javax.swing.JTextField();
        lblInspectionphase = new javax.swing.JLabel();
        cbinspectionphase = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblfuel = new javax.swing.JLabel();
        txtfuel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        lblheadlightandsignal = new javax.swing.JLabel();
        cbheadlight = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        lbloverallcondi = new javax.swing.JLabel();
        cbcondition = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        screen1viewrecords = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        btneditrecords = new javax.swing.JButton();
        screen1viewmainte = new javax.swing.JDialog();
        btneditmainte = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        screen1viewinspect = new javax.swing.JDialog();
        btneditinspect = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        screen1viewdmgreport = new javax.swing.JDialog();
        btneditdmgreport = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        viewdmg = new javax.swing.JDialog();
        title1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        btnsavedmgreport = new javax.swing.JButton();
        description = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        recordsscrollpane = new javax.swing.JScrollPane();
        recordstable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnrefresh = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnviewinspectionrecord = new javax.swing.JButton();
        btnviewmaintenancerecord = new javax.swing.JButton();
        btnviewdmg = new javax.swing.JButton();
        ID = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btnsave.setText("Save");
        btnsave.addActionListener(this::btnsaveActionPerformed);

        javax.swing.GroupLayout viewrecordsLayout = new javax.swing.GroupLayout(viewrecords.getContentPane());
        viewrecords.getContentPane().setLayout(viewrecordsLayout);
        viewrecordsLayout.setHorizontalGroup(
            viewrecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewrecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(viewrecordsLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(btnsave)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        viewrecordsLayout.setVerticalGroup(
            viewrecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewrecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsave)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnsavemainte.setText("Save");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel1.setLayout(new java.awt.GridLayout(12, 2, 15, 15));

        lblmotor2.setText("Motorcycle:");
        jPanel1.add(lblmotor2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBox1);

        lblmaintenancedate.setText("Maintenance Date:");
        jPanel1.add(lblmaintenancedate);
        jPanel1.add(txtmaintedate);

        lblmaintenancetype.setText("Maintenance Type:");
        jPanel1.add(lblmaintenancetype);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preventive", "Corrective" }));
        jPanel1.add(jComboBox4);

        jLabel12.setText("Service Category");
        jPanel1.add(jLabel12);
        jPanel1.add(jTextField12);

        jLabel16.setText("Work Performed:");
        jPanel1.add(jLabel16);
        jPanel1.add(jTextField19);

        jLabel13.setText("Parts Replaced:");
        jPanel1.add(jLabel13);
        jPanel1.add(jTextField11);

        jLabel17.setText("Odometer Reading(km):");
        jPanel1.add(jLabel17);
        jPanel1.add(jTextField13);

        jLabel2.setText("Maintenance Cost:");
        jPanel1.add(jLabel2);
        jPanel1.add(jTextField15);

        jLabel3.setText("Next Service Mileage:");
        jPanel1.add(jLabel3);
        jPanel1.add(jTextField14);

        jLabel14.setText("Maintenance Status:");
        jPanel1.add(jLabel14);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "In Progress", "Completed" }));
        jPanel1.add(jComboBox2);

        jLabel21.setText("Motorcycle Availability:");
        jPanel1.add(jLabel21);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available", "Under Maintenance" }));
        jPanel1.add(jComboBox3);

        jLabel15.setText("Remarks");
        jPanel1.add(jLabel15);
        jPanel1.add(jTextField16);

        javax.swing.GroupLayout viewmainteLayout = new javax.swing.GroupLayout(viewmainte.getContentPane());
        viewmainte.getContentPane().setLayout(viewmainteLayout);
        viewmainteLayout.setHorizontalGroup(
            viewmainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewmainteLayout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(btnsavemainte)
                .addContainerGap(320, Short.MAX_VALUE))
            .addGroup(viewmainteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
        );
        viewmainteLayout.setVerticalGroup(
            viewmainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewmainteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsavemainte)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnviewinspectsave.setText("Save");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel5.setLayout(new java.awt.GridLayout(15, 2, 15, 15));

        lblmotor.setText("Motorcycle:");
        jPanel5.add(lblmotor);

        cmbmotorcycle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(cmbmotorcycle);

        lblinspectiondate.setText("Inspection Date:");
        jPanel5.add(lblinspectiondate);
        jPanel5.add(txtinspectdate);

        lblInspectionphase.setText("Inspection Phase:");
        jPanel5.add(lblInspectionphase);

        cbinspectionphase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pre-Rental", "Post-Rental" }));
        jPanel5.add(cbinspectionphase);

        jLabel19.setText("Odometer Reading:");
        jPanel5.add(jLabel19);
        jPanel5.add(jTextField1);

        lblfuel.setText("Fuel Level:");
        jPanel5.add(lblfuel);
        jPanel5.add(txtfuel);

        jLabel4.setText("Tire Condition:");
        jPanel5.add(jLabel4);

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Scratched", "Dented" }));
        jPanel5.add(jComboBox8);

        jLabel22.setText("Brake Condition:");
        jPanel5.add(jLabel22);

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Worn", "Damaged" }));
        jPanel5.add(jComboBox9);

        lblheadlightandsignal.setText("Headlight and Signal Lights:");
        jPanel5.add(lblheadlightandsignal);

        cbheadlight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Functional", "Not Functional" }));
        jPanel5.add(cbheadlight);

        jLabel23.setText("Horns");
        jPanel5.add(jLabel23);

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Functional", "Not Functional" }));
        jPanel5.add(jComboBox10);

        jLabel24.setText("Mirrors");
        jPanel5.add(jLabel24);

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Complete", "Missing", "Damaged" }));
        jPanel5.add(jComboBox11);

        jLabel25.setText("Motorcycle Body:");
        jPanel5.add(jLabel25);

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Needs Repair" }));
        jPanel5.add(jComboBox12);

        jLabel1.setText("Engine Condition:");
        jPanel5.add(jLabel1);

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Unusual Noise", "Other Issue" }));
        jPanel5.add(jComboBox13);

        lbloverallcondi.setText("Overall Condition:");
        jPanel5.add(lbloverallcondi);

        cbcondition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good", "Minor Damage", "Needs Repair", "Unsafe" }));
        jPanel5.add(cbcondition);

        jLabel26.setText("Readiness Status:");
        jPanel5.add(jLabel26);

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ready", "Not Ready" }));
        jPanel5.add(jComboBox14);

        jLabel27.setText("Inspection Notes:");
        jPanel5.add(jLabel27);
        jPanel5.add(jTextField2);

        javax.swing.GroupLayout viewinspectLayout = new javax.swing.GroupLayout(viewinspect.getContentPane());
        viewinspect.getContentPane().setLayout(viewinspectLayout);
        viewinspectLayout.setHorizontalGroup(
            viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewinspectLayout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(btnviewinspectsave)
                .addContainerGap(316, Short.MAX_VALUE))
            .addGroup(viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewinspectLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        viewinspectLayout.setVerticalGroup(
            viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewinspectLayout.createSequentialGroup()
                .addContainerGap(570, Short.MAX_VALUE)
                .addComponent(btnviewinspectsave)
                .addGap(16, 16, 16))
            .addGroup(viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewinspectLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(51, Short.MAX_VALUE)))
        );

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        btneditrecords.setText("Edit");
        btneditrecords.addActionListener(this::btneditrecordsActionPerformed);

        javax.swing.GroupLayout screen1viewrecordsLayout = new javax.swing.GroupLayout(screen1viewrecords.getContentPane());
        screen1viewrecords.getContentPane().setLayout(screen1viewrecordsLayout);
        screen1viewrecordsLayout.setHorizontalGroup(
            screen1viewrecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screen1viewrecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(screen1viewrecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(screen1viewrecordsLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(btneditrecords)
                        .addGap(0, 308, Short.MAX_VALUE)))
                .addContainerGap())
        );
        screen1viewrecordsLayout.setVerticalGroup(
            screen1viewrecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, screen1viewrecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneditrecords)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        btneditmainte.setText("Edit");
        btneditmainte.addActionListener(this::btneditmainteActionPerformed);

        jTextArea3.setEditable(false);
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout screen1viewmainteLayout = new javax.swing.GroupLayout(screen1viewmainte.getContentPane());
        screen1viewmainte.getContentPane().setLayout(screen1viewmainteLayout);
        screen1viewmainteLayout.setHorizontalGroup(
            screen1viewmainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screen1viewmainteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(screen1viewmainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(screen1viewmainteLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(btneditmainte)
                        .addGap(0, 308, Short.MAX_VALUE)))
                .addContainerGap())
        );
        screen1viewmainteLayout.setVerticalGroup(
            screen1viewmainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, screen1viewmainteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneditmainte)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        btneditinspect.setText("Edit");
        btneditinspect.addActionListener(this::btneditinspectActionPerformed);

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        javax.swing.GroupLayout screen1viewinspectLayout = new javax.swing.GroupLayout(screen1viewinspect.getContentPane());
        screen1viewinspect.getContentPane().setLayout(screen1viewinspectLayout);
        screen1viewinspectLayout.setHorizontalGroup(
            screen1viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screen1viewinspectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(screen1viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(screen1viewinspectLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(btneditinspect)
                        .addGap(0, 308, Short.MAX_VALUE)))
                .addContainerGap())
        );
        screen1viewinspectLayout.setVerticalGroup(
            screen1viewinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, screen1viewinspectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneditinspect)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        btneditdmgreport.setText("Edit");
        btneditdmgreport.addActionListener(this::btneditdmgreportActionPerformed);

        jTextArea5.setEditable(false);
        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        javax.swing.GroupLayout screen1viewdmgreportLayout = new javax.swing.GroupLayout(screen1viewdmgreport.getContentPane());
        screen1viewdmgreport.getContentPane().setLayout(screen1viewdmgreportLayout);
        screen1viewdmgreportLayout.setHorizontalGroup(
            screen1viewdmgreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(screen1viewdmgreportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(screen1viewdmgreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(screen1viewdmgreportLayout.createSequentialGroup()
                        .addGap(308, 308, 308)
                        .addComponent(btneditdmgreport)
                        .addGap(0, 308, Short.MAX_VALUE)))
                .addContainerGap())
        );
        screen1viewdmgreportLayout.setVerticalGroup(
            screen1viewdmgreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, screen1viewdmgreportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneditdmgreport)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        title1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title1.setText("Damage Report");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.setLayout(new java.awt.GridLayout(9, 2, 15, 15));

        jLabel10.setText("Motorcycle:");
        jPanel3.add(jLabel10);
        jPanel3.add(jTextField8);

        jLabel18.setText("Damage Location: ");
        jPanel3.add(jLabel18);
        jPanel3.add(jTextField7);

        jLabel8.setText("Damage Type:");
        jPanel3.add(jLabel8);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scratch", "Dent", "Crack", "Broken Part" }));
        jPanel3.add(jComboBox5);

        jLabel20.setText("Damage Description:");
        jPanel3.add(jLabel20);
        jPanel3.add(jTextField5);

        jLabel6.setText("Date Reported:");
        jPanel3.add(jLabel6);
        jPanel3.add(jTextField6);

        jLabel5.setText("Action Required:");
        jPanel3.add(jLabel5);

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Repair", "Monitor", "No Action" }));
        jPanel3.add(jComboBox6);

        jLabel7.setText("Damage Status:");
        jPanel3.add(jLabel7);

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Under Repair", "Resolved" }));
        jPanel3.add(jComboBox7);

        jLabel9.setText("Additional Notes:");
        jPanel3.add(jLabel9);
        jPanel3.add(jTextField9);

        jLabel11.setText("Estimated Repair Cost:");
        jPanel3.add(jLabel11);
        jPanel3.add(jTextField10);

        btnsavedmgreport.setText("Save");
        btnsavedmgreport.addActionListener(this::btnsavedmgreportActionPerformed);

        javax.swing.GroupLayout viewdmgLayout = new javax.swing.GroupLayout(viewdmg.getContentPane());
        viewdmg.getContentPane().setLayout(viewdmgLayout);
        viewdmgLayout.setHorizontalGroup(
            viewdmgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewdmgLayout.createSequentialGroup()
                .addGroup(viewdmgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewdmgLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
                    .addGroup(viewdmgLayout.createSequentialGroup()
                        .addGroup(viewdmgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewdmgLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title1))
                            .addGroup(viewdmgLayout.createSequentialGroup()
                                .addGap(305, 305, 305)
                                .addComponent(btnsavedmgreport)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewdmgLayout.setVerticalGroup(
            viewdmgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewdmgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsavedmgreport)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        description.setText("Browse registered motorcycles and their current status.");

        title.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title.setText("Motorcycle Records");

        recordstable.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        recordstable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Brand", "Model", "Year", "Type", "Mileage", "Daily Rate ₱", "Status"
            }
        ));
        recordsscrollpane.setViewportView(recordstable);

        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 15, 0));

        btnrefresh.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnrefresh.setText("Refresh");
        jPanel2.add(btnrefresh);

        btnedit.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnedit.setText("Edit");
        jPanel2.add(btnedit);

        btndelete.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btndelete.setText("Delete");
        jPanel2.add(btndelete);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 15, 0));

        btnviewinspectionrecord.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnviewinspectionrecord.setText("View Inspection");
        btnviewinspectionrecord.addActionListener(this::btnviewinspectionrecordActionPerformed);
        jPanel4.add(btnviewinspectionrecord);

        btnviewmaintenancerecord.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnviewmaintenancerecord.setText("View Maintenance");
        btnviewmaintenancerecord.addActionListener(this::btnviewmaintenancerecordActionPerformed);
        jPanel4.add(btnviewmaintenancerecord);

        btnviewdmg.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnviewdmg.setText("View Damage Report");
        btnviewdmg.addActionListener(this::btnviewdmgActionPerformed);
        jPanel4.add(btnviewdmg);

        ID.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        ID.setText("Search ID:");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        btnsearch.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnsearch.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 173, Short.MAX_VALUE))
                    .addComponent(recordsscrollpane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(description)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ID)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsearch)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(description)
                    .addComponent(ID)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recordsscrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnviewinspectionrecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewinspectionrecordActionPerformed
        int selectedRow = recordstable.getSelectedRow();
        String selectedId = selectedRow != -1 ? recordstable.getValueAt(selectedRow, 0).toString() : "";

        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Inspection ID", "Motorcycle ID", "Date", "Phase", "Odometer", "Fuel Level", "Tire Condition", 
                "Brake Condition", "Headlight & Signals", "Horns", "Mirrors", "Body Condition", 
                "Engine Condition", "Overall Condition", "Readiness Status", "Inspection Notes"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        if (jTableInspectionView == null) {
            jTableInspectionView = new javax.swing.JTable();
        }
        jTableInspectionView.setModel(tableModel);

        Runnable populateTable = () -> {
            tableModel.setRowCount(0);
            for (InspectionRecord r : TrimotoSystem.inspections) {
                String rId = getRecordField(r, "recordId");
                if (selectedId.isEmpty() || r.motorcycleId.equalsIgnoreCase(selectedId)) {
                    tableModel.addRow(new Object[]{
                        rId, r.motorcycleId, r.date, r.phase, r.odometer, r.fuel, r.tires, 
                        r.brakes, r.headlight, r.horns, r.mirrors, r.body, 
                        r.engine, r.condition, r.readiness, r.notes
                    });
                }
            }
            packTableColumns(jTableInspectionView);
        };

        populateTable.run();
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane4.setViewportView(jTableInspectionView);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblFilter = new JLabel("Filter Status:");
        JComboBox<String> cmbFilter = new JComboBox<>(new String[]{"All", "Good", "Minor Damage", "Needs Repair", "Unsafe"});
        JTextField txtSearch = new JTextField(12);
        JButton btnFilter = new JButton("Apply Filter");

        filterPanel.add(lblFilter);
        filterPanel.add(cmbFilter);
        filterPanel.add(new JLabel("Search ID/Keyword:"));
        filterPanel.add(txtSearch);
        filterPanel.add(btnFilter);

        btnFilter.addActionListener(e -> {
            String selectedFilter = cmbFilter.getSelectedItem().toString();
            String keyword = txtSearch.getText().trim().toLowerCase();

            tableModel.setRowCount(0);
            for (InspectionRecord r : TrimotoSystem.inspections) {
                String rId = getRecordField(r, "recordId");
                boolean matchMotor = selectedId.isEmpty() || r.motorcycleId.equalsIgnoreCase(selectedId);
                boolean matchCondition = "All".equalsIgnoreCase(selectedFilter) || selectedFilter.equalsIgnoreCase(r.condition);
                boolean matchKey = keyword.isEmpty() || rId.toLowerCase().contains(keyword) || r.motorcycleId.toLowerCase().contains(keyword) || r.notes.toLowerCase().contains(keyword);

                if (matchMotor && matchCondition && matchKey) {
                    tableModel.addRow(new Object[]{
                        rId, r.motorcycleId, r.date, r.phase, r.odometer, r.fuel, r.tires, 
                        r.brakes, r.headlight, r.horns, r.mirrors, r.body, 
                        r.engine, r.condition, r.readiness, r.notes
                    });
                }
            }
            packTableColumns(jTableInspectionView);
        });

        JPanel btnContainer = new JPanel();
        btnContainer.add(btneditinspect);

        JButton btndeleteinspect = new JButton("Delete");
        btndeleteinspect.addActionListener(e -> deleteSelectedInspection(selectedId));
        btnContainer.add(btndeleteinspect);

        screen1viewinspect.getContentPane().removeAll();
        screen1viewinspect.getContentPane().setLayout(new BorderLayout(10, 10));
        screen1viewinspect.getContentPane().add(filterPanel, BorderLayout.NORTH);
        screen1viewinspect.getContentPane().add(jScrollPane4, BorderLayout.CENTER);
        screen1viewinspect.getContentPane().add(btnContainer, BorderLayout.SOUTH);

        screen1viewinspect.setSize(1000, 520);
        screen1viewinspect.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
        screen1viewinspect.setVisible(true);
    }//GEN-LAST:event_btnviewinspectionrecordActionPerformed

    private void btnviewmaintenancerecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewmaintenancerecordActionPerformed
        int selectedRow = recordstable.getSelectedRow();
        String selectedId = selectedRow != -1 ? recordstable.getValueAt(selectedRow, 0).toString() : "";

        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Maintenance ID", "Motorcycle ID", "Date", "Type", "Service Category", "Work Performed", 
                "Parts Replaced", "Odometer Reading (km)", "Maintenance Cost (₱)", 
                "Next Service Mileage", "Status", "Availability", "Remarks"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        if (jTableMaintenanceView == null) {
            jTableMaintenanceView = new javax.swing.JTable();
        }
        jTableMaintenanceView.setModel(tableModel);

        Runnable populateTable = () -> {
            tableModel.setRowCount(0);
            for (MaintenanceRecord r : TrimotoSystem.maintenanceRecords) {
                String rId = getRecordField(r, "recordId");
                if (selectedId.isEmpty() || r.motorcycleId.equalsIgnoreCase(selectedId)) {
                    tableModel.addRow(new Object[]{
                        rId, r.motorcycleId, r.date, r.type, r.category, r.workPerformed, r.partsReplaced, 
                        r.odometer, r.cost, r.nextServiceMileage, r.status, r.availability, r.remarks
                    });
                }
            }
            packTableColumns(jTableMaintenanceView);
        };

        populateTable.run();
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane3.setViewportView(jTableMaintenanceView);

        // Header Filter Panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblFilter = new JLabel("Filter Status:");
        JComboBox<String> cmbFilter = new JComboBox<>(new String[]{"All", "Pending", "In Progress", "Completed"});
        JTextField txtSearch = new JTextField(12);
        JButton btnFilter = new JButton("Apply Filter");

        filterPanel.add(lblFilter);
        filterPanel.add(cmbFilter);
        filterPanel.add(new JLabel("Search ID/Keyword:"));
        filterPanel.add(txtSearch);
        filterPanel.add(btnFilter);

        btnFilter.addActionListener(e -> {
            String selectedFilter = cmbFilter.getSelectedItem().toString();
            String keyword = txtSearch.getText().trim().toLowerCase();

            tableModel.setRowCount(0);
            for (MaintenanceRecord r : TrimotoSystem.maintenanceRecords) {
                String rId = getRecordField(r, "recordId");
                boolean matchMotor = selectedId.isEmpty() || r.motorcycleId.equalsIgnoreCase(selectedId);
                boolean matchStatus = "All".equalsIgnoreCase(selectedFilter) || selectedFilter.equalsIgnoreCase(r.status);
                boolean matchKey = keyword.isEmpty() || rId.toLowerCase().contains(keyword) || r.motorcycleId.toLowerCase().contains(keyword) || r.workPerformed.toLowerCase().contains(keyword);

                if (matchMotor && matchStatus && matchKey) {
                    tableModel.addRow(new Object[]{
                        rId, r.motorcycleId, r.date, r.type, r.category, r.workPerformed, r.partsReplaced, 
                        r.odometer, r.cost, r.nextServiceMileage, r.status, r.availability, r.remarks
                    });
                }
            }
            packTableColumns(jTableMaintenanceView);
        });

        JPanel btnContainer = new JPanel();
        btnContainer.add(btneditmainte);

        JButton btndeletemainte = new JButton("Delete");
        btndeletemainte.addActionListener(e -> deleteSelectedMaintenance(selectedId));
        btnContainer.add(btndeletemainte);

        screen1viewmainte.getContentPane().removeAll();
        screen1viewmainte.getContentPane().setLayout(new BorderLayout(10, 10));
        screen1viewmainte.getContentPane().add(filterPanel, BorderLayout.NORTH);
        screen1viewmainte.getContentPane().add(jScrollPane3, BorderLayout.CENTER);
        screen1viewmainte.getContentPane().add(btnContainer, BorderLayout.SOUTH);

        screen1viewmainte.setSize(1000, 520);
        screen1viewmainte.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
        screen1viewmainte.setVisible(true);
    }//GEN-LAST:event_btnviewmaintenancerecordActionPerformed

    private void btneditinspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditinspectActionPerformed
        int selectedRow = jTableInspectionView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(screen1viewinspect, "Please select an inspection record row to edit.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String targetRecId = jTableInspectionView.getValueAt(selectedRow, 0).toString();
        InspectionRecord targetRecord = null;
        for (InspectionRecord record : TrimotoSystem.inspections) {
            if (targetRecId.equalsIgnoreCase(getRecordField(record, "recordId"))) {
                targetRecord = record;
                break;
            }
        }

        if (targetRecord != null) {
            populateInspectionGui(targetRecord);
            viewinspect.pack();
            viewinspect.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
            viewinspect.setVisible(true);
        }

    }//GEN-LAST:event_btneditinspectActionPerformed

    private void btneditmainteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditmainteActionPerformed
        int selectedRow = jTableMaintenanceView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(screen1viewmainte, "Please select a maintenance row to edit.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String targetRecId = jTableMaintenanceView.getValueAt(selectedRow, 0).toString();
        MaintenanceRecord targetRecord = null;
        for (MaintenanceRecord record : TrimotoSystem.maintenanceRecords) {
            if (targetRecId.equalsIgnoreCase(getRecordField(record, "recordId"))) {
                targetRecord = record;
                break;
            }
        }

        if (targetRecord != null) {
            populateMaintenanceGui(targetRecord);
            viewmainte.pack();
            viewmainte.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
            viewmainte.setVisible(true);
        }
 
    }//GEN-LAST:event_btneditmainteActionPerformed

    private void btneditrecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditrecordsActionPerformed
    editSelectedMotorcycle();    }//GEN-LAST:event_btneditrecordsActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnviewdmgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewdmgActionPerformed

        int selectedRow = recordstable.getSelectedRow();
        String selectedId = selectedRow != -1 ? recordstable.getValueAt(selectedRow, 0).toString() : "";

        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Report ID", "Motorcycle ID", "Date", "Location", "Type", "Description", "Action Required", "Cost (₱)", "Status", "Notes"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        if (jTableDamageView == null) {
            jTableDamageView = new javax.swing.JTable();
        }
        jTableDamageView.setModel(tableModel);

        Runnable populateTable = () -> {
            tableModel.setRowCount(0);
            for (String r : TrimotoSystem.damageReports) {
                String rId = extractField(r, "Report ID");
                String motor = extractField(r, "Motorcycle");
                if (selectedId.isEmpty() || r.contains("Motorcycle: " + selectedId) || motor.equalsIgnoreCase(selectedId)) {
                    tableModel.addRow(new Object[]{
                        rId, motor, extractField(r, "Date"), extractField(r, "Location"), 
                        extractField(r, "Type"), extractField(r, "Description"), 
                        extractField(r, "Action Required"), extractField(r, "Cost"), 
                        extractField(r, "Status"), extractField(r, "Notes")
                    });
                }
            }
            packTableColumns(jTableDamageView);
        };

        populateTable.run();
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane5.setViewportView(jTableDamageView);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblFilter = new JLabel("Filter Status:");
        JComboBox<String> cmbFilter = new JComboBox<>(new String[]{"All", "Pending", "Under Repair", "Resolved"});
        JTextField txtSearch = new JTextField(12);
        JButton btnFilter = new JButton("Apply Filter");

        filterPanel.add(lblFilter);
        filterPanel.add(cmbFilter);
        filterPanel.add(new JLabel("Search ID/Keyword:"));
        filterPanel.add(txtSearch);
        filterPanel.add(btnFilter);

        btnFilter.addActionListener(e -> {
            String selectedFilter = cmbFilter.getSelectedItem().toString();
            String keyword = txtSearch.getText().trim().toLowerCase();

            tableModel.setRowCount(0);
            for (String r : TrimotoSystem.damageReports) {
                String rId = extractField(r, "Report ID");
                String motor = extractField(r, "Motorcycle");
                String status = extractField(r, "Status");

                boolean matchMotor = selectedId.isEmpty() || r.contains("Motorcycle: " + selectedId) || motor.equalsIgnoreCase(selectedId);
                boolean matchStatus = "All".equalsIgnoreCase(selectedFilter) || selectedFilter.equalsIgnoreCase(status);
                boolean matchKey = keyword.isEmpty() || rId.toLowerCase().contains(keyword) || motor.toLowerCase().contains(keyword) || r.toLowerCase().contains(keyword);

                if (matchMotor && matchStatus && matchKey) {
                    tableModel.addRow(new Object[]{
                        rId, motor, extractField(r, "Date"), extractField(r, "Location"), 
                        extractField(r, "Type"), extractField(r, "Description"), 
                        extractField(r, "Action Required"), extractField(r, "Cost"), 
                        status, extractField(r, "Notes")
                    });
                }
            }
            packTableColumns(jTableDamageView);
        });

        JPanel btnContainer = new JPanel();
        btnContainer.add(btneditdmgreport);

        JButton btndeletedmg = new JButton("Delete");
        btndeletedmg.addActionListener(e -> deleteSelectedDamage(selectedId));
        btnContainer.add(btndeletedmg);

        screen1viewdmgreport.getContentPane().removeAll();
        screen1viewdmgreport.getContentPane().setLayout(new BorderLayout(10, 10));
        screen1viewdmgreport.getContentPane().add(filterPanel, BorderLayout.NORTH);
        screen1viewdmgreport.getContentPane().add(jScrollPane5, BorderLayout.CENTER);
        screen1viewdmgreport.getContentPane().add(btnContainer, BorderLayout.SOUTH);

        screen1viewdmgreport.setSize(1000, 520);
        screen1viewdmgreport.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
        screen1viewdmgreport.setVisible(true);
    

    }//GEN-LAST:event_btnviewdmgActionPerformed

    private void btneditdmgreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditdmgreportActionPerformed
        int selectedRow = jTableDamageView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(screen1viewdmgreport, "Please select a damage report row to edit.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String targetReportId = jTableDamageView.getValueAt(selectedRow, 0).toString();
        String targetRecord = null;
        for (String record : TrimotoSystem.damageReports) {
            if (record.contains("Report ID: " + targetReportId)) {
                targetRecord = record;
                break;
            }
        }

        if (targetRecord != null) {
            populateDamageGui(targetRecord);
            viewdmg.pack();
            viewdmg.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
            viewdmg.setVisible(true);
        }

    }//GEN-LAST:event_btneditdmgreportActionPerformed

    private void btnsavedmgreportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsavedmgreportActionPerformed
    String motorcycle = jTextField8.getText().trim();
        String location = jTextField7.getText().trim();
        String type = jComboBox5.getSelectedItem().toString();
        String description = jTextField5.getText().trim();
        String date = jTextField6.getText().trim();
        String action = jComboBox6.getSelectedItem().toString();
        String status = jComboBox7.getSelectedItem().toString();
        String notes = jTextField9.getText().trim();
        String cost = jTextField10.getText().trim();

        if (motorcycle.isEmpty() || location.isEmpty() || date.isEmpty() || cost.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields (Motorcycle, Location, Date, Cost).", "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int tableRow = (jTableDamageView != null) ? jTableDamageView.getSelectedRow() : -1;
        String reportId = tableRow != -1 ? jTableDamageView.getValueAt(tableRow, 0).toString() : "DMG-" + (TrimotoSystem.damageReports.size() + 1001);

        String formattedRecord = "Report ID: " + reportId + "\n" +
                                 "Motorcycle: " + motorcycle + "\n" +
                                 "Date: " + date + "\n" +
                                 "Location: " + location + "\n" +
                                 "Type: " + type + "\n" +
                                 "Description: " + (description.isEmpty() ? "-" : description) + "\n" +
                                 "Action Required: " + action + "\n" +
                                 "Status: " + status + "\n" +
                                 "Notes: " + (notes.isEmpty() ? "-" : notes) + "\n" +
                                 "Cost: " + cost;

        if (tableRow != -1) {
            for (int i = 0; i < TrimotoSystem.damageReports.size(); i++) {
                if (TrimotoSystem.damageReports.get(i).contains("Report ID: " + reportId)) {
                    TrimotoSystem.damageReports.set(i, formattedRecord);
                    break;
                }
            }
        } else {
            TrimotoSystem.damageReports.add(formattedRecord);
        }

        JOptionPane.showMessageDialog(this, "Damage report saved successfully!");
        viewdmg.dispose();
        btnviewdmgActionPerformed(evt);
    }//GEN-LAST:event_btnsavedmgreportActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btneditdmgreport;
    private javax.swing.JButton btneditinspect;
    private javax.swing.JButton btneditmainte;
    private javax.swing.JButton btneditrecords;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnsavedmgreport;
    private javax.swing.JButton btnsavemainte;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnviewdmg;
    private javax.swing.JButton btnviewinspectionrecord;
    private javax.swing.JButton btnviewinspectsave;
    private javax.swing.JButton btnviewmaintenancerecord;
    private javax.swing.JComboBox<String> cbcondition;
    private javax.swing.JComboBox<String> cbheadlight;
    private javax.swing.JComboBox<String> cbinspectionphase;
    private javax.swing.JComboBox<String> cmbmotorcycle;
    private javax.swing.JLabel description;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblInspectionphase;
    private javax.swing.JLabel lblfuel;
    private javax.swing.JLabel lblheadlightandsignal;
    private javax.swing.JLabel lblinspectiondate;
    private javax.swing.JLabel lblmaintenancedate;
    private javax.swing.JLabel lblmaintenancetype;
    private javax.swing.JLabel lblmotor;
    private javax.swing.JLabel lblmotor2;
    private javax.swing.JLabel lbloverallcondi;
    private javax.swing.JScrollPane recordsscrollpane;
    private javax.swing.JTable recordstable;
    private javax.swing.JDialog screen1viewdmgreport;
    private javax.swing.JDialog screen1viewinspect;
    private javax.swing.JDialog screen1viewmainte;
    private javax.swing.JDialog screen1viewrecords;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JTextField txtfuel;
    private javax.swing.JTextField txtinspectdate;
    private javax.swing.JTextField txtmaintedate;
    private javax.swing.JDialog viewdmg;
    private javax.swing.JDialog viewinspect;
    private javax.swing.JDialog viewmainte;
    private javax.swing.JDialog viewrecords;
    // End of variables declaration//GEN-END:variables
}
