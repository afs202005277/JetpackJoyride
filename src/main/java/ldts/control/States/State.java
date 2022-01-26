package ldts.control.States;

import ldts.control.Controller;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State {



    public abstract void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException;


}
