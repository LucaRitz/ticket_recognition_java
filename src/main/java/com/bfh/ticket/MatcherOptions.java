package com.bfh.ticket;

/**
 * Available options for Matcher.
 */
public class MatcherOptions {

    private static double USE_DEFAULT_RATIO_TEST_THRESHOLD = -1;
    private static double USE_DEFAULT_SCORE_TEST_THRESHOLD = -1;
    private static double USE_DEFAULT_SCORE_THRESHOLD = -1;

    private final double ratioTestThreshold;
    private final double scoreTestThreshold;
    private final double scoreThreshold;

    /**
     * Constructs options with the default values.
     */
    public MatcherOptions() {
        this(USE_DEFAULT_RATIO_TEST_THRESHOLD, USE_DEFAULT_SCORE_TEST_THRESHOLD, USE_DEFAULT_SCORE_THRESHOLD);
    }

    /**
     * Constructs options with the given values.
     * @param ratioTestThreshold Required distance ratio between nearest and second nearest neighbour of a keypoint when matching.
     * @param scoreTestThreshold Required score ratio between best and second best TicketMatch in order for the best match to be confirmed.
     * @param scoreThreshold Minimum score required for a TicketMatch to be considered.
     */
    public MatcherOptions(double ratioTestThreshold, double scoreTestThreshold, double scoreThreshold) {
        this.ratioTestThreshold = ratioTestThreshold;
        this.scoreTestThreshold = scoreTestThreshold;
        this.scoreThreshold = scoreThreshold;
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
     * Required score ratio between best and second best TicketMatch in order for the best match to be confirmed.
     * E.g. a value of 0.5 means that the best match must have a score that is 2x higher
     * than the second best matches score.
     * @return
     */
    public double getScoreTestThreshold() {
        return scoreTestThreshold;
    }

    /**
     * Minimum score required for a TicketMatch to be considered. Any TicketMatch with a score lower than
     * this threshold is discarded. This is important in order for situations where an unknown ticket is provided
     * and there is no trained Ticket that matches, although some of them may be considered matches by the algorithm.
     * @return
     */
    public double getScoreThreshold() {
        return scoreThreshold;
    }
}
