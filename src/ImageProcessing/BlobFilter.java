/*
 * GPLv3
 * use ij-blob
 * for documents of ij-blob see:
 * http://www.atetric.com/atetric/javadoc/de.biomedical-imaging.ij/ij_blob/1.4.8/
 */
package ImageProcessing;

import AiSpindle.FullPolygon;
import ij.ImagePlus;
import ij.blob.Blob;
import ij.blob.ManyBlobs;
import ij.process.ImageProcessor;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xiao Zhou
 */
public abstract class BlobFilter {

    protected final List<BlobFilterParam> filterParams;
    protected final int perceptualThreshold;
    protected final ImagePlus imp;
    protected final List<Polygon> polygons;
    protected final boolean excludeOnEdge;

    /**
     *
     * @param imagePlus ImagePlus Object that need to be processed for
     * extracting Blobs. The background must be black. This imagePlus will be
     * modified.
     * @param excludeOnEdge whether to exclude structures touching the edges of
     * the image.
     * @param perceptualThreshold the threshold used by FFT to smooth contour.
     * Set to 0 if no smooth is needed.
     */
    public BlobFilter(ImagePlus imagePlus, boolean excludeOnEdge, int perceptualThreshold) {
        imp = imagePlus;
        this.excludeOnEdge = excludeOnEdge;
        this.perceptualThreshold = perceptualThreshold;
        //imp.show();

        filterParams = new ArrayList<>();
        polygons = new ArrayList<>();

    }

    /**
     * Filter and Smooth the blobs extracted from the input ImagePlus
     */
    public final void filterAndSmoothBlobs() {
        if (!polygons.isEmpty()) {
            return;//ready processed.
        }
        ManyBlobs blobs = new ManyBlobs(imp);
        blobs.setBackground(0);
        blobs.findConnectedComponents();
        for (BlobFilterParam param : filterParams) {
            blobs = blobs.filterBlobs(param.Min, param.Max, param.FilterMethod);
        }

        if (excludeOnEdge) {
            List<Blob> onEdges = new ArrayList<>();
            ImageProcessor ip = imp.getProcessor();
            for (Blob b : blobs) {
                if (b.isOnEdge(ip)) {
                    onEdges.add(b);
                }
            }
            blobs.removeAll(onEdges);
        }

        for (Blob b : blobs) {
            if (perceptualThreshold == 0) {
                polygons.add(b.getOuterContour());
            } else {
                polygons.add(new FullPolygon(b.getOuterContour()).getSmoothedContour(perceptualThreshold));
            }
        }
    }

    /**
     *
     * @return the outer contour polygons of the final filtered and smoothed
     * polygons.
     */
    public List<Polygon> getOuterContours() {
        return polygons;
    }

}
