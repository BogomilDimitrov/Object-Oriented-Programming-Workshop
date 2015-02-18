package entities.creatures;

import contracts.Shooting;
import entities.bullets.Bullet;
import enums.PlayerMovement;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Creature implements Shooting {
    private final int DEFAULT_PLAYER_DAMAGE = 10;

    private ArrayList<Bullet> playerBullets;
    private int playerDamage;


    private boolean movingLeft;
    private boolean movingRight;
    private boolean hasShot;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.hasShot = false;
        this.setPlayerDamage(DEFAULT_PLAYER_DAMAGE);
        setPlayerBullets(new ArrayList<>());
    }

    @Override
    public void tick() {
        this.getBoundingBox().setBounds(this.x+10, this.y+10, this.width-10, this.height-10);
        playerBullets.forEach(entities.bullets.Bullet::tick);

    }

    public void move(PlayerMovement playerMovement) {
        switch (playerMovement) {
            case Up: {
                this.y -= this.velocity;
                break;
            }
            case Down: {
                this.y += this.velocity;
                break;
            }
            case Left: {
                this.x -= this.velocity;
                this.movingRight = false;
                this.movingLeft = true;
                break;
            }
            case Right: {
                this.x += this.velocity;
                this.movingLeft = false;
                this.movingRight = true;
                break;
            }
            case Iddle: {
                this.movingRight = false;
                this.movingLeft = false;
                break;
            }
        }
    }

    @Override
    public void shoot() {
        this.getPlayerBullets().add(new Bullet(Assets.playerBullet, this.x + this.width / 2 - 17 / 4, this.y, 17 / 2, 47 / 2, 1));
    }

    @Override
    public void render(Graphics g) {
        if (this.movingLeft) {
            g.drawImage(Assets.playerSprite.crop(this.width * 2, 0, this.width, this.height), this.x, this.y, this.width, this.height, null);
        } else if (this.movingRight) {
            g.drawImage(Assets.playerSprite.crop(this.width*2,this.height,this.width,this.height), this.x, this.y, this.width, this.height, null);
        } else {
            g.drawImage(Assets.playerSprite.crop(0,this.height,this.width,this.height), this.x, this.y, this.width, this.height, null);
        }
        for (Bullet playerBullet : playerBullets) {
            playerBullet.render(g);
        }
    }

    @Override
    public boolean intersect(Rectangle enemyBoundingBox) {
        return this.getBoundingBox().contains(enemyBoundingBox);
    }

    public int getPlayerDamage() {
        return this.playerDamage;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public ArrayList<Bullet> getPlayerBullets() {
        return this.playerBullets;
    }

    public void setPlayerBullets(ArrayList<Bullet> playerBullets) {
        this.playerBullets = playerBullets;
    }

    public boolean getHasShot() {
        return this.hasShot;
    }

    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }
 }
