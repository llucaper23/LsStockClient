package View;

import Model.History;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistogramPanel extends JPanel {            // es l'encarregat d'anr afecgint barretes , el cridarem tantes vegades com barres volguem

    private int histogramHeight = 400 ;          // !!!el panell es redimensions segons les mides de les barres
    private int barWidth = 35;
    private int barGap = 8;

    private JPanel barPanel;
    private JPanel labelPanel;
    private History history;

    private List<Bar> bars = new ArrayList<Bar>();
    private ArrayList<History> h = new ArrayList<>();
    private float closePrice;
    private float candelaGran, candelaPetita, minA, maxA;
    private double scale;

    public HistogramPanel() {

        closePrice = 0;
        candelaPetita = candelaGran = 0;

        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        //Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);

        TitledBorder title;
        title = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Candlestick Chart");
        title.setTitleJustification(TitledBorder.CENTER);

        //Border inner = new EmptyBorder(10, 10, 0, 10);
        //Border compound = new CompoundBorder(outer, inner);
        //barPanel.setBorder( compound );
        barPanel.setBorder(title);
        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }

    public void addHistogramColumn(String label, float value, Color color, History history, ArrayList<History> h, float minA, float maxA) {
        this.history = history;
        this.h = h;
        this.minA = minA;
        this.maxA = maxA;

        if (history.getCloseSharePrice()< history.getOpenSharePrice()){
            Bar bar = new Bar(label, value, color); //Color.RED
            bars.add( bar );
        } else{
            Bar bar = new Bar(label, value, color); //Color.GREEN
            bars.add( bar );

        }

    }

    public void layoutHistogram() {
        barPanel.removeAll();
        labelPanel.removeAll();

        float maxValue = 0;
        float minValue = 0;
        int counter = 0;
        int counterAux = 9;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());
        candelaGran = maxValue;

        for (Bar bar: bars)
            minValue = Math.min(minValue, bar.getValue());
        candelaPetita = minValue;

        for (Bar bar: bars) {

            scale = histogramHeight / (maxA - minA);

            JLabel label = new JLabel();
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.CENTER); //centrem les candeles al centre del eix x

            int barHeight = (int) ((bar.getValue() * histogramHeight) / maxValue);
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight, counter, counterAux);

            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );

            counter++;
            counterAux--;
        }
    }

    private class Bar {

        private String label;
        private float value;
        private Color color;

        public Bar(String label, float value, Color color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel()
        {
            return label;
        }

        public float getValue()
        {
            return value;
        }

        public Color getColor()
        {
            return color;
        }
    }

    private class ColorIcon implements Icon {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;
        private int counter;
        private int counterAux;

        public ColorIcon(Color color, int width, int height, int counter, int counterAux) {
            this.color = color;
            this.width = width;
            this.height = height;
            this.counter = counter;
            this.counterAux = counterAux;
        }

        public int getIconWidth() {
            return width;
        }


        public int getIconHeight() {
            return height;
        }

        public int getCounter() {
            return counter;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {

            System.out.println(h.get(counterAux).getTime());
            System.out.println(" open : " + h.get(counterAux).getOpenSharePrice() + " close : " + h.get(counterAux).getCloseSharePrice() + " diff : " + (h.get(counterAux).getOpenSharePrice()-h.get(counterAux).getCloseSharePrice()));

            int open = (int)h.get(counterAux).getOpenSharePrice();
            int close = (int)h.get(counterAux).getCloseSharePrice();
            int diff = (int)(Math.abs(h.get(counterAux).getOpenSharePrice()-h.get(counterAux).getCloseSharePrice()));

            if (color == Color.RED) { //obertura > tancament

                g.setColor(Color.RED);
                g.fillRect(x, close, width, diff);
                g.dispose();
                //g.drawLine(x+(width/2), (int)openPrice, x + (width/2),  height);

                //g.setColor(Color.BLACK);
                //g.drawLine(x+(width/2), y, x + (width/2), y - (int)history.getMax_share_price()); //linia dalt
                //g.drawLine(x+(width/2),  y + height,x + (width/2), y + height + (int)(Math.abs(history.getMin_share_price()-history.getClose_share_price()))); //linia abaix


            } else { //obertura < tancaments

                g.setColor(Color.GREEN);
                g.fillRect(x, open, width, diff);
                g.dispose();
                //g.drawLine(x+(width/2), (int)(openPrice), x + (width/2), height); //linia dalt

                //g.setColor(Color.BLACK);
                //g.drawLine(x+(width/2), y, x + (width/2), y - (int)history.getMax_share_price()); //linia dalt
                //g.drawLine(x+(width/2),  y +height,x + (width/2), y + height + (int)(Math.abs(history.getMin_share_price()-history.getOpen_share_price()))); //linia abaix

            }


        }

    }

}