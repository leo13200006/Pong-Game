package helper;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Rect {
    public double x, y, width, height;
    public double roundNess;
    public Color color;

    public Rect(double x, double y, double width, double height, Color color, double roundNess) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.roundNess = roundNess;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new RoundRectangle2D.Double(x, y, width, height, roundNess, roundNess));
    }
}
