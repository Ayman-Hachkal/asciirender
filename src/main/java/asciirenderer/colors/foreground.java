package asciirenderer.colors;

import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;

public class foreground {
    Map<String, String> colors;

    public foreground() {
        colors = new HashMap<>() {{
            put("black", "\u001B[0;30m");
            put("red", "\u001B[0;31m");
            put("green", "\u001B[0;32m");
            put("yellow", "\u001B[0;33m");
            put("blue", "\u001B[0;34m");
            put("purple", "\u001B[0;35m");
            put("cyan", "\u001B[0;36m");
            put("white", "\u001B[0;37m");
            put("reset", "\u001B[0m");
        }};
    }

    public String getAnsi(String color) {
        if (colors.containsKey(color.toLowerCase())) {
            return colors.get(color);
        }
        else {
            throw new RuntimeErrorException(null);
        }
    }

    public String getColorKey(String ansi) {
        if (colors.containsValue(ansi)){
            for (String i : colors.keySet()) {
                if (colors.get(i).equals(ansi)) {
                    return i;
                } 
            }
        }
        return "reset";
    }
}
