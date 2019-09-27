package com.bfh.ticket;

public class BoundingBox {
    private final Point topLeft;
    private final Point bottomRight;

    public BoundingBox(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }
}
