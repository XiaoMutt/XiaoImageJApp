/*
 * GPL 3.0
 */
package AiSterlyzer;

import ij.ImagePlus;
import ij.ImageStack;
import java.awt.Polygon;
import java.util.List;

/**
 * Process the ImagePlus Object and identify asters.
 * The ImagePlus object will not be changed.
 * 
 * @author Xiao Zhou
 */
public class AsterIdentifier {

    private final AsterBlobs asterBlobs;

    public AsterIdentifier(ImagePlus imagePlus, int channel, boolean excludeOnEdge) {

        ImageStack imageStack = imagePlus.getImageStack();
        ImagePlus mtImp = new ImagePlus("mt", imageStack.getProcessor(channel).duplicate());
        asterBlobs = new AsterBlobs(mtImp, excludeOnEdge);
    }

    public List<Polygon> getAsterPolygons() {
        return asterBlobs.getOuterContours();
    }

}
