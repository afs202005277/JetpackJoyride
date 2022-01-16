package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface InputObserver {
    void input(KeyStroke input) throws IOException, URISyntaxException, InterruptedException, FontFormatException;
}
