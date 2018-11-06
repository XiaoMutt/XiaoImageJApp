/*
 * GPLv3
 */
package AiSpindle;

import ImageProcessing.BlobFilterParam;
import ImageProcessing.BlobFilter;
import ij.ImagePlus;
import ij.blob.Blob;
import ij.plugin.filter.RankFilters;
import ImageProcessing.AutoThresholder;
import ij.process.ImageProcessor;

/**
 *
 * @author Xiao Zhou
 */
public class MicrotubuleBlobs extends BlobFilter {

    /**
     *
     * @param imagePlus a single channel ImagePlus object need to be processed.
     */
    public MicrotubuleBlobs(ImagePlus imagePlus) {
        super(imagePlus, false, 2);
        int area = imp.getProcessor().getWidth() * imp.getProcessor().getHeight();
        //microtubule channel filter params;
        filterParams.add(new BlobFilterParam(Blob.GETCIRCULARITY, 4 * Math.PI, 100));
        filterParams.add(new BlobFilterParam(Blob.GETENCLOSEDAREA, Math.max(0.0005 * area, 10), Math.max(0.9 * area, 20)));
        ImageProcessor ip = imp.getChannelProcessor();
        new RankFilters().rank(ip, 3, RankFilters.MEDIAN);
        //apply autothresholder
        AutoThresholder.autoThreshold(imp, "Yen");
        //RubberBandBaseLineCorrecter.CorrectBaseline(imp.getProcessor());          
        filterAndSmoothBlobs();
    }

}
