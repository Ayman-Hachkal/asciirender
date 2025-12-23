package asciirenderer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class App 
{
    public static void main(String[] args) throws IOException, InterruptedException{
      Render render = new Render(20, 20);
      Frame frame = render.getFrame();
      Scenes scenes = new Scenes(frame);

      for (int row = 0; row < frame.getHeight(); row++) {
        for (int col = 0; col < frame.getWidth(); col++) {
          frame.setColor(row, col, "green");
        }
      }
      TimeUnit.SECONDS.sleep(1);
      scenes.nullFrame();
      scenes.movingX();

      while (true){
        scenes.movingX();
        render.printBoard();
        TimeUnit.MICROSECONDS.sleep(100);
      }
    }
}
