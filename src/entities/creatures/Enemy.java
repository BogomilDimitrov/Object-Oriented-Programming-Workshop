package entities.creatures;


import contracts.Intersectable;
import contracts.Shooting;
import gfx.Assets;

import java.awt.*;

public class Enemy extends Creature implements Intersectable, Shooting {
    private final int DEFAULT_DAMAGE = 5;

    public static int velocityModifier;

    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setCreatureDamage(DEFAULT_DAMAGE);
        velocityModifier = 1;
        this.setVelocity(2);
    }

    @Override
    public void tick() {
        this.x += this.getVelocity() * velocityModifier;
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, this.x, this.y, this.width, this.height, null);
        g.drawRect((int)this.getBoundingBox().getX(), (int)this.getBoundingBox().getY(), (int)this.getBoundingBox().getWidth(), (int)this.getBoundingBox().getHeight());
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return this.getBoundingBox().contains(rect);
    }

    @Override
    public void shoot() {

    }
}
