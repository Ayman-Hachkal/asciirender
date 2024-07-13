package asciirenderer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import asciirenderer.colors.foreground;

public class App 
{
    public static void main(String[] args) throws IOException, InterruptedException{
      Render render = new Render(20, 20);
      Frame frame = render.getFrame();
      foreground color = new foreground();

      for (int row = 0; row < frame.getHeight(); row++) {
        for (int col = 0; col < frame.getWidth(); col++) {
          frame.setColor(row, col, color.getAnsi("green"));
        }
      }

      while (true){
        render.printBoard();
        TimeUnit.SECONDS.sleep(1);
      }
    }
}
