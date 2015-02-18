package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage enemy;
    public static BufferedImage playerBullet;
    public static BufferedImage enemyBullet;
    public static SpriteSheet playerSprite;

    public static void init() {
        playerSprite = new SpriteSheet(ImageLoader.loadImage("/images/player.png"));
        playerBullet = ImageLoader.loadImage("/images/bullet.gif");
        enemyBullet = ImageLoader.loadImage("/images/enemy_bullet.gif");
        enemy = ImageLoader.loadImage("/images/enemy.png");
    }
}
