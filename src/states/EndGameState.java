package states;

import game.GameEngine;

import java.awt.*;

public class EndGameState extends State {
    private String message;
    public EndGameState(GameEngine game, String message) {
        super(game);
        this.message = message;
    }

    @Override
    public void tick() {
        this.game.stop();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getGameWidth(), game.getGameHeight());
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD ,30) );
        g.drawString(this.message, game.getGameWidth()/2-100, game.getGameHeight()/2);
    }
}
