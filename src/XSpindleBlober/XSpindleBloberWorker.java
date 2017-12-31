/*
 * GPLv3
 */
package XSpindleBlober;


import FileManager.FileIterator;
import GUI.ImageProcessingSwingWorker;
import ImageProcessing.ImageProcessor;
import ij.gui.Roi;
import java.io.File;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Xiao Zhou
 */
public class XSpindleBloberWorker extends ImageProcessingSwingWorker {

    private String OpenFolder;
    private int mtChannelBackgroundValue;
    private int dnaChannelBackgroundValue;    
    private int mtchannel;
    private int dnachannel;

    public int getMtchannel() {
        return mtchannel;
    }

    public int getDnachannel() {
        return dnachannel;
    }

    public void setMtchannel(int mtchannel) {
        this.mtchannel = mtchannel;
    }

    public void setDnachannel(int dnachannel) {
        this.dnachannel = dnachannel;
    }

    private int RollingBallRadius;
    private boolean UsingRollingBall;
    private boolean ManualROI;

    private Pattern FileNameRegex;
    private boolean useAutoDetection;

    public void setUseAutoDetection(boolean useAutoDetection) {
        this.useAutoDetection = useAutoDetection;
    }

    public boolean getUseAutoDetection() {
        return this.useAutoDetection;
    }

    public void setOpenFolder(String fileFolder) {
        OpenFolder = fileFolder;
    }



    public void setUsingRollingBall(boolean usingRollingBall) {
        UsingRollingBall = usingRollingBall;
    }

    public void setManualROI(boolean manualROI) {
        ManualROI = manualROI;
    }

    public void setRollingBallRadius(int radius) {
        RollingBallRadius = radius;
    }



    public void setFileNameRegex(String fileNameRegex) {
        FileNameRegex = Pattern.compile(fileNameRegex);
    }
    
    public void setmtChannelBackgroundValue(int backgroundValue) {
        mtChannelBackgroundValue = backgroundValue;
    }

    public void setDenominatorBackgroundValue(int backgroundValue) {
        dnaChannelBackgroundValue = backgroundValue;
    }


    @Override
    protected void done() {
        //TODO
    }

    @Override
    protected void processImage(String filePath) {
        ImageProcessor xip = new ImageProcessor(filePath);
        publish("INFO: processing " + filePath);

        //Subtract background;
        //Subtract background;
        if (UsingRollingBall) {
            xip.subtractBackgroundByRollingBall(RollingBallRadius, mtchannel);
            xip.subtractBackgroundByRollingBall(RollingBallRadius, dnachannel);
        } else {
            xip.subtractCertainBackground(mtChannelBackgroundValue, mtchannel);
            xip.subtractCertainBackground(dnaChannelBackgroundValue, dnachannel);
        }
        //Obtain ROIs;
        Roi[] rois = null;
        if (!ManualROI) {
            rois = xip.obtainROIs();
        }
        if (rois == null) {

            publish("INFO: manually choose ROI");
            pauseWorker(filePath);

            if ((rois = PickedRois.toArray(new Roi[PickedRois.size()])) != null) {
                publish("INFO: " + rois.length + (rois.length < 2 ? " ROI is" : " ROIs are") + " added");
                xip.saveROIs(rois, filePath);
            } else {
                publish("INFO: no ROIs were found; skip this image");
            }
        }

        if (rois != null) {
            //Process ROIs
            for (Roi roi : rois) {
                //TODO: save images
            }
        }
    }

    @Override
    protected Void doInBackground() {
        String runTag = java.util.UUID.randomUUID().toString();

        String workingFilePath;
        FileIterator xfi = new FileIterator(OpenFolder);

        while ((workingFilePath = xfi.nextFilePath()) != null && !isCancelled()) {
            //check name regex;
            File file = new File(workingFilePath);
            Matcher matcher = FileNameRegex.matcher(file.getName());
            if (matcher.matches()) {
                processImage(workingFilePath);
            }
        }
        return null;



    }

}
