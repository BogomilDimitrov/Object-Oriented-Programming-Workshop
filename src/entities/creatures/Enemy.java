package entities.creatures;

import contracts.Shooting;
import entities.bullets.Bullet;
import gfx.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Enemy extends Creature implements Shooting {

    private ArrayList<Bullet> enemyBullets;
    private int enemyDamage;

    public static int velocityModifier;


    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setEnemyBullets(new ArrayList<>());
        this.velocity = 2;
        velocityModifier = 1;
        this.setEnemyDamage(5);
    }

    @Override
    public boolean intersect(Rectangle enemyBoundingBox) {
        return false;
    }

    @Override
    public void tick() {
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
        this.x += this.velocity * velocityModifier;

        this.enemyBullets.forEach(entities.bullets.Bullet::tick);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, this.x, this.y, this.width, this.height, null);
        for (Bullet enemyBullet : enemyBullets) {
            enemyBullet.render(g);
        }
    }

    @Override
    public void shoot() {
        this.getEnemyBullets().add(new Bullet(Assets.enemyBullet, this.x + this.width/2 - 17/4, this.y, 17/2, 47/2, -1));
    }

    public ArrayList<Bullet> getEnemyBullets() {
        return this.enemyBullets;
    }

    public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public int getEnemyDamage() {
        return this.enemyDamage;
    }

    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }
}
