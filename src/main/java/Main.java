


import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Thread {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        Obstacle rocket = new Rocket();
        for (int i = 0; i < 70; i++) rocket.move();
        Obstacle laser1 = new LaserGroup();
        for (int i = 0; i < 60; i++) laser1.move();
        Obstacle laser2 = new LaserGroup();
        for (int i = 0; i < 50; i++) laser2.move();
        Obstacle laser3 = new LaserGroup();
        for (int i = 0; i < 30; i++) laser3.move();

        //System.out.print(rocket.getX() + " " + rocket.getY() + "\n");
        //RocketView view = new RocketView();
        //view.draw(rocket.getPosition());
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        factory.setInitialTerminalSize(new TerminalSize(132,74));
        Terminal terminal = factory.createTerminal();
        TerminalScreen screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        TextGraphics graphics = screen.newTextGraphics();
        screen.doResizeIfNecessary(); // resize screen if necessary
        screen.clear();
        /*graphics.drawRectangle(new TerminalPosition(rocket.getX(), rocket.getY()), new TerminalSize(6,4), 'R');
        screen.refresh();
        graphics.drawLine(laser1.getX(), laser1.getY(), laser1.getLastPosition().getX(), laser1.getLastPosition().getY(), 'L');
        screen.refresh();
        graphics.drawLine(laser2.getX(), laser2.getY(), laser2.getLastPosition().getX(), laser2.getLastPosition().getY(), 'L');
        screen.refresh();
        graphics.drawLine(laser2.getX(), laser2.getY(), laser2.getLastPosition().getX(), laser2.getLastPosition().getY(), 'L');
        screen.refresh();*/
        List<Obstacle> obstacles = new ArrayList<Obstacle>();
        for (int i = 0; i < 300; i++) {
            Thread.sleep(50);
            int random = (int) (Math.random() * (5 - 1)) + 1;
            if (random < 4) {
                if (i % 40 == 0)
                obstacles.add(new LaserGroup());
                screen.clear();
                for (Obstacle obstacle: obstacles) {
                    obstacle.move();
                    obstacle.draw(graphics);
                    screen.refresh();
                }
            }
            else{
                if ( i % 40 == 0)
                obstacles.add(new Rocket());
                screen.clear();
                for (Obstacle obstacle: obstacles) {
                    obstacle.move();
                    obstacle.draw(graphics);
                    screen.refresh();
                }
            }
        }
        screen.close();

    }
}
