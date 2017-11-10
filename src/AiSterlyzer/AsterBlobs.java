/*
 * GPLv3
 */
package AiSterlyzer;

import ImageProcessing.AutoThresholder;
import ImageProcessing.BlobFilter;
import ImageProcessing.BlobFilterParam;
import ij.ImagePlus;
import ij.blob.Blob;
import ij.plugin.filter.BackgroundSubtracter;
import ij.plugin.filter.RankFilters;
import ij.process.ImageProcessor;

/**
 *
 * @author Xiao Zhou
 */
public class AsterBlobs extends BlobFilter {

    /**
     * Get aster blobs, the ImagePlus object will be changed.
     *
     * @param imagePlus a single channel ImagePlus object need to be processed.
     * @param excludeOnEdge whether to exclude blobs on edge.
     */
    public AsterBlobs(ImagePlus imagePlus, boolean excludeOnEdge) {
        super(imagePlus, excludeOnEdge, 0);
        filterParams.add(new BlobFilterParam(Blob.GETENCLOSEDAREA, 100, Double.MAX_VALUE));

        ImageProcessor ip = imp.getChannelProcessor();
        
        //Gaussian Blur to even background noise.
        ip.blurGaussian(5);
        
        //rollingBall method to substract background;
        BackgroundSubtracter bgs = new BackgroundSubtracter();
        bgs.rollingBallBackground(ip, 50, false, false, true, true, false);
        
        //apply median filter
        new RankFilters().rank(ip, 2, RankFilters.MEDIAN); 
        
        //apply autothresholder
        AutoThresholder.autoTheshold(imp, "Triangle");
        

        //find blobs
        filterAndSmoothBlobs();
    }

}
