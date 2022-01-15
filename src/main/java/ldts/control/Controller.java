package ldts.control;

import com.googlecode.lanterna.screen.Screen;
import ldts.model.*;
import ldts.view.*;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;



public class Controller {
    private PlayerController playerController;
    private InputReader inputReader;
    private BackgroundView backgroundView;
    private ArrayList<Element> elements;
    private RocketView rocketView;
    private LaserView laserView;
    private CoinView coinView;
    private static final int LOWER_LIMIT = 1;
    private static Controller singleton = null;
    private final CounterView distanceCounterView;
    private final CounterView coinsCounterView;
    private Screen screen;

    public void setBackgroundView(BackgroundView backgroundView) {
        this.backgroundView = backgroundView;
    }

    public boolean checkCollisions(Element object, Player player)
    {
        boolean collision = false;
        if (object.isRocket() || object.isLaser())
        {
            Obstacle obstacle = (Obstacle) object;
            if (obstacle.isLaser()) {
                // Laser Collision
                if (object.getPosition().getX() <= player.getPosition().getX() && player.getPosition().getX() <= obstacle.getLastPosition().getX()) {
                    int m = 0;
                    if (object.getPosition().getX() == obstacle.getLastPosition().getX() && obstacle.getLastPosition().getY() <= player.getPosition().getY() && player.getPosition().getY() <= object.getPosition().getY())
                        collision = true;
                    else {
                        if (object.getPosition().getY() > obstacle.getLastPosition().getY()) m = -1;
                        else if (object.getPosition().getY() < obstacle.getLastPosition().getY()) m = 1;

                        int b = object.getPosition().getY() - m * object.getPosition().getX();

                        if (player.getPosition().getX() * m + b == player.getPosition().getY()) collision = true;
                    }
                }
            } else {
                // Rocket Collision
                Position temp = new Position(object.getX() + 1, object.getPosition().getY());
                if (object.getPosition().equals(player.getPosition()) || temp.equals(player.getPosition()))
                    collision = true;
            }
        }
        else{

        }
        return collision;
    }

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
    }

    public static Controller getInstance() {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    public void generateObjects(int i){
        if (i % 5 == 0) {
            int random = (int) (Math.random() * 6) + 1;
            if (random <= 4) elements.add(new Laser());
            else if (random <= 6) elements.add(new Coin());
            else elements.add(new Rocket());
        }
    }

    public void runInstructions() throws IOException {
        InstructionsView iView = new InstructionsView();
        iView.draw(playerController.getPlayerView(), backgroundView, laserView, coinView);
    }

    public void runMenu() throws IOException {
        MenuView menuView = new MenuView();
        menuView.draw(playerController.getPlayerView(), backgroundView, laserView, coinView);
    }

    public void drawElements(int xMin, int coins) throws IOException {
        screen.clear();
        backgroundView.draw(new Position(0, LOWER_LIMIT), xMin);
        for (Element element : elements) {
            element.move(-1, 0);
            if (element.isCoin() && !((Coin) element).isCollected())
                coinView.draw(element.getPosition());
            else if (element.isLaser())
                laserView.draw(element.getPosition(), ((Obstacle)element).getLastPosition());
            else if (element.isRocket())
                rocketView.draw(element.getPosition());
        }
        distanceCounterView.draw(new Position(screen.getTerminalSize().getColumns() - distanceCounterView.getUnits().length() - 10, 0), xMin);
        coinsCounterView.draw(new Position(0, 0), coins);
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        boolean gameOver;
        screen = View.initScreen();
        inputReader = new InputReader(screen);
        GameOverController gameOverController = new GameOverController(new GameOverView());
        inputReader.addObserver(playerController);
        inputReader.start();

        boolean f1, f2;
        do {
            gameOver = false;
            int xMin = 0, coinsCollected = 0;
            while (!gameOver) {
                long startTime = System.currentTimeMillis();
                generateObjects(xMin);
                drawElements(xMin, coinsCollected);
                playerController.step(LOWER_LIMIT);

                for (Element element : elements) {
                    if (element.isCoin() && element.getPosition().equals(playerController.getPlayer().getPosition())){
                        ((Coin) element).collect();
                        coinsCollected++;
                    }
                    else if (checkCollisions(element, playerController.getPlayer())) {
                        gameOver = true;
                    }
                }
                xMin++;
                long finalTime = System.currentTimeMillis();
                int timePerFrame = 1000 / 15;
                Thread.sleep(timePerFrame -(finalTime - startTime));
            }
            inputReader.addObserver(gameOverController);
            gameOverController.step();
            resetElements();
            while(!gameOverController.isEnterPressed());
            f1 = gameOverController.isGameOver();
            f2 = gameOverController.isEnterPressed();
            inputReader.removeObserver(gameOverController);
        }while(!(f1) && f2);
    }

    private void resetElements() {
        playerController.setPlayer(new Player());
        elements = new ArrayList<>();
    }
}
