package View;

import Model.History;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistogramPanel extends JPanel {            // es l'encarregat d'anr afecgint barretes , el cridarem tantes vegades com barres volguem

    private int histogramHeight = 100 ;          // !!!el panell es redimensions segons les mides de les barres
    private int barWidth = 35;
    private int barGap = 8;

    private JPanel barPanel;
    private JPanel labelPanel;
    private History history;

    private List<Bar> bars = new ArrayList<Bar>();

    public HistogramPanel()
    {
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

    public void addHistogramColumn(String label, float value, Color color, History history)
    {
        this.history = history;

        if (history.getClose_share_price()< history.getOpen_share_price()){
            Bar bar = new Bar(label, history.getOpen_share_price(), Color.RED);
            bars.add( bar );
        }else{
            Bar bar = new Bar(label, value, Color.GREEN);
            bars.add( bar );

        }

    }

    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();

        float maxValue = 0;

        for (Bar bar: bars)
            maxValue = Math.max(maxValue, bar.getValue());

        for (Bar bar: bars)
        {
            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.CENTER); //centrem les candeles al centre del eix x
            int barHeight = (int) ((bar.getValue() * histogramHeight) / maxValue);
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
    }

    private class Bar
    {
        private String label;
        private float value;
        private Color color;

        public Bar(String label, float value, Color color)
        {
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

    private class ColorIcon implements Icon
    {
        private int shadow = 3;

        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height)
        {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth()
        {
            return width;
        }

        public int getIconHeight()
        {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y)
        {

            g.setColor(color);
            //g.fillRect(x, y, width, height);
            g.fillRect(x, (int)history.getOpen_share_price(), width, height);
            //g.setColor(Color.GRAY);
            //g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);

            g.setColor(Color.BLACK);
            //g.drawLine(x+(width/2), y, x+(width/2), y - (int)history.getMax_share_price()); //linia dalt
            //g.drawLine(x+(width/2), y+height, x+(width/2), y+ height + (int)history.getMin_share_price()); //linia abaix
            g.drawLine(x+(width/2), (int)history.getOpen_share_price(), x+(width/2), y - (int)history.getMax_share_price()); //linia dalt
            g.drawLine(x+(width/2), (int)history.getOpen_share_price() + height, x+(width/2), y+ height + (int)history.getMin_share_price()); //linia abaix

        }
    }
}