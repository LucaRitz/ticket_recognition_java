package com.bfh.ticket;

/**
 * All available implementations of Algorithms used for Matcher and MetadataReader.
 */
public enum Algorithm {
    /**
     * Algorithm that uses SIFT (scale-invariant feature transform) keypoints and descriptors.
     */
    SIFT,
    /**
     * Algorithm that uses ORB (Oriented FAST and Rotated BRIEF) keypoints and descriptors.
     */
    ORB
}
