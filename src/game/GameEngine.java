package game;

import contracts.Updatable;
import gfx.Assets;
import input.InputHandler;
import states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Runnable, Updatable{
    public static final int DEFAULT_GAME_WIDTH = 800;
    public static final int DEFAULT_GAME_HEIGHT = 600;


    private Thread mainThread;
    private boolean isRunning;

    private String gameTitle;
    private int gameWidth;
    private int gameHeight;
    private GameWindow display;
    public static InputHandler inputHandler;

    private Graphics graphics;
    private BufferStrategy bs;

    //States
    private State gameState;

    public GameEngine (String title) {
        this.gameTitle = title;
        this.setGameWidth(DEFAULT_GAME_WIDTH);
        this.setGameHeight(DEFAULT_GAME_HEIGHT);
    }

    private void init() {
        //init JFrame and Canvas
        this.display = new GameWindow(this.gameTitle, this.getGameWidth(), this.getGameHeight());

        this.display.getCanvas().createBufferStrategy(2);
        this.bs = this.display.getCanvas().getBufferStrategy();

        inputHandler = new InputHandler(this.display.getFrame());

        //init States
        this.gameState = new GameState(this);
        StateManager.setState(this.gameState);

        //init Assets
        Assets.init();
    }

    @Override
    public void tick() {
        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g = this.bs.getDrawGraphics();
        g.clearRect(0, 0, this.getGameWidth(), this.getGameHeight());
        //Start Draw

        if (StateManager.getState() != null) {
            StateManager.getState().render(g);
        }

        //End Draw
        this.bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        this.init();

        while (isRunning) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.tick();
            this.render(this.graphics);
        }

        this.stop();
    }

    //Removed synchronized because there is no other thread that
    //can call the start method of the main thread
    public void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;

        this.mainThread = new Thread(this);
        this.mainThread.start();
    }

    //Removed synchronized because there is no other thread that
    //can call the stop method of the main thread
    public void stop() {
        if (!this.isRunning) {
            return;
        }
        this.isRunning = false;

        try {
            this.mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getGameWidth() {
        return this.gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }
}
