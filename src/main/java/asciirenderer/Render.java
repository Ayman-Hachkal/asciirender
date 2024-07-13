/* TODO
 * Correctly fix frame resizing (Tough) 
 * Add Color (Tough)
 * Commenting (easy)
 * Make test cases to see if it works correctly (Medium)
 * add windowing features (maybe) - Far in the future development
 * maybe change setPixel so a String multi character String can be passed through and cycle through the image (Easy)
 * add ability to set custom window size and chose the axis of the terminal location to display (medium)
 * compatibility to read input of folder images to render them in ascii
 */

 /*
  * Bugfix
  * when typing in terminal the input is printing out on the screen and not replaced
  */
package asciirenderer;

import java.io.IOException;

import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import asciirenderer.colors.foreground;

/**
 * The rendering engine for ascii characters in a terminal
 * @author Ayman Hachkal
 * @version 1.0 
 */
class Render { 
  int consoleHeight;
  int consoleWidth;
  Frame frame;
  Frame prevFrame;
  foreground textColor;

  /**
   * Initializes the window by getting the terminal dimensions
   * no Param are needed as it's all automatic
   * @throws IOException
   */
  public Render() throws IOException { 
    this.consoleWidth = 0;
    this.consoleHeight = 0;

    this.setDimensions();

    frame = new Frame(consoleHeight, consoleWidth);
    firstFrame();
  }

  /**
   * Render a custom height and width of terminal
   * @param height of the window you want as a percentage of the terminal
   * @param width of the window you want as a percentage of the terminal
   * @throws IOException 
   */
  public Render(int height, int width) throws IOException {

    setDimensions(height, width);

    frame = new Frame(consoleHeight, consoleWidth);
    firstFrame();
  }

  /**
   * Returns terminal height by cells
   * @return consoleHeight 
   */
  public int getHeight() {
    return consoleHeight;
  }

  /**
   * Returns terminal width by cells
   * @return consoleWidth
   */
  public int getWidth() {
    return consoleWidth;
  }
  /**
   * assign a value to a pixel in a String format,
   * String should only be one character long. and should only be in unicode
   * @param row location of the pixel that needs to be amended on the Y coordinate
   * @param col location of the pixel that needs to be amended on the X coordinate
   * @param obj the String that will be amended 
   * @return if it successfully assigned the String value to the frame pixel
   */
  public boolean setFrame(int row, int col, String obj) {
    if (row >= 0 || row < consoleHeight && col >= 0 || col < consoleWidth){
      frame.setPixel(row, col, obj);
      return true;
    }
    else return false;
  }
  /**
   * returns the frame as a Frame object
   * @return frame
   */
  public Frame getFrame() { 
    return frame;
  }
  
  /**
   * returns the previous frame (it's used to compare the ascii characters to change)
   * @return prevFrame
   */
  public Frame getPrevFrame() {
    return prevFrame;
  }
  /**
   * returns the pixel value
   * @return the pixel value of the main frame
   * @param row Y coordinate
   * @param col X coordinate
   */
  public String getPixel(int row, int col) {
    if (row >= 0 || row < consoleHeight && col >= 0 || col < consoleWidth){
      return frame.getPixelFrame(row, col);
    }
    throw new RuntimeException();
  }

  /**
   * when first initializing the render a *first frame* is generated with a # border around it 
   * this is done to know if the render works properly and is accessible for testing.
   * At the end of the method it copies {@link #frame} into {@link #prevFrame}
   */
  private void firstFrame() { 
    for (int row = 0; row < consoleHeight; row++) {
      for (int col = 0; col < consoleWidth; col++) {
        if (row == 0 || row == consoleHeight - 1|| col == 0 || col == consoleWidth - 1){ 
          frame.setPixel(row, col, "#");
        }
        else {
          frame.setPixel(row, col," ");
        }
      }
    }
    System.out.print(frame.toString());
    prevFrame = new Frame(consoleHeight, consoleWidth);
    prevFrame.frameCopy(frame);
  }

  /**
   * does not work properly right now but it's meant to change the dimensions of the window to the dimensions
   * of the terminal (i'm unsure if this is needed as there might be future implementation of smaller window sizes)
   * @return a boolean of if the terminal has correctly been resized
   * @throws IOException
   */
  public boolean changedDimensions() throws IOException {
    Terminal terminal = TerminalBuilder.terminal(); 
    Size size = terminal.getSize();
    int updateWidth = size.getColumns();
    int updateHeight = size.getRows();

    if (updateHeight!= consoleHeight || updateWidth!= consoleWidth){
      return true;
    }
    return false;
  }

  public int[] newDimensions() throws IOException {
    int[] dimensions = new int[2];
    Terminal terminal = TerminalBuilder.terminal();
    Size size = terminal.getSize();
    dimensions[0] = size.getRows();
    dimensions[1] = size.getColumns(); 
    return dimensions; 
  }

  public void setDimensions() throws IOException {
    Terminal terminal = TerminalBuilder.terminal(); 
    Size size = terminal.getSize();
    this.consoleWidth =  size.getColumns();
    this.consoleHeight = size.getRows();
    return; 
  }
  public void setDimensions(int width, int height) {
    this.consoleHeight = height;
    this.consoleWidth = width;
    return;
  }
  
  /**
   * prints the board to screen by comparing {@link #prevFrame} with {@link #frame} if there is a difference in 
   * the pixel the new frames pixel is then replaced using ansi escape code 
   */
  public void printBoard() {
    for (int row = 0; row < consoleHeight; row++) {
      for (int col = 0; col < consoleWidth; col++) {
        if (!frame.getColorFrame(row, col).equals(prevFrame.getColorFrame(row, col)) 
        ||  !frame.getPixelFrame(row, col).equals(prevFrame.getPixelFrame(row, col))) {
          System.out.print("\u001b[" + (row + 1)+ ";" + (col + 1)+ "H");
          System.out.println(frame.getColorFrame(row, col));
          System.out.print(frame.getPixelFrame(row, col));
        }
      }
    }
    System.out.print("\u001b[" + consoleHeight + ";" + consoleWidth+ "H");
    System.out.flush();
    prevFrame.frameCopy(frame);
  }

  public String ansi(int row, int col) {
    return textColor.getAnsi(frame.getColorFrame(row, col));
  }
}
