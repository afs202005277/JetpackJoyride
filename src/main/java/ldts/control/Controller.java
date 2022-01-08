package ldts.control;


import ldts.model.*;
import ldts.view.*;


import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;



public class Controller {
    private boolean gameOver = false;
    private final Player player;
    private PlayerView playerView;
    private BackgroundView backgroundView;
    private ArrayList<Obstacle> obstacles;
    private RocketView rocketView;
    private LaserView laserView;
    private static final int LOWER_LIMIT = 1;
    private static Controller singleton = null;

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

    private Controller() throws IOException, URISyntaxException, FontFormatException {
        player = new Player();
        playerView = new PlayerView();
        backgroundView = new BackgroundView(LOWER_LIMIT);
        rocketView = new RocketView();
        laserView = new LaserView();
        obstacles = new ArrayList<>();
    }

    public static Controller getInstance() throws IOException, URISyntaxException, FontFormatException {
        if (singleton == null)
            singleton = new Controller();
        return singleton;
    }

    public void generateObstacles(int i){
        if (i % 15 == 0) {
            int random = (int) (Math.random() * (5 - 1)) + 1;
            if (random < 4) obstacles.add(new Laser());
            else obstacles.add(new Rocket());
        }
    }

    public void drawElements(int xMin) throws IOException {
        View.getScreen().clear();
        backgroundView.draw(new Position(0, LOWER_LIMIT), xMin);
        playerView.draw(player.getPosition());
        for (Obstacle obstacle: obstacles) {
            obstacle.move();
            if (obstacle.isLaser()) laserView.draw(obstacle.getPosition(), obstacle.getLastPosition());
            else rocketView.draw(obstacle.getPosition());
        }
        View.getScreen().refresh();
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        View.initScreen();
        Command command = new Command(View.getScreen());
        command.start();
        int xMin = 0, i = 0;
        while (!gameOver) {
            Character keyPressed = command.useKey();
            if (keyPressed == ' '){
                if (player.getPosition().getY() < View.getScreen().getTerminalSize().getRows())
                    player.goHigher();
            }
            else if (keyPressed == 'q'){
                gameOver = true;
            }
            else if (player.getPosition().getY() > LOWER_LIMIT + 1) {
                player.goLower();
            }
            generateObstacles(i);
            drawElements(xMin);
            xMin++;
            i++;
            Thread.sleep(60);
        }
        System.exit(0);
    }
}
