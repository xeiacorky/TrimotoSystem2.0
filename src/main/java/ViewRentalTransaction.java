import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class ViewRentalTransaction extends javax.swing.JPanel {

    private javax.swing.JTable jTableDetailView;

   public ViewRentalTransaction() {
        UIManager.put("Button.focusWidth", 0);
        initComponents();
        addCustomComponents(); 
        setupEvents();
        loadTableData();
    }

   private void addCustomComponents() {
        jPanel1.removeAll();
        jPanel1.setLayout(new java.awt.GridLayout(1, 3, 15, 0));
        jPanel1.add(btnviewtransac);
        jPanel1.add(btnreturnedmark);
        jPanel1.add(btnrefresh);
        jPanel1.revalidate();
        jPanel1.repaint();

        lblStatusFilter = new javax.swing.JLabel("Filter:");
        cmbStatusFilter = new javax.swing.JComboBox<>(new String[] { "All", "Active", "Completed" });
        cmbStatusFilter.addActionListener(e -> searchTransaction());

        java.awt.Container topContainer = btnsearch.getParent();
        if (topContainer != null) {
            topContainer.add(lblStatusFilter);
            topContainer.add(cmbStatusFilter);
            topContainer.revalidate();
            topContainer.repaint();
        }
    }
    
    private void setupEvents() {
        btnrefresh.addActionListener(e -> loadTableData());
        btnsearch.addActionListener(e -> searchTransaction());
        btnreturnedmark.addActionListener(e -> markReturned());
        btnviewtransac.addActionListener(this::btnviewtransacActionPerformed);
    }

    public void loadTableData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (RentalTransaction rt : TrimotoSystem.rentals) {
            model.addRow(new Object[]{
                rt.transactionId, rt.motorcycleId, rt.renter, rt.days, rt.totalCost, rt.initialPayment, rt.date, rt.returnDate, rt.status
            });
        }
    }

    private void packTableColumns(javax.swing.JTable table) {
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 85; // Minimum column width
            
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

    private void searchTransaction() {
        String searchId = jTextField3.getText().trim().toLowerCase();
        String selectedStatus = cmbStatusFilter != null ? cmbStatusFilter.getSelectedItem().toString() : "All";

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (RentalTransaction rt : TrimotoSystem.rentals) {
            boolean matchesSearch = searchId.isEmpty() 
                || rt.transactionId.toLowerCase().contains(searchId) 
                || rt.motorcycleId.toLowerCase().contains(searchId)
                || rt.renter.toLowerCase().contains(searchId);

            boolean matchesStatus = "All".equalsIgnoreCase(selectedStatus) 
                || rt.status.equalsIgnoreCase(selectedStatus);

            if (matchesSearch && matchesStatus) {
                model.addRow(new Object[]{
                    rt.transactionId, rt.motorcycleId, rt.renter, rt.days, rt.totalCost, rt.initialPayment, rt.date, rt.returnDate, rt.status
                });
            }
        }
    }

    private void markReturned() {
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            String transId = jTable1.getValueAt(row, 0).toString();
            String motorId = jTable1.getValueAt(row, 1).toString();

            for (RentalTransaction rt : TrimotoSystem.rentals) {
                if (rt.transactionId.equals(transId)) {
                    rt.status = "Completed";
                }
            }

            Motorcycle m = TrimotoSystem.findMotorcycleById(motorId);
            if (m != null) {
                m.status = "Available";
            }

            loadTableData();
            notifyDashboardRefresh();

            JOptionPane.showMessageDialog(this, "Motorcycle marked as Returned and set to Available!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a rental transaction.");
        }
    }

    private void editTransaction() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a transaction to edit.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String transId = jTable1.getValueAt(row, 0).toString();
        RentalTransaction target = null;
        for (RentalTransaction rt : TrimotoSystem.rentals) {
            if (rt.transactionId.equalsIgnoreCase(transId)) {
                target = rt;
                break;
            }
        }

        if (target != null) {
            String newRenter = JOptionPane.showInputDialog(this, "Edit Renter Name:", target.renter);
            if (newRenter == null) return;

            String daysStr = JOptionPane.showInputDialog(this, "Edit Rental Days:", target.days);
            if (daysStr == null) return;

            String costStr = JOptionPane.showInputDialog(this, "Edit Total Cost (₱):", target.totalCost);
            if (costStr == null) return;

            String payStr = JOptionPane.showInputDialog(this, "Edit Initial Payment (₱):", target.initialPayment);
            if (payStr == null) return;

            String newReturnDate = JOptionPane.showInputDialog(this, "Edit Return Date:", target.returnDate);
            if (newReturnDate == null) return;

            String newStatus = JOptionPane.showInputDialog(this, "Edit Status (Active/Completed):", target.status);
            if (newStatus == null) return;

            try {
                target.renter = newRenter.trim();
                target.days = Integer.parseInt(daysStr.trim());
                target.totalCost = Double.parseDouble(costStr.trim());
                target.initialPayment = Double.parseDouble(payStr.trim());
                target.returnDate = newReturnDate.trim();
                target.status = newStatus.trim();

                loadTableData();
                notifyDashboardRefresh();
                JOptionPane.showMessageDialog(this, "Rental Transaction updated successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format for Days, Cost, or Initial Payment.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteTransaction() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a transaction to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this rental transaction?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String transId = jTable1.getValueAt(row, 0).toString();
            TrimotoSystem.rentals.removeIf(rt -> rt.transactionId.equalsIgnoreCase(transId));

            loadTableData();
            notifyDashboardRefresh();
            JOptionPane.showMessageDialog(this, "Transaction deleted successfully.");
        }
    }

    private void notifyDashboardRefresh() {
        java.awt.Window topWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (topWindow instanceof MainDashboard) {
            ((MainDashboard) topWindow).refreshAllPanels();
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewtransac = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnsave = new javax.swing.JButton();
        description = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnviewtransac = new javax.swing.JButton();
        btnreturnedmark = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        ID = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        btnsave.setText("Save");
        btnsave.addActionListener(this::btnsaveActionPerformed);

        javax.swing.GroupLayout viewtransacLayout = new javax.swing.GroupLayout(viewtransac.getContentPane());
        viewtransac.getContentPane().setLayout(viewtransacLayout);
        viewtransacLayout.setHorizontalGroup(
            viewtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewtransacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(viewtransacLayout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(btnsave)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        viewtransacLayout.setVerticalGroup(
            viewtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewtransacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnsave)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        description.setText("Review rental history and transaction status.");

        title.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        title.setText("Rental Transaction");

        jScrollPane1.setToolTipText("");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Motorcycle ID", "Renter", "Rental Days", "Total Cost", "Initial Payment", "Date Rented", "Return Date", "Status"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setLayout(new java.awt.GridLayout(1, 3, 15, 0));

        btnviewtransac.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnviewtransac.setText("View Transaction Details");
        btnviewtransac.addActionListener(this::btnviewtransacActionPerformed);
        jPanel1.add(btnviewtransac);

        btnreturnedmark.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnreturnedmark.setText("Mark Motorcycle Returned");
        jPanel1.add(btnreturnedmark);

        btnrefresh.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnrefresh.setText("Refresh");
        jPanel1.add(btnrefresh);

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
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(title)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(description)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ID)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsearch)
                        .addGap(16, 16, 16))))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ID)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnsearch))
                    .addComponent(description))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnviewtransacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewtransacActionPerformed

        String initialQuery = "";

        DefaultTableModel detailModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Transaction ID", "Motorcycle ID", "Renter Name", "Address", "Contact Number", 
                "Driver's License No.", "Days Rented", "Total Cost (₱)", 
                "Initial Payment (₱)", "Payment Type", "Date Rented", 
                "Expected Return Date", "Status"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        if (jTableDetailView == null) {
            jTableDetailView = new javax.swing.JTable();
        }
        jTableDetailView.setModel(detailModel);

        Runnable populateTable = () -> {
            detailModel.setRowCount(0);
            for (RentalTransaction rt : TrimotoSystem.rentals) {
                detailModel.addRow(new Object[]{
                    rt.transactionId, rt.motorcycleId, rt.renter, 
                    rt.address != null && !rt.address.isEmpty() ? rt.address : "-", 
                    rt.contactNumber != null && !rt.contactNumber.isEmpty() ? rt.contactNumber : "-", 
                    rt.licenseNumber != null && !rt.licenseNumber.isEmpty() ? rt.licenseNumber : "-", 
                    rt.days, rt.totalCost, rt.initialPayment, 
                    rt.paymentType != null ? rt.paymentType : "-", 
                    rt.date, rt.returnDate, rt.status
                });
            }
            packTableColumns(jTableDetailView);
        };

        populateTable.run();

        JPanel topSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel lblSearch = new JLabel("Search ID:");
        JTextField txtPopupSearch = new JTextField("", 15);
        JButton btnPopupSearch = new JButton("Search");

        topSearchPanel.add(lblSearch);
        topSearchPanel.add(txtPopupSearch);
        topSearchPanel.add(btnPopupSearch);

        btnPopupSearch.addActionListener(e -> {
            String q = txtPopupSearch.getText().trim().toLowerCase();
            detailModel.setRowCount(0);
            for (RentalTransaction rt : TrimotoSystem.rentals) {
                if (q.isEmpty() || rt.transactionId.toLowerCase().contains(q) || rt.motorcycleId.toLowerCase().contains(q) || rt.renter.toLowerCase().contains(q)) {
                    detailModel.addRow(new Object[]{
                        rt.transactionId, rt.motorcycleId, rt.renter, 
                        rt.address != null ? rt.address : "-", 
                        rt.contactNumber != null ? rt.contactNumber : "-", 
                        rt.licenseNumber != null ? rt.licenseNumber : "-", 
                        rt.days, rt.totalCost, rt.initialPayment, 
                        rt.paymentType != null ? rt.paymentType : "-", 
                        rt.date, rt.returnDate, rt.status
                    });
                }
            }
            packTableColumns(jTableDetailView);
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton btnPopupEdit = new JButton("Edit");
        JButton btnPopupDelete = new JButton("Delete");

        btnPopupEdit.addActionListener(e -> showRentalProcessingEditForm());
        btnPopupDelete.addActionListener(e -> {
            int selRow = jTableDetailView.getSelectedRow();
            if (selRow == -1) {
                JOptionPane.showMessageDialog(viewtransac, "Please select a transaction row to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(viewtransac, "Are you sure you want to delete this transaction?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String tId = jTableDetailView.getValueAt(selRow, 0).toString();
                TrimotoSystem.rentals.removeIf(r -> r.transactionId.equalsIgnoreCase(tId));
                populateTable.run();
                loadTableData();
                notifyDashboardRefresh();
                JOptionPane.showMessageDialog(viewtransac, "Transaction deleted successfully.");
            }
        });

        btnPanel.add(btnPopupEdit);
        btnPanel.add(btnPopupDelete);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setViewportView(jTableDetailView);

        viewtransac.getContentPane().removeAll();
        viewtransac.getContentPane().setLayout(new BorderLayout(10, 10));
        viewtransac.getContentPane().add(topSearchPanel, BorderLayout.NORTH);
        viewtransac.getContentPane().add(jScrollPane2, BorderLayout.CENTER);
        viewtransac.getContentPane().add(btnPanel, BorderLayout.SOUTH);

        viewtransac.setSize(1000, 480);
        viewtransac.setLocationRelativeTo(javax.swing.SwingUtilities.getWindowAncestor(this));
        viewtransac.setVisible(true);

    }//GEN-LAST:event_btnviewtransacActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
    viewtransac.dispose();    }//GEN-LAST:event_btnsaveActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
searchTransaction();
    }//GEN-LAST:event_jTextField3ActionPerformed
