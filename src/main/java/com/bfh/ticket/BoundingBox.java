package com.bfh.ticket;

/**
 * Rectangular bounding box on a TicketImage.
 */
public class BoundingBox {

    private final Point topLeft;
    private final Point bottomRight;

    /**
     * Constructs a BoundingBox limited by the given top-left and bottom-right corner coordinates.
     * @param topLeft Point/Coordinate of the top-left corner.
     * @param bottomRight Point/Coordinate of the bottom-right corner.
     * @throws CtiException if the rectangle is invaild.
     */
    public BoundingBox(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Point of the top-left corner.
     * @return
     */
    public Point getTopLeft() {
        return topLeft;
    }

    /**
     * Point of the bottom-right corner.
     * @return
     */
    public Point getBottomRight() {
        return bottomRight;
    }
}
