package ldts.control;

import ldts.model.*;
import ldts.view.BackgroundView;
import ldts.view.LaserView;
import ldts.view.PlayerView;
import ldts.view.RocketView;


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

    public Controller() throws IOException, URISyntaxException, FontFormatException {
        player = new Player();
        playerView = new PlayerView();
        backgroundView = new BackgroundView(LOWER_LIMIT);
        rocketView = new RocketView();
        laserView = new LaserView();
        obstacles = new ArrayList<Obstacle>();
    }

    public void run() throws IOException, InterruptedException {
        Command command = new Command(playerView.getScreen());
        command.start();
        int xMin = 0, i = 0;
        Obstacle rocket = new Rocket();
        Obstacle laser = new Laser();
        while (!gameOver) {
            playerView.getScreen().clear();
            backgroundView.draw(new Position(player.getPosition().getX(), LOWER_LIMIT), xMin);
            playerView.draw(player.getPosition());
            Character keyPressed = command.useKey();
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
            //rocket.move();
            //laser.move();
            //rocketView.draw(rocket.getPosition());
            //laserView.draw(laser.getPosition(), laser.getLastPosition());
            if (keyPressed == ' '){
                if (player.getPosition().getY() < playerView.getScreen().getTerminalSize().getRows())
                    player.goHigher();
            }
            else {
                if (player.getPosition().getY() > LOWER_LIMIT + 1)
                    player.goLower();
            }
            xMin++;
            Thread.sleep(34);
            i++;
        }
    }
}