private void showRentalProcessingEditForm() {
        int selectedRow = jTableDetailView.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(viewtransac, "Please select a transaction to edit from the detail table.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String transId = jTableDetailView.getValueAt(selectedRow, 0).toString();
        RentalTransaction target = null;
        for (RentalTransaction rt : TrimotoSystem.rentals) {
            if (rt.transactionId.equalsIgnoreCase(transId)) {
                target = rt;
                break;
            }
        }

        if (target == null) return;

        javax.swing.JDialog editDialog = new javax.swing.JDialog(javax.swing.SwingUtilities.getWindowAncestor(this), "Edit Rental Transaction", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        
        JTextField txtRenter = new JTextField(target.renter);
        JTextField txtAddress = new JTextField(target.address != null ? target.address : "");
        JTextField txtContact = new JTextField(target.contactNumber != null ? target.contactNumber : "");
        JTextField txtDuration = new JTextField(String.valueOf(target.days));
        JTextField txtLicense = new JTextField(target.licenseNumber != null ? target.licenseNumber : "");
        JTextField txtCost = new JTextField(String.valueOf(target.totalCost));
        JTextField txtPayment = new JTextField(String.valueOf(target.initialPayment));
        JTextField txtReturn = new JTextField(target.returnDate != null ? target.returnDate : "");
        
        javax.swing.JCheckBox cbPartial = new javax.swing.JCheckBox("Partial Payment", "Partial".equalsIgnoreCase(target.paymentType));
        javax.swing.JCheckBox cbFull = new javax.swing.JCheckBox("Full Payment", "Full".equalsIgnoreCase(target.paymentType));
        
        cbPartial.addActionListener(e -> { if (cbPartial.isSelected()) cbFull.setSelected(false); });
        cbFull.addActionListener(e -> { if (cbFull.isSelected()) cbPartial.setSelected(false); });

        JComboBox<String> cmbStatus = new JComboBox<>(new String[]{"Active", "Completed"});
        cmbStatus.setSelectedItem(target.status);

        JPanel formPanel = new JPanel(new java.awt.GridLayout(10, 2, 10, 10));
        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Renter Name:"));
        formPanel.add(txtRenter);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(txtAddress);
        formPanel.add(new JLabel("Contact Number:"));
        formPanel.add(txtContact);
        formPanel.add(new JLabel("Rental Duration (Days):"));
        formPanel.add(txtDuration);
        formPanel.add(new JLabel("Driver's License #:"));
        formPanel.add(txtLicense);
        formPanel.add(new JLabel("Total Cost (₱):"));
        formPanel.add(txtCost);
        formPanel.add(new JLabel("Initial Payment (₱):"));
        formPanel.add(txtPayment);
        
        JPanel cbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbPanel.add(cbPartial);
        cbPanel.add(cbFull);
        formPanel.add(new JLabel("Payment Type:"));
        formPanel.add(cbPanel);

        formPanel.add(new JLabel("Expected Return Date:"));
        formPanel.add(txtReturn);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(cmbStatus);

        JButton btnSave = new JButton("Save Changes");
        final RentalTransaction targetRecord = target;

        btnSave.addActionListener(e -> {
            try {
                targetRecord.renter = txtRenter.getText().trim();
                targetRecord.address = txtAddress.getText().trim();
                targetRecord.contactNumber = txtContact.getText().trim();
                targetRecord.days = Integer.parseInt(txtDuration.getText().trim());
                targetRecord.licenseNumber = txtLicense.getText().trim();
                targetRecord.totalCost = Double.parseDouble(txtCost.getText().trim());
                targetRecord.initialPayment = Double.parseDouble(txtPayment.getText().trim());
                targetRecord.returnDate = txtReturn.getText().trim();
                targetRecord.paymentType = cbPartial.isSelected() ? "Partial" : (cbFull.isSelected() ? "Full" : "Standard");
                targetRecord.status = cmbStatus.getSelectedItem().toString();
                editDialog.dispose();
                loadTableData();
                btnviewtransacActionPerformed(null);
                notifyDashboardRefresh();
                JOptionPane.showMessageDialog(this, "Rental Transaction Updated Successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog, "Please enter valid numbers for Duration, Cost, and Initial Payment.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editDialog.setLayout(new BorderLayout());
        editDialog.add(formPanel, BorderLayout.CENTER);
        editDialog.add(btnSave, BorderLayout.SOUTH);
        editDialog.setSize(500, 480);
        editDialog.setLocationRelativeTo(viewtransac);
        editDialog.setVisible(true);
    }

    private javax.swing.JLabel lblStatusFilter;
    private javax.swing.JComboBox<String> cmbStatusFilter;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnreturnedmark;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnviewtransac;
    private javax.swing.JLabel description;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel title;
    private javax.swing.JDialog viewtransac;
    // End of variables declaration//GEN-END:variables
}
