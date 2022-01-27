package ldts.control.states;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public interface State {

    public abstract void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException, AWTException;

}
