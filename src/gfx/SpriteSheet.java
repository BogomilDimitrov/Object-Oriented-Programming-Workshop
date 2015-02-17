package gfx;


import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sourceImg;

    public SpriteSheet(String path) {
        this.sourceImg = ImageLoader.loadImage(path);
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return this.sourceImg.getSubimage(x, y, width, height);
    }
}
