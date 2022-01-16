package ldts;

import ldts.control.Controller;
import ldts.control.MenuController;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        Controller controller = Controller.getInstance();
        controller.runMenu();



        /*String BACKGROUND = "#57AAF8";
        String WALLS = "#595959";
        View.initScreen();
        MenuView mv = new MenuView();
        MenuController mc = new MenuController(mv, new PlayerView(BACKGROUND, "#D5433C", "!"), new BackgroundView(WALLS, BACKGROUND, ' ', ' ', 1), new CoinView(BACKGROUND, "#DEAC4C", "#"), new LaserView("#fffb54", ' '));
        mc.step();*/
    }
}