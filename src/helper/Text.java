package helper;

import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x, y;
    public double width, height;
    public Color color;

    public Text(String text, Font font, double x, double y, Color color) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();
        this.color = color;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.drawString(text, (float)x, (float) y);
    }
}
