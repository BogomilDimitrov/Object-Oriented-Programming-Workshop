package game;

import contracts.Updatable;
import gfx.Assets;
import input.InputHandler;
import states.GameState;
import states.State;
import states.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Runnable, Updatable{

    private Thread thread;
    private boolean isRunning;

    private GameWindow display;

    private String title;
    private int width, height;

    private Graphics g;
    private BufferStrategy bs;
    public static InputHandler inputHandler;

    private State currentState;

    public GameEngine(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

    }

    private void init() {
        this.display = new GameWindow(this.title, this.width, this.height);
        this.display.getCanvas().createBufferStrategy(2);
        this.bs = this.display.getCanvas().getBufferStrategy();
        this.g = this.bs.getDrawGraphics();
        Assets.init();

        this.inputHandler = new InputHandler(this.display.getFrame());

        this.currentState = new GameState(this);
        StateManager.setCurrentState(this.currentState);
    }

    @Override
    public void tick() {
        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g = this.bs.getDrawGraphics();

        g.clearRect(0, 0, this.width, this.height);
        //Start Drawing

        if (StateManager.getCurrentState() != null) {
            StateManager.getCurrentState().render(g);
        }

        //END Drawing
        this.bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

        while (isRunning) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tick();
            render(this.g);
        }

        stop();
    }

    public synchronized void start() {
        if (this.thread != null) {
            return;
        }

        this.isRunning = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() {
        if (this.thread == null) {
            return;
        }

        this.isRunning = false;

        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
