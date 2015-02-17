package states;

import entities.bullets.Bullet;
import entities.bullets.BulletType;
import entities.creatures.Enemy;
import entities.creatures.Player;
import game.GameEngine;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private Player player;
    private ArrayList<Enemy> enemies;

    private Bullet bullet;

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
        this.player = new Player(350, 400, 100, 100);
        this.enemies = new ArrayList<Enemy>();
        this.enemies.add(new Enemy(50, 50, 40, 44));
        this.enemies.add(new Enemy(150, 50, 40, 44));
        this.enemies.add(new Enemy(250, 50, 40, 44));
        this.enemies.add(new Enemy(350, 50, 40, 44));
        this.enemies.add(new Enemy(450, 50, 40, 44));
        this.enemies.add(new Enemy(550, 50, 40, 44));
        this.enemies.add(new Enemy(650, 50, 40, 44));
        this.bullet = new Bullet(BulletType.Enemy, 20, 20);
    }

    @Override
    public void tick() {
        this.player.tick();
        moveEnemies();

        if (this.player.getHealth() == 0) {
            System.out.println("You died!");
            game.stop();
        }

        if (this.player.getBullets().size() > 0) {
            for (int i = 0; i < this.player.getBullets().size(); i++) {
                for (int j = 0; j < this.enemies.size(); j++) {
                    if (this.player.getBullets().size() < 1) {
                        return;
                    }
                    if (this.player.getBullets().size() > 0 &&
                            this.enemies.get(j).intersects(this.player.getBullets().get(i).getBoundingBox())) {
                        this.player.getBullets().remove(i);
                        this.enemies.get(j).setHealth(this.enemies.get(j).getHealth() - this.player.getCreatureDamage());
                        if (this.enemies.get(j).getHealth() <= 0) {
                            this.enemies.remove(j);
                        }
                    }
                }

                if (this.player.getBullets().size() < 1) {
                    break;
                }
                if (!this.player.getBullets().get(i).intersects(new Rectangle(0, 0, 800, 600))) {
                    this.player.getBullets().remove(i);
                }
            }
        }
    }

    private void moveEnemies() {
        for (Enemy enemy : enemies) {
            enemy.tick();

            if (enemy.getBoundingBox().getX() +
                    enemy.getBoundingBox().getWidth() > 800 ||
                    enemy.getBoundingBox().getX() < 0) {
                Enemy.velocityModifier *= -1;
                for (Enemy enemy1 : enemies) {
                    enemy1.tick();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        this.player.render(g);

        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
        this.bullet.render(g);


        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.black);
        g.drawString("Your health is: " + this.player.getHealth(), 10, 590);
        g.setColor(Color.gray);
    }
}
