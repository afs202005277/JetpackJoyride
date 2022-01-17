package ldts.control;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class InputReader extends Thread {
    private final Screen screen;
    private final ArrayList<InputObserver> observers;

    public InputReader(Screen screen) {
        this.screen = screen;
        observers = new ArrayList<>();
    }

    public synchronized void addObserver(InputObserver obs) {
        observers.add(obs);
    }

    public synchronized void removeObserver(InputObserver obs) {
        observers.remove(obs);
    }

    public synchronized void notify(KeyStroke c) throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        for (InputObserver observer : observers)
            observer.input(c);
    }

    @Override @SuppressWarnings("CatchAndPrintStackTrace")
    public void run() {
        while (true) {
            try {
                inputReader(screen);
            } catch (IOException | URISyntaxException | InterruptedException | FontFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void inputReader(Screen screen) throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        KeyStroke keyStroke = screen.readInput();
        notify(keyStroke);
    }
}