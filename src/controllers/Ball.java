package controllers;

import helper.Constants;
import helper.Rect;
import helper.Text;

public class Ball {

    public Rect ball;
    public Rect leftPadding, rightPaddding;
    public Text leftScoreText, rightScoreText;

    private double vy = 20.0;
    private double vx = -140.0;
    private double speed = 1.05;

    public Ball(Rect ball, Rect leftPadding, Rect rightPaddding, Text leftScoreText, Text rightScoreText) {
        this.ball = ball;
        this.leftPadding = leftPadding;
        this.rightPaddding = rightPaddding;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public double calculateNewVelocityAngle(Rect paddle) {
        double relativeInterSectY = (paddle.y + (paddle.height) / 2.0) - (this.ball.y + (this.ball.height / 2.0));
        double normalIntersectY = relativeInterSectY / (paddle.height / 2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;
        return Math.toRadians(theta);
    }

    public void update(double dt) {
        if (vx < 0) {
            if (this.ball.x <= this.leftPadding.x + this.leftPadding.width &&
                    this.ball.x + this.ball.width >= this.leftPadding.x &&
                this.ball.y >= this.leftPadding.y && this.ball.y <= this.leftPadding.y + this.leftPadding.height) {
                double theta = calculateNewVelocityAngle(leftPadding);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = ( -Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSignX = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSignX);
                this.vy = newVy;
            } else if (this.ball.x + this.ball.width < 0) {
                System.out.println("You has lost a point");
                int score = Integer.parseInt(rightScoreText.text);
                score ++;
                rightScoreText.text = "" + score;
                this.ball.x = (Constants.SCREEN_WIDTH / 2) - (Constants.BALL_SIZE / 2);
                this.ball.y = Constants.SCREEN_HEIGHT / 2;
            }
        } else if (vx > 0) {
            if (this.ball.x + this.ball.width >= this.rightPaddding.x &&
                this.ball.x <= this.rightPaddding.x + this.rightPaddding.width &&
                this.ball.y >= this.rightPaddding.y && this.ball.y <= this.rightPaddding.y + this.rightPaddding.height) {
                double theta = calculateNewVelocityAngle(rightPaddding);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = ( -Math.sin(theta)) * Constants.BALL_SPEED;
                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;
                speed += Constants.BALL_SPEED_INCRESE;
            } else if (this.ball.x + this.ball.width > Constants.SCREEN_WIDTH) {
                System.out.println("AI has lost a point");
                int score = Integer.parseInt(leftScoreText.text);
                score ++;
                leftScoreText.text = "" + score;
                this.ball.x = (Constants.SCREEN_WIDTH / 2) - (Constants.BALL_SIZE / 2);
                this.ball.y = Constants.SCREEN_HEIGHT / 2;
            }
        }

        if (vy > 0) {
            if (this.ball.y + this.ball.height > Constants.SCREEN_HEIGHT)
                this.vy *= -1;
        } else if (vy < 0) {
            if (this.ball.y < Constants.TOOLBAR_HEIGHT)
                this.vy *= -1;
        }

        this.ball.x += vx * dt * speed;
        this.ball.y += vy * dt * speed;
    }
}
