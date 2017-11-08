/*
 * GPLv3
 */
package XLineScan;

import ij.IJ;
import ij.io.DirectoryChooser;
import java.beans.PropertyChangeEvent;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Xiao Zhou
 */
public class XLineScanGUI extends javax.swing.JFrame {

    XSpinldeLineiPickUpWindow xrpw;

    /**
     * Creates new form XLineScanGUI
     */
    public XLineScanGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        openFolderBn = new javax.swing.JButton();
        openFolderTf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fileNameRegexCb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        normalizedDataPointsSp = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        runBn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        subtractCertainBackgroundRb = new javax.swing.JRadioButton();
        numeratorBackgroundValueSp = new javax.swing.JSpinner();
        useRollingBallRb = new javax.swing.JRadioButton();
        rollingBallRadiusSp = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        denominatorBackgroundValueSp = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        roiLineWidthSp = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        numeratorChannelCb = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        denominatorChannelCb = new javax.swing.JComboBox();
        manualROICb = new javax.swing.JCheckBox();
        useAutoDetectionCb = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        mtChannelCb = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        dnaChannelCb = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XLineScan");

        openFolderBn.setText("Open a Folder");
        openFolderBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFolderBnActionPerformed(evt);
            }
        });

        openFolderTf.setEditable(false);

        jLabel4.setText("Image file name follow the regex:");

        fileNameRegexCb.setEditable(true);
        fileNameRegexCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "^.+\\.[Zz][Ii][Pp]\\z", "^.+\\.[Tt][Ii][Ff]\\z", "^.*DAPI.*\\.[Tt][Ii][Ff]\\z", "^.*FITC.*\\.[Tt][Ii][Ff]\\z", "^.*Red.*\\.[Tt][Ii][Ff]\\z", "^.*TRITC.*\\.[Tt][Ii][Ff]\\z" }));

        jLabel3.setText("data points");

        normalizedDataPointsSp.setModel(new javax.swing.SpinnerNumberModel(101, 3, 100001, 1));

        jLabel2.setText("Normalize raw data to ");

        runBn.setText("Run");
        runBn.setEnabled(false);
        runBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runBnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Substract Background"));

        buttonGroup1.add(subtractCertainBackgroundRb);
        subtractCertainBackgroundRb.setSelected(true);
        subtractCertainBackgroundRb.setText("Subtract a certain background:");

        numeratorBackgroundValueSp.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 10));

        buttonGroup1.add(useRollingBallRb);
        useRollingBallRb.setText("Subtract background usingthe  Rolling Ball method with a Radius of");

        rollingBallRadiusSp.setModel(new javax.swing.SpinnerNumberModel(150, 10, 1000, 10));

        jLabel7.setText("Numerator Channel Image Background:");

        denominatorBackgroundValueSp.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 10));

        jLabel8.setText("Denominator Channel Image Background:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(useRollingBallRb, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rollingBallRadiusSp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(subtractCertainBackgroundRb, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(denominatorBackgroundValueSp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(numeratorBackgroundValueSp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subtractCertainBackgroundRb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(numeratorBackgroundValueSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(denominatorBackgroundValueSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useRollingBallRb)
                    .addComponent(rollingBallRadiusSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setText("Set ROI Line Width to");

        roiLineWidthSp.setModel(new javax.swing.SpinnerNumberModel(3, 0, 50, 1));

        jLabel5.setText("Numerator Channel:");

        numeratorChannelCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        jLabel6.setText("Denominator Channel:");

        denominatorChannelCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        manualROICb.setSelected(true);
        manualROICb.setText("Ignore any saved ROIs in image files and mannually choose ROIs for each image by poping up each image");

        useAutoDetectionCb.setSelected(true);
        useAutoDetectionCb.setText("Use auto detection");

        jLabel9.setText("microtubule channel");

        mtChannelCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel10.setText("DNA channel");

        dnaChannelCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(runBn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(openFolderBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileNameRegexCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(openFolderTf)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(useAutoDetectionCb)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mtChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(dnaChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(manualROICb, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numeratorChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(denominatorChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(normalizedDataPointsSp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3))
                                    .addComponent(roiLineWidthSp, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openFolderBn)
                    .addComponent(openFolderTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fileNameRegexCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numeratorChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(denominatorChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(roiLineWidthSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(normalizedDataPointsSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(manualROICb)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useAutoDetectionCb)
                    .addComponent(jLabel9)
                    .addComponent(mtChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(dnaChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(runBn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFolderBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFolderBnActionPerformed
        DirectoryChooser dc = new DirectoryChooser("Choose a Image Folder");
        String dir = dc.getDirectory();
        if (dir != null) {
            openFolderTf.setText(dir);
        }
        runBn.setEnabled(openFolderTf.getText().length() != 0);
    }//GEN-LAST:event_openFolderBnActionPerformed

    private void runBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runBnActionPerformed
        XLineScanWorker worker = new XLineScanWorker();
        if ("Run".equals(runBn.getText())) {
            runBn.setText("Cancel");
            worker.setOpenFolder(openFolderTf.getText());
            worker.setNumeratorChannelIndex(Integer.parseInt(numeratorChannelCb.getSelectedItem().toString()));
            worker.setDenomiatorChannelIndex(Integer.parseInt(denominatorChannelCb.getSelectedItem().toString()));
            worker.setFileNameRegex(fileNameRegexCb.getSelectedItem().toString());
            worker.setUsingRollingBall(useRollingBallRb.isSelected());
            worker.setRollingBallRadius(Integer.parseInt(rollingBallRadiusSp.getValue().toString()));
            worker.setNumeratorBackgroundValue(Integer.parseInt(numeratorBackgroundValueSp.getValue().toString()));
            worker.setDenominatorBackgroundValue(Integer.parseInt(denominatorBackgroundValueSp.getValue().toString()));
            worker.setROILineWidth(Integer.parseInt(roiLineWidthSp.getValue().toString()));
            worker.setNormalizedLength(Integer.parseInt(normalizedDataPointsSp.getValue().toString()));
            worker.setManualROI(manualROICb.isSelected());
            worker.setUseAutoDetection(useAutoDetectionCb.isSelected());
            worker.setMtchannel(Integer.parseInt(mtChannelCb.getSelectedItem().toString()));
            worker.setDnachannel(Integer.parseInt(dnaChannelCb.getSelectedItem().toString()));

            worker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {

                if (worker.isCancelled()) {
                    IJ.log("INFO: XLineScan canncelled");
                    runBn.setText("Run");
                } else if (worker.isDone()) {
                    try {
                        runBn.setText("Run");
                        XLineScanResult results = (XLineScanResult) worker.get();
                        if (results != null) {
                            results.drawPlots();
                        }
                        IJ.log("INFO: XLineScan completed");
                    } catch (InterruptedException ex) {
                        IJ.log("ERROR: Run interrupted: " + ex.getMessage());
                    } catch (ExecutionException ex) {
                        IJ.log("ERROR: Run error: " + ex.getMessage());
                    }
                } else if (evt1.getPropertyName().equals("PausedAt")) {
                    String pausedAt = (String) evt1.getNewValue();
                    xrpw = new XSpinldeLineiPickUpWindow(pausedAt, worker, worker.getUseAutoDetection(), worker.getMtchannel(), worker.getDnachannel());
                    xrpw.setVisible(true);

                }
            });

            worker.execute();

        } else {
            if (xrpw != null) {
                xrpw.canel();
                xrpw=null;
            }
            worker.cancel(true);
        }
    }//GEN-LAST:event_runBnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JSpinner denominatorBackgroundValueSp;
    private javax.swing.JComboBox denominatorChannelCb;
    private javax.swing.JComboBox<String> dnaChannelCb;
    private javax.swing.JComboBox fileNameRegexCb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox manualROICb;
    private javax.swing.JComboBox<String> mtChannelCb;
    private javax.swing.JSpinner normalizedDataPointsSp;
    private javax.swing.JSpinner numeratorBackgroundValueSp;
    private javax.swing.JComboBox numeratorChannelCb;
    private javax.swing.JButton openFolderBn;
    private javax.swing.JTextField openFolderTf;
    private javax.swing.JSpinner roiLineWidthSp;
    private javax.swing.JSpinner rollingBallRadiusSp;
    private javax.swing.JButton runBn;
    private javax.swing.JRadioButton subtractCertainBackgroundRb;
    private javax.swing.JCheckBox useAutoDetectionCb;
    private javax.swing.JRadioButton useRollingBallRb;
    // End of variables declaration//GEN-END:variables

}
