package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import ldts.model.*;
import ldts.view.*;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;



public class Controller {
    private Player player;
    private InputReader inputReader;
    private PlayerView playerView;
    private BackgroundView backgroundView;
    private GameOverView gameOverView;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Element> coins;
    private RocketView rocketView;
    private LaserView laserView;
    private CoinView coinView;
    private static final int LOWER_LIMIT = 1;
    private static Controller singleton = null;
    private CounterView distanceCounterView;
    private CounterView coinsCounterView;
    private int timePerFrame = 1000 / 15;
    private Screen screen;

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }

    public void setBackgroundView(BackgroundView backgroundView) {
        this.backgroundView = backgroundView;
    }

    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean checkCollisions(Element object, Player player)
    {
        boolean collision = false;
        if (object.isObstacle())
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
        else
        {
            if (object.getPosition().equals(player.getPosition())) collision = true;
        }
        return collision;
    }

    public Controller() {
        String BACKGROUND = "#57AAF8";
        String WALLS = "#595959";
        player = new Player();
        inputReader = new InputReader(View.getScreen());
        playerView = new PlayerView(BACKGROUND, "#D5433C", "!");
        backgroundView = new BackgroundView(WALLS, BACKGROUND, ' ', ' ', LOWER_LIMIT);
        gameOverView = new GameOverView();
        rocketView = new RocketView(BACKGROUND, "#000000", "$%");
        laserView = new LaserView("#fffb54", ' ');
        coinView = new CoinView(BACKGROUND, "#DEAC4C", "#");
        obstacles = new ArrayList<>();
        coins = new ArrayList<>();
        distanceCounterView = new CounterView(WALLS, "#000000", "meters");
        coinsCounterView = new CounterView(WALLS, "#DEAC4C", "coins");
    }

    public static Controller getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    public void generateObjects(int i){
        if (i % 5 == 0) {
            int random = (int) (Math.random() * (7 - 1)) + 1;
            if (random <= 4) obstacles.add(new Laser());
            else if (random <= 6) coins.add(new Coin());
            else obstacles.add(new Rocket());
        }
    }

    public void runInstructions() throws IOException {
        InstructionsView iView = new InstructionsView();
        iView.draw(playerView, backgroundView, laserView, coinView);
    }

    public void runMenu() throws IOException {
        MenuView menuView = new MenuView();
        menuView.draw(playerView, backgroundView, laserView, coinView);
    }

    public void drawElements(int xMin, int coins) throws IOException {
        screen.clear();
        backgroundView.draw(new Position(0, LOWER_LIMIT), xMin);
        playerView.draw(player.getPosition());
        for (Obstacle obstacle : obstacles) {
            Element object = (Element) obstacle;
            object.move(-1, 0);
            if (obstacle.isLaser()) laserView.draw(object.getPosition(), obstacle.getLastPosition());
            else rocketView.draw(object.getPosition());
        }
        for (Element coin : this.coins)
        {
            coin.move(-1, 0);
            coinView.draw(coin.getPosition());
        }
        distanceCounterView.draw(new Position(screen.getTerminalSize().getColumns() - distanceCounterView.getUnits().length() - 10, 0), xMin);
        coinsCounterView.draw(new Position(0, 0), coins);
        screen.refresh();
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        boolean gameOver = false;
        screen = View.initScreen();
        do {
            PlayerController playerController = new PlayerController(player, inputReader);
            inputReader.start();
            int xMin = 0, coinsCollected = 0;
            while (!gameOver) {
                inputReader.notify();
                long startTime = System.currentTimeMillis();
                playerController.step(LOWER_LIMIT);
                generateObjects(xMin);
                drawElements(xMin, coinsCollected);

                for (Obstacle obstacle : obstacles) {
                    if (checkCollisions(obstacle, player)) {
                        gameOver = true;
                    }
                }
                for (int j = 0; j < coins.size(); j++)
                {
                    if (checkCollisions(coins.get(j), player))
                    {
                        coins.remove(j);
                        coinsCollected++;
                        j--;
                    }
                }
                xMin++;
                long finalTime = System.currentTimeMillis();
                Thread.sleep(timePerFrame-(finalTime - startTime));
                inputReader.wait();
            }
            while (true) {
                gameOverView.draw(null);
                KeyStroke x = screen.readInput();
                if (x.getKeyType() == KeyType.ArrowUp) {
                    gameOverView.moveSelected(-1);
                } else if (x.getKeyType() == KeyType.ArrowDown) {
                    gameOverView.moveSelected(1);
                } else if (x.getKeyType() == KeyType.Enter) {
                    if (gameOverView.getSelected() == 1)
                        System.exit(0);
                    gameOver = false;
                    break;
                }
            }
            resetElements();
        }while(true);
    }

    private void resetElements() throws IOException, URISyntaxException, FontFormatException {
        player = new Player();
        obstacles = new ArrayList<>();
    }
}
