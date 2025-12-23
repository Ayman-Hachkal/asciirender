package asciirenderer;
/**
 * description of class Frame 
 * @author Ayman Hachkal
 * @version 
 */

import asciirenderer.colors.Color;

public class Frame {
    int height;
    int width;
    String[][] pixelFrame;
    String[][] colorFrame;
    Color color;

    /**
     * Sets the dimensions of the frame
     * @param height of frame by cells
     * @param width of frame by cells
     */
    public Frame(int height, int width){ 
        pixelFrame = new String[height][width];
        colorFrame = new String[height][width];
        color = new Color();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                colorFrame[row][col] = color.getAnsi("reset"); 
            }
        }
        this.height = height;
        this.width = width;
    }

    /**
     * @return the height by cells
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * @return the width by cells
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the 2D Array of the frame
     */
    public String[][] getFullPixelFrame() {
        return pixelFrame;
    }
    
    public String[][] getFullColorFrame() {
        return colorFrame;
    }

    /**
     * @returns the pixel value 
     * @param y coordinate of the pixel
     * @param x coordinate of the pixel
     */
    public String getPixelFrame(int y, int x) {
        if (checkPixel(y, x)) { 
            return pixelFrame[y][x];
        }
        throw new RuntimeException("frame " + y + " " + x + " does not exist");
    }

    public String getColorFrame(int y, int x)  {
        if (checkPixel(y, x)) {
            return colorFrame[y][x];
        } 
        throw new RuntimeException("frame " + y + " " + x + " does not exist");
    }

    /**
     * sets the pixel of the frame
     * @param y coordinate of the frame
     * @param x coordinate of the frame
     * @param pixel String for the pixel (one character long)
     * @return 
     */
    public void setPixel(int y, int x, String pixel) {
        if (checkPixel(y, x)) {
            pixelFrame[y][x] = pixel;
            return;
        }
        else throw new RuntimeException("frame " + y + " " + x + " does not exist");
    }

    public void setColor(int y, int x, String desiredColor) {
        if (checkPixel(y, x)) {
            colorFrame[y][x] = color.getAnsi(desiredColor) ;
            return;
        }
    }
    
    private boolean checkPixel(int y, int x) {
        if (y >= 0 && y <= height && x >= 0 && x <= width) { 
            return true;
        }
        return false;
    }

    public boolean frameCopy(Frame copyFrame) {
        for (int row = 0; row < copyFrame.getHeight(); row++) {
            for (int col = 0; col < copyFrame.getWidth(); col++) {
                if (checkPixel(row, col)) {
                    pixelFrame[row][col] = copyFrame.getPixelFrame(row, col);
                    colorFrame[row][col] = copyFrame.getColorFrame(row, col);
                }
                else return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < pixelFrame.length; i++) {
            str += "\n";
            for (int j = 0; j < pixelFrame[i].length; j++) {
                if (pixelFrame[i][j] == null) {
                    str += " ";
                }
                else str += pixelFrame[i][j];
            }
        }
        return str;
    }
}
