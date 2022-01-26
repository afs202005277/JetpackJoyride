package ldts.control.States;

import com.googlecode.lanterna.screen.Screen;
import ldts.control.*;
import ldts.model.*;
import ldts.view.*;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class RunningState extends State {
    private static final int LOWER_LIMIT = 1;
    private static boolean gameOver = false;
    private static int coinsCollected = 0;
    private Screen screen;
    private PlayerController playerController;
    private ArrayList<Element> elements;
    private final ElementFactory elementFactory;
    private BackgroundView backgroundView;
    private RocketView rocketView;
    private LaserView laserView;
    private CoinView coinView;
    private CounterView distanceCounterView;
    private CounterView coinsCounterView;

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public RunningState(Screen screen, PlayerController playerController) {
        String BACKGROUND = "#57AAF8";
        String WALLS = "#595959";
        this.screen = screen;
        this.playerController = playerController;
        this.elements = new ArrayList<>();
        elementFactory = new ElementFactory(5);
        backgroundView = new BackgroundView(WALLS, BACKGROUND, ' ', ' ', LOWER_LIMIT);
        rocketView = new RocketView(BACKGROUND, "#000000", "$%");
        laserView = new LaserView("#fffb54", ' ');
        coinView = new CoinView(BACKGROUND, "#DEAC4C", "#");
        distanceCounterView = new CounterView(WALLS, "#000000", "meters");
        coinsCounterView = new CounterView(WALLS, "#DEAC4C", "coins");
    }

    public void generateObjects(int i) {
        if (elementFactory.generateElements(i) != null)
            elements.add(elementFactory.generateElements(i));
    }
    public void drawElements(int xMin, int coins) throws IOException {
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
        coinsCounterView.draw(new Position(0, 0), coins);
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
    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public static void setCoinsCollected(int coinsCollected) {
        RunningState.coinsCollected = coinsCollected;
    }

    public static void setGameOver(boolean gameOver) {
        RunningState.gameOver = gameOver;
    }

    public static int getCoinsCollected() {
        return coinsCollected;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void resetElements() {
        playerController.setPlayer(new Player());
        elements = new ArrayList<>();
        coinsCollected = 0;
        gameOver = false;
    }
    public static void incrementCoinsCollected(){
        coinsCollected++;
    }

    public static void endGame() {
        gameOver = true;
    }

    @Override
    public void step() throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        InputReader inputReader = new InputReader(screen);
        inputReader.addObserver(playerController);
        inputReader.start();
        playerController.setPlayer(new Player());
        resetElements();
        gameOver = false;
        int xMin = 0;
        while (!gameOver) {
            long startTime = System.currentTimeMillis();
            generateObjects(xMin);
            drawElements(xMin, coinsCollected);
            playerController.step(LOWER_LIMIT);
            for (Element element : elements) {
                element.checkCollision(playerController.getPlayer().getPosition());
            }
            xMin++;
            long finalTime = System.currentTimeMillis();
            int timePerFrame = 1000 / 15;
            try {
                Thread.sleep(timePerFrame - (finalTime - startTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        inputReader.clear();
        Controller.getInstance().runGameOver();
    }

}

