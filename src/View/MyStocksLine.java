package View;

import javax.swing.*;
import java.awt.*;

//A aquesta classe se li passara tota la info, i crearà una "línia" cada cop que se la cridi
public class MyStocksLine extends JFrame {

    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");
    private JLabel labelNumShares = new JLabel(" ");;
    private JLabel labelShares = new JLabel("SHARES");
    private JLabel labelProfitLoss = new JLabel("Profit / Loss");
    private JLabel labelPLValue = new JLabel(" ");;

    private JButton buttonSell =  new JButton("SELL");

    public MyStocksLine () {

        configureView();

        JPanel panelBackground = new JPanel(new FlowLayout());

        //Dins dels setText hi aniran les variables del que ens passin per la funció
        labelCompanyName.setText("COMPANY NAME");
        labelSharePrice.setText("321.98"+ " €");
        labelProfitLoss.setText("Profit/Loss: ");
        labelPLValue.setText("-11.45");
        labelNumShares.setText("14 ");

        //panelCompanyName
        JPanel panelCompanyName = new JPanel();

        panelCompanyName.add(labelCompanyName);

        //panelPL
        JPanel panelPL = new JPanel(new FlowLayout());

        panelPL.add(labelProfitLoss);
        panelPL.add(labelPLValue);

        //panelInfoShares
        JPanel panelInfoShares = new JPanel();
        panelInfoShares.setLayout(new BoxLayout(panelInfoShares, BoxLayout.Y_AXIS));

        panelInfoShares.add(labelSharePrice);
        panelInfoShares.add(panelPL);


        //panelNumShares
        JPanel panelNumShares = new JPanel();
        panelNumShares.setLayout(new BoxLayout(panelNumShares, BoxLayout.X_AXIS));

        panelNumShares.add(labelNumShares);
        panelNumShares.add(labelShares);

        //panelBackground
        panelBackground.add(panelCompanyName);
        panelBackground.add(panelInfoShares);
        panelBackground.add(panelNumShares);
        panelBackground.add(buttonSell);

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
}