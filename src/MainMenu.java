import helper.Constants;
import helper.Text;
import helper.Time;
import listner.KL;
import listner.ML;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListiner = new KL();
    public ML mouseListner = new ML();
    public Text startText, pong;
    public boolean isRunning = true;
    public Time timeCounter;

    public MainMenu() {
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
        pong = new Text("Pong", new Font("Times new Roman", Font.PLAIN, 80), Constants.SCREEN_WIDTH / 2.0 - 90, Constants.SCREEN_HEIGHT / 2.0 - 100, Color.WHITE);
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
        pong.draw(g2);
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
