package entities.bullets;

import entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Bullet extends Entity {
    private BufferedImage img;
    private int bulletVelocity;

    public Bullet(BufferedImage img, int x, int y, int width, int height, int velocityModifier) {
        super(x, y, width, height);
        this.bulletVelocity = 3 * velocityModifier;
        this.img = img;
    }

    @Override
    public boolean intersect(Rectangle enemyBoundingBox) {
        return this.boundingBox.intersects(enemyBoundingBox);
    }

    @Override
    public void tick() {
        this.y -= this.bulletVelocity;
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }
}
