package View;

import Model.History;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistogramPanel extends JPanel {            // es l'encarregat d'anr afecgint barretes , el cridarem tantes vegades com barres volguem

    private int histogramHeight = 450 ;          // !!!el panell es redimensions segons les mides de les barres
    private int barWidth = 35;
    private int barGap = 8;

    private JPanel barPanel;
    private JPanel labelPanel;
    private JPanel labelYPannel;
    private History history;
    private List<Candela> candelas = new ArrayList<>();
    private float closePrice;
    private float candelaGran, candelaPetita, minA, maxA;
    private double scale;

    public HistogramPanel() {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 10));
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
        labelYPannel = new JPanel(new GridLayout(0,1,barGap,0));
        labelYPannel.setBorder(new EmptyBorder(5,10,0,10));

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
        add(labelYPannel, BorderLayout.WEST);
    }

    public void addHistogramColumn(History history, float minA, float maxA, int numCandela) {
        this.history = history;
        this.minA = minA;
        this.maxA = maxA;
        Candela candela;
        float scale = 380/(maxA-minA);
        if (history.getCloseSharePrice() > history.getOpenSharePrice()){
            candela = new Candela(5, Math.round(history.getCloseSharePrice()*scale), Math.round((history.getCloseSharePrice()-history.getOpenSharePrice()*scale)), 50, Math.round(history.getMinSharePrice()*scale), Math.round(history.getMaxSharePrice()*scale),Math.round(history.getOpenSharePrice()*scale),Math.round(history.getCloseSharePrice()*scale), String.valueOf(history.getTime()), Math.round(maxA * scale), Math.round(minA*scale));
        }else{
            candela = new Candela(5, Math.round(history.getOpenSharePrice()*scale), Math.round((history.getOpenSharePrice()-history.getCloseSharePrice()*scale)), 50, Math.round(history.getMinSharePrice()*scale), Math.round(history.getMaxSharePrice()*scale),Math.round(history.getOpenSharePrice()*scale),Math.round(history.getCloseSharePrice()*scale), String.valueOf(history.getTime()), Math.round(maxA * scale), Math.round(minA*scale));
        }
        candelas.add(candela);
    }

    public void layoutHistogram() {
        barPanel.removeAll();
        labelPanel.removeAll();
        labelYPannel.removeAll();
        for (Candela candela: candelas) {
            barPanel.add(candela);
            JLabel barLabel = new JLabel( candela.getTime() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }
        for (int i = (int)maxA; i >= minA; i--){
            JLabel barLabelY = new JLabel( i + "" );
            labelYPannel.add(barLabelY);
            i = i - 9;
        }
        System.out.println("hola");
    }
}