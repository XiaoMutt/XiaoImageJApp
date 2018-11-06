/*
 * GPLv3
 */
package XBlobAnalyzer;

import FileManager.FileIterator;
import GUI.ImageProcessingSwingWorker;
import ImageProcessing.ImageProcessor;
import ij.gui.Roi;
import ij.process.ImageStatistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Xiao Zhou
 */
public class XSterlyzerWorker extends ImageProcessingSwingWorker {

    private String OpenFolder;
    private int Channel;
    private boolean IgnoreROI;
    private XSterlyzerDataSaver DataSaver;
    private Pattern FileNameRegex;
    private String bsFileName;

    public String getBsFileName() {
        return bsFileName;
    }

    public void setBsFileName(String bsFileName) {
        this.bsFileName = bsFileName;
    }
    
    public void setIgnoreROI(boolean IgnoreROI) {
        this.IgnoreROI = IgnoreROI;
    }

    public void setOpenFolder(String OpenFolder) {
        this.OpenFolder = OpenFolder;
    }


    public void setChannel(int channel) {
        this.Channel = channel;
    }

    public void setPickedRois(ArrayList<Roi> pickedRois) {
        this.PickedRois = pickedRois;
    }




    public void setFileNameRegex(String fileNameRegex) {
        FileNameRegex = Pattern.compile(fileNameRegex);
    }

    @Override
    protected void processImage(String filePath) {
        ImageProcessor xip = new ImageProcessor(filePath);
        publish("INFO: processing " + filePath);

        //Obtain ROIs;
        Roi[] rois = xip.obtainROIs();

        if (rois == null || IgnoreROI) {
            publish("INFO: choose ROI");
            pauseWorker(filePath);
            if ((rois = PickedRois.toArray(new Roi[PickedRois.size()])) != null) {
                publish("INFO: " + rois.length + (rois.length < 2 ? " ROI is" : " ROIs are") + " added");
                xip.saveROIs(rois, filePath);
            } else {
                publish("INFO: image skipped");
            }
        }

        if (rois != null) {
            //process rois;
            double[][] areaNmean = new double[rois.length][2];
            for (int i = 0; i < rois.length; i++) {
                ImageStatistics is = xip.obtainMeasurements(rois[i]);
                areaNmean[i][0] = is.area;
                areaNmean[i][1] = is.mean;
            }
            try {
                DataSaver.saveAreaAndMean(areaNmean);
                DataSaver.saveAsterCount(rois.length);
            } catch (IOException ex) {
                publish("ERROR: cannot save data");
            }
        }
    }

    public int getChannel() {
        return Channel;
    }

    @Override
    protected void done() {
        try {
            if (DataSaver != null) {
                //the done may be called several times during cancel or normal finish. This is to make sure the DataSaver is closed();
                DataSaver.close();
            }
        } catch (IOException ex) {
            publish("ERROR: cannot save the result file");
        }
    }

    @Override
    protected Void doInBackground() {
        String runTag = java.util.UUID.randomUUID().toString();
        try {
            DataSaver = new XSterlyzerDataSaver(OpenFolder + File.separator + runTag + "-Result.xlsx");
        } catch (FileNotFoundException ex) {
            publish("ERROR: cannot create the file to save results");
            return null;
        }

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
