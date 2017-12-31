/*
 * GPLv3
 */
package GUI;

import ij.ImagePlus;
import ij.gui.Overlay;
import ij.gui.Roi;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Xiao Zhou
 */
public class RoiListManager extends javax.swing.JFrame {

    private final HashMap<String, Roi> roiMap;//maintain the Rois
    private final ImagePlus imp;
    private final RoiPickUpWindow imageWd;
    private Overlay overlay;
    private final DefaultListModel<String> listModel;
    private int editingIndex = -1;
    private int countRoi = 0;
    private final JPopupMenu editPu = new JPopupMenu();
    private final JTextField editTf = new JTextField();

    ;

    /**
     * Creates new form RoiListManager
     *
     * @param imageWindow the ImageWindow paired with this RoiManager.
     */
    public RoiListManager(RoiPickUpWindow imageWindow) {
        roiMap = new HashMap<>();
        listModel = new DefaultListModel<>();
        imp = imageWindow.getImagePlus();
        imageWd = imageWindow;
        initComponents();

    }

    public void editRoiName() {
        //initiate edit popup;
        editTf.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
        editTf.addActionListener((ActionEvent e) -> {
            String name = listModel.getElementAt(editingIndex);
            listModel.set(editingIndex, editTf.getText());
            Roi roi = roiMap.get(name);
            roiMap.remove(name);
            roiMap.put(editTf.getText(), roi);
            overlay.remove(roi);
            overlay.add(roi, editTf.getText());
            editPu.setVisible(false);
        });

        editPu.setBorder(new EmptyBorder(0, 0, 0, 0));
        editPu.add(editTf);

        int row = roiDisplayList.getSelectedIndex();
        Rectangle r = roiDisplayList.getCellBounds(row, row);
        editPu.setPreferredSize(new Dimension(r.width, r.height));
        editPu.show(roiDisplayList, r.x, r.y);
        editTf.setText(roiDisplayList.getSelectedValue());
        editTf.selectAll();
        editTf.requestFocusInWindow();
    }

    @Override
    public void setVisible(boolean visible) {

        loadRoiFromImpToList();
        super.setVisible(visible);
    }

    public void selectRoiInDisplayList(String name) {
        if (name != null) {
            roiDisplayList.setSelectedValue(name, true);
            addBn.setText("Accept");
            editingIndex = roiDisplayList.getSelectedIndex();
        } else {
            addBn.setText("Add");
        }
    }

    private void loadRoiFromImpToList() {
        if (imp.getOverlay() != null) {
            overlay = imp.getOverlay();
        } else {
            overlay = new Overlay();
            imp.setOverlay(overlay);
        }
        //set stroke color
        overlay.setStrokeColor(Color.yellow);
        //set label color
        overlay.setLabelColor(Color.magenta);
        //set label font
        overlay.setLabelFont(new Font("Helvetica", Font.BOLD, 18));
        //draw labels and use name as labels
        overlay.drawLabels(true);
        overlay.drawNames(true);
        for (Roi roi : overlay.toArray()) {
            //String name = Integer.toString(i) + "-" + roi.getHashCode();
            String name = roi.getName();
            roiMap.put(name, roi);
            listModel.addElement(name);
            countRoi++;
        }
    }

