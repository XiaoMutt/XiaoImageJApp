/*
 * GPLv3
 */
package XBlobAnalyzer;

import GUI.RoiPickUpWindow;
import bsh.EvalError;
import bsh.Interpreter;
import ij.IJ;
import java.io.IOException;
/**
 *
 * @author Xiao Zhou
 */
public class XSterRoiPickUpWindow extends RoiPickUpWindow {

    public XSterRoiPickUpWindow(String fileName, XSterlyzerWorker XSterlyzerWorker, int channel) {
        super(fileName, XSterlyzerWorker);
        try {
            Interpreter itp=new Interpreter();
            
            itp.set("fileName", fileName);
            itp.set("channel", channel);
            itp.source(XSterlyzerWorker.getBsFileName());

        } catch (EvalError | IOException ex) {
            IJ.log("BeanShell Script Error: "+ex.getMessage());
        }
    }



}
