/*
 * GPLv3
 */
package AiSpindle;

import ImageProcessing.BlobFilter;
import ImageProcessing.BlobFilterParam;
import ij.ImagePlus;
import ij.blob.Blob;
import ij.plugin.filter.RankFilters;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

/**
 *
 * @author Xiao Zhou
 */
public class DNABlobs extends BlobFilter {

    /**
     * 
     * @param imagePlus a single channel ImagePlus object need to be processed.
     */
    public DNABlobs(ImagePlus imagePlus) {
        super(imagePlus,false, 5);
        int area = imp.getProcessor().getWidth() * imp.getProcessor().getHeight();

        //DNA channel filter params;
        filterParams.add(new BlobFilterParam(Blob.GETCIRCULARITY, 4 * Math.PI, 600));
        filterParams.add(new BlobFilterParam(Blob.GETENCLOSEDAREA, Math.max(3e-4 * area, 100), Math.max(0.5 * area, 15)));
        ImageProcessor ip=imp.getChannelProcessor();
        new RankFilters().rank(ip, 3, RankFilters.MEDIAN);
        //apply autothresholder
        ImageProcessing.AutoThresholder.autoTheshold(imp, "Yen");      
        //RubberBandBaseLineCorrecter.CorrectBaseline(imp.getProcessor());         
        filterAndSmoothBlobs();
    }

}
