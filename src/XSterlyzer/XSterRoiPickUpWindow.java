/*
 * GPLv3
 */
package XSterlyzer;

import AiSterlyzer.AsterIdentifier;
import GUI.RoiPickUpWindow;
import ij.ImagePlus;
import ij.gui.Overlay;
import ij.gui.PolygonRoi;
import ij.gui.Roi;
import java.awt.Polygon;
import java.util.List;

/**
 *
 * @author Xiao Zhou
 */
public class XSterRoiPickUpWindow extends RoiPickUpWindow {

    public XSterRoiPickUpWindow(String fileName, XSterlyzerWorker XSterlyzerWorker, int mtChannel) {
        super(fileName, XSterlyzerWorker);
        Overlay overlay = autoDetection(imp, mtChannel);
        imp.setOverlay(overlay);
    }

    private Overlay autoDetection(ImagePlus imp, int mtChannel) {
        AsterIdentifier ai = new AsterIdentifier(imp, mtChannel, true);
        Overlay overlay = new Overlay();
        List<Polygon> asters = ai.getAsterPolygons();
        int i = 0;
        for (Polygon s : asters) {
            overlay.add(new PolygonRoi(s, Roi.POLYGON), Integer.toString(i));
            i++;
        }
        return overlay;
    }

}
