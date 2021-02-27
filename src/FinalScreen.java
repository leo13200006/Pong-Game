import helper.Constants;
import helper.Text;
import helper.Time;
import listner.KL;
import listner.ML;

import javax.swing.*;
import java.awt.*;

public class FinalScreen extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListiner = new KL();
    public ML mouseListner = new ML();
    public Text startText, winnerName;
    public boolean isRunning = true;
    public Time timeCounter;

    public FinalScreen() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        g2 = (Graphics2D) this.getGraphics();
        this.addKeyListener(keyListiner);
        this.addMouseListener(mouseListner);
        this.addMouseMotionListener(mouseListner);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        startText = new Text("Start The Game", new Font("Times new Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 140.0, Constants.SCREEN_HEIGHT / 2.0, Color.WHITE);
        if ( Constants.winner == 0) {
            winnerName = new Text("You Wins Yeyy", new Font("Times new Roman", Font.PLAIN, 80), Constants.SCREEN_WIDTH / 2.0 - 240, Constants.SCREEN_HEIGHT / 2.0 - 100, Constants.PLAYER_ONE_PADDEL_COLOR);
        } else {
            winnerName = new Text("AI Wins", new Font("Times new Roman", Font.PLAIN, 80), Constants.SCREEN_WIDTH / 2.0 - 150, Constants.SCREEN_HEIGHT / 2.0 - 100, Constants.AI_PADDEL_COLOR);
        }
        timeCounter = new Time();
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        if(mouseListner.getX() > startText.x && mouseListner.getX() < startText.x + startText.width &&
                mouseListner.getY() > startText.y - startText.height / 2.0 && mouseListner.getY() < startText.y + startText.height / 2.0) {
            startText.color = new Color(200, 200, 200);

            if(mouseListner.isPressed) {
                Main.changeState(1);
            }

        } else {
            startText.color = Color.WHITE;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        startText.draw(g2);
        winnerName.draw(g2);
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = timeCounter.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;
            update(deltaTime);
        }
        this.dispose();
    }
}
