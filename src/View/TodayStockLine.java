package view;

import javax.swing.*;
import java.awt.*;

public class TodayStockLine extends JFrame {

    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");
    private JLabel labelProfitLoss = new JLabel(" ");
    private JLabel labelChange = new JLabel(" ");
    private JLabel labelChangePercentage = new JLabel(" ");


    public TodayStockLine() {

        configureView();

        JPanel panelBackground = new JPanel(new FlowLayout());

        //Dins dels setText hi aniran les variables del que ens passin per la funció
        labelCompanyName.setText("COMPANY NAME");
        labelSharePrice.setText("321.98"+ " €");
        labelChange.setText(" +0.08 " + "€");
        labelChangePercentage.setText(" ( " + "+0.08" + " % )");

        //panelCompanyName
        JPanel panelCompanyName = new JPanel();

        panelCompanyName.add(labelCompanyName);

        //panelChange
        JPanel panelChange = new JPanel();
        panelChange.setLayout(new BoxLayout(panelChange, BoxLayout.X_AXIS));

        panelChange.add(labelChange);
        panelChange.add(labelChangePercentage);

        //panelShareInfo
        JPanel panelShareInfo = new JPanel();
        panelShareInfo.setLayout(new BoxLayout(panelShareInfo, BoxLayout.Y_AXIS));

        panelShareInfo.add(labelSharePrice);
        panelShareInfo.add(panelChange);

        //panelBackground
        panelBackground.add(panelCompanyName);
        panelBackground.add(panelShareInfo);

        //"COLLAGE"
        this.add(panelBackground);

    }

    private void configureView () {

        setSize(500,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodayStockLine().setVisible(true);
            }
        });
    }
}
