package com.bfh.ticket;

/**
 * Two-dimensional Point on a TicketImage.
 */
public class Point {

    private final int x;
    private final int y;

    /**
     * Constructs a Point.
     * @param x X-Coordinate of the Point.
     * @param y Y-Coordinate of the Point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * X-Coordinate of the Point.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Y-Coordinate of the Point.
     * @return
     */
    public int getY() {
        return y;
    }
}
