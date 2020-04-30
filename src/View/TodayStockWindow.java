package View;

import Controller.PrincipalController;

import javax.swing.*;
import java.awt.*;

//se li ha de passar tota la info de les diferents accions de totes les empreses, i també el saldo actual de l'usuari

public class TodayStockWindow extends JFrame {

    private static String MYACTIONS = "MYACTIONS";
    private static String LOGOUT = "LOGOUT";

    private float actualUserMoney;

    private JLabel labelSaldoActual = new JLabel("SALDO ACTUAL: ");
    private JLabel labelTotalSaldo = new JLabel(" ");

    private JButton buttonMevaBorsa = new JButton("LA MEVA BORSA");
    private JButton buttonLogOut = new JButton("LOG OUT");
    private JButton buttonBack = new JButton("BACK");

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

        panelCentral.add(buttonBack);
        panelCentral.add(panelShares);
        panelCentral.add(buttonMevaBorsa);
        panelCentral.add(panelSaldo);
        panelCentral.add(buttonLogOut);

        //"COLLAGE"
        this.add(panelCentral);



    }

    public void setSaldoActualUser (float money) {
        actualUserMoney = money;
    }

    private void configureView () {

        setSize(900,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void registraControlador(PrincipalController principalController) {
        buttonLogOut.setActionCommand(LOGOUT);
        buttonLogOut.addActionListener(principalController);
        buttonMevaBorsa.setActionCommand(MYACTIONS);
        buttonMevaBorsa.addActionListener(principalController);
    }
}