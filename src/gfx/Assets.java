package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static SpriteSheet player;
    public static BufferedImage enemy;
    public static BufferedImage playerBullet;
    public static BufferedImage enemyBullet;

    public static void init() {
        player = new SpriteSheet("/images/player.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        playerBullet = ImageLoader.loadImage("/images/bullet.gif");
        enemyBullet = ImageLoader.loadImage("/images/enemy_bullet.gif");
    }
}
