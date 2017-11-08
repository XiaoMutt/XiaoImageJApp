/*
 * GPLv3
 */
package XLineScan;

import FileManager.FileIterator;
import GUI.ImageProcessingSwingWorker;
import ImageProcessing.ImageProcessor;
import ij.gui.Roi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Xiao Zhou
 */
public class XLineScanWorker extends ImageProcessingSwingWorker {

    private String OpenFolder;
    private int NumeratorChannelIndex;
    private int DenominatorChannelIndex;
    private int NumeratorBackgroundValue;
    private int DenominatorBackgroundValue;
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
    private int NormalizedLength;
    private Pattern FileNameRegex;
    private int ROILineWidth;
    private boolean useAutoDetection;

    private XLineDataSaver DataSaver;
    private double[][] XMean;//0 is Normalized Numerator Values; 1 is Normalized Denominator Values; 2 is Normalized Ratio Values;
    private double[][] Xi2Sum;//0 is Normalized Numerator Values; 1 is Normalized Denominator Values; 2 is Normalized Ratio Values;
    private double[][] SD;//0 is Normalized Numerator Values; 1 is Normalized Denominator Values; 2 is Normalized Ratio Values;

    public void setUseAutoDetection(boolean useAutoDetection) {
        this.useAutoDetection = useAutoDetection;
    }

    public boolean getUseAutoDetection() {
        return this.useAutoDetection;
    }

    public void setOpenFolder(String fileFolder) {
        OpenFolder = fileFolder;
    }

    public void setNumeratorChannelIndex(int channel) {
        NumeratorChannelIndex = channel;
    }

    public void setDenomiatorChannelIndex(int channel) {
        DenominatorChannelIndex = channel;
    }

    public void setNumeratorBackgroundValue(int backgroundValue) {
        NumeratorBackgroundValue = backgroundValue;
    }

    public void setDenominatorBackgroundValue(int backgroundValue) {
        DenominatorBackgroundValue = backgroundValue;
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

    public void setNormalizedLength(int normalizedLength) {
        NormalizedLength = normalizedLength;
    }

    public void setFileNameRegex(String fileNameRegex) {
        FileNameRegex = Pattern.compile(fileNameRegex);
    }

    public void setROILineWidth(int roiLineWidth) {
        ROILineWidth = roiLineWidth;
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
    protected void processImage(String filePath) {
        ImageProcessor xip = new ImageProcessor(filePath);
        xip.setROILineWidth(ROILineWidth);
        publish("INFO: processing " + filePath);

        //Subtract background;
        if (UsingRollingBall) {
            xip.subtractBackgroundByRollingBall(RollingBallRadius, NumeratorChannelIndex);
            xip.subtractBackgroundByRollingBall(RollingBallRadius, DenominatorChannelIndex);
        } else {
            xip.subtractCertainBackground(NumeratorBackgroundValue, NumeratorChannelIndex);
            xip.subtractCertainBackground(DenominatorBackgroundValue, DenominatorChannelIndex);
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
                double[] nprofile = xip.obtainIntensityProfile(roi, NumeratorChannelIndex);
                double[] dprofile = xip.obtainIntensityProfile(roi, DenominatorChannelIndex);
                double length = xip.obtainLength(roi);

                //calculate and normalize
                XLineRatioNormalizer xlrn = new XLineRatioNormalizer(nprofile, dprofile, NormalizedLength);
                if (xlrn.isThereInfinityWarning()) {
                    publish("WARNING: infinity values resulted from calculation");
                }
                //save data;
                double[][] normalized_profiles = new double[XMean.length][]; //0 is Normalized Numerator Values; 1 is Normalized Denominator Values; 2 is Normalized Ratio Values;

                normalized_profiles[0] = xlrn.getNormalizedNumeratorData();
                normalized_profiles[1] = xlrn.getNormalizedDenominatorData();

                normalized_profiles[2] = xlrn.getNormalizedRatios();
                double[][] datasets = {nprofile, dprofile, xlrn.getRawRatios(), normalized_profiles[0], normalized_profiles[1], normalized_profiles[2], new double[]{length}};
                try {
                    DataSaver.saveDataToExcel(datasets);
                    for (int n = 0; n < XMean.length; n++) {
                        for (int i = 0; i < NormalizedLength; i++) {
                            Xi2Sum[n][i] += normalized_profiles[n][i] * normalized_profiles[n][i];
                            XMean[n][i] = (XMean[n][i] * (DataSaver.getRowCount() - 1) + normalized_profiles[n][i]) / (DataSaver.getRowCount());
                        }
                    }
                    publish("INFO: data saved to DataSet" + DataSaver.getRowCount());
                } catch (IOException ex) {
                    publish("ERROR: cannot save data");
                }
            }
        }
    }

    @Override
    protected XLineScanResult doInBackground() {
        String runTag = java.util.UUID.randomUUID().toString();
        try {
            DataSaver = new XLineDataSaver(OpenFolder + File.separator + runTag + "result.xlsx");
            //0 is Normalized Numerator Values; 1 is Normalized Denominator Values; 2 is Normalized Ratio Values;
            XMean = new double[3][NormalizedLength];
            Xi2Sum = new double[3][NormalizedLength];
            SD = new double[3][NormalizedLength];

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

        for (int n = 0; n < XMean.length; n++) {

            for (int i = 0; i < NormalizedLength; i++) {
                SD[n][i] = Math.sqrt((Xi2Sum[n][i] - XMean[n][i] * XMean[n][i] * DataSaver.getRowCount()) / DataSaver.getRowCount());
            }
        }
        DataSaver.saveNormalizedMeanAndSD(XMean, SD);
        XLineScanResult results = new XLineScanResult(NormalizedLength);
        results.setNumeratorProfile(XMean[0], SD[0]);
        results.setDenominatorProfile(XMean[1], SD[1]);
        results.setRaitoProfile(XMean[2], SD[2]);
        try {
            DataSaver.close();
            publish("INFO: processing completed");

        } catch (IOException ex) {
            publish("ERROR: cannot save the result file");
        }
        return results;

    }

}
