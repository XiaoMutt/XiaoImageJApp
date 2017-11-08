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

    private final int mtChannel;

    public XSterRoiPickUpWindow(String fileName, XSterlyzerWorker XSterlyzerWorker, int mtChannel) {
        super(fileName, XSterlyzerWorker, true);
        this.mtChannel = mtChannel;
    }

    @Override
    protected Overlay autoDetection(ImagePlus imp) {
        AsterIdentifier ai = new AsterIdentifier(imp, mtChannel, true);
        Overlay overlay = new Overlay();
        List<Polygon> asters = ai.getAsterPolygons();
        int i = 0;
        for (Polygon s : asters) {
            PolygonRoi pr = new PolygonRoi(s, Roi.POLYGON);
            pr.setName(Integer.toString(i));
            overlay.add(pr);
            i++;
        }
        return overlay;
    }

}
