import java.awt.CardLayout;
import javax.swing.UIManager;


public class MainDashboard extends javax.swing.JFrame {  
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainDashboard.class.getName());  
    
   public MainDashboard() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new javax.swing.Timer(1000, e -> {
            String formattedDate = java.time.LocalDate.now().format(
                java.time.format.DateTimeFormatter.ofPattern("MMMM dd, yyyy")
            );
            lbldate.setText(formattedDate);
        }).start();

        UIManager.put("Button.focusWidth", 0);
        initComponents();
        refreshDashboardCounters();
    }
   
    public void refreshDashboardCounters() {
        long total = TrimotoSystem.motorcycles.size();
        long available = TrimotoSystem.motorcycles.stream()
            .filter(m -> "Available".equalsIgnoreCase(m.status)).count();
        long rented = TrimotoSystem.motorcycles.stream()
            .filter(m -> "Rented".equalsIgnoreCase(m.status)).count();
        long maintenance = TrimotoSystem.motorcycles.stream()
            .filter(m -> "Under Maintenance".equalsIgnoreCase(m.status)).count();
        long inspection = TrimotoSystem.motorcycles.stream()
            .filter(m -> "For Inspection".equalsIgnoreCase(m.status)).count();
        long repair = TrimotoSystem.motorcycles.stream()
            .filter(m -> "Under Repair".equalsIgnoreCase(m.status)).count();

        lbltotalvalue.setText(String.valueOf(total));
        lbltotalvalue2.setText(String.valueOf(available));
        lbltotalvalue1.setText(String.valueOf(rented));
        lbltotalvalue3.setText(String.valueOf(maintenance));
        lbltotalvalue5.setText(String.valueOf(inspection));
        lbltotalvalue4.setText(String.valueOf(repair));
    }

   private void showCard(String name) {
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        cl.show(pnlswitcher, name);
        refreshDashboardCounters();
        if (pnlInspection != null) pnlInspection.refreshMotorcycleDropdown();
        if (pnlmaintenance != null) pnlmaintenance.refreshMotorcycleDropdown();
        if (pnlprocessing != null) pnlprocessing.refreshMotorcycleDropdown();
        if (pnlviewrecords != null) pnlviewrecords.loadTableData();
        if (pnlrentaltransaction != null) pnlrentaltransaction.loadTableData();
    }

    public void refreshAllPanels() {
        refreshDashboardCounters();
        if (pnlInspection != null) pnlInspection.refreshMotorcycleDropdown();
        if (pnlmaintenance != null) pnlmaintenance.refreshMotorcycleDropdown();
        if (pnlprocessing != null) pnlprocessing.refreshMotorcycleDropdown();
        if (pnlviewrecords != null) pnlviewrecords.loadTableData();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlsidebar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btndashboard = new javax.swing.JButton();
        btninspect = new javax.swing.JButton();
        btnmanagemotor = new javax.swing.JButton();
        btnmaintenance = new javax.swing.JButton();
        btnrentalprocess = new javax.swing.JButton();
        btnMotorcyclerecords = new javax.swing.JButton();
        btnRentalTransac = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Header = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        pnlDashboard = new javax.swing.JPanel();
        pnlswitcher = new javax.swing.JPanel();
        pnloverview = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        quickactions = new javax.swing.JPanel();
        ManageMotorcycle = new javax.swing.JButton();
        MotorcycleInspection = new javax.swing.JButton();
        MaintenanceRecording = new javax.swing.JButton();
        RentalProcessing = new javax.swing.JButton();
        ViewMotorcycleRecords = new javax.swing.JButton();
        ViewRentalTransactions = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        overview = new javax.swing.JPanel();
        pnlTotalcard = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbltotalvalue = new javax.swing.JLabel();
        pnlavailable = new javax.swing.JPanel();
        lblAvailabletitle = new javax.swing.JLabel();
        lbltotalvalue2 = new javax.swing.JLabel();
        pnlrented = new javax.swing.JPanel();
        lbltotalvalue1 = new javax.swing.JLabel();
        lblrentedtitle = new javax.swing.JLabel();
        pnlformainte = new javax.swing.JPanel();
        lblformaintenancetitle = new javax.swing.JLabel();
        lbltotalvalue3 = new javax.swing.JLabel();
        pnlforinspect1 = new javax.swing.JPanel();
        lblformaintenancetitle2 = new javax.swing.JLabel();
        lbltotalvalue5 = new javax.swing.JLabel();
        pnlforinspect = new javax.swing.JPanel();
        lblformaintenancetitle1 = new javax.swing.JLabel();
        lbltotalvalue4 = new javax.swing.JLabel();
        pnlInspection = new MotorcycleInspection();
        pnlmaintenance = new MaintenanceRecording();
        pnlprocessing = new RentalProcessing();
        pnlrentaltransaction = new ViewRentalTransaction();
        pnlmanage = new ManageMotorcycles();
        pnlviewrecords = new ViewMotorcycleRecords();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlsidebar.setBackground(new java.awt.Color(4, 41, 79));
        pnlsidebar.setForeground(new java.awt.Color(242, 242, 242));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TriMoto");

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Rental Management");

        btndashboard.setText("Dashboard");
        btndashboard.setFocusPainted(false);
        btndashboard.addActionListener(this::btndashboardActionPerformed);

        btninspect.setText("Motorcycle Inspection");
        btninspect.setFocusPainted(false);
        btninspect.addActionListener(this::btninspectActionPerformed);

        btnmanagemotor.setText("Manage Motorcycles");
        btnmanagemotor.setFocusPainted(false);
        btnmanagemotor.addActionListener(this::btnmanagemotorActionPerformed);

        btnmaintenance.setText("Maintenance Recording");
        btnmaintenance.setFocusPainted(false);
        btnmaintenance.addActionListener(this::btnmaintenanceActionPerformed);

        btnrentalprocess.setText("Rental Processing");
        btnrentalprocess.setFocusPainted(false);
        btnrentalprocess.addActionListener(this::btnrentalprocessActionPerformed);

        btnMotorcyclerecords.setText("View Motorcycle Records");
        btnMotorcyclerecords.setFocusPainted(false);
        btnMotorcyclerecords.addActionListener(this::btnMotorcyclerecordsActionPerformed);

        btnRentalTransac.setText("View Rental Transaction");
        btnRentalTransac.setFocusPainted(false);
        btnRentalTransac.addActionListener(this::btnRentalTransacActionPerformed);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Trimoto | Offline system");

        javax.swing.GroupLayout pnlsidebarLayout = new javax.swing.GroupLayout(pnlsidebar);
        pnlsidebar.setLayout(pnlsidebarLayout);
        pnlsidebarLayout.setHorizontalGroup(
            pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsidebarLayout.createSequentialGroup()
                .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlsidebarLayout.createSequentialGroup()
                        .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlsidebarLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)))
                            .addGroup(pnlsidebarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlsidebarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btndashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnmanagemotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btninspect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnmaintenance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRentalTransac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMotorcyclerecords, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(btnrentalprocess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlsidebarLayout.setVerticalGroup(
            pnlsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlsidebarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(btndashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnmanagemotor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btninspect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnrentalprocess, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnMotorcyclerecords, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRentalTransac, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        getContentPane().add(pnlsidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 710));

        Header.setBackground(new java.awt.Color(255, 255, 255));
        Header.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204))));
        Header.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setText("Motorcycle Rental, Inspection, and Maintenance Management System");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, -10, 1000, 90));

        pnlDashboard.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        pnlDashboard.setLayout(new javax.swing.BoxLayout(pnlDashboard, javax.swing.BoxLayout.LINE_AXIS));

        pnlswitcher.setLayout(new java.awt.CardLayout());

        pnloverview.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        pnloverview.setForeground(java.awt.Color.gray);
        pnloverview.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel4.setText("Dashboard Overview");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Manage motorcycles, inspections, maintenance, and rentals.");

        quickactions.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        quickactions.setLayout(new java.awt.GridLayout(3, 2, 15, 15));

        ManageMotorcycle.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        ManageMotorcycle.setText("<html><div style='width:220px;padding:5px'><b>Manage Motorcycles</b><br><br><span style='color:#667788'>Register and update motorcycle details</span></div></html>");
        ManageMotorcycle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ManageMotorcycle.addActionListener(this::ManageMotorcycleActionPerformed);
        quickactions.add(ManageMotorcycle);

        MotorcycleInspection.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        MotorcycleInspection.setText("<html><div style='width:220px;padding:5px'><b>Motorcycle Inspection</b><br><br><span style='color:#667788'>Record condition and damage reports</span></div></html>");
        MotorcycleInspection.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MotorcycleInspection.addActionListener(this::MotorcycleInspectionActionPerformed);
        quickactions.add(MotorcycleInspection);

        MaintenanceRecording.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        MaintenanceRecording.setText("<html><div style='width:220px;padding:5px'><b>Maintenance Recording</b><br><br><span style='color:#667788'>Record service history and expenses</span></div></html>");
        MaintenanceRecording.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MaintenanceRecording.addActionListener(this::MaintenanceRecordingActionPerformed);
        quickactions.add(MaintenanceRecording);

        RentalProcessing.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        RentalProcessing.setText("<html><div style='width:220px;padding:5px'><b>Rental Processing</b><br><br><span style='color:#667788'>Create a new rental transaction</span></div></html>");
        RentalProcessing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RentalProcessing.addActionListener(this::RentalProcessingActionPerformed);
        quickactions.add(RentalProcessing);

        ViewMotorcycleRecords.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        ViewMotorcycleRecords.setText("<html><div style='width:220px;padding:5px'><b>View Motorcycle Records</b><br><br><span style='color:#667788'>View details, inspections, and maintenance</span></div></html>");
        ViewMotorcycleRecords.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ViewMotorcycleRecords.addActionListener(this::ViewMotorcycleRecordsActionPerformed);
        quickactions.add(ViewMotorcycleRecords);

        ViewRentalTransactions.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        ViewRentalTransactions.setText("<html><div style='width:220px;padding:5px'><b>View Rental Transactions</b><br><br><span style='color:#667788'>Review recorded rental transactions</span></div></html>");
        ViewRentalTransactions.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ViewRentalTransactions.addActionListener(this::ViewRentalTransactionsActionPerformed);
        quickactions.add(ViewRentalTransactions);

        jPanel8.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel10.setText("Quick Actions");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        overview.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.pressedBackground"));
        overview.setLayout(new java.awt.GridLayout(1, 0, 15, 0));

        pnlTotalcard.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(41, 128, 185));
        jLabel6.setText("Total Motorcycles");

        lbltotalvalue.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lbltotalvalue.setForeground(new java.awt.Color(41, 128, 185));
        lbltotalvalue.setText("0");

        javax.swing.GroupLayout pnlTotalcardLayout = new javax.swing.GroupLayout(pnlTotalcard);
        pnlTotalcard.setLayout(pnlTotalcardLayout);
        pnlTotalcardLayout.setHorizontalGroup(
            pnlTotalcardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalcardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTotalcardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltotalvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlTotalcardLayout.setVerticalGroup(
            pnlTotalcardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalcardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotalvalue)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        overview.add(pnlTotalcard);

        pnlavailable.setBackground(new java.awt.Color(255, 255, 255));

        lblAvailabletitle.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblAvailabletitle.setForeground(new java.awt.Color(39, 174, 96));
        lblAvailabletitle.setText("Available");

        lbltotalvalue2.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lbltotalvalue2.setForeground(new java.awt.Color(39, 174, 96));
        lbltotalvalue2.setText("0");

        javax.swing.GroupLayout pnlavailableLayout = new javax.swing.GroupLayout(pnlavailable);
        pnlavailable.setLayout(pnlavailableLayout);
        pnlavailableLayout.setHorizontalGroup(
            pnlavailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlavailableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlavailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAvailabletitle)
                    .addComponent(lbltotalvalue2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pnlavailableLayout.setVerticalGroup(
            pnlavailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlavailableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvailabletitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotalvalue2)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        overview.add(pnlavailable);

        pnlrented.setBackground(new java.awt.Color(255, 255, 255));

        lbltotalvalue1.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lbltotalvalue1.setForeground(new java.awt.Color(142, 68, 173));
        lbltotalvalue1.setText("0");

        lblrentedtitle.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblrentedtitle.setForeground(new java.awt.Color(142, 68, 173));
        lblrentedtitle.setText("Rented");

        javax.swing.GroupLayout pnlrentedLayout = new javax.swing.GroupLayout(pnlrented);
        pnlrented.setLayout(pnlrentedLayout);
        pnlrentedLayout.setHorizontalGroup(
            pnlrentedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrentedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlrentedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblrentedtitle)
                    .addComponent(lbltotalvalue1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pnlrentedLayout.setVerticalGroup(
            pnlrentedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlrentedLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lblrentedtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotalvalue1)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        overview.add(pnlrented);

        pnlformainte.setBackground(new java.awt.Color(255, 255, 255));

        lblformaintenancetitle.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblformaintenancetitle.setForeground(new java.awt.Color(214, 123, 38));
        lblformaintenancetitle.setText("Under Maintenance");

        lbltotalvalue3.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lbltotalvalue3.setForeground(new java.awt.Color(214, 123, 38));
        lbltotalvalue3.setText("0");

        javax.swing.GroupLayout pnlformainteLayout = new javax.swing.GroupLayout(pnlformainte);
        pnlformainte.setLayout(pnlformainteLayout);
        pnlformainteLayout.setHorizontalGroup(
            pnlformainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlformainteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlformainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblformaintenancetitle)
                    .addComponent(lbltotalvalue3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        pnlformainteLayout.setVerticalGroup(
            pnlformainteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlformainteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblformaintenancetitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotalvalue3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        overview.add(pnlformainte);

        pnlforinspect1.setBackground(new java.awt.Color(255, 255, 255));

        lblformaintenancetitle2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblformaintenancetitle2.setForeground(new java.awt.Color(227, 191, 78));
        lblformaintenancetitle2.setText("For Inspection");

        lbltotalvalue5.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lbltotalvalue5.setForeground(new java.awt.Color(227, 191, 78));
        lbltotalvalue5.setText("0");

        javax.swing.GroupLayout pnlforinspect1Layout = new javax.swing.GroupLayout(pnlforinspect1);
        pnlforinspect1.setLayout(pnlforinspect1Layout);
        pnlforinspect1Layout.setHorizontalGroup(
            pnlforinspect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlforinspect1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlforinspect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblformaintenancetitle2)
                    .addComponent(lbltotalvalue5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        pnlforinspect1Layout.setVerticalGroup(
            pnlforinspect1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlforinspect1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblformaintenancetitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotalvalue5)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        overview.add(pnlforinspect1);

        pnlforinspect.setBackground(new java.awt.Color(255, 255, 255));

        lblformaintenancetitle1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblformaintenancetitle1.setForeground(new java.awt.Color(117, 80, 46));
        lblformaintenancetitle1.setText("Under Repair");

        lbltotalvalue4.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lbltotalvalue4.setForeground(new java.awt.Color(117, 80, 46));
        lbltotalvalue4.setText("0");

        javax.swing.GroupLayout pnlforinspectLayout = new javax.swing.GroupLayout(pnlforinspect);
        pnlforinspect.setLayout(pnlforinspectLayout);
        pnlforinspectLayout.setHorizontalGroup(
            pnlforinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlforinspectLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlforinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblformaintenancetitle1)
                    .addComponent(lbltotalvalue4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        pnlforinspectLayout.setVerticalGroup(
            pnlforinspectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlforinspectLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblformaintenancetitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotalvalue4)
                .addContainerGap(128, Short.MAX_VALUE))
        );

        overview.add(pnlforinspect);

        javax.swing.GroupLayout pnloverviewLayout = new javax.swing.GroupLayout(pnloverview);
        pnloverview.setLayout(pnloverviewLayout);
        pnloverviewLayout.setHorizontalGroup(
            pnloverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnloverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnloverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnloverviewLayout.createSequentialGroup()
                        .addGroup(pnloverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quickactions, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(overview, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnloverviewLayout.setVerticalGroup(
            pnloverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnloverviewLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(overview, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quickactions, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlswitcher.add(pnloverview, "dashboard");
        pnlswitcher.add(pnlInspection, "inspection");
        pnlswitcher.add(pnlmaintenance, "maintenance");
        pnlswitcher.add(pnlprocessing, "processing");
        pnlswitcher.add(pnlrentaltransaction, "transactions");
        pnlswitcher.add(pnlmanage, "manage");
        pnlswitcher.add(pnlviewrecords, "records");

        pnlDashboard.add(pnlswitcher);

        getContentPane().add(pnlDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 990, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndashboardActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("dashboard");
    }//GEN-LAST:event_btndashboardActionPerformed

    private void btnrentalprocessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrentalprocessActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("processing");
    }//GEN-LAST:event_btnrentalprocessActionPerformed

    private void ManageMotorcycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageMotorcycleActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("manage");
    }//GEN-LAST:event_ManageMotorcycleActionPerformed

    private void btnmanagemotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmanagemotorActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
       showCard("manage");
    }//GEN-LAST:event_btnmanagemotorActionPerformed

    private void btninspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninspectActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("inspection");
    }//GEN-LAST:event_btninspectActionPerformed

    private void btnmaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmaintenanceActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
       showCard("maintenance");
    }//GEN-LAST:event_btnmaintenanceActionPerformed

    private void btnMotorcyclerecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotorcyclerecordsActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("records");
    }//GEN-LAST:event_btnMotorcyclerecordsActionPerformed

    private void btnRentalTransacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentalTransacActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("transactions");
    }//GEN-LAST:event_btnRentalTransacActionPerformed

    private void MotorcycleInspectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MotorcycleInspectionActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("inspection");
    }//GEN-LAST:event_MotorcycleInspectionActionPerformed

    private void MaintenanceRecordingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintenanceRecordingActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("maintenance");
    }//GEN-LAST:event_MaintenanceRecordingActionPerformed

    private void RentalProcessingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RentalProcessingActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("processing");
    }//GEN-LAST:event_RentalProcessingActionPerformed

    private void ViewMotorcycleRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMotorcycleRecordsActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("records");
    }//GEN-LAST:event_ViewMotorcycleRecordsActionPerformed

    private void ViewRentalTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRentalTransactionsActionPerformed
        CardLayout cl = (CardLayout) pnlswitcher.getLayout();
        showCard("transactions");
    }//GEN-LAST:event_ViewRentalTransactionsActionPerformed

   public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainDashboard().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JButton MaintenanceRecording;
    private javax.swing.JButton ManageMotorcycle;
    private javax.swing.JButton MotorcycleInspection;
    private javax.swing.JButton RentalProcessing;
    private javax.swing.JButton ViewMotorcycleRecords;
    private javax.swing.JButton ViewRentalTransactions;
    private javax.swing.JButton btnMotorcyclerecords;
    private javax.swing.JButton btnRentalTransac;
    private javax.swing.JButton btndashboard;
    private javax.swing.JButton btninspect;
    private javax.swing.JButton btnmaintenance;
    private javax.swing.JButton btnmanagemotor;
    private javax.swing.JButton btnrentalprocess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAvailabletitle;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblformaintenancetitle;
    private javax.swing.JLabel lblformaintenancetitle1;
    private javax.swing.JLabel lblformaintenancetitle2;
    private javax.swing.JLabel lblrentedtitle;
    private javax.swing.JLabel lbltotalvalue;
    private javax.swing.JLabel lbltotalvalue1;
    private javax.swing.JLabel lbltotalvalue2;
    private javax.swing.JLabel lbltotalvalue3;
    private javax.swing.JLabel lbltotalvalue4;
    private javax.swing.JLabel lbltotalvalue5;
    private javax.swing.JPanel overview;
    private javax.swing.JPanel pnlDashboard;
    private MotorcycleInspection pnlInspection;
    private javax.swing.JPanel pnlTotalcard;
    private javax.swing.JPanel pnlavailable;
    private javax.swing.JPanel pnlforinspect;
    private javax.swing.JPanel pnlforinspect1;
    private javax.swing.JPanel pnlformainte;
    private MaintenanceRecording pnlmaintenance;
    private ManageMotorcycles pnlmanage;
    private javax.swing.JPanel pnloverview;
    private RentalProcessing pnlprocessing;
    private ViewRentalTransaction pnlrentaltransaction;
    private javax.swing.JPanel pnlrented;
    private javax.swing.JPanel pnlsidebar;
    private javax.swing.JPanel pnlswitcher;
    private ViewMotorcycleRecords pnlviewrecords;
    private javax.swing.JPanel quickactions;
    // End of variables declaration//GEN-END:variables
}
