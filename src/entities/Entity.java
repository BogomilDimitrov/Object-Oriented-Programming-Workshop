package entities;

import contracts.Intersectable;
import contracts.Updatable;

import java.awt.*;

public abstract class Entity implements Updatable, Intersectable {
    protected int x, y;
    protected int width, height;
    protected Rectangle boundingBox;
    protected int velocity;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = 2;
        this.setBoundingBox(new Rectangle(this.x, this.y, this.width, this.height));
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
