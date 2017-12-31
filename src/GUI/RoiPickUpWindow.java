/*
 * GPLv3
 */
package GUI;

import ij.IJ;
import ij.gui.Roi;
import ij.gui.StackWindow;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Xiao Zhou
 */
public abstract class RoiPickUpWindow extends StackWindow {
    
    protected final RoiListManager rlm;
    protected final ImageProcessingSwingWorker ipsw;
    
    public RoiPickUpWindow(String fileName, ImageProcessingSwingWorker worker) {
        super(IJ.openImage(fileName));
        this.ipsw = worker;
        rlm = new RoiListManager(this);
        ic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ic.mouseClicked(e);

            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                ic.mousePressed(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                ic.mouseReleased(e);
                Roi roi=imp.getRoi();
                if(roi!=null)
                rlm.selectRoiInDisplayList(roi.getName());                
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
        
        ic.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_DELETE){
                    rlm.deleteRois();
                }   
            }

            @Override
            public void keyReleased(KeyEvent e) {
             

            }
        });
        
    }
    
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        ipsw.resumeWorker(rlm.getRois());
        rlm.dispose();
        this.close();
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        rlm.setLocation(this.getLocation().x + this.getWidth(), this.getLocation().y);
        rlm.setVisible(true);
    }
    
    public void canel() {
        rlm.dispose();
        this.close();
        ipsw.resumeWorker(null);
    }
    
}
