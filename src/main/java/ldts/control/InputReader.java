package ldts.control;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputReader implements KeyListener {
    private ArrayList<InputObserver> inputObservers;

    public InputReader(ArrayList<InputObserver> inputObservers) {
        this.inputObservers = inputObservers;
    }

    public InputReader() {
        inputObservers = new ArrayList<>();
    }

    public void addObserver(InputObserver obs){
        inputObservers.add(obs);
    }

    public void removeObserver(InputObserver obs){
        inputObservers.remove(obs);
    }

    public void notifyObservers(char c){
        for (InputObserver inputObserver : inputObservers){
            inputObserver.input(c);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        notifyObservers(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        notifyObservers(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
