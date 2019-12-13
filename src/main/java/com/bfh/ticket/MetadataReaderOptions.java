package com.bfh.ticket;

public class MetadataReaderOptions {
    public static double USE_DEFAULT_RATIO_THRESHOLD = -1;
    public static String USE_DEFAULT_LANGUAGE = "";

    private final double ratioTestThreshold;
    private final String language;

    public MetadataReaderOptions() {
        this(USE_DEFAULT_RATIO_THRESHOLD, USE_DEFAULT_LANGUAGE);
    }

    public MetadataReaderOptions(double ratioTestThreshold, String language) {
        this.ratioTestThreshold = ratioTestThreshold;
        this.language = language != null ? language : "";
    }

    public double getRatioTestThreshold() {
        return ratioTestThreshold;
    }

    public String getLanguage() {
        return language;
    }
}
