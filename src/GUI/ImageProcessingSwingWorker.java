/*
 *GPLv3
 */
package GUI;

import ij.IJ;
import ij.gui.Roi;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author Xiao Zhou
 */
public abstract class ImageProcessingSwingWorker extends SwingWorker<Object, String> {

    protected volatile List<Roi> PickedRois;

    /**
     * Fire a PausedAt property change, and send fileName to event-dispatching
     * thread that catches this property change. This thread will be paused.
     *
     * @param fileName the fileName
     */
    public synchronized void pauseWorker(String fileName) {
        firePropertyChange("PausedAt", "", fileName);

        try {
            wait();
        } catch (InterruptedException ex) {
            publish("WARINING: processing is canceled");
        }

    }

    /**
     * Resume current thread with picked rois.
     *
     * @param pickedRois the picked rois send into this thread.
     */
    public synchronized void resumeWorker(ArrayList<Roi> pickedRois) {
        //firePropertyChange("ResumedWith", "", PickedRois);
        this.PickedRois = pickedRois;
        notify();
    }

    @Override
    protected void process(List<String> chunks) {
        chunks.stream().forEach((str) -> {
            IJ.log(str);
        });
    }
    /**
     * Process the image file in the SwingWorker thread.
     * @param filePath the file path of the image file that needs to be opened.
     */
    protected abstract void processImage(String filePath);
}
