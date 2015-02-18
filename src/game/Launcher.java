package game;

public class Launcher {
    public static void main(String[] args) {
        GameEngine game = new GameEngine("Alien Invaders");
        game.setGameWidth(800);
        game.setGameHeight(600);

        game.start();
    }
}
