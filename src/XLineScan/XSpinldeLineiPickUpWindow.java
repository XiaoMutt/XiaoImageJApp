/*
 * GPLv3
 */

package XLineScan;

import AiSpindle.PointPair;
import AiSpindle.SpindleContourAnalyzer;
import AiSpindle.SpindleIdentifier;
import GUI.RoiPickUpWindow;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Line;
import ij.gui.Overlay;
import ij.gui.Toolbar;
import java.util.List;

/**
 *
 * @author Xiao Zhou
 */
public class XSpinldeLineiPickUpWindow extends RoiPickUpWindow {
    private final int mtChannel;
    private final int dnaChannel;
    
    public XSpinldeLineiPickUpWindow(String fileName, XLineScanWorker lineScanWorker, boolean autoDetection, int mtChannel, int dnaChannel) {
        super(fileName, lineScanWorker);
        IJ.setTool(Toolbar.LINE);        
        this.mtChannel=mtChannel;
        this.dnaChannel=dnaChannel;
        if (autoDetection) {
            Overlay overlay = autoDetection(imp);
            imp.setOverlay(overlay);
        }        
    }

    private Overlay autoDetection(ImagePlus imp) {
        SpindleIdentifier si = new SpindleIdentifier(imp, mtChannel, dnaChannel);
        Overlay overlay = new Overlay();
        List<SpindleContourAnalyzer> spindles = si.getSpindles();
        int i=0;
        for (SpindleContourAnalyzer s : spindles) {
            if (s.getSpindleType() == SpindleContourAnalyzer.SINGLE_MT_SINGLE_DNA) {
                PointPair pp = s.getMainMtAxis();
                overlay.add(new Line(pp.A.x, pp.A.y, pp.B.x, pp.B.y), Integer.toString(i));
                i++;
            }
        }

        return overlay;
    }


}
