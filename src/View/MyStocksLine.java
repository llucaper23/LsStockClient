package View;

import Controller.PrincipalController;
import Model.Company;
import Model.UserCompany;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//A aquesta classe se li passara tota la info, i crearà una "línia" cada cop que se la cridi
public class MyStocksLine extends JPanel {

    private static String SELL = "SELL";

    final int MAX_HEIGHT_SHARES = 600;
    final int MAX_WIDTH_SHARES = 50;

    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");
    private JLabel labelNumShares = new JLabel(" ");;
    private JLabel labelShares = new JLabel("SHARES");
    private JLabel labelProfitLoss = new JLabel("Profit / Loss");
    private JLabel labelPLValue = new JLabel(" ");;

    private JButton buttonSell =  new JButton("SELL");

    public MyStocksLine (UserCompany company, ArrayList<Company> allCompanies) {

        JPanel panelBackground = new JPanel(new FlowLayout());
        panelBackground.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        panelBackground.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));


        for (int i = 0; i < allCompanies.size(); i++) {
            if (company.getCompanyId() == allCompanies.get(i).getCompanyId()) {
                labelCompanyName.setText(allCompanies.get(i).getCompanyName());
            }
        }

        labelSharePrice.setText(String.valueOf(company.getBuyPrice()) + " €");
        labelProfitLoss.setText("Profit/Loss: ");
        labelPLValue.setText("-11.45 hardcoded");
        labelNumShares.setText(String.valueOf(company.getQuantity()));

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

        buttonSell.putClientProperty("company_id", company.getCompanyId());

        //panelBackground
        panelBackground.add(panelCompanyName);
        panelBackground.add(panelInfoShares);
        panelBackground.add(panelNumShares);
        panelBackground.add(buttonSell);

        //"COLLAGE"
        this.add(panelBackground);

    }

    public void registraControlador(PrincipalController principalController) {
        buttonSell.setActionCommand(SELL);
        buttonSell.addActionListener(principalController);
    }

}