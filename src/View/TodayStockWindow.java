package View;

import javax.swing.*;
import java.awt.*;

//se li ha de passar tota la info de les diferents accions de totes les empreses, i també el saldo actual de l'usuari

public class TodayStockWindow extends JFrame {

    private JLabel labelSaldoActual = new JLabel("SALDO ACTUAL: ");
    private JLabel labelTotalSaldo = new JLabel(" ");

    private JButton buttonComprarAccions = new JButton("LA MEVA BORSA");

    JTable table = new JTable(2, 4);

    public TodayStockWindow () {

        configureView();

        //panelSaldo
        JPanel panelSaldo = new JPanel();
        panelSaldo.setLayout(new BoxLayout(panelSaldo, BoxLayout.X_AXIS));

        labelTotalSaldo.setText("1234.56" + " €");

        panelSaldo.add(labelSaldoActual);
        panelSaldo.add(labelTotalSaldo);

        //panelShares

        JPanel panelShares = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.getViewport().setBackground(Color.WHITE);

        panelShares.add(scrollPane);

        //panelCentral
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        panelCentral.add(panelShares);
        panelCentral.add(buttonComprarAccions);
        panelCentral.add(panelSaldo);

        //"COLLAGE"
        this.add(panelCentral);



    }

    private void configureView () {

        setSize(900,500);
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
                new TodayStockWindow().setVisible(true);
            }
        });
    }
}