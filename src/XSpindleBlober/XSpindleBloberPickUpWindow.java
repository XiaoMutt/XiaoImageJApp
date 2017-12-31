package XSpindleBlober;

import AiSpindle.FullPolygon;
import AiSpindle.SpindleContourAnalyzer;
import AiSpindle.SpindleIdentifier;
import GUI.ImageProcessingSwingWorker;
import GUI.RoiPickUpWindow;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Overlay;
import ij.gui.PolygonRoi;
import ij.gui.Roi;
import ij.gui.Toolbar;
import java.util.List;

/**
 *
 * @author xiaozhou
 */
public class XSpindleBloberPickUpWindow extends RoiPickUpWindow {

    private final int mtChannel;
    private final int dnaChannel;

    public XSpindleBloberPickUpWindow(String fileName, ImageProcessingSwingWorker worker, boolean autoDetection, int mtChannel, int dnaChannel) {
        super(fileName, worker);
        IJ.setTool(Toolbar.POLYGON);
        this.mtChannel = mtChannel;
        this.dnaChannel = dnaChannel;
        if (autoDetection) {
            Overlay overlay = autoDetection(imp);
            imp.setOverlay(overlay);
        }
    }

    private Overlay autoDetection(ImagePlus imp) {
        SpindleIdentifier si = new SpindleIdentifier(imp, mtChannel, dnaChannel);
        Overlay overlay = new Overlay();
        List<SpindleContourAnalyzer> spindles = si.getSpindles();
        int i = 0;
        for (SpindleContourAnalyzer b : spindles) {
            for (FullPolygon mt : b.mtFullPolygons) {
                overlay.add(new PolygonRoi(mt.getPolygon(), Roi.POLYGON), "Spindle" + Integer.toString(i));
            }
            for (FullPolygon dna : b.dnaFullPolygons) {
                overlay.add(new PolygonRoi(dna.getPolygon(), Roi.POLYGON), "DNA" + Integer.toString(i));
            }
            i++;
        }

        return overlay;
    }

}
