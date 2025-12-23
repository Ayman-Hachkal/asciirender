package asciirenderer;

/**
 * Fun little scenes to test if render works properly 
 *
 * @author Ayman Hachkal 
 * @version 
 */
public class Scenes {
    Frame frame;

    public Scenes(Frame frame) {
        this.frame = frame;
    }

    public void nullFrame() {
        for (int row = 0; row < frame.getHeight(); row++) {
            for (int col = 0 ; col < frame.getWidth(); col++) {
                frame.setPixel(row, col, " ");
            }
        }
    }

    public void movingX() { 
        for (int row = 0; row < frame.getHeight(); row++) {
            for (int col = 0; col < frame.getWidth(); col++) {
                if (frame.getPixelFrame(row, col).equals("X")) {
                    frame.setPixel(row, col, " ");
                    if (col >= frame.getWidth() - 1) {
                        if (row >= frame.getHeight() - 1) {
                            frame.setPixel(0, 0, "X");
                            return;
                        }
                        else {
                            frame.setPixel(row + 1, 0, "X");
                            return;
                        }
                    }
                    else {
                        frame.setPixel(row , col + 1, "X");
                        return;
                    }
                }
            }
        }
        frame.setPixel(0, 0, "X");
        return;
    }
}
