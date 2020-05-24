package View;

import Model.History;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HistogramPanel extends JPanel {            // es l'encarregat d'anr afecgint barretes , el cridarem tantes vegades com barres volguem
    private int barGap = 8;

    private JPanel barPanel;
    private JPanel labelPanel;
    private JPanel labelYPannel;
    private List<Candela> candelas = new ArrayList<>();
    private float minA, maxA;

    public HistogramPanel(String name) {
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );
        barPanel = new JPanel( new GridLayout(1, 10));
        TitledBorder title;
        title = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), name);
        title.setTitleJustification(TitledBorder.CENTER);
        barPanel.setBorder(title);
        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );
        labelYPannel = new JPanel(new GridLayout(0,1,barGap,0));
        labelYPannel.setBorder(new EmptyBorder(5,10,0,10));
        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
        add(labelYPannel, BorderLayout.WEST);
    }

    public void addHistogramColumn(History history, float minA, float maxA) {
        this.minA = minA;
        this.maxA = maxA;
        Candela candela;
        float scale = 380/(maxA-minA);
        if (history != null){
            if (history.getCloseSharePrice() > history.getOpenSharePrice()){
                candela = new Candela(5, Math.round(history.getMinSharePrice()*scale), Math.round(history.getMaxSharePrice()*scale),Math.round(history.getOpenSharePrice()*scale),Math.round(history.getCloseSharePrice()*scale), String.valueOf(history.getTime()), Math.round(maxA * scale));
            }else{
                candela = new Candela(5 , Math.round(history.getMinSharePrice()*scale), Math.round(history.getMaxSharePrice()*scale),Math.round(history.getOpenSharePrice()*scale),Math.round(history.getCloseSharePrice()*scale), String.valueOf(history.getTime()), Math.round(maxA * scale));
            }
        }else{
            candela = new Candela(5,0, 0,0,0, "No info", 0);
        }
        candelas.add(candela);

    }

    public void layoutHistogram() {
        barPanel.removeAll();
        labelPanel.removeAll();
        labelYPannel.removeAll();
        JLabel barLabel = new JLabel("");
        labelPanel.add(barLabel);
        for (Candela candela: candelas) {
            barPanel.add(candela);
            String time = candela.getTime();
            if (time.equalsIgnoreCase("No Info")){
                barLabel = new JLabel(String.valueOf(candela.getTime()));
                barLabel.setHorizontalAlignment(JLabel.CENTER);
                labelPanel.add( barLabel );
            }else{
                StringBuilder builder = new StringBuilder(time);
                builder.deleteCharAt(7);
                builder.deleteCharAt(6);
                builder.deleteCharAt(5);
                barLabel = new JLabel(String.valueOf(builder));
                barLabel.setHorizontalAlignment(JLabel.CENTER);
                labelPanel.add( barLabel );
            }

        }
        float eixY = maxA;
        for (int i = 9; i >= 0; i--){
            JLabel barLabelY = new JLabel( String.format("%.2f", eixY));
            labelYPannel.add(barLabelY);
            eixY = eixY - (maxA - minA)/10;
        }
    }
}