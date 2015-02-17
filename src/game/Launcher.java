package game;


public class Launcher {
    public static void main(String[] args) {
        GameEngine game = new GameEngine("Alien Invaders OOP!", 800, 600);
        game.start();
    }
}
