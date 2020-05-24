package View;
import javax.swing.*;
import java.awt.*;

public class Candela extends JPanel {
    private int x;
    private int y;
    private int height;
    private int weight;
    private int min;
    private int max;
    private int open;
    private int close;
    private String time;
    private int maxim;
    private int minim;

    public Candela(int x, int y, int height, int weight, int min, int max, int open, int close, String time, int maxim, int minim) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.weight = weight;
        this.min = min;
        this.max = max;
        this.open = open;
        this.close = close;
        this.time = time;
        this.maxim = maxim;
        this.minim = minim;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (open > close){
            g.setColor(Color.RED);
            g.fillRect(x, maxim-open, 50, open-close);
            g.setColor(Color.BLACK);
            g.drawLine(30, maxim-max, 30, maxim - open);
            g.drawLine(30, maxim-min, 30, maxim-close);
        }else{
            g.setColor(Color.GREEN);
            g.fillRect(x, maxim-close, 50, close-open);
            g.setColor(Color.BLACK);
            g.drawLine(30, maxim-max, 30, maxim-close);
            g.drawLine(30, maxim-min, 30, maxim-open);
        }
    }

    public String getTime() {
        return time;
    }
}
