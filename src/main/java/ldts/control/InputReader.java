package ldts.control;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class InputReader extends Thread {
    private final Screen screen;
    private final ArrayList<InputObserver> observers;
    private Character key = '0';

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

    public synchronized void notify(KeyStroke c) {
        for (InputObserver observer : observers)
            observer.input(c);
    }

    public synchronized Character getKey() {
        return key;
    }

    public synchronized Character useKey() {
        Character tmp = key;
        key = '0';
        return tmp;
    }

    public synchronized void changeKey(Character c) {
        key = c;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                inputReader(screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void inputReader(Screen screen) throws IOException {
        KeyStroke keyStroke = screen.readInput();
        notify(keyStroke);
    }
}