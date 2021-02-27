package controllers;

import helper.Rect;

public class AiController {

    public PlayerController playerController;
    public Rect ball;

    public AiController(PlayerController playerController, Rect ball) {
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update(double dt) {
        playerController.update(dt);
        if (ball.y + ball.height / 2.0 < playerController.player.y + playerController.player.height / 2.0) {
            playerController.moveUp(dt);
        } else if (ball.y + ball.height / 2.0 > playerController.player.y + playerController.player.height / 2.0) {
            playerController.moveDown(dt);
        }
    }

}
