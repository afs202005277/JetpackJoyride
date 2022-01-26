package ldts.control;

import com.googlecode.lanterna.screen.Screen;
import ldts.model.*;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class Controller {
    private static final int LOWER_LIMIT = 1;
    private static Controller singleton = null;
    private CounterView distanceCounterView;
    private CounterView coinsCounterView;
    private final PlayerController playerController;
    private BackgroundView backgroundView;
    private ArrayList<Element> elements;
    private RocketView rocketView;
    private LaserView laserView;
    private CoinView coinView;
    private final MenuController menuController;
    private Screen screen;
    private final ElementFactory elementFactory;
    private static int coinsCollected = 0;
    private static boolean gameOver = false;


    public Controller() {
        String BACKGROUND = "#57AAF8";
        String WALLS = "#595959";
        playerController = new PlayerController(new Player(), new PlayerView(BACKGROUND, "#D5433C", "!"));
        backgroundView = new BackgroundView(WALLS, BACKGROUND, ' ', ' ', LOWER_LIMIT);
        rocketView = new RocketView(BACKGROUND, "#000000", "$%");
        laserView = new LaserView("#fffb54", ' ');
        coinView = new CoinView(BACKGROUND, "#DEAC4C", "#");
        elements = new ArrayList<>();
        distanceCounterView = new CounterView(WALLS, "#000000", "meters");
        coinsCounterView = new CounterView(WALLS, "#DEAC4C", "coins");
        menuController = new MenuController(playerController.getPlayerView(), backgroundView, coinView, laserView);
        elementFactory = new ElementFactory(5);
    }

    public static Controller getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    public static void setSingleton(Controller singleton) {
        Controller.singleton = singleton;
    }

    public void setDistanceCounterView(CounterView distanceCounterView) {
        this.distanceCounterView = distanceCounterView;
    }

    public void setCoinsCounterView(CounterView coinsCounterView) {
        this.coinsCounterView = coinsCounterView;
    }

    public void setRocketView(RocketView rocketView) {
        this.rocketView = rocketView;
    }

    public void setLaserView(LaserView laserView) {
        this.laserView = laserView;
    }

    public void setCoinView(CoinView coinView) {
        this.coinView = coinView;
    }

    public void setBackgroundView(BackgroundView backgroundView) {
        this.backgroundView = backgroundView;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void generateObjects(int i) {
        Element tmp = elementFactory.generateElements(i);
        if (tmp != null)
            elements.add(tmp);
    }

    public static void setCoinsCollected(int coinsCollected) {
        Controller.coinsCollected = coinsCollected;
    }

    public void runInstructions() throws IOException {
        InstructionsView iView = new InstructionsView();
        iView.draw(playerController.getPlayerView(), backgroundView, laserView, coinView);
    }

    public void runMenu() throws IOException, URISyntaxException, FontFormatException {
        screen = View.initScreen();
        while(!menuController.isKeepRunning())
            menuController.step();
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public void drawElements(int xMin) throws IOException {
        screen.clear();
        backgroundView.draw(new Position(0, LOWER_LIMIT));
        for (Element element : elements) {
            element.move(-1, 0);
            if (element.isCoin() && !((Coin) element).isCollected())
                coinView.draw(element.getPosition());
            else if (element.isLaser())
                laserView.draw(element.getPosition(), ((Laser) element).getLastPosition());
            else if (element.isRocket()) {
                element.move(-1, 0);
                rocketView.draw(element.getPosition());
            }
        }
        distanceCounterView.draw(new Position(screen.getTerminalSize().getColumns() - distanceCounterView.getUnits().length() - 10, 0), xMin);
        coinsCounterView.draw(new Position(0, 0), coinsCollected);
    }

    public static void incrementCoinsCollected(){
        coinsCollected++;
    }

    public static void endGame() {
        gameOver = true;
    }

    private void resetElements() {
        playerController.setPlayer(new Player());
        elements = new ArrayList<>();
        coinsCollected = 0;
        gameOver = false;
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        InputReader inputReader = new InputReader(screen);
        inputReader.addObserver(playerController);
        inputReader.start();
        GameOverController gameOverController = new GameOverController(new GameOverView());

        boolean stopGame, enterPressed, goToMainMenu;
        do {
            gameOver = false;
            int xMin = 0;
            while (!gameOver) {
                long startTime = System.currentTimeMillis();
                generateObjects(xMin);
                drawElements(xMin);
                playerController.step(LOWER_LIMIT);
                for (Element element : elements) {
                    element.checkCollision(playerController.getPlayer().getPosition());
                }
                xMin++;
                long finalTime = System.currentTimeMillis();
                int timePerFrame = 1000 / 15;
                Thread.sleep(timePerFrame - (finalTime - startTime));
            }
            inputReader.addObserver(gameOverController);
            gameOverController.step();
            resetElements();
            while (!gameOverController.isEnterPressed());
            stopGame = gameOverController.isGameOver();
            enterPressed = gameOverController.isEnterPressed();
            goToMainMenu = gameOverController.isMainMenu();
            inputReader.removeObserver(gameOverController);
        } while (!stopGame && enterPressed && !goToMainMenu);
        inputReader.clear();
        runMenu();
    }
}
