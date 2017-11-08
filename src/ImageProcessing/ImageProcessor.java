/*
 * GPLv3
 */

package ImageProcessing;

import ij.ImagePlus;
import ij.IJ;
import ij.gui.Overlay;
import ij.gui.ProfilePlot;
import ij.gui.Roi;
import ij.io.FileSaver;
import ij.plugin.filter.BackgroundSubtracter;
import ij.process.ImageStatistics;

/**
 * Process images using IJ class methods:
 * The original image will not be changed.
 *
 * @author Xiao Zhou
 */
public class ImageProcessor {

    private final ImagePlus imagePlus;
    private final ImagePlus originalImp;
    private final boolean[] backgroundProcessed;
    private double roiLineWidth;
    private final Overlay imageOverlay;

    public ImageProcessor(String fileName) {
        originalImp = IJ.openImage(fileName);
        imagePlus=originalImp.duplicate();
        imageOverlay = new Overlay();
        int n = imagePlus.getNChannels();
        backgroundProcessed = new boolean[n];
        for (int i = 0; i < n; i++) {
            backgroundProcessed[i] = false;
        }
    }

    public void setROILineWidth(double width) {
        roiLineWidth = width;
    }

    public boolean subtractBackgroundByRollingBall(int radius, int channel) {
        if (backgroundProcessed[channel - 1] == false && radius > 0) {
            imagePlus.setC(channel);
            BackgroundSubtracter bgs = new BackgroundSubtracter();
            bgs.rollingBallBackground(imagePlus.getProcessor(), radius, false, false, false, false, false);
            backgroundProcessed[channel - 1] = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean subtractCertainBackground(int backgroundValue, int channel) {
        if (backgroundProcessed[channel - 1] == false && backgroundValue > 0) {
            imagePlus.setC(channel);
            imagePlus.getProcessor().subtract(backgroundValue);
            backgroundProcessed[channel - 1] = true;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Return an array of Roi that are present in the overlay of the image or
     * null if there is none
     *
     * @return an array of Roi present in the overlay of the image or null if
     * there is none
     */
    public Roi[] obtainROIs() {
        Overlay oly = imagePlus.getOverlay();
        Roi[] result;
        if (oly != null) {
            result = oly.toArray();
            if (result.length == 0) {
                result = null;
            }
        } else {
            result = null;
        }
        return result;
    }

    public int obtainChannelCount() {
        return imagePlus.getNChannels();
    }

    /**
     * Return intensity profile of the line ROI or null if roi is null or roi is
     * not line roi
     *
     * @param roi Line Roi
     * @param channel The index of the channel need to be measured
     * @return intensity profile of the line ROI or null if roi is null or roi
     * is not line roi
     */
    public double[] obtainIntensityProfile(Roi roi, int channel) {
        int n = imagePlus.getNChannels();
        if (channel > n || channel < 1) {
            return null;
        } else {
            imagePlus.setC(channel);
        }

        if (roi != null && roi.isLine()) {
            roi.setStrokeWidth(roiLineWidth);
            imagePlus.setRoi(roi);
            ProfilePlot pfp = new ProfilePlot(imagePlus);
            return pfp.getProfile();
        } else {
            return null;
        }
    }

    /**
     * Return the length of a line ROI
     *
     * @param roi The ROI need to be measured
     * @return the length of a line ROI or 0 if the input ROI is null or not a
     * line ROI
     */
    public double obtainLength(Roi roi) {
        if (roi != null && roi.isLine()) {
            return roi.getLength();
        } else {
            return 0;
        }
    }
    
    
    public ImageStatistics obtainMeasurements(Roi roi){
        imagePlus.setRoi(roi);
        return ImageStatistics.getStatistics(imagePlus.getProcessor());
    }
    /**
     * Save rois onto the image into a file with the given file name.
     * @param rois rois needs to be saved.
     * @param fileName the given file name.
     */

    public void saveROIs(Roi[] rois, String fileName) {
        for (Roi roi : rois) {
            imageOverlay.add(roi);
        }
        originalImp.setOverlay(imageOverlay);
        FileSaver fs = new FileSaver(originalImp);

        String ext = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) {
            ext = fileName.substring(i + 1);
        }
        switch (ext.toLowerCase()) {
            case "bmp":
                fs.saveAsBmp(fileName);
                break;
            case "fits":
                fs.saveAsFits(fileName);
                break;
            case "gif":
                fs.saveAsGif(fileName);
                break;
            case "jpg":
            case "jpeg":
                fs.saveAsJpeg(fileName);
                break;
            case "lut":
                fs.saveAsLut(fileName);
                break;
            case "png":
                fs.saveAsPng(fileName);
                break;
            case "raw":
                fs.saveAsRaw(fileName);
                break;
            case "pgm":
                fs.saveAsPgm(fileName);
                break;
            case "tif":
            case "tiff":
                fs.saveAsTiff(fileName);
                break;
            case "zip":
                fs.saveAsZip(fileName);
                break;

        }

    }

}
