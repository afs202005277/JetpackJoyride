package ldts.control.States;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State {

    public abstract void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException, AWTException;

}
