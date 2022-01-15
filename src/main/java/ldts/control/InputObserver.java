package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;

public interface InputObserver {
    void input(KeyStroke input);
}
