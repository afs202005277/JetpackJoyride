package ldts;

import ldts.control.Controller;
import ldts.view.View;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        Controller controller = Controller.getInstance();
        View.initScreen();
        controller.runInstructions();
    }
}