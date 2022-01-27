package ldts.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class View {
    protected static int numberCols = 51;
    protected static int numberRows = 18;
    static protected Screen screen = null;
    static protected TextGraphics graphics;

    private static Screen createScreen(Terminal terminal) throws IOException {
        Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        return screen;
    }

    private static AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = View.class.getClassLoader().getResource("player_new.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 30);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    private static Terminal createTerminal(AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(numberCols, numberRows);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        ((AWTTerminalFrame) terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                System.exit(0);
            }
        });
        return terminal;
    }

    public static Screen initScreen() throws IOException, FontFormatException, URISyntaxException {
        if (screen != null)
            return screen;
        Terminal terminal = createTerminal(loadSquareFont());
        screen = createScreen(terminal);
        numberCols = screen.getTerminalSize().getColumns();
        numberRows = screen.getTerminalSize().getRows();
        graphics = screen.newTextGraphics();
        return screen;
    }

    public static Screen getScreen() {
        return screen;
    }

    public static void setScreen(Screen screen) {
        View.screen = screen;
    }

    public static TextGraphics getGraphics() {
        return graphics;
    }

    public static void setGraphics(TextGraphics graphics) {
        View.graphics = graphics;
    }

    public static int getNumberRows() {
        return numberRows;
    }

    public static int getNumberColumns() {
        return numberCols;
    }

    protected TextColor stringToColor(String s) {
        return TextColor.Factory.fromString(s);
    }
}
