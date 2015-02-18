package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private Dimension gameDim;

    public GameWindow(String title, int width, int height) {
        this.title = title;
        this.gameDim = new Dimension(width, height);

        initFrame();
    }

    private void initFrame() {
        this.frame = new JFrame(this.title);
        this.frame.setSize(this.gameDim);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setLocationRelativeTo(null);

        this.initCanvas();

        this.frame.add(canvas);
        this.frame.pack();
    }

    private void initCanvas() {
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(this.gameDim);
        this.canvas.setMaximumSize(this.gameDim);
        this.canvas.setMinimumSize(this.gameDim);
        this.canvas.setFocusable(false);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}