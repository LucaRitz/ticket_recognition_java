package com.bfh.ticket;

public class MatcherOptions {
    public static double USE_DEFAULT_RATIO_TEST_THRESHOLD = -1;
    public static double USE_DEFAULT_SCORE_TEST_THRESHOLD = -1;
    public static double USE_DEFAULT_SCORE_THRESHOLD = -1;

    private final double ratioTestThreshold;
    private final double scoreTestThreshold;
    private final double scoreThreshold;

    public MatcherOptions() {
        this(USE_DEFAULT_RATIO_TEST_THRESHOLD, USE_DEFAULT_SCORE_TEST_THRESHOLD, USE_DEFAULT_SCORE_THRESHOLD);
    }

    public MatcherOptions(double ratioTestThreshold, double scoreTestThreshold, double scoreThreshold) {
        this.ratioTestThreshold = ratioTestThreshold;
        this.scoreTestThreshold = scoreTestThreshold;
        this.scoreThreshold = scoreThreshold;
    }

    public double getRatioTestThreshold() {
        return ratioTestThreshold;
    }

    public double getScoreTestThreshold() {
        return scoreTestThreshold;
    }

    public double getScoreThreshold() {
        return scoreThreshold;
    }
}
