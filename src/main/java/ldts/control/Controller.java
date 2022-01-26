package ldts.control;

import com.googlecode.lanterna.screen.Screen;
import ldts.control.States.*;
import ldts.model.*;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class Controller {
    private static Controller singleton = null;
    private final PlayerController playerController;
    private final MenuState menuState;
    private Screen screen;
    private State state;


    public Controller() {
        String BACKGROUND = "#57AAF8";
        String WALLS = "#595959";
        playerController = new PlayerController(new Player(), new PlayerView(BACKGROUND, "#D5433C", "!"));
        menuState = new MenuState(playerController.getPlayerView());
        state = new MenuState(playerController.getPlayerView());
    }

    public static Controller getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    public static void setSingleton(Controller singleton) {
        Controller.singleton = singleton;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void runInstructions() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        state = new InstructionsState(playerController);
        state.step();
    }

    public void runMenu() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        state = new MenuState(playerController.getPlayerView());
        screen = View.initScreen();
        while(!menuState.isKeepRunning())
            state.step();
    }

    public void runGameOver () throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        state = new GameOverState(new GameOverView());
        state.step();
    }
    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        state = new RunningState(screen, playerController);
        while (true)
            state.step();

    }
}
