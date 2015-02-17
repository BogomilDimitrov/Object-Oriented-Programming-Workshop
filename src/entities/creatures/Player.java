package entities.creatures;

import contracts.Intersectable;
import contracts.Shooting;
import entities.bullets.Bullet;
import entities.bullets.BulletType;
import game.GameEngine;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature implements Intersectable, Shooting {
    private final int DEFAULT_DAMAGE = 10;

    private boolean isMovingLeft;
    private boolean isMovingRight;
    private boolean hasShot;

    private ArrayList<Bullet> bullets;


    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setCreatureDamage(DEFAULT_DAMAGE);
        this.setBullets(new ArrayList<Bullet>());
    }

    @Override
    public void tick() {
        if (GameEngine.inputHandler.up) {
            this.y -= this.getVelocity();
        } else if(GameEngine.inputHandler.down) {
            this.y += this.getVelocity();
        }

        if (GameEngine.inputHandler.left) {
            this.x -= this.getVelocity();
            this.isMovingLeft = true;
            this.isMovingRight = false;
        } else if(GameEngine.inputHandler.right) {
            this.x += this.getVelocity();
            this.isMovingRight = true;
            this.isMovingLeft = false;
        } else {
            this.isMovingLeft = false;
            this.isMovingRight = false;
        }

        this.getBoundingBox().setBounds(this.x + 20,
                this.y + 20,
                this.width - 40,
                this.height - 40);

        if (GameEngine.inputHandler.space && !hasShot) {
            shoot();
            hasShot = true;
        } else if (!GameEngine.inputHandler.space) {
            hasShot = false;
        }

        for (Bullet bullet : getBullets()) {
            bullet.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if (isMovingLeft) {
            g.drawImage(Assets.player.crop(200, 0, 100, 100), this.x, this.y, null);
        } else if (isMovingRight) {
            g.drawImage(Assets.player.crop(200, 100, 100, 100), this.x, this.y, null);
        } else {
            g.drawImage(Assets.player.crop(0, 0, 100, 100), this.x, this.y, null);
        }

        for (Bullet bullet : getBullets()) {
            bullet.render(g);
        }

        g.setColor(Color.red);
        g.drawRect((int) this.getBoundingBox().getX(), (int) this.getBoundingBox().getY(),
                (int) this.getBoundingBox().getWidth(), (int) this.getBoundingBox().getHeight());
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return this.getBoundingBox().contains(rect);
    }

    @Override
    public void shoot() {
        this.getBullets().add(new Bullet(BulletType.Player, this.x + this.width / 2, this.y));
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}
