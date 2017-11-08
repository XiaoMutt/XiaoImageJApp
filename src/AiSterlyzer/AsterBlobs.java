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
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

/**
 *
 * @author Xiao Zhou
 */
public class AsterBlobs extends BlobFilter{
/**
 * Get aster blobs, the ImagePlus object will be changed.
 * @param imagePlus a single channel ImagePlus object need to be processed.
 * @param excludeOnEdge whether to exclude blobs on edge.
 */
    public AsterBlobs(ImagePlus imagePlus, boolean excludeOnEdge) {
        super(imagePlus, excludeOnEdge, 0);
        filterParams.add(new BlobFilterParam(Blob.GETENCLOSEDAREA, 100, Double.MAX_VALUE));
        imp.resetDisplayRange();//if not reset the ImageConverter will convert according to the display range.
        //convert to 8-bit;
        new ImageConverter(imp).convertToGray8();
        
        ImageProcessor ip=imp.getChannelProcessor();
        
        //rollingBall method to substract background, using smooth and sliding paraboloid, radius=50;
        BackgroundSubtracter bgs = new BackgroundSubtracter();
        bgs.rollingBallBackground(ip, 100, false, false, false, true, false);
        //apply median filter
        new RankFilters().rank(ip, 2, RankFilters.MEDIAN);     
        //apply autothresholder
        AutoThresholder.autoTheshold(imp, "Li");
        //find blobs
        filterAndSmoothBlobs();        
    }

}
