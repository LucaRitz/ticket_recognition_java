package com.bfh.ticket;

public class TicketImage {
    private final String imagePath;
    private final int width;
    private final int height;
    private final int bytesPerPixel;
    private final int bytesPerLine;
    private final byte[] image;

    public TicketImage(String imagePath) {
        this(imagePath, 0, 0, 0, 0, new byte[0]);
    }

    public TicketImage(int width, int height, int bytesPerPixel, int bytesPerLine, byte[] image) {
        this(null, width, height, bytesPerPixel, bytesPerLine, image);
    }

    private TicketImage(String imagePath, int width, int height, int bytesPerPixel, int bytesPerLine, byte[] image) {
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.bytesPerPixel = bytesPerPixel;
        this.bytesPerLine = bytesPerLine;
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBytesPerPixel() {
        return bytesPerPixel;
    }

    public int getBytesPerLine() {
        return bytesPerLine;
    }
}
