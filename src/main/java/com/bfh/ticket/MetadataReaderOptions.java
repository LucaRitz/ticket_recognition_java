package com.bfh.ticket;

/**
 * Available options for MetadataReader.
 */
public class MetadataReaderOptions {

    private static double USE_DEFAULT_RATIO_THRESHOLD = -1;
    private static String USE_DEFAULT_LANGUAGE = "";

    private final double ratioTestThreshold;
    private final String language;

    /**
     * Constructs options with default values.
     */
    public MetadataReaderOptions() {
        this(USE_DEFAULT_RATIO_THRESHOLD, USE_DEFAULT_LANGUAGE);
    }

    /**
     * Constructs options with the given values.
     * @param ratioTestThreshold Required distance ratio between nearest and second nearest neighbour of a keypoint when matching.
     * @param language Tesseract language file(s) to be used. Multiple files can be concatenated by a "+", like "deu+eng".
     */
    public MetadataReaderOptions(double ratioTestThreshold, String language) {
        this.ratioTestThreshold = ratioTestThreshold;
        this.language = language != null ? language : USE_DEFAULT_LANGUAGE;
    }

    /**
     * Required distance ratio between nearest and second nearest neighbour of a keypoint when matching.
     * A better and more elaborate description can be found in David G. Lowe's paper
     * "Distinctive Image Features from Scale-Invariant Keypoints".
     * @return
     */
    public double getRatioTestThreshold() {
        return ratioTestThreshold;
    }

    /**
     * Tesseract language file(s) to be used. Multiple files can be concatenated by a "+", like "deu+eng"
     * @return
     */
    public String getLanguage() {
        return language;
    }
}
