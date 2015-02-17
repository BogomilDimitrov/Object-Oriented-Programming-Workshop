package entities.bullets;


import contracts.Intersectable;
import entities.Entity;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity implements Intersectable{

    private BufferedImage image;

    private int velocityModifier;

    public Bullet(BulletType type, int x, int y) {
        super(x, y, 10, 28);
        init(type);
    }

    private void init(BulletType type) {
        if (type == BulletType.Enemy) {
            this.image = Assets.enemyBullet;
            this.velocityModifier = 1;
        } else {
            this.image = Assets.playerBullet;
            this.velocityModifier = -1;
        }
    }

    @Override
    public void tick() {
        this.y += this.getVelocity() * this.velocityModifier;
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.image, this.x, this.y, this.width, this.height, null);
        g.drawRect((int)this.getBoundingBox().getX(), (int)this.getBoundingBox().getY(), (int)this.getBoundingBox().getWidth(), (int)this.getBoundingBox().getHeight());
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return this.getBoundingBox().contains(rect);
    }
}
