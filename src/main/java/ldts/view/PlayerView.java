package ldts.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import ldts.model.Position;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class PlayerView extends View{
    public PlayerView() throws IOException, URISyntaxException, FontFormatException {
        initScreen();
    }

    @Override
    public void draw(Position pos) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000C66"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        screen.setCharacter(pos.getX(), ROWS-pos.getY(), TextCharacter.fromCharacter('k')[0]);
        refresh();
    }
}
