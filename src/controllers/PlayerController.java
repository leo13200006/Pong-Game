package controllers;

import helper.Constants;
import helper.Rect;
import listner.KL;

import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect player;
    public KL keyListener;

    public PlayerController(Rect player, KL keyListener) {
        this.player = player;
        this.keyListener = keyListener;
    }

    public PlayerController(Rect rect) {
        this.player = rect;
        this.keyListener = null;
    }

    public void update(double dt) {
        if (keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN))
                moveDown(dt);

            if (keyListener.isKeyPressed(KeyEvent.VK_UP))
                moveUp(dt);
        }
    }

    public void moveUp(double dt) {
        if ((player.y - Constants.PADDEL_SPEED * dt) > Constants.TOOLBAR_HEIGHT)
            player.y -= Constants.PADDEL_SPEED * dt;
    }

    public void moveDown(double dt) {
        if ((player.y + Constants.PADDEL_SPEED * dt) < Constants.SCREEN_HEIGHT - Constants.PADDEL_HEIGHT)
            player.y += Constants.PADDEL_SPEED * dt;
    }
}
