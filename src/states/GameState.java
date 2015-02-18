package states;

import entities.creatures.Enemy;
import entities.creatures.Player;
import enums.PlayerMovement;
import game.GameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State {
    private Player player;
    private ArrayList<Enemy> enemies;

    private int shotsPerTick;
    private double timePerShoot;
    private long lastTimeShot;
    private double deltaBetweenShots;

    public GameState(GameEngine game) {
        super(game);
        this.enemies = new ArrayList<Enemy>();
        this.enemies.add(new Enemy(50, 50, 40, 44));
        this.enemies.add(new Enemy(150, 50, 40, 44));
        this.enemies.add(new Enemy(250, 50, 40, 44));
        this.enemies.add(new Enemy(350, 50, 40, 44));
        this.enemies.add(new Enemy(450, 50, 40, 44));
        this.enemies.add(new Enemy(550, 50, 40, 44));
        this.enemies.add(new Enemy(650, 50, 40, 44));
        this.player = new Player(game.getGameWidth() / 2 - 50, game.getGameHeight() - 150, 100, 100);

        this.shotsPerTick = 5;
        this.timePerShoot = 1_000_000_000.0 / this.shotsPerTick;
        this.lastTimeShot = System.nanoTime();
        this.deltaBetweenShots = 0;
    }

    @Override
    public void tick() {
        if (GameEngine.inputHandler.up) {
            this.player.move(PlayerMovement.Up);
        } else if (GameEngine.inputHandler.down) {
            this.player.move(PlayerMovement.Down);
        } else {
            this.player.move(PlayerMovement.Iddle);
        }

        if (GameEngine.inputHandler.left) {
            this.player.move(PlayerMovement.Left);
        } else if (GameEngine.inputHandler.right) {
            this.player.move(PlayerMovement.Right);
        } else {
            this.player.move(PlayerMovement.Iddle);
        }

        if (GameEngine.inputHandler.spacebar) {
            if (!this.player.getHasShot()) {
                this.player.shoot();
                this.player.setHasShot(true);
            }
        } else {
            this.player.setHasShot(false);
        }

        this.player.tick();
        this.moveEnemies();
        this.enemyShoot();

        for (Enemy enemy : this.enemies) {
            if (enemy.getEnemyBullets().size() > 0) {
                for (int i = 0; i < enemy.getEnemyBullets().size(); i++) {
                    if (enemy.getEnemyBullets().get(i).intersect(this.player.getBoundingBox())) {
                        this.player.setHealth(this.player.getHealth() - enemy.getEnemyDamage());
                        enemy.getEnemyBullets().remove(i);
                    }
                }
            }
        }

        if (this.player.getPlayerBullets().size() > 0) {
            for (int i = 0; i < this.player.getPlayerBullets().size(); i++) {
                for (int j = 0; j < this.enemies.size(); j++) {
                    if (this.player.getPlayerBullets().size() < 1) {
                        return;
                    }
                    if (this.player.getPlayerBullets().size() > 0 &&
                            this.player.getPlayerBullets().get(i).intersect(this.enemies.get(j).getBoundingBox())) {
                        this.player.getPlayerBullets().remove(i);
                        this.enemies.get(j).setHealth(this.enemies.get(j).getHealth() - this.player.getPlayerDamage());
                        if (this.enemies.get(j).getHealth() <= 0) {
                            this.enemies.remove(j);
                        }
                    }

                }

                if (this.player.getPlayerBullets().size() < 1) {
                    break;
                }
                if (!this.player.getPlayerBullets().get(i)
                        .intersect(new Rectangle(0, 0, game.getGameWidth(), game.getGameHeight()))) {
                    this.player.getPlayerBullets().remove(i);
                }
            }
        }

        if (this.enemies.size() < 1) {
            StateManager.setState(new EndGameState(game, "You won"));
        }
        if (this.player.getHealth() <= 0) {
            StateManager.setState(new EndGameState(game, "You died!"));
        }
    }

    private void enemyShoot() {
        long now = System.nanoTime();
        this.deltaBetweenShots += (now - this.lastTimeShot) / this.timePerShoot;
        this.lastTimeShot = now;

        if (this.deltaBetweenShots >= 1) {
            Random random = new Random();
            int enemyShooting = random.nextInt(this.enemies.size());

            this.enemies.get(enemyShooting).shoot();
            this.deltaBetweenShots--;
        }
    }

    private void moveEnemies() {
        for (Enemy enemy : this.enemies) {
            enemy.tick();

            if (enemy.getBoundingBox().getX() +
                    enemy.getBoundingBox().getWidth() > 800 ||
                    enemy.getBoundingBox().getX() < 0) {
                Enemy.velocityModifier *= -1;
                for (Enemy movingEnemy : this.enemies) {
                    movingEnemy.tick();
                    movingEnemy.tick();
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
        g.setColor(Color.black);
        g.drawString("Your health: " + this.player.getHealth(), 10, 590);
    }
}
