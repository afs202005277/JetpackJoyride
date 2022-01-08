package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public BackgroundView getBackgroundView() {
        return backgroundView;
    }

    public Controller() throws IOException, URISyntaxException, FontFormatException {
        player = new Player();
        playerView = new PlayerView();
        View.initScreen();
        backgroundView = new BackgroundView(LOWER_LIMIT);
        rocketView = new RocketView();
        laserView = new LaserView();
        obstacles = new ArrayList<Obstacle>();
    }

    public void run() throws IOException, InterruptedException {
        Command command = new Command(View.getScreen());
        command.start();
        int xMin = 0, i = 0;
        while (!gameOver) {
            Character keyPressed = command.useKey();
            /*KeyStroke keyPressedtmp = playerView.getScreen().pollInput();
            char keyPressed = '0';
            if (keyPressedtmp != null)
                keyPressed = keyPressedtmp.getCharacter();*/
            if (keyPressed == ' '){
                if (player.getPosition().getY() < View.getScreen().getTerminalSize().getRows())
                    player.goHigher();
            }
            else {
                if (player.getPosition().getY() > LOWER_LIMIT + 1)
                    player.goLower();
            }
            View.getScreen().clear();
            backgroundView.draw(new Position(0, LOWER_LIMIT), xMin);
            playerView.draw(player.getPosition());

            if (i % 15 == 0) {
                int random = (int) (Math.random() * (5 - 1)) + 1;
                if (random < 4) obstacles.add(new Laser());
                else obstacles.add(new Rocket());
            }
            for (Obstacle obstacle: obstacles) {
                obstacle.move();
                if (obstacle.type()) laserView.draw(obstacle.getPosition(), obstacle.getLastPosition());
                else rocketView.draw(obstacle.getPosition());
            }
            View.getScreen().refresh();
            xMin++;
            i++;
            Thread.sleep(60);
        }
    }
}
