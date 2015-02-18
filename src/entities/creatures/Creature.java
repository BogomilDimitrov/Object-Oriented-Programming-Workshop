package entities.creatures;

import entities.Entity;

public abstract class Creature extends Entity {
    public static final int DEFAULT_HEALTH = 100;
    public static final int DEFAULT_VELOCITY = 3;

    private int health;

    public Creature(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setHealth(DEFAULT_HEALTH);
        this.velocity = DEFAULT_VELOCITY;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
