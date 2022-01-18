package ldts.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.view.GameOverView;

import java.io.IOException;

public class GameOverController implements InputObserver {
    private final GameOverView gameOverView;
    private boolean gameOver = true;
    private boolean enterPressed = false;
    private boolean mainMenu = false;

    public GameOverController(GameOverView gameOverView) {
        this.gameOverView = gameOverView;
    }

    @SuppressWarnings("CatchAndPrintStackTrace")
    public void step() {
        try {
            gameOverView.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOver = true;
        enterPressed = false;
    }

    public boolean isMainMenu() {
        return mainMenu;
    }

    @Override
    public void input(KeyStroke input) {
        if (input.getKeyType() == KeyType.ArrowUp) {
            gameOverView.moveSelected(-1);
        } else if (input.getKeyType() == KeyType.ArrowDown) {
            gameOverView.moveSelected(1);
        }
        this.step();
        if (input.getKeyType() == KeyType.Enter) {
            if (gameOverView.getSelected() == 1)
                mainMenu = true;
            gameOver = false;
            enterPressed = true;
        }
    }

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public synchronized boolean isEnterPressed() {
        return enterPressed;
    }
}
