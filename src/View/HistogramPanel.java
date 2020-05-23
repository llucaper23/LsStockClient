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
    private float maxim, minim;
    private double scale;

    public HistogramPanel() {

        closePrice = 0;
        minim  = maxim  = 0;

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

    public void addHistogramColumn(String label, float value, Color color, History history, ArrayList<History> h) {
        this.history = history;
        this.h = h;

        if (history.getClose_share_price()< history.getOpen_share_price()){
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
            maxim = maxValue;

        for (Bar bar: bars)
            minValue = Math.min(minValue, bar.getValue());
            minim = minValue;

        for (Bar bar: bars) {

            scale = (maxim - minim) * 0.4;

            JLabel label = new JLabel(bar.getValue() + "");
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

            float openPrice = 0;

            if (counterAux != 0) {
                openPrice = closePrice; //igualem el preu d'obertura al tancament de l'anterior

            } else {
                openPrice = h.get(counterAux).getOpen_share_price(); //punt 0, serà la primera candela de l'esquerra de tot.
            }

            System.out.println("OpenPrice sense escalat" + openPrice);

            openPrice = openPrice / (float)scale;

            System.out.println("OpenPrice amb escalat" + openPrice);

            if (color == Color.RED) { //obertura > tancament

                g.setColor(Color.RED);
                g.fillRect(x, y, width, height);
                g.drawLine(x+(width/2), (int)openPrice, x + (width/2),  height);

                //g.setColor(Color.BLACK);
                //g.drawLine(x+(width/2), y, x + (width/2), y - (int)history.getMax_share_price()); //linia dalt
                //g.drawLine(x+(width/2),  y + height,x + (width/2), y + height + (int)(Math.abs(history.getMin_share_price()-history.getClose_share_price()))); //linia abaix


            } else { //obertura < tancaments

                g.setColor(Color.GREEN);
                g.fillRect(x, y, width, height);
                g.drawLine(x+(width/2), (int)(openPrice), x + (width/2), height); //linia dalt

                //g.setColor(Color.BLACK);
                //g.drawLine(x+(width/2), y, x + (width/2), y - (int)history.getMax_share_price()); //linia dalt
                //g.drawLine(x+(width/2),  y +height,x + (width/2), y + height + (int)(Math.abs(history.getMin_share_price()-history.getOpen_share_price()))); //linia abaix

            }


            closePrice = h.get(counterAux).getClose_share_price() + height;
            System.out.println("ClosePrice" + closePrice);


        }

    }
}