    public ArrayList<Roi> getRois() {
        ArrayList<Roi> pickedRois = new ArrayList<>();
        for (String key : roiMap.keySet()) {
            pickedRois.add(roiMap.get(key));
        }
        return pickedRois;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        roiDisplayList = new javax.swing.JList<>();
        deleteBn = new javax.swing.JButton();
        addBn = new javax.swing.JButton();
        doneBn = new javax.swing.JButton();
        hideCb = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ROI Manager");
        setAlwaysOnTop(true);
        setResizable(false);

        roiDisplayList.setModel(listModel);
        roiDisplayList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roiDisplayListMouseClicked(evt);
            }
        });
        roiDisplayList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                roiDisplayListKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(roiDisplayList);

        deleteBn.setText("Delete");
        deleteBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBnActionPerformed(evt);
            }
        });

        addBn.setText("Add");
        addBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBnActionPerformed(evt);
            }
        });

        doneBn.setText("Done");
        doneBn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneBnActionPerformed(evt);
            }
        });

        hideCb.setText("Hide");
        hideCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideCbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(doneBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hideCb)
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteBn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hideCb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(doneBn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roiDisplayListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roiDisplayListMouseClicked
        if (evt.getClickCount() == 2) {
            //this is Roi name editing event;
            editRoiName();
        } else {
            if (editingIndex != -1) {//this is an editing event;
                addBn.setText("Add");
                String name = listModel.getElementAt(editingIndex);
                //get roi from imp;
                Roi roi = imp.getRoi();
                if (roi != null) {//there is a new roi
                    Roi oldRoi = roiMap.get(name);
                    if (roi != oldRoi) {
                        //replace the old roi with the new roi;
                        roiMap.put(name, roi);
                        overlay.remove(oldRoi);
                        overlay.add(roi, name);
                    }
                } else if (roi == null) {//there is not a new roi
                    //put back the old roi;
                    //get old roi;
                    roi = roiMap.get(name);
                    overlay.add(roi, name);
                }
                editingIndex = -1;
                imp.deleteRoi();
                addBn.setText("Accept");
            }

            editingIndex = roiDisplayList.locationToIndex(evt.getPoint());
            String name = listModel.get(editingIndex);
            Roi roi = roiMap.get(name);
            imp.setRoi(roi);
        }
    }//GEN-LAST:event_roiDisplayListMouseClicked

    private void deleteBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBnActionPerformed
        deleteRois();
    }//GEN-LAST:event_deleteBnActionPerformed

    private void addBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBnActionPerformed
        Roi roi = imp.getRoi();
        if (roi != null) {
            roi.setStrokeColor(Color.yellow);
            if (editingIndex != -1) {//this is editing
                String name = listModel.getElementAt(editingIndex);
                Roi oldRoi = roiMap.get(name);
                if (roi != oldRoi) {
                    //replace the old roi with the new roi;
                    roiMap.put(name, roi);
                    overlay.remove(oldRoi);
                    overlay.add(roi, name);
                }
                editingIndex = -1;
                addBn.setText("Add");
            } else {//this is adding new
                String name = Integer.toString(countRoi);
                countRoi++;
                listModel.addElement(name);
                roi.setName(name);
                //roiDisplayList.setModel(listModel);
                roiMap.put(name, roi);
                overlay.add(roi);
            }
        } else {//the roi is missing or invalid
            addBn.setText("Add");
        }
        imp.deleteRoi();
        imp.setOverlay(overlay);
    }//GEN-LAST:event_addBnActionPerformed

    private void doneBnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneBnActionPerformed
        if (editingIndex != -1) {
            addBn.setText("Add");
            String name = listModel.getElementAt(editingIndex);
            Roi roi = imp.getRoi();
            roiMap.replace(name, roi);
            overlay.add(roi);
            editingIndex = -1;
            imp.deleteRoi();

        }

        imageWd.dispatchEvent(new WindowEvent(imageWd, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_doneBnActionPerformed

    private void hideCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideCbActionPerformed
        imp.deleteRoi();
        if (editingIndex != -1) {//this is editing event;
            addBn.setText("Add");
            editingIndex = -1;

        }
        if (hideCb.isSelected()) {
            imp.setOverlay(null);
        } else {
            imp.setOverlay(overlay);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_hideCbActionPerformed

    private void roiDisplayListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roiDisplayListKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            deleteRois();
        }
    }//GEN-LAST:event_roiDisplayListKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBn;
    private javax.swing.JButton deleteBn;
    private javax.swing.JButton doneBn;
    private javax.swing.JCheckBox hideCb;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> roiDisplayList;
    // End of variables declaration//GEN-END:variables

    public void deleteRois() {
        List<String> selected = roiDisplayList.getSelectedValuesList();

        for (String name : selected) {
            listModel.removeElement(name);
            Roi roi = roiMap.remove(name);
            overlay.remove(roi);
        }
        if (editingIndex != -1) {
            editingIndex = -1;
            addBn.setText("Add");
        }
        imp.deleteRoi();
        imp.setOverlay(overlay);
        hideCb.setSelected(false);
    }

}
