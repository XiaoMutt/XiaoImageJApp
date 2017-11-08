/*
 * GPLv3
 */

package GUI;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.Overlay;
import ij.gui.StackWindow;

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
