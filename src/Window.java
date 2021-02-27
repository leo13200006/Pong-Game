import controllers.AiController;
import controllers.Ball;
import controllers.PlayerController;
import helper.Constants;
import helper.Rect;
import helper.Text;
import helper.Time;
import listner.KL;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListiner = new KL();
    public Rect player1;
    public PlayerController playerController;
    public Rect ai;
    public AiController aiController;
    public Rect ball;
    public Ball ballController;
    public Text leftScoreText, rightScoreText;
    public boolean isRunning;
    public Time timeCounter;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        g2 = (Graphics2D) this.getGraphics();
        this.addKeyListener(keyListiner);

        Constants.TOOLBAR_HEIGHT = this.getInsets().top;

        Font font = new Font("Times new Roman", Font.PLAIN, Constants.TEXT_SIZE);
        leftScoreText = new Text("0", font, Constants.TEXT_X_POS, Constants.TEXT_Y_POS, Constants.PLAYER_ONE_PADDEL_COLOR);
        rightScoreText = new Text("0", font, Constants.TEXT_X_POS + 100, Constants.TEXT_Y_POS, Constants.AI_PADDEL_COLOR);

        player1 = new Rect(Constants.HORIZONTAL_PADDING, Constants.VERTICAL_PADDING, Constants.PADDEL_WIDTH, Constants.PADDEL_HEIGHT, Constants.PLAYER_ONE_PADDEL_COLOR, Constants.PADDEL_ROUNDNESS);

        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDEL_WIDTH - Constants.HORIZONTAL_PADDING, Constants.VERTICAL_PADDING, Constants.PADDEL_WIDTH, Constants.PADDEL_HEIGHT, Constants.AI_PADDEL_COLOR, Constants.PADDEL_ROUNDNESS);

        ball = new Rect((Constants.SCREEN_WIDTH) - (Constants.BALL_SIZE / 2), Constants.SCREEN_HEIGHT / 2, Constants.BALL_SIZE, Constants.BALL_SIZE, Constants.BALL_COLOR, Constants.BALL_ROUNDNESS);

        playerController = new PlayerController(player1, keyListiner);
        ballController = new Ball(ball, player1, ai, leftScoreText, rightScoreText);
        aiController = new AiController(new PlayerController(ai), ball);
        timeCounter = new Time();
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);
        playerController.update(dt);
        ballController.update(dt);
        aiController.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        g2.setColor(new Color(255, 255, 255, 100));
        g2.setStroke(new BasicStroke(4));
        g2.draw(new Line2D.Double(Constants.SCREEN_WIDTH / 2.0, 0, Constants.SCREEN_WIDTH / 2.0, Constants.SCREEN_HEIGHT));
        player1.draw(g2);
        ai.draw(g2);
        ball.draw(g2);
        leftScoreText.draw(g2);
        rightScoreText.draw(g2);

        if (Integer.parseInt(leftScoreText.text) == Constants.MAX_COUNT) {
            Constants.winner = 0;
            isRunning = false;
            Main.changeState(2);
        }

        if (Integer.parseInt(rightScoreText.text) == Constants.MAX_COUNT) {
            Constants.winner = 1;
            isRunning = false;
            Main.changeState(2);
        }
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        isRunning = true;
        while (isRunning) {
            double time = timeCounter.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;
            update(deltaTime);
        }
        this.dispose();
    }
}
