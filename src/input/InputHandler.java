package input;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
    public boolean up, down, left, right, spacebar;

    public InputHandler(JFrame frame) {
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.up = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            this.down = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.left = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            this.right = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            this.spacebar = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.up = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            this.down = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.left = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            this.right = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            this.spacebar = false;
        }
    }
}
