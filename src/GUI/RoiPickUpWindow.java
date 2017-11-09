/*
 * GPLv3
 */
package GUI;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ImageCanvas;
import ij.gui.StackWindow;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Xiao Zhou
 */
public abstract class RoiPickUpWindow extends StackWindow {
    
    protected final RoiListManager roiList;
    protected final ImageProcessingSwingWorker ipsw;
    
    public RoiPickUpWindow(String fileName, ImageProcessingSwingWorker worker) {
        super(IJ.openImage(fileName));
        this.ipsw = worker;
        roiList = new RoiListManager(this);
        ic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ic.mouseClicked(e);
                roiList.selectRoi(imp.getRoi());
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                ic.mousePressed(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                ic.mouseReleased(e);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                ic.mouseEntered(e);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                ic.mouseExited(e);
            }
        });
        
    }
    
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        ipsw.resumeWorker(roiList.getRois());
        roiList.dispose();
        this.close();
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        roiList.setLocation(this.getLocation().x + this.getWidth(), this.getLocation().y);
        roiList.setVisible(true);
    }
    
    public void canel() {
        roiList.dispose();
        this.close();
        ipsw.resumeWorker(null);
    }
    
}
