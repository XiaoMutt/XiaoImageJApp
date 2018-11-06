/*
 * GPLv3
 */
package XBlobAnalyzer;

import ij.IJ;
import ij.ImageJ;
import ij.io.DirectoryChooser;
import ij.io.OpenDialog;
import java.beans.PropertyChangeEvent;

/**
 *
 * @author Xiao Zhou
 */
public class XSterlyzerGUI extends javax.swing.JFrame {

    XSterRoiPickUpWindow pickupWindow;

    /**
     * Creates new form XLineScanGUI
     */
    public XSterlyzerGUI() {
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
        runBn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        mtChannelCb = new javax.swing.JComboBox();
        ignorelROICb = new javax.swing.JCheckBox();
        bsFileBn = new javax.swing.JButton();
        beanShellFileTf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XSterlyzer");

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

        runBn.setText("Run");
        runBn.setEnabled(false);
        runBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runBnActionPerformed(evt);
            }
        });

        jLabel5.setText("Microtubule Channel");

        mtChannelCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        ignorelROICb.setSelected(true);
        ignorelROICb.setText("Ignore any saved ROIs in image files and mannually choose ROIs for each image by poping up each image");

        bsFileBn.setText("Choose a BeanShell file");
        bsFileBn.setEnabled(false);
        bsFileBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsFileBnActionPerformed(evt);
            }
        });

        beanShellFileTf.setEditable(false);
        beanShellFileTf.setText("Not implemented yet");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(runBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mtChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(467, 467, 467))
                    .addComponent(ignorelROICb, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bsFileBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(openFolderBn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileNameRegexCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(openFolderTf)
                            .addComponent(beanShellFileTf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(mtChannelCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bsFileBn)
                    .addComponent(beanShellFileTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(ignorelROICb)
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
        runBn.setEnabled(openFolderTf.getText().length() != 0&&beanShellFileTf.getText().length()!=0);
    }//GEN-LAST:event_openFolderBnActionPerformed

    private void runBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runBnActionPerformed
        XSterlyzerWorker worker = new XSterlyzerWorker();
        if ("Run".equals(runBn.getText())) {
            runBn.setText("Cancel");
            worker.setOpenFolder(openFolderTf.getText());
            worker.setChannel(Integer.parseInt(mtChannelCb.getSelectedItem().toString()));
            worker.setFileNameRegex(fileNameRegexCb.getSelectedItem().toString());
            worker.setIgnoreROI(ignorelROICb.isSelected());
            worker.setBsFileName(beanShellFileTf.getText());

            worker.addPropertyChangeListener((PropertyChangeEvent evt1) -> {
                if (worker.isCancelled()) {
                    IJ.log("INFO: XSterlyzer canncelled");
                    runBn.setText("Run");
                } else if (worker.isDone()) {
                    IJ.log("INFO: Xsterlyzer finished");
                    runBn.setText("Run");
                } else if (evt1.getPropertyName().equals("PausedAt")) {
                    String pausedAt = (String) evt1.getNewValue();
                    pickupWindow = new XSterRoiPickUpWindow(pausedAt, worker, worker.getChannel());
                    pickupWindow.setVisible(true);

                }
            });

            worker.execute();

        } else {
            if (pickupWindow != null) {
                pickupWindow.canel();
                pickupWindow=null;
            }
            worker.cancel(true);

        }
    }//GEN-LAST:event_runBnActionPerformed

    private void bsFileBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsFileBnActionPerformed
        OpenDialog od = new OpenDialog("Choose a BeanShell File");
        if (od.getPath() != null) {
            beanShellFileTf.setText(od.getPath());
        }
        runBn.setEnabled(openFolderTf.getText().length() != 0&&beanShellFileTf.getText().length()!=0);        // TODO add your handling code here:
    }//GEN-LAST:event_bsFileBnActionPerformed
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new XSterlyzerGUI().setVisible(true);
            ImageJ imj = new ImageJ(ImageJ.EMBEDDED);
            imj.exitWhenQuitting(true);
            imj.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField beanShellFileTf;
    private javax.swing.JButton bsFileBn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox fileNameRegexCb;
    private javax.swing.JCheckBox ignorelROICb;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox mtChannelCb;
    private javax.swing.JButton openFolderBn;
    private javax.swing.JTextField openFolderTf;
    private javax.swing.JButton runBn;
    // End of variables declaration//GEN-END:variables

}
